package sample.wrk;

import sample.ctrl.Controller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerListener extends Thread {

    private DatagramSocket socket;
    private volatile boolean running;
    private byte[] buf = new byte[8192];
    private Controller controller;
    DatagramSocket ds;

    public ServerListener(Controller c) throws SocketException {
        super("Srv Listener");
        ds = new DatagramSocket(3945);

        controller = c;
    }

    public void run() {
        running = true;

        while (running) {
            try {

                DatagramPacket dp = new DatagramPacket(buf, buf.length);
                ds.receive(dp);

                BufferedImage i = ImageIO.read(new ByteArrayInputStream(dp.getData()));

                controller.showImage(i);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }

    public void setRunning(boolean run) {
        running = run;
    }
}
