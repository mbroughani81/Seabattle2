package seabattle.client.graphic.game.view;

import seabattle.shared.game.Board;
import seabattle.shared.game.BoardCell;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private Board board;

    public synchronized void setBoard(Board board) {
        this.board = board;
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (board == null)
            return;
        int unitWidth = 400 / board.getWidth();
        int unitHeight = 400 / board.getHeight();
        BoardCell[][] boardCells = board.getBoardCells();
        for (int i = 0; i < board.getHeight(); i++) {
            for (int t = 0; t < board.getWidth(); t++) {
                BoardCell tem = boardCells[i][t];
                Color gColor = g.getColor();
                g.setColor(tem.getColor());
                g.fillRect(t * unitWidth, i * unitHeight,t * unitWidth + unitWidth, i * unitHeight + unitHeight);
                g.setColor(gColor);
            }
        }
    }
}
