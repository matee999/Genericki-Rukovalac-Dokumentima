package app.views.page.slot_content.text_content;

import app.models.slot.Slot;
import app.models.slot.content.TextSlotContent;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class TextEditorToolbar extends JToolBar {

    private JComboBox<String> size;
    private JComboBox<String> style;
    private JComboBox<String> color;

    public TextEditorToolbar(TextEditor editor) {
        this.setFloatable(false);

        URL boldImgUrl = getClass().getResource("/app/actions/images/bold.png");
        ImageIcon boldIcon = null;
        if (boldImgUrl != null)
            boldIcon = new ImageIcon(new ImageIcon(boldImgUrl).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        JButton bold = new JButton();
        bold.setIcon(boldIcon);
        bold.setToolTipText("Bold");
        bold.addActionListener(editor.getController().getBoldTextListener());
        this.add(bold);

        URL italicImgUrl = getClass().getResource("/app/actions/images/italic.png");
        ImageIcon italicIcon = null;
        if (italicImgUrl != null)
            italicIcon = new ImageIcon(new ImageIcon(italicImgUrl).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        JButton italic = new JButton();
        italic.setIcon(italicIcon);
        italic.setToolTipText("Italic");
        italic.addActionListener(editor.getController().getItalicTextActionListener());
        this.add(italic);

        this.addSeparator();

        size = new JComboBox<>(new String[]{"8", "9", "10", "11", "12", "14",
                "16", "18", "20", "24", "36", "48", "72"
        });
        size.setSelectedItem("14");
        size.setEditable(false);
        size.setToolTipText("Izaberite veličinu fonta");
        size.addActionListener(editor.getController().getFontSizeActionListener());
        this.add(size);

        this.addSeparator();

        style = new JComboBox<>(new String[]{"Serif", "Agency FB", "Arial",
                "Calibri", "Century Gothic",
                "Comic Sans MS", "Courier New", "Forte",
                "Garamond", "Monospaced", "Segoe UI",
                "Times New Roman", "Trebuchet MS", "Serif"});
        style.setEditable(false);
        style.setToolTipText("Izaberite stil fonta");
        style.addActionListener(editor.getController().getFontStyleActionListener());
        this.add(style);

        this.addSeparator();

        color = new JComboBox<>(new String[]{"Crna", "Crvena", "Zelena",
                "Plava", "Narandžasta", "Pink"
        });
        color.setEditable(false);
        color.setToolTipText("Izaberite boju");
        color.addActionListener(editor.getController().getFontColorActionListener());
        this.add(color);
    }

    public int getSelectedFontSize(){
        return Integer.parseInt(size.getSelectedItem().toString());
    }
    public String getSelectedFontStyle(){
        return style.getSelectedItem().toString();
    }
    public String getSelectedFontColor(){
        return color.getSelectedItem().toString();
    }
}
