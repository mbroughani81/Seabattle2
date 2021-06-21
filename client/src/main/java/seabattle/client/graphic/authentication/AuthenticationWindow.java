package seabattle.client.graphic.authentication;

import seabattle.client.graphic.AbstractPanel;
import seabattle.client.graphic.authentication.view.AuthFrame;
import seabattle.client.graphic.authentication.view.LoginPanel;
import seabattle.client.graphic.authentication.view.PanelType;
import seabattle.client.graphic.authentication.view.SignupPanel;
import seabattle.client.graphic.authentication.view.listener.NewUserEventListener;
import seabattle.client.graphic.graphiclistener.StringInvoker;
import seabattle.client.graphic.graphiclistener.StringListener;
import seabattle.client.graphic.authentication.view.event.NewUserEvent;
import seabattle.shared.request.NewUser;
import seabattle.shared.request.RequestListener;
import seabattle.shared.request.UserLogin;


import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;

public class AuthenticationWindow implements StringInvoker {
    private final RequestListener listener;
    private final AuthFrame authFrame;
    private final Map<PanelType, AbstractPanel> panels;
    private volatile PanelType currentPanel;

    LinkedList <StringListener> stringListeners = new LinkedList<>();

    public AuthenticationWindow(RequestListener listener) {
        this.listener = listener;
        this.authFrame = new AuthFrame();
        this.panels = new EnumMap<>(PanelType.class);
        this.currentPanel = null;
    }

    public void initialize() {
        setLoginPanel();
//        setSignupPanel();
        authFrame.setVisible(true);
    }

    public void deactivate() {
        authFrame.dispose();
    }

    private void setLoginPanel() {
        currentPanel = PanelType.LOGIN_PANEL;
        LoginPanel loginPanel = new LoginPanel(listener);
        loginPanel.addStringListener(new StringListener() {
            @Override
            public void run(String s) {
                if (s.equals("SignupBtnClicked")) {
                    setSignupPanel();
                }
            }
        });
        loginPanel.setNewUserEventListener(new NewUserEventListener() {
            @Override
            public void run(NewUserEvent event) {
                //listener.listen(new NewUser(event.getUsername(), event.getPassword(), event.getRegisterTime()));
                System.out.println("ok! authwindow");
                listener.listen(new UserLogin(event.getUsername(), event.getPassword()));
            }
        });
        authFrame.setContentPane(loginPanel);;
        panels.put(PanelType.LOGIN_PANEL, loginPanel);
    }

    private void setSignupPanel() {
        currentPanel = PanelType.SIGNUP_PANEL;
        SignupPanel signupPanel = new SignupPanel(listener);
        signupPanel.addStringListener(new StringListener() {
            @Override
            public void run(String s) {
                if (s.equals("LoginBtnClicked")) {
                    setLoginPanel();
                }
            }
        });
        signupPanel.setNewUserEventListener(new NewUserEventListener() {
            @Override
            public void run(NewUserEvent event) {
                listener.listen(new NewUser(event.getUsername(), event.getPassword(), event.getRegisterTime()));
            }
        });
        authFrame.setContentPane(signupPanel);
        panels.put(PanelType.SIGNUP_PANEL, signupPanel);
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
}
