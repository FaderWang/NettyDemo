package org.faderw;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {
    public static void main(String[] args) throws IOException {
        // System.out.println("this is netty-demo");
        int port = 8080;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("The time server is start in port :" + port);
            Socket socket = null;

            //创建TimeServer线程池
            TimeServerHandlerExecutePool executePool = new TimeServerHandlerExecutePool(50, 10000);
            while (true) {
                socket = serverSocket.accept();
                executePool.execute(new TimeServerHandler(socket));
            }
        } catch (Exception e) {
            if (null != serverSocket) {
                System.out.println("The time server close!");
                serverSocket.close();
            }
        }
    }
}