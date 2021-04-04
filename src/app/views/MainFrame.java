package app.views;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import app.models.workspace.Workspace;
import app.views.hierarchy.HierarchyTree;
import app.actions.ActionManager;
import app.views.miscellaneous.MenuBar;
import app.views.miscellaneous.ToolBar;
import app.views.workspace.WorkspaceView;

public class MainFrame extends JFrame implements ClipboardOwner {

    private static MainFrame instance = null;

    private Workspace workspace;
    private WorkspaceView workspaceView;

    private HierarchyTree hierarchyTree;
    private JSplitPane splitPane;

    private Clipboard clipboard = new Clipboard("CB");

    private MainFrame() {

        initialize();
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    private void initialize() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize((int) (screenWidth / 1.5), (int) (screenHeight / 1.5));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("GeRuDok");

        this.setLocationRelativeTo(null);

        MenuBar menuBar = new MenuBar();
        this.setJMenuBar(menuBar);

        ToolBar toolBar = new ToolBar();
        this.add(toolBar, BorderLayout.NORTH);

        initialiseWorkspaceTree();

        JScrollPane scroll = new JScrollPane(hierarchyTree);
        scroll.setMinimumSize(new Dimension(200, 150));
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, workspaceView);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.1);

        addWindowListener(new WindowListener() {
            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowOpened(WindowEvent e) {
                MainFrame frame = (MainFrame) e.getComponent();
                int result = JOptionPane.showConfirmDialog(frame, "Da li želite da otvorite postojeće radno okruženje?",
                        "Otvaranje radnog okruženja", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    ActionManager.getInstance().getSwitchWorkspace().actionPerformed(
                            new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {
                MainFrame frame = (MainFrame) e.getComponent();
                int result = JOptionPane.showConfirmDialog(frame, "Da li želite da sačuvate svoje radno okruženje?",
                        "Gašenje programa", JOptionPane.YES_NO_CANCEL_OPTION);
                if (result == JOptionPane.CANCEL_OPTION) {
                    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                } else if (result == JOptionPane.NO_OPTION) {
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                } else if (result == JOptionPane.YES_OPTION) {
                    ActionManager.getInstance().getSaveWorkspace().actionPerformed(
                            new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        this.getContentPane().add(splitPane, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public HierarchyTree getHierarchyTree() {
        return hierarchyTree;
    }

    public WorkspaceView getWorkspaceView() {
        return workspaceView;
    }

    public void setWorkspaceView(WorkspaceView workspaceView) {
        this.workspaceView = workspaceView;

        splitPane.setRightComponent(workspaceView);
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    private void initialiseWorkspaceTree() {
        this.workspace = new Workspace();

        this.workspaceView = new WorkspaceView(workspace);

        this.hierarchyTree = new HierarchyTree();
        this.hierarchyTree.setModel(new DefaultTreeModel(workspace));
    }

    public Clipboard getClipboard() {
        return clipboard;
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {}
}
