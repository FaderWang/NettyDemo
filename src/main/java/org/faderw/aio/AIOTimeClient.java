package org.faderw.aio;

/**
 * Created by FaderW on 2018/6/28
 */

public class AIOTimeClient {

    public static void main(String[] args) {
        AsyncTimeClientHandler handler = new AsyncTimeClientHandler("127.0.0.1", 8080);
        new Thread(handler, "AIOTimeClientHandler").start();
    }
}
