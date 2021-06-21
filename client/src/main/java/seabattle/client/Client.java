package seabattle.client;

import seabattle.client.config.SocketConfig;
import seabattle.client.listener.MainResponseHandler;
import seabattle.client.listener.network.SocketRequestSender;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketConfig socketConfig = new SocketConfig();
        try {
            Socket socket = new Socket(socketConfig.getIp(), Integer.parseInt(socketConfig.getPort()));
            MainResponseHandler responseHandler = new MainResponseHandler(new SocketRequestSender(socket));
            responseHandler.start();
        } catch (IOException e) {
            System.out.println("Problem with connecting to seabattle.server");
        }
    }
}
