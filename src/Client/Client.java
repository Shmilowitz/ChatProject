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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Kristian Nielsen
 */
public class Client extends Observable {


    Socket socket;
    PrintWriter pw;
    Scanner scan;
    boolean running = true;

    public Client() {

    }

    public void connect(String ip, int port) throws UnknownHostException, IOException {
        
        
        socket = new Socket(InetAddress.getByName(ip), port);
        
        pw = new PrintWriter(socket.getOutputStream(), true);
        scan = new Scanner(socket.getInputStream());
        
        while (true) {
            receive();
        }
    }

    public void send(String s) throws InterruptedException {
        while(pw == null){
            Thread.sleep(10);
        }
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
        
        Client c = new Client();
        
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                ClientJFrame cjf = new ClientJFrame(c);
                cjf.setVisible(true);
                
            }
        });
        t1.start();
        
        
    }
}
