package seabattle.client.graphic.mainmenu.view;

import seabattle.shared.game.ScoreboardRecord;
import seabattle.shared.game.SpectateListRecord;

import javax.swing.*;
import java.awt.*;

public class SpectateListPanel extends JPanel {
    boolean isWorking;
    JTextArea test;
    SpectateListRecord[] records;

    public SpectateListPanel() {
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

    public void updateScoreboard(SpectateListRecord[] records) {
        System.out.println("I shoult update spectatelist!! spectatelist");
        this.records = records;
        test.setText("");
        for (SpectateListRecord record : records) {
            test.setText(test.getText() + record.getUsername1() + " " + record.getUsername2() + "\n");
        }
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public int getGameId(int n) {
        if (records == null)
            return -1;
        if (records.length <= n)
            return -1;
        return records[n].getGameId();
    }
}
