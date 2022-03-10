package com.sda.jdbc.dao;

import com.sda.jdbc.przyklad4.Country;
import com.sda.jdbc.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl implements CountryDao {

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

    private Country mapResultSetToCountry(ResultSet resultSet) throws SQLException {
        return new Country(resultSet.getInt("CO_ID"), resultSet.getString("CO_NAME"), resultSet.getString("CO_ALIAS"));
    }

    private Connection getConnection() {
        return new DatabaseConnection().getConnection();
    }

}
