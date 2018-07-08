package org.faderw.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by FaderW on 2018/7/8
 */

public class EchoClientHandler extends ChannelHandlerAdapter {

    public final ByteBuf writeBuf;

    public EchoClientHandler() {
        String msg = "QUERY TIME ORDER$_";
        writeBuf = Unpooled.copiedBuffer(msg.getBytes());
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(writeBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("服务器已经收到我的信息：【" + body + "】");
    }
}
