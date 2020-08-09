package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendsServer {
    String username;
    String friendname;
    /*获取用户名*/
    public void getusername(String username){
        this.username = username;
    }
    public void getusernameAndfriendname(String username , String friendname){
        this.username = username;
        this.friendname = friendname;
    }
    /*获取好友列表*/
    public String[] getFriends() throws SQLException {
        Connection connection = JDBC.getConnection();
        PreparedStatement preparedStatement = null;
        String sqlgetfrends = "SELECT * FROM frend WHERE frendA= ?";
        ResultSet resultSet = null;
        int count =0 ;
        String[] foundfriends = new String[10];
        try {
            preparedStatement = connection.prepareStatement(sqlgetfrends);
            preparedStatement.setString(1,this.username);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                foundfriends[count] = resultSet.getString(3);
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBC.close(connection,preparedStatement,resultSet);
        }
        return foundfriends;
    }
    /*添加好友*/
    public void makefriend() throws SQLException {
        Connection connection = JDBC.getConnection();
        PreparedStatement preparedStatement = null;
        String sqlmakefriend = "INSERT INTO frend(frendA,frendB) VALUES('"+this.username+"','"+this.friendname+"');";
        try {
            preparedStatement = connection.prepareStatement(sqlmakefriend);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBC.close(connection,preparedStatement);
        }
    }
    public void  delefriend(String username, String friendname) throws SQLException {
        Connection connection = JDBC.getConnection();
        PreparedStatement preparedStatement = null;
        String sqlmakefriend = "delete from frend where frendA = '"+username+"' and frendB = '"+friendname+"';";
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
