package app.views.page.slot_content.image_content;

import app.models.slot.content.ImageSlotContent;
import app.models.slot.content.TextSlotContent;
import app.views.MainFrame;
import app.views.page.slot_content.text_content.TextEditor;
import app.views.page.slot_content.text_content.TextEditorToolbar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ImageEditorController {

    private ImageSlotContent imageContent;
    private ImageEditor editor;

    public ImageEditorController(ImageSlotContent imageContent, ImageEditor editor) {
        this.imageContent = imageContent;
        this.editor = editor;
    }

    public ActionListener getChangeImageClickedListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                int result = jfc.showOpenDialog(editor);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selected = jfc.getSelectedFile();
                    imageContent.setImagePath(selected);
                    editor.updatePhoto(selected);
                }
            }
        };
    }
}
