package com.zmm;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @author zhongzuoming <zhongzuoming, 1299076979@qq.com>
 * @version v1.0
 * @Description
 * @encoding UTF-8
 * @date 2019-11-27
 * @time 09:13
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
public class DiscardClient {
    public static void main(String[] args) {
        //创建发送数据线程池
        EventLoopGroup group = new NioEventLoopGroup();
        //设置客户端引导类
        Bootstrap bootstrap = new Bootstrap();


        bootstrap.group(group).
                //设置selector模型
                channel(NioSocketChannel.class).
                //添加处理连接请求handler
                handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
                        ch.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                        ch.pipeline().addLast(new DiscardClientHandler());
                    }
                });

        //设置请求连接配置
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1",8088));

        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()){
                        System.out.println("connn estable");
                    }else {
                        System.out.println("connn faild");
                        future.cause().printStackTrace();
                    }
            }
        });


    }
}
