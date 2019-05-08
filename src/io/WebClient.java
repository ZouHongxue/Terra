package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author: zouhongxue
 * @Date: 2019/4/22 4:40 PM
 */
public class WebClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 3333));
        ByteBuffer writeBuffer = ByteBuffer.allocate(128);
        ByteBuffer readBuffer = ByteBuffer.allocate(128);
        writeBuffer.put("hello world".getBytes());
        writeBuffer.flip();

        while (true) {
            writeBuffer.rewind();
            socketChannel.write(writeBuffer);
            readBuffer.clear();
            socketChannel.read(readBuffer);
            readBuffer.flip();
            System.out.println("收到的数据" + new String(readBuffer.array()));
        }
    }
}
