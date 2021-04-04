package app.views.document.share_document;

import app.models.project.Project;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ShareDocumentDialogCellRenderer extends JLabel implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Project project = (Project) value;

        setText(project.getName());

        setPreferredSize(new Dimension(100, 50));

        URL imageURL = getClass().getResource("/app/actions/images/new_project.png");
        ImageIcon icon = null;
        if (imageURL != null)
            icon = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        setIcon(icon);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setEnabled(list.isEnabled());
        setFont(new Font("Arial", Font.PLAIN, 18));
        setOpaque(true);

        return this;
    }
}
