package seabattle.client.graphic;

import seabattle.shared.loop.Loop;

import javax.swing.*;

public class MyFrame extends JFrame {
    private final Loop loop;

    public MyFrame() {
        this.setSize(1000, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.loop = new Loop(40, this::update);
    }

//
//    @Override
//    public void setContentPane(Container contentPane) {
//        super.setContentPane(contentPane);
//        repaint();
//        revalidate();
//    }

    private void update() {
        this.repaint();
        this.revalidate();
    }

    @Override
    public void setVisible(boolean b) {
        if (b) loop.start();
        else loop.stop();
        super.setVisible(b);
    }
}
