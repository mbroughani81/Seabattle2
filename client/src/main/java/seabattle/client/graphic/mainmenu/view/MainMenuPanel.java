package seabattle.client.graphic.mainmenu.view;

import seabattle.client.graphic.graphiclistener.StringInvoker;
import seabattle.client.graphic.graphiclistener.StringListener;
import seabattle.client.listener.UserData;
import seabattle.shared.request.GetPlayerInfo;
import seabattle.shared.request.GetScoreboard;
import seabattle.shared.request.GetSpectateList;
import seabattle.shared.request.RequestListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MainMenuPanel extends JPanel implements StringInvoker {

    RequestListener listener;

    private final JButton newGameBtn;
    private final JButton spectateGamesBtn;
    private final JButton scoreboardBtn;
    private final JButton aboutPlayerBtn;
    private UserData userData;

    LinkedList<StringListener> stringListeners = new LinkedList<>();

    public MainMenuPanel(RequestListener listener, UserData userData) {
        this.listener = listener;
        this.userData = userData;

        setBackground(Color.GREEN);
        setOpaque(true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        newGameBtn = new JButton("Start new game");
        newGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                checkStringListeners("StartNewGameClicked");
            }
        });
        newGameBtn.setPreferredSize(new Dimension(400, 70));
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(newGameBtn, gbc);

        spectateGamesBtn = new JButton("Spectate Games");
        spectateGamesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                listener.listen(new GetSpectateList(true));
            }
        });
        spectateGamesBtn.setPreferredSize(new Dimension(400, 70));
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(spectateGamesBtn, gbc);

        scoreboardBtn = new JButton("Scoreboard");
        scoreboardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                listener.listen(new GetScoreboard(true));
            }
        });
        scoreboardBtn.setPreferredSize(new Dimension(400, 70));
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(scoreboardBtn, gbc);

        aboutPlayerBtn = new JButton("About player");
        aboutPlayerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                listener.listen(new GetPlayerInfo(userData.getUsername()));
            }
        });
        aboutPlayerBtn.setPreferredSize(new Dimension(400, 70));
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(aboutPlayerBtn, gbc);

    }

    @Override
    public void addStringListener(StringListener listener) {
        stringListeners.add(listener);
    }

    @Override
    public void checkStringListeners(String s) {
        for (StringListener stringListener : stringListeners) {
            stringListener.run(s);
        }
    }
}
