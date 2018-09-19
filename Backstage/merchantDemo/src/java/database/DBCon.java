package java.database;

import java.sql.*;

public class DBCon {
        private static Statement statement;
        private static Connection dbConn;

        public static ResultSet getResultSet(String sql){
            try{
                ResultSet resultSet= statement.executeQuery(sql);
                return resultSet;
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(e);
                return null;
            }
        }
        public static boolean executeQuery(String sql)throws SQLException {
            try{
                statement.execute(sql);
                return true;
            }catch (Exception e){
                System.out.println(e);
                throw e;
            }
        }
    static {
            String driverName="com.mysql.cj.jdbc.Driver";
            String dbURL="jdbc:mysql://193.112.44.141:3306/huaqi?useUnicode=true&characterEncoding=utf8&useSSL=false&useAffectedRows=true&serverTimezone=Hongkong&autoReconnect=true";
            String userName="huaqi";
            String userPwd="huaqi";
            try
            {
                Class.forName(driverName);
                dbConn= DriverManager.getConnection(dbURL,userName,userPwd);
                statement=dbConn.createStatement();
                System.out.println("连接数据库成功");
            }
            catch(Exception e)
            {
                e.printStackTrace();
                System.out.print("连接失败");
            };
        }

}
