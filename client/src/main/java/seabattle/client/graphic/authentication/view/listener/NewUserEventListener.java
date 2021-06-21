package seabattle.client.graphic.authentication.view.listener;


import seabattle.client.graphic.authentication.view.event.NewUserEvent;

public interface NewUserEventListener {
    void run(NewUserEvent event);
}
