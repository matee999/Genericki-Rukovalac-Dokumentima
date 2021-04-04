package app.views.document;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import app.Utilities;
import app.models.page.Page;
import app.models.page.PageListener;

/**
 * Created by Qwerasdzxc on 27/12/2019.
 */
class PageThumbnail extends JPanel implements PageListener {

    private Page page;

    private JPanel paper;
    private TitledBorder pageTitleBorder;

    public PageThumbnail(Page page) {
        this.page = page;
        this.page.addObserver(this);

        Dimension pageDimensions = new Dimension(Utilities.PAGE_THUMBNAIL_WIDTH, Utilities.PAGE_THUMBNAIL_HEIGHT);

        setMaximumSize(pageDimensions);
        setPreferredSize(pageDimensions);
        setMinimumSize(pageDimensions);
        setBackground(Color.LIGHT_GRAY);
        setAlignmentY(CENTER_ALIGNMENT);

        EmptyBorder paddingBorder = new EmptyBorder(15, 0, 15, 0);
        pageTitleBorder = BorderFactory.createTitledBorder(new EmptyBorder(0, 0, 0, 0), page.getName());
        pageTitleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);
        pageTitleBorder.setTitleJustification(TitledBorder.CENTER);
        CompoundBorder border = new CompoundBorder(paddingBorder, pageTitleBorder);

        setLayout(new BorderLayout());
        setBorder(border);

        paper = new JPanel();
        paper.setBackground(Color.WHITE);
        add(paper);

        setVisible(true);
    }

    @Override
    public void onPageChangedName(String name) {
        pageTitleBorder.setTitle(name);
    }

    @Override
    public void onPageSelected(Page page) {
        paper.setBackground(new Color(120, 120, 120, 150));
    }

    public void resetColor() {
        paper.setBackground(Color.WHITE);
    }

    public Page getPage() {
        return page;
    }
}
