import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Main {





    public static JFrame reportWindows;

    public static void fillReportBug(){
        reportWindows =  new JFrame("Report Bug");
        reportWindows.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        reportWindows.setSize(640, 720);
        JTextField titre = new JTextField(30);
        JPanel panneau = new JPanel();
        JLabel titreText = new JLabel("Titre : ");
        panneau.setLayout(new BorderLayout());
        panneau.add(titreText, BorderLayout.CENTER);
        panneau.add(titre, BorderLayout.EAST);
        JLabel description = new JLabel("Description : ");
        JTextArea descriptionArea = new JTextArea(24, 60);
        JPanel panneau2 = new JPanel();
        panneau2.setLayout(new BorderLayout());
        panneau2.add(description, BorderLayout.CENTER);
        panneau2.add(descriptionArea, BorderLayout.EAST);
        reportWindows.add(panneau, BorderLayout.NORTH);
        reportWindows.add(panneau2, BorderLayout.CENTER);
        JButton soumettre = new JButton("Soumettre");
        soumettre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportWindows.setVisible(false);
            }
        });
        reportWindows.add(soumettre, BorderLayout.SOUTH);
        reportWindows.setVisible(false);
    }
    public static JPanel fillOverlay(){
        JPanel overlayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Fill the panel with a semi-transparent color
                g.setColor(new Color(0, 0, 0, 128)); // 128 is the alpha value (0-255)
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        JButton addReport = new JButton("Report Bug");
        addReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportWindows.setVisible(true);
            }
        });

        overlayPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel informations = new JPanel();
        informations.setLayout(new GridBagLayout());
        informations.add(new JLabel("Pseudo"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.ipadx = 40;
        c.gridx = 0;
        c.gridy = 0;
        overlayPanel.add(informations, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        overlayPanel.add(new JLabel("This is an overlay"), c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.ipadx = 40;
        c.gridx = 2;
        c.gridy = 0;
        overlayPanel.add(addReport, c);

        return overlayPanel;
    }

    public static void main(String[] args) {
        fillReportBug();
        JFrame frame = new JFrame("Overlay Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLayout(new BorderLayout());

        // Create a panel for the overlay
        JPanel overlayPanel = fillOverlay();
        ImageIcon imageIcon = new ImageIcon("resources/kbp_tensei_pic2.png");
        JLabel image = new JLabel(imageIcon);
        image.setOpaque(true);
        // Add components to the main frame
        JButton showOverlayButton = new JButton("Show Overlay");
        showOverlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                image.setVisible(false);
                overlayPanel.setVisible(true);
            }
        });

        JButton hideOverlayButton = new JButton("Hide Overlay");
        hideOverlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                overlayPanel.setVisible(false);image.setVisible(true);
            }
        });



        frame.add(showOverlayButton, BorderLayout.NORTH);
        frame.add(hideOverlayButton, BorderLayout.SOUTH);

        frame.add(image);
        frame.add(overlayPanel, BorderLayout.CENTER);

        overlayPanel.setVisible(false);
        frame.setVisible(true);
    }
}