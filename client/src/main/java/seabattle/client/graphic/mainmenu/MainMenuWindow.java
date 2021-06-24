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
import java.util.logging.LoggingPermission;

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
    private Loop spectateGameLoop;
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
        this.spectateGameLoop = new Loop(1, this::sendUpdateSpectateBoard);
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

    public void showSpectateList(int cnt) {
        if (spectateListPanel.isWorking())
            return;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                spectateListPanel.setWorking(true);
                spectateListLoop.start();
                String[] options = new String[cnt];
                for (int i = 0; i < cnt; i++)
                    options[i] = String.valueOf(i);
                int n = JOptionPane.showOptionDialog(
                        null,
                        spectateListPanel,
                        "s",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        "select"
                );
                spectateListPanel.setWorking(false);
                if (n != -1) {
                    int gameId = spectateListPanel.getGameId(n);
                    spectateGamePanel.setSourceGame(gameId);
                    if (gameId != -1) {
                        listener.listen(new GetSpectateGame());
                    }
                }
                System.out.println("result mainmenuwindo " + n);
                spectateListLoop.stop();
                spectateListLoop = new Loop(1, MainMenuWindow.this::sendGetSpectateList);
            }
        });
        t1.start();
    }

    public void showSpectateGame() {
        if (spectateGamePanel.isWorking())
            return;
        System.out.println("Noice mmw");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                spectateGamePanel.setWorking(true);
                spectateGameLoop.start();
                JOptionPane.showMessageDialog(null, spectateGamePanel);
                spectateGamePanel.setWorking(false);
                spectateGameLoop.stop();
                spectateGameLoop = new Loop(1, MainMenuWindow.this::sendUpdateSpectateBoard);
            }
        });
        t1.start();
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

    public void updateSpectateList(SpectateListRecord[] records) {
        spectateListPanel.updateScoreboard(records);
    }

    public void updateScoreboard(ScoreboardRecord[] scoreboardRecord) {
        scoreboardPanel.updateScoreboard(scoreboardRecord);
    }

    public void sendUpdateSpectateBoard() {
        int gameId = spectateGamePanel.getSourceGame();
        if (gameId == -1)
            return;
        listener.listen(new GetSpectateBoard(gameId, 1));
        listener.listen(new GetSpectateBoard(gameId, 2));
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
