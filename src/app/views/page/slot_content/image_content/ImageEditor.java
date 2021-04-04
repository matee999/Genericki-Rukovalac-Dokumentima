package app.views.page.slot_content.image_content;

import app.models.slot.content.ImageSlotContent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ImageEditor extends JDialog {

    private ImageEditorController controller;

    private JLabel label;

    public ImageEditor(ImageSlotContent content) {
        this.setSize(new Dimension(600, 600));
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        controller = new ImageEditorController(content, this);

        ImageEditorToolbar toolbar = new ImageEditorToolbar(this);
        this.add(toolbar, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        label = new JLabel();
        label.setPreferredSize(new Dimension(500, 500));

        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);

        this.add(panel, BorderLayout.CENTER);
        this.pack();

        if (content.getImagePath() != null)
            updatePhoto(content.getImagePath());
    }

    public ImageEditorController getController() {
        return controller;
    }

    public void updatePhoto(File photo) {
        Image img = new ImageIcon(photo.getAbsolutePath()).getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newImg);
        label.setIcon(icon);
    }
}
