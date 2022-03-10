package com.sda.jdbc.dao;

import com.sda.jdbc.przyklad4.Country;
import com.sda.jdbc.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl implements CountryDao {

    private static final int CO_ID_PARAMETER_INDEX = 1;

    @Override
    public List<Country> getAll() {
        List<Country> countries = new ArrayList<>();

        String sqlSelect = "SELECT * FROM Country";

        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                countries.add(mapResultSetToCountry(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return countries;
    }

    @Override
    public Country findById(int id) {
        String findByIdQuery = "Select * from Country where CO_ID = ?";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(findByIdQuery)) {
            preparedStatement.setInt(CO_ID_PARAMETER_INDEX, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToCountry(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Country mapResultSetToCountry(ResultSet resultSet) throws SQLException {
        return new Country(resultSet.getInt("CO_ID"), resultSet.getString("CO_NAME"), resultSet.getString("CO_ALIAS"));
    }

    private Connection getConnection() {
        return new DatabaseConnection().getConnection();
    }

}
