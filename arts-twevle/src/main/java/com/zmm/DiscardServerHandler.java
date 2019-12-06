package com.zmm;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhongzuoming <zhongzuoming, 1299076979@qq.com>
 * @version v1.0
 * @Description
 * @encoding UTF-8
 * @date 2019-11-14
 * @time 08:55
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
public class DiscardServerHandler extends SimpleChannelInboundHandler<String> {


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //管道被连接上
        System.out.println("====channelInactive");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //读取客户端发送过来数据
        System.out.println("收到客户端内容"+msg);
        ctx.write("服务端发送消息测试");
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 异常处理关闭管道连接
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //读取完客服端发送数据 清除缓存
        ctx.flush();
    }
}
