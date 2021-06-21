package seabattle.client.graphic.game.view;

import seabattle.shared.game.Board;
import seabattle.shared.request.ClickOnBoard;
import seabattle.shared.request.RequestListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel {

    RequestListener listener;
    BoardPanel board1;
    BoardPanel board2;

    public GamePanel(RequestListener listener) {
        this.listener = listener;

        setBackground(Color.PINK);
        setOpaque(true);
        setLayout(null);

        setBoard1();
        add(board1);
        setBoard2();
        add(board2);
    }

    private void setBoard1() {
        int unitWidth = 400 / 10;
        int unitHeight = 400 / 10;
        board1 = new BoardPanel();
        board1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                listener.listen(new ClickOnBoard(
                        mouseEvent.getY() / unitHeight,
                        mouseEvent.getX() / unitWidth,
                        1));
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
        board1.setLayout(null);
        board1.setBackground(Color.GRAY);
        board1.setOpaque(true);
        board1.setSize(400, 400);
        board1.setBounds(new Rectangle(10, 10, board1.getWidth(), board1.getHeight()));
    }

    private void setBoard2() {
        int unitWidth = 400 / 10;
        int unitHeight = 400 / 10;
        board2 = new BoardPanel();
        board2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                listener.listen(new ClickOnBoard(
                        mouseEvent.getY() / unitHeight,
                        mouseEvent.getX() / unitWidth,
                        2));
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
        board2.setLayout(null);
        board2.setBackground(Color.GRAY);
        board2.setOpaque(true);
        board2.setSize(400, 400);
        board2.setBounds(new Rectangle(500, 10, board2.getWidth(), board2.getHeight()));
    }

    public void updateBoard(Board board, int id) {
        if (id == 1) {
            updateBoard1(board);
        } else {
            updateBoard2(board);
        }
    }

    private void updateBoard1(Board board) {
        board1.setBoard(board);
    }

    private void updateBoard2(Board board) {
        board2.setBoard(board);
    }

}
