package seabattle.client.graphic.authentication.view;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel() {
        setLayout(new BorderLayout());
    }

    public void setCenter(JPanel panel) {
        removeAll();
        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
