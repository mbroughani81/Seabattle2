package seabattle.client.graphic.game;

import seabattle.client.graphic.game.view.GameFrame;
import seabattle.client.graphic.game.view.GamePanel;
import seabattle.shared.game.Board;
import seabattle.shared.loop.Loop;
import seabattle.shared.request.GetBoard;
import seabattle.shared.request.RequestListener;

import java.awt.*;

public class GameWindow {
    private final RequestListener listener;
    private final GameFrame gameFrame;
    private GamePanel gamePanel;
    private final Loop loop;
    boolean isActive;

    public GameWindow(RequestListener listener) {
        this.listener = listener;
        this.gameFrame = new GameFrame();
        this.gamePanel = null;
        this.loop = new Loop(2, this::sendUpdateBoardRequest);
        this.isActive = false;
    }

    public void initialize() {
        setGamePanel();
        gameFrame.setVisible(true);
        System.out.println("**Start updateing boards gamewindow");
        loop.start();
        isActive = true;
    }

    public void deactivate() {
        loop.stop();
        gameFrame.dispose();
        isActive = false;
    }

    private void setGamePanel() {
        if (gamePanel != null) {
            BorderLayout layout = (BorderLayout) gameFrame.getLayout();
            gameFrame.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        }
        gamePanel = new GamePanel(listener);
        gameFrame.add(gamePanel, BorderLayout.CENTER);
    }

    private void sendUpdateBoardRequest() {
        listener.listen(new GetBoard(1));
        listener.listen(new GetBoard(2));
    }

    public void updateBoard(Board board, int id) {
//        System.out.println("I Should be updating now...");
        gamePanel.updateBoard(board, id);
    }

    public boolean isActive() {
        return isActive;
    }
}
