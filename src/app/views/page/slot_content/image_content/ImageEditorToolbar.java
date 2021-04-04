package app.views.page.slot_content.image_content;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageEditorToolbar extends JToolBar {

    public ImageEditorToolbar(ImageEditor editor) {
        this.setFloatable(false);

        URL url = getClass().getResource("/app/actions/images/photo.png");
        ImageIcon icon = null;
        if (url != null)
            icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        JButton button = new JButton();
        button.setIcon(icon);
        button.setToolTipText("Zameni sliku");
        button.addActionListener(editor.getController().getChangeImageClickedListener());
        this.add(button);
    }
}
