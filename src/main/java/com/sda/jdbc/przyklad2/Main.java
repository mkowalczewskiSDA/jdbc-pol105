package com.sda.jdbc.przyklad2;

import com.sda.jdbc.util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    private static int CITY_INDEX = 1;

    public static void main(String[] args) {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        String query = "Select * from Address where add_city = ?";


        try(PreparedStatement preparedStatement = databaseConnection
                            .getConnection()
                            .prepareStatement(query)
        ) {

            preparedStatement.setString(CITY_INDEX, "Bydgoszcz");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("ADD_STREET"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
