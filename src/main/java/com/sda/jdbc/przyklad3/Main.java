package com.sda.jdbc.przyklad3;

import com.sda.jdbc.util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    private static final int BUILDING_NO_INDEX = 1;
    private static final int POSTAL_CODE_INDEX = 2;

    public static void main(String[] args) {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        String sqlUpdate = "Update Address "+
                " Set ADD_BUILDING_NO = ?"+
                " WHERE ADD_POSTAL_CODE = ?";

        try(PreparedStatement preparedStatement = databaseConnection
                .getConnection()
                .prepareStatement(sqlUpdate)) {
            String buildingNumber = "132";
            String postalCode = "79677";
            preparedStatement.setString(BUILDING_NO_INDEX, buildingNumber);
            preparedStatement.setString(POSTAL_CODE_INDEX, postalCode);

            int rowsUpdated = preparedStatement.executeUpdate();
            System.out.println("zaktualizowano "+ rowsUpdated + " wierszy");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
