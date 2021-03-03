package fr.ensta.BDD;

import java.sql.*;

public class ServerBDD {
    private static Connection connect = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static String url = "jdbc:mysql://localhost:3306/EnstarDesktop";
    private static String user = "root", pass = "";

    public static void main(String[] args) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connect = DriverManager.getConnection(url, user, pass);
                statement = connect.createStatement();
                resultSet = statement.executeQuery("select * from users");
                while (resultSet.next()){
                    String username=resultSet.getString(1);
                    String password=resultSet.getString(2);
                    System.out.println(username+" "+password);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }
}