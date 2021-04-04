package app.actions;

import javax.swing.JOptionPane;

import app.views.MainFrame;

public class ErrorHandler {

    public static void showFileError() {
        JOptionPane.showMessageDialog(MainFrame.getInstance(), "Greška u otvaranju izabranog file-a.");
    }

    public static void showNoProjectsError() {
        JOptionPane.showMessageDialog(MainFrame.getInstance(), "Ne postoji projekat u koji može da se podeli dokument.");
    }

    public static void showDocumentDeletedDialog() {
        JOptionPane.showMessageDialog(MainFrame.getInstance(), "Ne postoji projekat u koji mogu da se prebace dokumenti.");
    }

    public static void showRenameError() {
        JOptionPane.showMessageDialog(null, "Naziv ne može biti prazan!",
                "Greška", JOptionPane.ERROR_MESSAGE);
    }
}
