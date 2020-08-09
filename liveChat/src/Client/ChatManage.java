package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatManage {
    private ChatManage(){}
    private static final ChatManage instance = new ChatManage();
    public static ChatManage getCM (){
        return instance;
    }
    ClientWindow window;
    Socket socket;
    /*传入ip、账号、密码*/
    String IP;
    String user;
    String password;
    /*定义输入输出流*/
    BufferedReader reader;
    PrintWriter writer;
    /*绑定window*/
    public void setWindow(ClientWindow window){
        this.window = window;
    }
    public void connect(String ip, String user , String password) {
        this.IP = ip;
        this.user = user;
        this.password = password;
        new Thread(){
            @Override
            public void run(){
                try{
                    /*接收ip地址传到socket中*/
                    socket = new Socket(IP,8848);
                    writer = new PrintWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream(),"UTF-8"
                            )
                    );
                    reader = new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream(),"UTF-8"
                            )
                    );
                    String line;
                    /*传账号密码，用,分割*/
                    writer.write(user + "," + password);
                    writer.flush();
                    /*收到数据输出到窗口文本区中*/
                    while((line = reader.readLine()) != null){
                        window.appendText("收到： "+line);
                    }
                    /*关闭输入输出流*/
                    writer.close();
                    reader.close();
                    writer = null;
                    reader = null;
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    /*发出输入框的数据*/
    public void send(String text) {
        if (writer != null){
            writer.write(text + "\n");
            writer.flush();
        }else {
            window.appendText("连接已断开");
        }
    }

}
