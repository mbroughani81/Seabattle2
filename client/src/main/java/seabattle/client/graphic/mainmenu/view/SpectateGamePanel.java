package seabattle.client.graphic.mainmenu.view;

import seabattle.shared.game.ScoreboardRecord;

import javax.swing.*;
import java.awt.*;

public class SpectateGamePanel extends JPanel {
    boolean isWorking;
    JTextArea test;

    public SpectateGamePanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.CYAN);
        setOpaque(true);
        GridBagConstraints gbc = new GridBagConstraints();
        setPreferredSize(new Dimension(500, 500));

        test = new JTextArea("Loading...");
        test.setPreferredSize(new Dimension(200, 200));
        test.setFont(new Font(null, Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(test, gbc);

    }

    public void updateScoreboard(ScoreboardRecord[] scoreboardRecords) {
        System.out.println("I shoult update scoreboard!! scoreboardpanel");
        test.setText("");
        for (ScoreboardRecord record : scoreboardRecords) {
            test.setText(test.getText() + record.getUsername() + " " + record.isOnline() + " " + record.getScore() + "\n");
        }
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }
}
