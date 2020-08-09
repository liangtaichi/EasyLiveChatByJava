package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListen extends Thread{
    @Override
    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(8848);
            while (true){
                Socket socket = serverSocket.accept();
                /*连接客户端传送到新线程*/
                ChatServer chatServer = new ChatServer();
                chatServer.ChatServer(socket);
                chatServer.start();
                /*整合到服务器管理集合*/
                ServerManager.getServerManager().add(chatServer);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
