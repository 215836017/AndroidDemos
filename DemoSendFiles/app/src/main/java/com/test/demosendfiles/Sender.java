package com.test.demosendfiles;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class Sender {

    private String serverIp;
    private int port;

    private Map<String, String> paths;

    public Sender(String serverIp) {
        this.serverIp = serverIp;
        this.port = 2357;
    }

    public void setPaths(Map<String, String> paths) {
        this.paths = paths;
    }

    public void sendFile() {

    }

    private void init() {
        try {
            Socket socket = new Socket(serverIp, port);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
