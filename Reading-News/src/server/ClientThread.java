/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author biidz
 */
public class ClientThread extends Thread {

    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;

    public ClientThread(Socket socket) {
        this.socket = socket;
        try {
            this.bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String line = this.br.readLine();
                if (null == line) {
                    break;
                }
                System.out.println(this.socket.getInetAddress() + " said: " + line);
                ChatServer.sendMsg(line);
            } catch (IOException e) {
                System.err.println(e);
                break;
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedReader getBr() {
        return br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }

    public BufferedWriter getBw() {
        return bw;
    }

    public void setBw(BufferedWriter bw) {
        this.bw = bw;
    }

}
