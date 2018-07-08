package org.faderw.nio;

/**
 * Created by FaderW on 2018/6/26
 */

public class NIOTimeServer {

    public static void main(String[] args) {
        NIOMultiplexerTimerServer multiplexerTimerServer = new NIOMultiplexerTimerServer(8080);
        new Thread(multiplexerTimerServer, "multiplexerTimeServer").start();
    }
}
