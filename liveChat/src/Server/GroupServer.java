package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupServer {
    String[] member;
    String groupname;
    /*获取成员和群名*/
    public void getMemberAndGroupName(String[] member , String groupname){
        this.member = member;
        this.groupname = groupname;
    }
    /*获取群名*/
    public void getGroupName(String groupname){
        this.groupname = groupname;
    }
    /*建群*/
    public void makeGroup() throws SQLException {
        Connection connection = JDBC.getConnection();
        PreparedStatement preparedStatement = null;

        for (int i = 0; i < member.length; i++) {
            try {
                String sqlmakeGroup = "INSERT INTO groupship (groupname,groupmember) VALUES ("+groupname+","+member[i]+");";
                preparedStatement = connection.prepareStatement(sqlmakeGroup);
                preparedStatement.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        JDBC.close(connection,preparedStatement);
    }
    /*搜索群成员，返回成员数组*/
    public String[] searchGroupMember(String groupname) throws SQLException {
        Connection connection = JDBC.getConnection();
        PreparedStatement preparedStatement = null;
        String[] foundmember = new String[10];
        String sqlsearchMember = "SELECT * FROM groupship WHERE groupname= ?";
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sqlsearchMember);
            preparedStatement.setString(1,groupname);
            resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                foundmember[count] = resultSet.getString(3);
                count++;
            }
            } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBC.close(connection,preparedStatement,resultSet);
        }
        return foundmember;
    }
    /*群组聊天记录*/
    public void sendtogroup(String username , String groupname , String infomation) throws SQLException {
        Connection connection = JDBC.getConnection();
        PreparedStatement preparedStatement = null;
        String sqlmakefriend = "INSERT INTO "+"grouprecord"+" (groupname,username,information) VALUES('"+groupname+"','"+username+"','"+infomation+"');";
        try {
            preparedStatement = connection.prepareStatement(sqlmakefriend);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBC.close(connection,preparedStatement);
        }
    }
    public ArrayList<String> getgrouprecord(String groupname ) throws SQLException {
        Connection connection = JDBC.getConnection();
        PreparedStatement preparedStatement = null;
        String sqlgetRecord = "select username,information from "+"grouprecord where groupname = "+groupname+";";
        ResultSet resultSet = null;
        ArrayList<String> records= new ArrayList<String>();
        try {
            preparedStatement = connection.prepareStatement(sqlgetRecord);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                records.add(resultSet.getString(1)+":");
                records.add(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBC.close(connection,preparedStatement,resultSet);
        }return records;
    }
    /*删除群员*/
    public void deletegroupmember(String groupname , String groupmember) throws SQLException {
        Connection connection = JDBC.getConnection();
        PreparedStatement preparedStatement = null;
        String sqlmakefriend = "delete from groupship where groupname = '"+groupname+"' and groupmember = '"+groupmember+"';";
        try {
            preparedStatement = connection.prepareStatement(sqlmakefriend);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBC.close(connection,preparedStatement);
        }
    }
    /*删除群*/
    public void deletgroup(String groupname) throws SQLException {
        Connection connection = JDBC.getConnection();
        PreparedStatement preparedStatement = null;
        String sqlmakefriend = "delete from groupship where groupname = '"+groupname+"';";
        try {
            preparedStatement = connection.prepareStatement(sqlmakefriend);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBC.close(connection,preparedStatement);
        }
    }
}
