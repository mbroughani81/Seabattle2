package seabattle.client.graphic.mainmenu;

import seabattle.client.graphic.authentication.view.LoginPanel;
import seabattle.client.graphic.graphiclistener.StringInvoker;
import seabattle.client.graphic.graphiclistener.StringListener;
import seabattle.client.graphic.mainmenu.view.MainMenuFrame;
import seabattle.client.graphic.mainmenu.view.MainMenuPanel;
import seabattle.client.graphic.mainmenu.view.ScoreboardPanel;
import seabattle.client.listener.UserData;
import seabattle.shared.game.ScoreboardRecord;
import seabattle.shared.loop.Loop;
import seabattle.shared.request.GetScoreboard;
import seabattle.shared.request.RequestListener;
import seabattle.shared.request.StartGame;
import seabattle.shared.request.UpdateLastSeen;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MainMenuWindow implements StringInvoker {

    private final RequestListener listener;
    private final JFrame mainMenuFrame;
    private MainMenuPanel mainMenuPanel;
    private ScoreboardPanel scoreboardPanel;
    private UserData userData;
    private Loop scoreboardLoop;
    private Loop updateLastSeenLoop;

    LinkedList<StringListener> stringListeners = new LinkedList<>();


    public MainMenuWindow(RequestListener listener) {
        this.listener = listener;
        this.mainMenuFrame = new MainMenuFrame();
        this.mainMenuPanel = null;
        this.scoreboardPanel = new ScoreboardPanel();
        this.scoreboardLoop = new Loop(1, this::sendGetScoreboard);
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
//        JOptionPane.showMessageDialog(null, info);
        LoginPanel loginPanel = new LoginPanel(listener);
//        loginPanel.setSize(700, 700);
//        loginPanel.setPreferredSize(new Dimension(700, 700));
//        JOptionPane.showMessageDialog(null, loginPanel);
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
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, "gooz");
            }
        });
        t1.start();
    }

    public void updateSpectateList(String info) {
        System.out.println("shit to update! mainmenuwindow");
    }
}
