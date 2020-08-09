package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;


public class ChatServer extends Thread{
    Socket socket;
    String username;


    /*获取socket*/
    public void ChatServer(Socket socket){
        this.socket = socket;

    }
    /*得到输出流*/
    public void out(String out){
        try{
            socket.getOutputStream().write((out+"\n").getBytes("UTF-8"));
        }catch (IOException e){
            ServerManager.getServerManager().remove(this);
            e.printStackTrace();
        }
    }
    @Override
    public void run(){
        /*连接到服务器*/
        out("您已连接服务器");
        try{
            /*获取socket输入流*/
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream(),"UTF-8"
                    )
            );
            String line = null;
            //String username = null;
            /*验证登陆*/
            /*if ((*/line = bufferedReader.readLine();/*) == null){*/
                String[] login = line.split(",");
                Login login1 = new Login();
                login1.getUserAndPassword(login[0],login[1]);
                if (!login1.login()){
                    this.run();
                }
                    this.username = login[0];
            //}
            /*登陆成功*/
            out("您已登陆");
            /*根据输入内容执行操作*/
            while ((line = bufferedReader.readLine()) != null){
                String[] information = line.split("\\|");
                if (information[0].equals("makegroup")){
                    String[] member = information[1].split(",");
                    String groupname = information[2];
                    GroupServer groupServer = new GroupServer();
                    groupServer.getMemberAndGroupName(member,groupname);
                    groupServer.makeGroup();
                    out("您已建群,群名： " + groupname + " ,群成员: " + information[1] +"\n");
                }
                else if (information[0].equals("searchgroupmember") ){
                    String groupname = information[1];
                    GroupServer groupServer = new GroupServer();
                    groupServer.getGroupName(groupname);
                    String[] member;
                    member = groupServer.searchGroupMember(groupname);
                    out("群名: " + groupname);
                    out("成员: ");
                    for (int i = 0; i < member.length; i++) {
                        out(member[i]);
                    }
                }
                else if (information[0].equals("sendto")){
                    String[] userAndInf = information[1].split(":");
                    ChatHistory chatHistory = new ChatHistory();
                    ServerManager.getServerManager().privateChat(this,userAndInf[1],userAndInf[0]);
                    chatHistory.getSenderAndReceiver(this.username , userAndInf[0]);
                    chatHistory.pushSendRecord(this.username,userAndInf[1]);
                }
                else if (information[0].equals("searchfriends")){
                    FriendsServer friendsServer = new FriendsServer();
                    friendsServer.getusername(this.username);
                    String[] friends;
                    friends = friendsServer.getFriends();
                    out("好友：");
                    for (int i = 0; i < friends.length; i++) {
                        if (!friends[i].equals(null)) {
                            out(friends[i]);
                        }
                    }
                }
                else if (information[0].equals("makefriends")){
                    FriendsServer friendsServer = new FriendsServer();
                    friendsServer.getusernameAndfriendname(this.username,information[1]);
                    friendsServer.makefriend();
                    out("已添加好友！");
                }
                else if (information[0].equals("showrecord")){
                    ChatHistory chatHistory = new ChatHistory();
                    ArrayList<String> record =chatHistory.searchPrivateRecord(this.username);
                    int size = record.size();
                    String[] rec = (String[])record.toArray(new String[size]);
                    out("聊天记录：");
                    for (int i = 0; i < rec.length; i++) {
                        out(rec[i]);
                    }
                }
                else if (information[0].equals("sendtogroup")){
                    String[] gui = information[1].split(":");
                    GroupServer groupServer = new GroupServer();
                    groupServer.sendtogroup(this.username,gui[0],gui[1]);
                    out("已发送到群组："+gui[0]);
                }
                else if (information[0].equals("showgrouprecord")){
                    GroupServer groupServer = new GroupServer();
                    ArrayList<String>grouprecord = groupServer.getgrouprecord(information[1]);
                    int size = grouprecord.size();
                    String[] rec = (String[])grouprecord.toArray(new String[size]);
                    out("群组"+information[1]+"聊天记录：");
                    for (int i = 0; i < rec.length; i++) {
                        out(rec[i]);
                    }
                }
                else if (information[0].equals("deletefriend")){
                    FriendsServer friendsServer = new FriendsServer();
                    friendsServer.delefriend(this.username,information[1]);
                    out("已删除好友："+information[1]);
                }
                else if (information[0].equals("deletegroupmember")){
                    String[] groupnameandmember = information[1].split(":");
                    GroupServer groupServer = new GroupServer();
                    groupServer.deletegroupmember(groupnameandmember[0],groupnameandmember[1]);
                    out("群成员："+groupnameandmember[1]+"已被删除");
                }
                else if (information[0].equals("deletegroup")){
                    GroupServer groupServer = new GroupServer();
                    groupServer.deletgroup(information[1]);
                    out("群组："+information[1]+"已被删除");
                }
                else if (information[0].equals("state")){
                    ServerManager.getServerManager().showstate(this);
                }
            }
            /*收尾*/
            bufferedReader.close();
            ServerManager.getServerManager().remove(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
