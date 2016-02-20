/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Client.Client;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kristian Nielsen
 */
public class Test1 {

    String ip = "localhost";
    int port = 9999;

    @Test
    public void Test1() {
        String[] args = {ip, Integer.toString(port)};
        new Thread(() -> {
            Server.Server.main(args);
        }).start();
        System.out.println("server started");
        
        Client c = new Client(ip, port);
        System.out.println("new client");
        try {
            c.connect();
            System.out.println("client connected");
        } catch (IOException ex) {
        }
        c.send("USER#test");
        assertEquals(c.receive(), "USERS#test");
        c.send("SEND#test#hey");
        assertEquals(c.receive(), "MESSAGE#test#hey");
        c.send("LOGOUT#");

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
