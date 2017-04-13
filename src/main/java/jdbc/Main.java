package jdbc;

import java.sql.*;

public class Main {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/jdbc_example";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "";


    public static void main(String[] args) throws SQLException {
        String createTable = "CREATE TABLE dbuser(" +
                " user_id INT PRIMARY KEY," +
                " first_name VARCHAR (50) UNIQUE NOT NULL," +
                " last_name VARCHAR (50) NOT NULL," +
                " created_on TIMESTAMP NOT NULL" +
                ");";
        String insertRow =  "INSERT INTO dbuser " +
                            "(user_id, first_name, last_name, created_on)" +
                            " VALUES (3, 'Egor', 'Egorov', '2017-04-12' )";
        String select = "SELECT user_id, first_name, last_name FROM dbuser";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        System.out.println("Get connection");
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        System.out.println("Create statement");
        statement = connection.createStatement();

//        statement.execute(createTable); //Создание таблицы
//        statement.execute(insertRow); //Вставляем запись

        resultSet = statement.executeQuery(select); //получаем записи

        System.out.println("Get ResultSet");
        while (resultSet.next()){
            System.out.print(resultSet.getInt("user_id") + " ");
            System.out.print(resultSet.getString("first_name") + " ");
            System.out.println(resultSet.getString("last_name"));
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
