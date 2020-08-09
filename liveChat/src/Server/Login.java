package Server;

import java.sql.*;


public class Login {
    /*账号密码以及其获取函数*/
    private String user ;
    private String password;
    public void getUserAndPassword(String user, String password){
        this.user = user;
        this.password = password;
    }

    /*通过sql查找*/
    public boolean login() throws SQLException {
        Connection connection = JDBC.getConnection();
        PreparedStatement preparedStatement = null;
        String sqllogin = "SELECT * FROM chatuser WHERE username= '"+this.user+"' AND password = '"+this.password+"' ;";
        ResultSet resultSet = null;
        try{
            connection = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sqllogin);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBC.close(connection,preparedStatement,resultSet);
        }
        return false;
    }

}
