package app.views.document;

import app.models.AbstractNode;
import app.models.document.DocListener;
import app.models.document.Document;
import app.models.page.Page;
import app.models.page.PageListener;
import app.models.project.ProjListener;
import app.models.project.Project;
import app.views.MainFrame;
import app.views.page.PageView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static app.actions.ActNewNode.createTreePathFromNode;

public class DocumentView extends JPanel implements ProjListener, DocListener, PageListener {

    private Project parent;
    public Document document;

    private Page activePage;
    private PageView activePageView;

    private JPanel pageStripPanel;
    private JPanel body;
    private JLabel pathLabel;

    private ArrayList<PageThumbnail> thumbnails = new ArrayList<>();

    public DocumentView(Document document, Project parent) {
        super();

        this.document = document;
        this.document.addObserver(this);
        this.parent = parent;
        this.parent.addObserver(this);

        body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.PAGE_AXIS));
        body.setBackground(Color.LIGHT_GRAY);
        body.setBorder(new EmptyBorder(10, 0, 0, 10));

        JScrollPane bodyScroll = new JScrollPane(body);

        pageStripPanel = new JPanel();
        pageStripPanel.setLayout(new BoxLayout(pageStripPanel, BoxLayout.Y_AXIS));
        pageStripPanel.setBackground(Color.LIGHT_GRAY);

        JScrollPane scroll = new JScrollPane(pageStripPanel);
        scroll.setMinimumSize(new Dimension(200, 150));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, bodyScroll);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.1);

        setLayout(new BorderLayout());
        add(splitPane);

        addExistingPages();

        pathLabel = new JLabel();

        setVisible(true);
    }

    private void addExistingPages() {
        for (AbstractNode node : document.getChildren()) {
            Page page = (Page) node;
            page.addObserver(this);

            PageThumbnail thumbnail = new PageThumbnail(page);
            thumbnail.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onPageThumbnailClicked(thumbnail);
                }
            });
            thumbnails.add(thumbnail);
            pageStripPanel.add(thumbnail);
            repaint();
        }
    }

    public void onPageThumbnailClicked(PageThumbnail thumbnail) {
        activePage = thumbnail.getPage();

        body.removeAll();
        activePageView = new PageView(thumbnail.getPage());
        body.add(activePageView);

        List<Object> nodePath = new ArrayList<Object>();
        nodePath.add(parent.getParent());
        nodePath.add(parent);
        nodePath.add(document);
        nodePath.add(thumbnail.getPage());

        MainFrame.getInstance().getHierarchyTree().setSelectionPath(new TreePath(nodePath.toArray()));

        for (PageThumbnail tn : thumbnails) {
            if (tn != thumbnail)
                tn.resetColor();
        }

        revalidate();
        repaint();
    }

    @Override
    public void onPageCreated(Page page) {
        activePage = page;
        page.addObserver(this);

        PageThumbnail thumbnail = new PageThumbnail(page);
        thumbnail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onPageThumbnailClicked(thumbnail);
            }
        });

        for (PageThumbnail tn : thumbnails)
            tn.resetColor();

        thumbnails.add(thumbnail);
        pageStripPanel.add(thumbnail);

        body.removeAll();
        activePageView = new PageView(page);
        body.add(activePageView);

        revalidate();

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
    }

    @Override
    public void onPageDeleted(Page page, int index) {
        page.removeObserver(this);

        PageThumbnail thumbnail = thumbnails.get(index);
        pageStripPanel.remove(thumbnail);
        thumbnails.remove(thumbnail);

        if (activePage == page) {
            body.removeAll();
            activePageView = null;
            activePage = null;
        }

        revalidate();
        repaint();

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
    }

    @Override
    public void onPageSelected(Page page) {
        activePage = page;

        body.removeAll();
        activePageView = new PageView(page);
        body.add(activePageView);

        for (PageThumbnail tn : thumbnails)
            tn.resetColor();

        revalidate();
        repaint();
    }

    @Override
    public void onDocumentChangedName(Document document) {
        Project parent = (Project) document.getParent();

        pathLabel.setText(parent.getName() + " -> " + document.getName());
    }

    @Override
    public void onProjectChangedName(String name) {
        pathLabel.setText(name + " -> " + document.getName());
    }

    public Document getDocument() {
        return document;
    }

    public Page getActivePage() {
        return activePage;
    }

    public PageView getActivePageView() {
        return activePageView;
    }
}
