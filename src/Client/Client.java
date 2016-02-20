/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

/**
 *
 * @author Kristian Nielsen
 */
public class Client extends Observable {

    private static void lineHandler(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Socket socket;
    int port;
    String ip;
    PrintWriter pw;
    Scanner scan;
    boolean running = true;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void connect() throws UnknownHostException, IOException {
        socket = new Socket(InetAddress.getByName(ip), port);
        
        pw = new PrintWriter(socket.getOutputStream(), true);
        scan = new Scanner(socket.getInputStream());
    }

    public void send(String s) {
        pw.println(s);
        System.out.println(s);
    }

    public String receive() {
        String msg = scan.nextLine();
        setChanged();
        notifyObservers(msg);
        return msg;
    }

    public void stop() throws IOException {
        pw.println("LOGOUT#");
        socket.close();
    }

    public void updateList(String str) {
    }

    public static void main(String[] args) throws IOException {
        String ip = "";
        int port = 0;
        
        if (args.length > 1) {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }else{
            ip = "kristian3.cloudapp.net";
            port = 9090;
        }

        Client c = new Client(ip, port);
        c.connect();
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                ClientJFrame cjf = new ClientJFrame(c);
                cjf.setVisible(true);
            }
        });
        t1.start();
        while (true) {
            c.receive();
        }
    }
}
