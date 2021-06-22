package seabattle.client.graphic.mainmenu;

import seabattle.client.graphic.authentication.view.LoginPanel;
import seabattle.client.graphic.graphiclistener.StringInvoker;
import seabattle.client.graphic.graphiclistener.StringListener;
import seabattle.client.graphic.mainmenu.view.*;
import seabattle.client.listener.UserData;
import seabattle.shared.game.ScoreboardRecord;
import seabattle.shared.game.SpectateListRecord;
import seabattle.shared.loop.Loop;
import seabattle.shared.request.*;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MainMenuWindow implements StringInvoker {

    private final RequestListener listener;
    private final JFrame mainMenuFrame;
    private MainMenuPanel mainMenuPanel;
    private ScoreboardPanel scoreboardPanel;
    private SpectateListPanel spectateListPanel;
    private SpectateGamePanel spectateGamePanel;
    private UserData userData;
    private Loop scoreboardLoop;
    private Loop spectateListLoop;
    private Loop updateLastSeenLoop;

    LinkedList<StringListener> stringListeners = new LinkedList<>();


    public MainMenuWindow(RequestListener listener) {
        this.listener = listener;
        this.mainMenuFrame = new MainMenuFrame();
        this.mainMenuPanel = null;
        this.scoreboardPanel = new ScoreboardPanel();
        this.spectateListPanel = new SpectateListPanel();
        this.spectateGamePanel = new SpectateGamePanel();
        this.scoreboardLoop = new Loop(1, this::sendGetScoreboard);
        this.spectateListLoop = new Loop(1, this::sendGetSpectateList);
        this.updateLastSeenLoop = new Loop(1, this::sendUpdateLastSeen);
        this.updateLastSeenLoop.start();
    }

    public void initialize() {
        setMainMenuPanel();
        mainMenuFrame.setVisible(true);
    }

    public void deactivate() {
        mainMenuFrame.dispose();
    }

    private void setMainMenuPanel() {
        if (mainMenuPanel != null) {
            BorderLayout layout = (BorderLayout) mainMenuFrame.getLayout();
            mainMenuFrame.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        }
        mainMenuPanel = new MainMenuPanel(listener, userData);
        mainMenuPanel.addStringListener(new StringListener() {
            @Override
            public void run(String s) {
                if (s.equals("StartNewGameClicked")) {
//                    mainMenuFrame.dispose();
                    listener.listen(new StartGame());
                    checkStringListeners("NewGameChosen");
                }
            }
        });
        mainMenuFrame.add(mainMenuPanel, BorderLayout.CENTER);
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

    public void showPlayerInfo(String info) {
        JOptionPane.showMessageDialog(null, info);

    }

    public void showScoreboard() {
        if (scoreboardPanel.isWorking())
            return;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                scoreboardPanel.setWorking(true);
                scoreboardLoop.start();
                JOptionPane.showMessageDialog(null, scoreboardPanel);
                scoreboardPanel.setWorking(false);
                scoreboardLoop.stop();
                scoreboardLoop = new Loop(1, MainMenuWindow.this::sendGetScoreboard);
            }
        });
        t1.start();

    }

    public void updateScoreboard(ScoreboardRecord[] scoreboardRecord) {
        scoreboardPanel.updateScoreboard(scoreboardRecord);
    }

    private void sendGetScoreboard() {
        listener.listen(new GetScoreboard(false));
    }

    private void sendGetSpectateList() {
        listener.listen(new GetSpectateList(false));
    }

    private void sendUpdateLastSeen() {
        if (userData != null)
            listener.listen(new UpdateLastSeen(userData.getUsername()));
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public void showSpectateList() {
        if (spectateGamePanel.isWorking())
            return;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                spectateListPanel.setWorking(true);
                spectateListLoop.start();
                JOptionPane.showMessageDialog(null, spectateListPanel);
                spectateListPanel.setWorking(false);
                spectateListLoop.stop();
                spectateListLoop = new Loop(1, MainMenuWindow.this::sendUpdateLastSeen);
            }
        });
        t1.start();
    }

    public void updateSpectateList(SpectateListRecord[] records) {
        spectateListPanel.updateScoreboard(records);
    }
}
