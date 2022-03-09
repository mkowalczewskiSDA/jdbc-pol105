package com.sda.jdbc.przyklad4;

import com.sda.jdbc.util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Przyklad4 {
    public static void main(String[] args) {

        String sqlSelect = "Select * from Address" +
                " join Country on add_co_id = co_id";

        DatabaseConnection databaseConnection = new DatabaseConnection();

        try(Statement statement = databaseConnection.getConnection().createStatement()) {

            ResultSet resultSet = statement.executeQuery(sqlSelect);
            List<Address> addresses = new ArrayList<>();

            while (resultSet.next()) {
                addresses.add(mapAddress(resultSet));
            }

            addresses.forEach(System.out::println);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    private static Address mapAddress(ResultSet resultSet) throws SQLException {
        return new Address(
                resultSet.getInt("ADD_ID"),
                resultSet.getString("ADD_STREET"),
                resultSet.getString("ADD_BUILDING_NO"),
                resultSet.getString("ADD_APARTAMENT_NO"),
                resultSet.getString("ADD_CITY"),
                resultSet.getString("ADD_POSTAL_CODE"),
                mapCountry(resultSet));
    }

    private static Country mapCountry(ResultSet resultSet) throws SQLException {
        return new Country(
                resultSet.getInt("CO_ID"),
                resultSet.getString("CO_NAME"),
                resultSet.getString("CO_ALIAS")
        );
    }
}
