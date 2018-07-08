package org.faderw.nio;

/**
 * Created by FaderW on 2018/6/25
 */

public class NIOTimeClient {

    public static void main(String[] args) {
        NIOTimeClientHandler nioTimeClientHandler = new NIOTimeClientHandler("127.0.0.1", 8080);
        new Thread(nioTimeClientHandler, "nioTimeClientHandler").start();
    }
}
