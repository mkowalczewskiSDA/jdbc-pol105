package com.sda.jdbc.zadanie1;

import com.sda.jdbc.util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    private static final int FIRST_NAME_PARAMETER_INDEX = 1;
    private static final int LAST_NAME_PARAMETER_INDEX = 2;

    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();

        String query = "Select USR_FIRSTNAME FN, USR_LASTNAME LN, USR_EMAIL E from User where USR_FIRSTNAME = ? and USR_LASTNAME = ?;";
        String nameParameter = "Jan";
        String lastNameParameter = "Kowalski";

        try(PreparedStatement preparedStatement = databaseConnection
                .getConnection()
                .prepareStatement(query)) {

            preparedStatement.setString(FIRST_NAME_PARAMETER_INDEX, nameParameter);
            preparedStatement.setString(LAST_NAME_PARAMETER_INDEX, lastNameParameter);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("FN"));
                System.out.println(resultSet.getString("LN"));
                System.out.println(resultSet.getString("E"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
