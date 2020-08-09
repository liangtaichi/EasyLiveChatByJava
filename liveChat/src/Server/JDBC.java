package Server;

import javax.xml.transform.Result;
import java.sql.*;

public class JDBC {
    public Connection connection;
    public PreparedStatement preparedStatement;
    public Result result;
    private static final String driverClass = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String user = "yourroot";
    private static final String password = "yourpassword";
    /*注册驱动*/
    static {
        try{
            Class.forName(driverClass);
        }catch (ClassNotFoundException e){

        }
    }
    /*获取连接*/
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection( url , user , password);
        return connection;
    }
    /*关闭重载*/
    public static void close(Connection connection , Statement statement)  {
        if (statement != null){
            try {
                statement.close();
            }catch (SQLException e){
            }
        }
        if (connection != null){
            try {
                connection.close();
            }catch (SQLException e){
            }
    }}
    /*关闭资源*/
    public static void close(Connection connection , Statement statement , ResultSet resultSet){
        if (resultSet != null){
            try{
                resultSet.close();
            }catch (SQLException e){
            }
        }
        if (statement != null){
            try {
                statement.close();
            }catch (SQLException e){
            }
        }
        if (connection != null){
            try {
                connection.close();
            }catch (SQLException e){
            }
        }
    }
}
