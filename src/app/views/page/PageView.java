package app.views.page;

import app.Utilities;
import app.commands.AddSlotCommand;
import app.commands.CommandManager;
import app.graphics.elements.PageElement;
import app.graphics.elements.PageShape;
import app.graphics.elements.shapes.CircleElement;
import app.graphics.elements.shapes.RectangleElement;
import app.graphics.painters.PagePainter;
import app.graphics.painters.shapes.CirclePainter;
import app.graphics.painters.shapes.RectanglePainter;
import app.models.document.DocListener;
import app.models.document.Document;
import app.models.element_selection.ElementSelectionListener;
import app.models.page.Page;
import app.models.page.PageListener;
import app.models.project.ProjListener;
import app.models.project.Project;
import app.models.slot.Slot;
import app.models.slot.SlotSelection;
import app.state.StateManager;
import app.views.MainFrame;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PageView extends JPanel implements ProjListener, DocListener, PageListener, ElementSelectionListener {

    private Page page;

    private JLabel pathLabel;
    private TitledBorder pageTitleBorder;
    private CanvasView content;

    private StateManager stateManager;

    public PageView(Page page) {
        this.page = page;
        this.page.addObserver(this);
        this.page.getSelectionModel().addObserver(this);
        ((Document) this.page.getParent()).addObserver(this);
        ((Project) this.page.getParent().getParent()).addObserver(this);

        Dimension pageDimensions = new Dimension(Utilities.PAGE_WIDTH, Utilities.PAGE_HEIGHT);

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

        PageController controller = new PageController(this);

        content = new CanvasView(page);
        content.setBackground(Color.WHITE);
        content.addMouseListener(controller);
        content.addMouseMotionListener(controller);
        add(content);

        stateManager = new StateManager(this);
        PaletteBar paletteBar = new PaletteBar(stateManager, this);
        add(paletteBar, BorderLayout.NORTH);

        pathLabel = new JLabel();

        setVisible(true);
    }

    public void replaceCursor(Cursor cursor) {
        if (cursor.getType() != getCursor().getType()) {
            setCursor(cursor);
        }
    }

    public PageShape getOverlappedElement(Point2D mousePosition) {
        Iterator<Slot> it = page.getSlotsIterator();
        while (it.hasNext()) {
            PageElement element = it.next().getElement();
            PageShape shape = (PageShape) element;
            PagePainter painter = (PagePainter) shape.getElementPainter();

            Area area = new Area(painter.getShape());

            Point2D rotatedPosition = rotatePoint(area.getBounds2D().getCenterX(), area.getBounds2D().getCenterY(), shape.getAngle(), mousePosition);

            boolean inArea = rotatedPosition.getX() > area.getBounds2D().getX() &&
                    rotatedPosition.getX() < area.getBounds2D().getX() + area.getBounds2D().getWidth() &&
                    rotatedPosition.getY() > area.getBounds2D().getY() &&
                    rotatedPosition.getY() < area.getBounds2D().getY() + area.getBounds2D().getHeight();

            if (inArea)
                return shape;
        }

        return null;
    }

    public Point2D rotatePoint(double cx, double cy, int angle, Point2D point) {
        point = (Point2D) point.clone();

        double s = Math.sin(Math.toRadians(angle));
        double c = Math.cos(Math.toRadians(angle));

        point.setLocation(point.getX() - cx, point.getY() - cy);

        double xnew = point.getX() * c - point.getY() * s;
        double ynew = point.getX() * s + point.getY() * c;

        point.setLocation(xnew + cx, ynew + cy);

        return point;
    }

    public void updateSelectionRectangle(Rectangle2D selectionRectangle) {
        content.setSelectionRectangle(selectionRectangle);
    }

    public void paste() {
        Transferable clipboardContent = MainFrame.getInstance().getClipboard().getContents(MainFrame.getInstance());

        if ((clipboardContent != null) &&
                (clipboardContent.isDataFlavorSupported(SlotSelection.flavor))) {
            try {
                Slot slot;
                java.util.List<Slot> tempElements = (List<Slot>) clipboardContent.getTransferData(SlotSelection.flavor);
                ArrayList<Slot> createdSlots = new ArrayList<>(tempElements.size());
                int newElementCount = tempElements.size();
                for (int i = 0; i < newElementCount; i++) {
                    if (tempElements.get(i) != null) {
                        slot = (Slot) tempElements.get(i).clone();

                        PageShape shape = (PageShape) slot.getElement();

                        Point2D newLocation = (Point2D) shape.getPosition().clone();
                        newLocation.setLocation(shape.getPosition().getX() + 20, shape.getPosition().getY() + 20);
                        shape.setPosition(newLocation);

                        if (shape instanceof CircleElement)
                            slot.getElement().setElementPainter(new CirclePainter(shape));
                        else if (shape instanceof RectangleElement)
                            slot.getElement().setElementPainter(new RectanglePainter(shape));

                        createdSlots.add(slot);
                        page.getSelectionModel().addToSelectionList(slot.getElement());
                    }
                }

                CommandManager.getInstance().addCommand(new AddSlotCommand(page, createdSlots));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    @Override
    public void onSlotChanged() {
        content.repaint();
    }

    @Override
    public void onSelectionChanged() {
        content.repaint();
    }

    @Override
    public void onProjectChangedName(String name) {
        Document doc = (Document) page.getParent();
        pathLabel.setText(name + " -> " + doc.getName() + " -> " + page.getName());
    }

    @Override
    public void onDocumentChangedName(Document document) {
        Project project = (Project) page.getParent().getParent();
        pathLabel.setText(project.getName() + " -> " + document.getName() + " -> " + page.getName());
    }

    @Override
    public void onPageChangedName(String name) {
        Project project = (Project) page.getParent().getParent();
        Document doc = (Document) page.getParent();

        pathLabel.setText(project.getName() + " -> " + doc.getName() + " -> " + name);
        pageTitleBorder.setTitle(name);
    }

    public Page getPage() {
        return page;
    }
}
