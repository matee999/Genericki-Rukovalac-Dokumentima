package app.views.page.slot_content.text_content;

import app.models.slot.Slot;
import app.models.slot.content.TextSlotContent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextEditor extends JDialog {

    private TextSlotContent textElement;
    private TextEditorController controller;
    private TextEditorToolbar toolbar;
    private JTextArea textArea;

    public TextEditor(TextSlotContent content) {
        this.textElement = content;

        textArea = new JTextArea();

        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        textArea.setText(textElement.getContent());

        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);

        controller = new TextEditorController(content, this);
        controller.updateText();

        toolbar = new TextEditorToolbar(this);

        this.add(toolbar, BorderLayout.NORTH);
        this.setSize(new Dimension(600, 600));
        this.setLocationRelativeTo(null);
        this.toFront();

        textArea.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent arg0) {
                textElement.setContent(textArea.getText());
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
                textElement.setContent(textArea.getText());
            }

            @Override
            public void keyPressed(KeyEvent arg0) {
                textElement.setContent(textArea.getText());
            }
        });
    }

    public TextEditorToolbar getToolbar() {
        return toolbar;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public TextEditorController getController() {
        return controller;
    }
}
