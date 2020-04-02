package com.chc.test.java.service.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String value = (String) msg;
        System.out.println("服务器收到客户端msg" + value);
//        ctx.write("1111");
//        ctx.write("222");
    }

}

