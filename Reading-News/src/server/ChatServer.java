/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 *
 * @author biidz
 */
public class ChatServer {

    public static ArrayList<ClientThread> listClient;

    public static void main(String[] args) throws IOException {
        listClient = new ArrayList<>();
        ServerSocket ss = new ServerSocket(6000);
        System.err.println("Server start!");
        while (true) {
            ClientThread ct = new ClientThread(ss.accept());
            listClient.add(ct);
            ct.start();
        }
    }

    public static void sendMsg(String msg) {
        try {
            for (ClientThread clientThread : listClient) {
                if (clientThread.getSocket().isConnected()) {
                    clientThread.getBw().write(msg);
                    clientThread.getBw().newLine();
                    clientThread.getBw().flush();
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
