package seabattle.client.graphic.authentication.view.listener;

import seabattle.client.graphic.authentication.view.event.NewUserEvent;

public interface NewUserEventInvoker {
    void setNewUserEventListener(NewUserEventListener newUserEventListener);
    void checkNewUserEventListener(NewUserEvent event);
}
