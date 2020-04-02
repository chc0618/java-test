package com.chc.test.java.service.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class NettyServer {

    public static void main(String[] args) {
        try{
            System.out.println("服务器启动。。。");
            //1.创建两个线程，一个负责接收客户端，一个负责
            NioEventLoopGroup pGroup = new NioEventLoopGroup();
            NioEventLoopGroup cGroup = new NioEventLoopGroup();
            //2.创建辅助类
            ServerBootstrap b = new ServerBootstrap();
            b.group(pGroup, cGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ByteBuf buf = Unpooled.copiedBuffer("_t".getBytes());
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
                            // 设置返回结果为String 类型
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new ServerHandler());
                        }
                    });
            ChannelFuture cf = b.bind(8091).sync();
            /**CloseFuture异步方式关闭*/
            cf.channel().closeFuture().sync();
            pGroup.shutdownGracefully();
            cGroup.shutdownGracefully();
        } catch (Exception e){

        }finally {

        }


    }

}
