package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChatHistory {
    String sender = null;
    String[] infomation = null;
    String receiver = null;
    /*获取发送者和接受者*/
    public void getSenderAndReceiver(String sender , String receiver){
        this.sender = sender;
        this.receiver = receiver;
    }
    /*存入发送聊天记录*/
    public void pushSendRecord(String username ,String infomation) throws SQLException {
        Connection connection = JDBC.getConnection();
        PreparedStatement preparedStatement = null;
        String sqlmakefriend = "INSERT INTO "+username+"Record"+" (SendorReceive,information) VALUES('send','"+"to:"+this.receiver+": "+infomation+"');";
        try {
            preparedStatement = connection.prepareStatement(sqlmakefriend);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBC.close(connection,preparedStatement);
        }
    }
    /*存入接收聊天记录*/
    public void  pushReceiveRecord(String username , String infomation) throws SQLException {
        Connection connection = JDBC.getConnection();
        PreparedStatement preparedStatement = null;
        String sqlmakefriend = "INSERT INTO "+username+"Record"+" (SendorReceive,information) VALUES('receive','"+infomation+"');";
        try {
            preparedStatement = connection.prepareStatement(sqlmakefriend);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBC.close(connection,preparedStatement);
        }
    }
    /*查询私聊内容*/
    public ArrayList<String> searchPrivateRecord(String user) throws SQLException {
        Connection connection = JDBC.getConnection();
        PreparedStatement preparedStatement = null;
        String sqlgetRecord = "select information from "+user+"record";
        ResultSet resultSet = null;
        ArrayList<String> records= new ArrayList<String>();
        try {
            preparedStatement = connection.prepareStatement(sqlgetRecord);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                records.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBC.close(connection,preparedStatement,resultSet);
        }return records;
    }
}

