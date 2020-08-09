package Server;

import java.sql.SQLException;
import java.util.Vector;

public class ServerManager {
    /*将不同客户端变成一个个单例*/
    private void ServerManager(){}
    private static final ServerManager serverManager = new ServerManager();
    public static ServerManager getServerManager(){
        return serverManager;
    }
    /*将不同客户端放入vector容器中*/
    Vector <ChatServer> vector = new Vector<ChatServer>();
    public void add(ChatServer chatServer){
        vector.add(chatServer);
    }
    /*断开连接*/
    public void remove(ChatServer chatServer){
        vector.remove(chatServer);
    }
    /*私聊*/
    public void privateChat(ChatServer chatServer , String out , String receiver) throws SQLException {
        for (int i = 0; i < vector.size() ; i++) {
            ChatServer chatServer1 = vector.get(i);
            /*用户名符合时发送*/
            if (chatServer1.username.equals(receiver)){
                ChatHistory chatHistory = new ChatHistory();
                chatServer1.out(chatServer.username+": "+out);
                chatHistory.pushReceiveRecord(receiver,"from:"+chatServer.username+":"+out);
            }
        }
    }
    public void showstate(ChatServer chatServer){
        for (int i = 0; i < vector.size(); i++) {
            ChatServer chatServer1 = vector.get(i);
            /*在线时发送用户名+在线*/
            if (!chatServer.equals(chatServer1)){
                chatServer.out(chatServer1.username+"在线");
            }
        }
    }
}
