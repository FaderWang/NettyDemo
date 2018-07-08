package org.faderw.aio;

/**
 * Created by FaderW on 2018/6/27
 */

public class AIOTimeServer {

    public static void main(String[] args) {
        AsyncTimeServerHandler handler = new AsyncTimeServerHandler(8080);
        new Thread(handler, "AIOTimeServerHandler").start();
    }
}
