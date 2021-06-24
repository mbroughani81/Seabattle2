package seabattle.client.graphic.game.view;

import seabattle.shared.request.AcceptBoard;
import seabattle.shared.request.NewBoard;
import seabattle.shared.request.RequestListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGamePanel extends GamePanel {

    JButton newRandom;
    JButton acceptBoard;

    public MainGamePanel(RequestListener listener) {
        super(listener);
        newRandom = new JButton("New board");
        newRandom.setBounds(300, 410, 200, 70);
        newRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                listener.listen(new NewBoard());
            }
        });
        add(newRandom);

        acceptBoard = new JButton("OK");
        acceptBoard.setBounds(210, 410, 90, 70);
        acceptBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                listener.listen(new AcceptBoard());
            }
        });
        add(acceptBoard);
    }


}
