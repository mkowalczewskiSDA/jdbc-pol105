package com.sda.jdbc.zadanie2;

import com.sda.jdbc.util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    private static final int CATEGORY_NAME_INDEX = 1;

    public static void main(String[] args) {
        String insert = "Insert into Category (CAT_NAME) values (?)";

        DatabaseConnection databaseConnection = new DatabaseConnection();

        try(PreparedStatement preparedStatement = databaseConnection
                .getConnection()
                .prepareStatement(insert)) {

            preparedStatement.setString(CATEGORY_NAME_INDEX, "Cars");
            int rowsCount = preparedStatement.executeUpdate();

            System.out.println("dodano "+ rowsCount + " wierszy");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
