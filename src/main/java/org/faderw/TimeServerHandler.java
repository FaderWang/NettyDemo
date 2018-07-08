package org.faderw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable{

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
	public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream());

            String currentTimeString = null;
            String body = null;
            while (true) {
                body = in.readLine();
                if (body == null) {
                    break;
                }
                System.out.println("The time server receive order:" + body);
                currentTimeString = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                System.out.println(currentTimeString);
            }
        } catch (Exception e) {
            //TODO: handle exception
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e1) {
                    //TODO: handle exception
                    e1.printStackTrace();
                }
            }
            if (null != out) {
                out.close();
            }
            if (this.socket != null) {
                try {
                 this.socket.close();   
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
	}
    
}