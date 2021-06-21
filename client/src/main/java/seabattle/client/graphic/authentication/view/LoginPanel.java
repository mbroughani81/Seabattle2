package seabattle.client.graphic.authentication.view;

import seabattle.client.graphic.AbstractPanel;
import seabattle.client.graphic.authentication.view.event.NewUserEvent;
import seabattle.client.graphic.authentication.view.listener.NewUserEventInvoker;
import seabattle.client.graphic.authentication.view.listener.NewUserEventListener;
import seabattle.client.graphic.graphiclistener.StringInvoker;
import seabattle.client.graphic.graphiclistener.StringListener;
import seabattle.shared.request.RequestListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class LoginPanel extends AbstractPanel implements StringInvoker, ActionListener, NewUserEventInvoker {
    TextField username;
    TextField password;
    JButton signupButton;
    JButton loginButton;
    JLabel errorLabel;

    RequestListener listener;
    LinkedList<StringListener> stringListeners = new LinkedList<>();
    NewUserEventListener newUserEventListener;

    public LoginPanel(RequestListener listener) {
        this.listener = listener;

        setLayout(new GridBagLayout());
        setBackground(Color.RED);
        setOpaque(true);
        GridBagConstraints gbc = new GridBagConstraints();

        username = new TextField();
        username.setPreferredSize(new Dimension(200, 40));
        password = new TextField();
        password.setPreferredSize(new Dimension(200, 40));
        signupButton = new JButton("signup");
        signupButton.setPreferredSize(new Dimension(100, 40));
        signupButton.addActionListener(this);
        loginButton = new JButton("login");
        loginButton.setPreferredSize(new Dimension(70, 40));
        loginButton.addActionListener(this);
        errorLabel = new JLabel();

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel usernameLabel = new JLabel("username : ");
        usernameLabel.setPreferredSize(new Dimension(200, 40));
        this.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(username, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel passwordLabel = new JLabel("password : ");
        passwordLabel.setPreferredSize(new Dimension(200, 40));
        this.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(password, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(errorLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(signupButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(loginButton, gbc);

    }

    @Override
    public void addStringListener(StringListener listener) {
        stringListeners.add(listener);
    }

    @Override
    public void checkStringListeners(String s) {
        for (StringListener listener : stringListeners)
            listener.run(s);
    }

    @Override
    public void setNewUserEventListener(NewUserEventListener newUserEventListener) {
        this.newUserEventListener = newUserEventListener;
    }

    @Override
    public void checkNewUserEventListener(NewUserEvent event) {
        if (newUserEventListener != null) {
            newUserEventListener.run(event);
        } else {
            System.out.println("NULL LoginPanel");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            checkStringListeners("SignupBtnClicked");
        }
        if (e.getSource() == loginButton) {
//            checkStringListeners("LoginBtnClicked");
//            listener.listen(new StartGame());
//            checkStringListeners("Close");
            checkNewUserEventListener(new NewUserEvent(username.getText(), password.getText(), LocalDateTime.now()));
        }
    }


}
