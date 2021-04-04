package app.views.miscellaneous;

import javax.swing.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutDialog extends JDialog {
	

    public AboutDialog(JFrame parent) {
        super(parent, "About Dialog", true);
        
        setLocationRelativeTo(parent);
        

        Box b = Box.createVerticalBox();
        b.add(Box.createGlue());
        getContentPane().add(b, "Center");
        
        b.add(new JLabel("Članovi tima 09 (grupa 202):"));
        b.add(new JLabel(" "));

        b.add(new JLabel("Luka Petrović - RN 33/2018"));
        ImageIcon IconL = null;
		try {
            IconL = new ImageIcon(this.getClass().getResource("/images/Luka.jpg"));
            Image image = IconL.getImage(); // transform it
            Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            newimg.getScaledInstance(200, 200, 1);
            IconL = new ImageIcon(newimg);
        } catch (Exception e) {
            e.printStackTrace();
        }
		b.add(new JLabel(IconL));
        b.add(new JLabel(" "));

        b.add(new JLabel("Matija Pleskonjić - RN 59/2018"));
        ImageIcon Icon = null;
		try {
            Icon = new ImageIcon(this.getClass().getResource("/images/Matija.jpg"));
            Image image = Icon.getImage(); // transform it
            Image newimg = image.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            Icon = new ImageIcon(newimg);
        } catch (Exception e) {
            e.printStackTrace();
        } 
		b.add(new JLabel(Icon));
        
        b.add(Box.createGlue());
        getContentPane().add(b, "Center");

        JPanel p2 = new JPanel();
        JButton ok = new JButton("Ok");
        p2.add(ok);
        getContentPane().add(p2, "South");
        
        this.setLocationRelativeTo(null);

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setVisible(false);
            }
        });

        this.setSize(400, 500);
    }
}
