/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import entity.Champion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author biidz
 */
public class Client2 extends Application{
    private long clientId;
    private final String HOST = "localhost";
    private final int PORT = 6000;
    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private GraphicsContext gc;
    private int minX = 30;
    private int minY = 30;
    private final int width = 100;
    private final int height = 100;
    private final int step = 5;
    private final int X = 1000;
    private final int Y = 600;
    private final String Q = "/img/Q.png";
    private final String W = "/img/W.png";
    private final String E = "/img/E.png";
    private final String R = "/img/R.png";
    private final String currentImageUrl = "/img/default.png";
    private Image face;

    @Override
    public void start(Stage theStage) throws Exception {
        this.clientId = System.currentTimeMillis();
        this.socket = new Socket(HOST, PORT);

        try {
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.oos = new ObjectOutputStream(this.socket.getOutputStream());
            this.ois = new ObjectInputStream(this.socket.getInputStream());
            NinjaClientThread nct = new NinjaClientThread();
            nct.start();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        theStage.setTitle("Ninja v1");
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(this.X, this.Y);
        root.getChildren().add(canvas);

        gc = canvas.getGraphicsContext2D();

        face = new Image(
                this.currentImageUrl,
                this.width, this.height, false, false);
        gc.drawImage(face, this.minX, this.minY);
        Champion p = new Champion(this.clientId,
                currentImageUrl, this.minX, this.minY, this.width, this.height, this.minX, this.minY);
        this.oos.writeObject(p);
        this.oos.flush();

        theScene.setOnKeyPressed((event) -> {
            int ox = this.minX;
            int oy = this.minY;
            if (null != event.getCode()) {
                switch (event.getCode()) {
                    case RIGHT:
                        if (this.minX >= (this.X - this.width)) {
                            break;
                        } else {
                            gc.clearRect(this.minX, this.minY, this.width, this.height);
                            this.minX += this.step;
                            gc.drawImage(face, this.minX, this.minY);
                        }
                        break;
                    case LEFT:
                        if (this.minX == 0) {
                            break;
                        } else {
                            gc.clearRect(this.minX, this.minY, this.width, this.height);
                            this.minX -= this.step;
                            gc.drawImage(face, this.minX, this.minY);
                        }
                        break;
                    case UP:
                        if (this.minY == 0) {
                            break;
                        } else {
                            gc.clearRect(this.minX, this.minY, this.width, this.height);
                            this.minY -= this.step;
                            gc.drawImage(face, this.minX, this.minY);
                        }
                        break;
                    case DOWN:
                        if (this.minY >= (this.Y - this.height)) {
                            break;
                        } else {
                            gc.clearRect(this.minX, this.minY, this.width, this.height);
                            this.minY += this.step;
                            gc.drawImage(face, this.minX, this.minY);
                        }
                        break;
                    case Q:
                        gc.clearRect(this.minX, this.minY, this.width, this.height);
                        this.face = new Image(
                                this.Q,
                                this.width, this.height, false, false);
                        gc.drawImage(face, this.minX, this.minY);
                        break;
                    case W:
                        gc.clearRect(this.minX, this.minY, this.width, this.height);
                        this.face = new Image(
                                this.W,
                                this.width, this.height, false, false);
                        gc.drawImage(face, this.minX, this.minY);
                        break;
                    case E:
                        gc.clearRect(this.minX, this.minY, this.width, this.height);
                        this.face = new Image(
                                this.E,
                                this.width, this.height, false, false);
                        gc.drawImage(face, this.minX, this.minY);
                        break;
                    case R:
                        gc.clearRect(this.minX, this.minY, this.width, this.height);
                        this.face = new Image(
                                this.R,
                                this.width, this.height, false, false);
                        gc.drawImage(face, this.minX, this.minY);
                        break;
                    default:
                        break;
                }
                try {
                    this.oos.writeObject(new Champion(this.clientId,
                            currentImageUrl, this.minX, this.minY, this.width, this.height, ox, oy));
                    this.oos.flush();
                } catch (IOException e) {

                }
            }
        });
        theStage.show();
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

    class NinjaClientThread extends Thread {

        @Override
        public void run() {

            while (true) {
                try {
                    Champion b = (Champion) ois.readObject();
                    if (b.getId() == clientId) {
                        gc.clearRect(b.getOx(), b.getOy(), b.getW(), b.getH());
                        gc.drawImage(face, b.getX(), b.getY());
                    } else {
                        face = new Image(
                                b.getImg(),
                                b.getW(), b.getH(), false, false);
                        gc.clearRect(b.getOx(), b.getOy(), b.getW(), b.getH());
                        gc.drawImage(face, b.getX(), b.getY());
                    }
                } catch (IOException e) {
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NinjaClient.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
