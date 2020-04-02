package com.chc.test.java.service.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.ByteBuffer;

public class NettyClient {

    public static void main(String[] args) {
        try{
            System.out.println("客户端启动。。。");
            //1.创建两个线程，一个负责接收客户端，一个负责
            NioEventLoopGroup pGroup = new NioEventLoopGroup();
            //2.创建辅助类
            Bootstrap b = new Bootstrap();
            b.group(pGroup).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ByteBuf buf = Unpooled.copiedBuffer("_t".getBytes());
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
                            // 设置返回结果为String 类型
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture cf = b.connect("127.0.0.1",8091).sync();
            cf.channel().writeAndFlush(Unpooled.wrappedBuffer("test1_t".getBytes()));
//            Thread.sleep(1000);
            cf.channel().writeAndFlush(Unpooled.wrappedBuffer("test1_t".getBytes()));
//            Thread.sleep(1000);
            cf.channel().writeAndFlush(Unpooled.wrappedBuffer("test1_t".getBytes()));
//            Thread.sleep(1000);
            cf.channel().writeAndFlush(Unpooled.wrappedBuffer("test1_t".getBytes()));
//            Thread.sleep(1000);
            cf.channel().writeAndFlush(Unpooled.wrappedBuffer("test1_t".getBytes()));
            cf.channel().closeFuture().sync();
            pGroup.shutdownGracefully();
        } catch (Exception e){

        }finally {

        }


    }

}
