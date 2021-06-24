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
    JLabel board1Label;
    JLabel board2Label;

    public GamePanel() {
        setBackground(Color.PINK);
        setOpaque(true);
        setLayout(null);

        setBoard1();
        add(board1);
        setBoard1Label();
        add(board1Label);
        setBoard2();
        add(board2);
        setBoard2Label();
        add(board2Label);
    }

    public GamePanel(RequestListener listener) {
        this();
        this.listener = listener;
    }

    private void setBoard1() {
        int unitWidth = 400 / 10;
        int unitHeight = 400 / 10;
        board1 = new BoardPanel();
        board1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (listener != null)
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
                if (listener != null)
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

    private void setBoard1Label() {
        board1Label = new JLabel("salam1");
        board1Label.setOpaque(true);
        board1Label.setFont(new Font(null, Font.ITALIC, 15));
        board1Label.setSize(200, 70);
        board1Label.setBounds(10, 410, 200, 70);
    }

    private void updateBoard1Label(Board board) {
        board1Label.setText(board.getPlayerUsername());
        if (board.getTimer() > 0) {
            board1Label.setText(board1Label.getText() + " " + board.getTimer());
        }
        if (board.getMessage() != null) {
            board1Label.setText(board1Label.getText() + board.getMessage());
        }
        if (board.isTurnBoard()) {
            board1Label.setBackground(Color.ORANGE);
        } else {
            board1Label.setBackground(Color.PINK);
        }
    }

    private void setBoard2Label() {
        board2Label = new JLabel("salam2");
        board2Label.setOpaque(true);
        board2Label.setFont(new Font(null, Font.ITALIC, 15));
        board2Label.setSize(200, 70);
        board2Label.setBounds(500, 410, 200, 70);
    }

    private void updateBoard2Label(Board board) {
        board2Label.setText(board.getPlayerUsername());
        if (board.getTimer() > 0) {
            board2Label.setText(board2Label.getText() + " " + board.getTimer());
        }
        if (board.getMessage() != null) {
            board2Label.setText(board2Label.getText() + board.getMessage());
        }
        if (board.isTurnBoard()) {
            board2Label.setBackground(Color.ORANGE);
        } else {
            board2Label.setBackground(Color.PINK);
        }
    }

    public void updateBoard(Board board, int id) {
        System.out.println("Board is is GamePanel  " + board.getPlayerUsername() + " " + id);
        if (id == 1) {
            updateBoard1(board);
            updateBoard1Label(board);
        } else {
            updateBoard2(board);
            updateBoard2Label(board);
        }
    }

    private void updateBoard1(Board board) {
        board1.setBoard(board);
    }

    private void updateBoard2(Board board) {
        board2.setBoard(board);
    }

}
