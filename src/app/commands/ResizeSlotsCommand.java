package app.commands;

import app.graphics.elements.PageElement;
import app.graphics.elements.PageShape;
import app.models.page.Page;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static app.Utilities.getShapeType;
import static app.Utilities.recreateElement;

public class ResizeSlotsCommand extends AbstractCommand {

    private Page page;

    private ArrayList<PageElement> resized = new ArrayList<>();

    private double dx;
    private double dy;
    private boolean redo = false;

    private int cursorType;

    public ResizeSlotsCommand(Page page, double dx, double dy, int cursorType) {
        this.page = page;
        this.dx = dx;
        this.dy = dy;
        this.cursorType = cursorType;

        this.resized.addAll(page.getSelectionModel().getSelectionList());
    }

    @Override
    public void doCommand() {
        if (!redo) {
            redo = true;
        } else {
            ArrayList<PageShape> newElements = new ArrayList<>();

            switch (cursorType) {
                case Cursor.N_RESIZE_CURSOR:
                    for (PageElement element : resized) {

                        PageShape shape = (PageShape) element;

                        double height = (int) (shape.getSize().getHeight() - dy);

                        // Stop the resize if the height gets too small:
                        if (height < 10) {
                            height = 10;
                            dy = 0;
                        }

                        PageShape newElement = recreateElement(shape.getId(),
                                new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY() + dy),
                                new Dimension((int) shape.getSize().getWidth(), (int) height),
                                shape.getPaint(), shape.getAngle(), getShapeType(shape)
                        );

                        newElements.add(newElement);
                    }
                    break;
                case Cursor.NW_RESIZE_CURSOR:
                    for (PageElement element : resized) {

                        PageShape shape = (PageShape) element;

                        double width = (int) shape.getSize().getWidth() - dx;
                        double height = (int) shape.getSize().getHeight() - dy;

                        // Stop the resize if the height gets too small:
                        if (height < 10) {
                            height = 10;
                            dy = 0;
                        }
                        if (width < 10) {
                            width = 10;
                            dx = 0;
                        }

                        PageShape newElement = recreateElement(shape.getId(),
                                new Point2D.Double(shape.getPosition().getX() + dx, shape.getPosition().getY() + dy),
                                new Dimension((int) width, (int) height),
                                shape.getPaint(), shape.getAngle(), getShapeType(shape)
                        );

                        newElements.add(newElement);
                    }
                    break;
                case Cursor.W_RESIZE_CURSOR:
                    for (PageElement element : resized) {

                        PageShape shape = (PageShape) element;

                        double width = (int) shape.getSize().getWidth() - dx;

                        if (width < 10) {
                            width = 10;
                            dx = 0;
                        }

                        PageShape newElement = recreateElement(shape.getId(),
                                new Point2D.Double(shape.getPosition().getX() + dx, shape.getPosition().getY()),
                                new Dimension((int) width, (int) shape.getSize().getHeight()),
                                shape.getPaint(), shape.getAngle(), getShapeType(shape)
                        );

                        newElements.add(newElement);
                    }
                    break;
                case Cursor.SW_RESIZE_CURSOR:
                    for (PageElement element : resized) {

                        PageShape shape = (PageShape) element;

                        double width = (int) shape.getSize().getWidth() - dx;
                        double height = (int) shape.getSize().getHeight() + dy;

                        // Stop the resize if the height gets too small:
                        if (height < 10) {
                            height = 10;
                            dy = 0;
                        }
                        if (width < 10) {
                            width = 10;
                            dx = 0;
                        }

                        PageShape newElement = recreateElement(shape.getId(),
                                new Point2D.Double(shape.getPosition().getX() + dx, shape.getPosition().getY()),
                                new Dimension((int) width, (int) height),
                                shape.getPaint(), shape.getAngle(), getShapeType(shape)
                        );

                        newElements.add(newElement);
                    }
                    break;
                case Cursor.S_RESIZE_CURSOR:
                    for (PageElement element : resized) {

                        PageShape shape = (PageShape) element;

                        double height = (int) shape.getSize().getHeight() + dy;

                        // Stop the resize if the height gets too small:
                        if (height < 10) {
                            height = 10;
                            dy = 0;
                        }

                        PageShape newElement = recreateElement(shape.getId(),
                                new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                                new Dimension((int) shape.getSize().getWidth(), (int) height),
                                shape.getPaint(), shape.getAngle(), getShapeType(shape)
                        );

                        newElements.add(newElement);
                    }
                    break;
                case Cursor.SE_RESIZE_CURSOR:
                    for (PageElement element : resized) {

                        PageShape shape = (PageShape) element;

                        double width = (int) shape.getSize().getWidth() + dx;
                        double height = (int) shape.getSize().getHeight() + dy;

                        // Stop the resize if the height gets too small:
                        if (height < 10) {
                            height = 10;
                            dy = 0;
                        }
                        if (width < 10) {
                            width = 10;
                            dx = 0;
                        }

                        PageShape newElement = recreateElement(shape.getId(),
                                new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                                new Dimension((int) width, (int) height),
                                shape.getPaint(), shape.getAngle(), getShapeType(shape)
                        );

                        newElements.add(newElement);
                    }
                    break;
                case Cursor.E_RESIZE_CURSOR:
                    for (PageElement element : resized) {

                        PageShape shape = (PageShape) element;

                        double width = (int) shape.getSize().getWidth() + dx;

                        if (width < 10) {
                            width = 10;
                            dx = 0;
                        }

                        PageShape newElement = recreateElement(shape.getId(),
                                new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                                new Dimension((int) width, (int) shape.getSize().getHeight()),
                                shape.getPaint(), shape.getAngle(), getShapeType(shape)
                        );

                        newElements.add(newElement);
                    }
                    break;
                case Cursor.NE_RESIZE_CURSOR:
                    for (PageElement element : resized) {

                        PageShape shape = (PageShape) element;

                        double width = (int) shape.getSize().getWidth() + dx;
                        double height = (int) shape.getSize().getHeight() - dy;

                        // Stop the resize if the height gets too small:
                        if (height < 10) {
                            height = 10;
                            dy = 0;
                        }
                        if (width < 10) {
                            width = 10;
                            dx = 0;
                        }

                        PageShape newElement = recreateElement(shape.getId(),
                                new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY() + dy),
                                new Dimension((int) width, (int) height),
                                shape.getPaint(), shape.getAngle(), getShapeType(shape)
                        );

                        newElements.add(newElement);
                    }
                    break;
                default:
                    return;
            }

            for (int i = 0; i < resized.size(); i++) {
                page.updateSlot(newElements.get(i));
                resized.set(i, newElements.get(i));
            }

            page.getSelectionModel().removeAllFromSelectionList();
        }
    }

    @Override
    public void undoCommand() {
        ArrayList<PageShape> newElements = new ArrayList<>();

        switch (cursorType) {
            case Cursor.N_RESIZE_CURSOR:
                for (PageElement element : resized) {

                    PageShape shape = (PageShape) element;

                    double height = (int) (shape.getSize().getHeight() + dy);

                    // Stop the resize if the height gets too small:
                    if (height < 10) {
                        height = 10;
                        dy = 0;
                    }

                    PageShape newElement = recreateElement(shape.getId(),
                            new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY() - dy),
                            new Dimension((int) shape.getSize().getWidth(), (int) height),
                            shape.getPaint(), shape.getAngle(), getShapeType(shape)
                    );

                    newElements.add(newElement);
                }
                break;
            case Cursor.NW_RESIZE_CURSOR:
                for (PageElement element : resized) {

                    PageShape shape = (PageShape) element;

                    double width = (int) shape.getSize().getWidth() + dx;
                    double height = (int) shape.getSize().getHeight() + dy;

                    // Stop the resize if the height gets too small:
                    if (height < 10) {
                        height = 10;
                        dy = 0;
                    }
                    if (width < 10) {
                        width = 10;
                        dx = 0;
                    }

                    PageShape newElement = recreateElement(shape.getId(),
                            new Point2D.Double(shape.getPosition().getX() - dx, shape.getPosition().getY() - dy),
                            new Dimension((int) width, (int) height),
                            shape.getPaint(), shape.getAngle(), getShapeType(shape)
                    );

                    newElements.add(newElement);
                }
                break;
            case Cursor.W_RESIZE_CURSOR:
                for (PageElement element : resized) {

                    PageShape shape = (PageShape) element;

                    double width = (int) shape.getSize().getWidth() + dx;

                    if (width < 10) {
                        width = 10;
                        dx = 0;
                    }

                    PageShape newElement = recreateElement(shape.getId(),
                            new Point2D.Double(shape.getPosition().getX() - dx, shape.getPosition().getY()),
                            new Dimension((int) width, (int) shape.getSize().getHeight()),
                            shape.getPaint(), shape.getAngle(), getShapeType(shape)
                    );

                    newElements.add(newElement);
                }
                break;
            case Cursor.SW_RESIZE_CURSOR:
                for (PageElement element : resized) {

                    PageShape shape = (PageShape) element;

                    double width = (int) shape.getSize().getWidth() + dx;
                    double height = (int) shape.getSize().getHeight() - dy;

                    // Stop the resize if the height gets too small:
                    if (height < 10) {
                        height = 10;
                        dy = 0;
                    }
                    if (width < 10) {
                        width = 10;
                        dx = 0;
                    }

                    PageShape newElement = recreateElement(shape.getId(),
                            new Point2D.Double(shape.getPosition().getX() - dx, shape.getPosition().getY()),
                            new Dimension((int) width, (int) height),
                            shape.getPaint(), shape.getAngle(), getShapeType(shape)
                    );

                    newElements.add(newElement);
                }
                break;
            case Cursor.S_RESIZE_CURSOR:
                for (PageElement element : resized) {

                    PageShape shape = (PageShape) element;

                    double height = (int) shape.getSize().getHeight() - dy;

                    // Stop the resize if the height gets too small:
                    if (height < 10) {
                        height = 10;
                        dy = 0;
                    }

                    PageShape newElement = recreateElement(shape.getId(),
                            new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                            new Dimension((int) shape.getSize().getWidth(), (int) height),
                            shape.getPaint(), shape.getAngle(), getShapeType(shape)
                    );

                    newElements.add(newElement);
                }
                break;
            case Cursor.SE_RESIZE_CURSOR:
                for (PageElement element : resized) {

                    PageShape shape = (PageShape) element;

                    double width = (int) shape.getSize().getWidth() - dx;
                    double height = (int) shape.getSize().getHeight() - dy;

                    // Stop the resize if the height gets too small:
                    if (height < 10) {
                        height = 10;
                        dy = 0;
                    }
                    if (width < 10) {
                        width = 10;
                        dx = 0;
                    }

                    PageShape newElement = recreateElement(shape.getId(),
                            new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                            new Dimension((int) width, (int) height),
                            shape.getPaint(), shape.getAngle(), getShapeType(shape)
                    );

                    newElements.add(newElement);
                }
                break;
            case Cursor.E_RESIZE_CURSOR:
                for (PageElement element : resized) {

                    PageShape shape = (PageShape) element;

                    double width = (int) shape.getSize().getWidth() - dx;

                    if (width < 10) {
                        width = 10;
                        dx = 0;
                    }

                    PageShape newElement = recreateElement(shape.getId(),
                            new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                            new Dimension((int) width, (int) shape.getSize().getHeight()),
                            shape.getPaint(), shape.getAngle(), getShapeType(shape)
                    );

                    newElements.add(newElement);
                }
                break;
            case Cursor.NE_RESIZE_CURSOR:
                for (PageElement element : resized) {

                    PageShape shape = (PageShape) element;

                    double width = (int) shape.getSize().getWidth() - dx;
                    double height = (int) shape.getSize().getHeight() + dy;

                    // Stop the resize if the height gets too small:
                    if (height < 10) {
                        height = 10;
                        dy = 0;
                    }
                    if (width < 10) {
                        width = 10;
                        dx = 0;
                    }

                    PageShape newElement = recreateElement(shape.getId(),
                            new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY() - dy),
                            new Dimension((int) width, (int) height),
                            shape.getPaint(), shape.getAngle(), getShapeType(shape)
                    );

                    newElements.add(newElement);
                }
                break;
            default:
                return;
        }

        for (int i = 0; i < resized.size(); i++) {
            page.updateSlot(newElements.get(i));
            resized.set(i, newElements.get(i));
        }

        page.getSelectionModel().removeAllFromSelectionList();
    }
}
