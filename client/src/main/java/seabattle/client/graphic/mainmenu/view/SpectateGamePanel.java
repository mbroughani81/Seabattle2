package seabattle.client.graphic.mainmenu.view;

import seabattle.client.graphic.game.view.GamePanel;
import seabattle.shared.game.ScoreboardRecord;

import javax.swing.*;
import java.awt.*;

public class SpectateGamePanel extends GamePanel {
    boolean isWorking;
    JTextArea test;
    int sourceGame;

    public SpectateGamePanel() {
        super();
        setPreferredSize(new Dimension(1000, 500));
    }

    public void updateScoreboard(ScoreboardRecord[] scoreboardRecords) {
        System.out.println("I shoult update spectategame!! spectategamepanle");
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

    public int getSourceGame() {
        return sourceGame;
    }

    public void setSourceGame(int sourceGame) {
        this.sourceGame = sourceGame;
    }
}
