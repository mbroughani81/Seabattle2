package seabattle.client.graphic.game.view;

import seabattle.shared.request.NewBoard;
import seabattle.shared.request.RequestListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGamePanel extends GamePanel {

    JButton newRandom;

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
    }


}
