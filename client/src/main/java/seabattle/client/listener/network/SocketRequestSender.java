package seabattle.client.listener.network;

import seabattle.client.listener.RequestSender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import seabattle.shared.gson.Deserializer;
import seabattle.shared.gson.LocalDateTimeDeserializer;
import seabattle.shared.gson.LocalDateTimeSerializer;
import seabattle.shared.gson.Serializer;
import seabattle.shared.request.Request;
import seabattle.shared.response.Response;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class SocketRequestSender implements RequestSender {
    private final Socket socket;
    private final PrintWriter output;
    private final Scanner input;
    private final Gson gson;

    public SocketRequestSender(Socket socket) throws IOException {
        this.socket = socket;
        this.output = new PrintWriter(socket.getOutputStream(), true);
        this.input = new Scanner(socket.getInputStream());
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Response.class, new Deserializer<>())
                .registerTypeAdapter(Request.class, new Serializer<>())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .create();
    }

    @Override
    public Response send(Request request) {
        String requestString = gson.toJson(request, Request.class);
        output.println(requestString);
        String responseString = input.nextLine();
        return gson.fromJson(responseString, Response.class);
    }

    @Override
    public void close() {

    }
}
