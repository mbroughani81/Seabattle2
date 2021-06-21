package seabattle.server.controller;

import seabattle.server.config.SocketConfig;
import seabattle.server.controller.game.GameLobby;
import seabattle.server.controller.network.SocketResponseSender;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketManager extends Thread {
    SocketConfig socketConfig = new SocketConfig();

    public SocketManager() throws IOException {
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(socketConfig.getPort()));
            GameLobby gameLobby = new GameLobby();
            while(true) {
                System.out.println("--Waiting for new seabattle.client--");
                Socket socket = serverSocket.accept();
                System.out.println("--Connected--");
                ClientThread clientThread = new ClientThread(new SocketResponseSender(socket), gameLobby);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
