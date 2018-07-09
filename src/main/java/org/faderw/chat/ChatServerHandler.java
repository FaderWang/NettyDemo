package org.faderw.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by FaderW on 2018/7/9
 */

public class ChatServerHandler extends ChannelHandlerAdapter{

    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        group.add(channel);
        group.writeAndFlush("[client] - " + channel.remoteAddress() + "加入\n");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        group.add(channel);
        group.writeAndFlush("[client] - " + channel.remoteAddress() + "离开\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("[client] - " + channel.remoteAddress() + "异常");

        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:"+ incoming.remoteAddress()+"离线");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;

        Channel incoming = ctx.channel();
        for (Channel channel : group) {
            if (incoming != channel) {
                incoming.writeAndFlush("[" + incoming.remoteAddress() + "】:" + body + "\n");
            } else {
                incoming.writeAndFlush("[you】：" + body + "\n");
            }
        }
    }

}
