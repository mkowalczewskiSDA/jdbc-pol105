package com.sda.jdbc.dao;

import com.sda.jdbc.przyklad4.Country;
import com.sda.jdbc.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl implements CountryDao {

    private static final int CO_ID_PARAMETER_INDEX = 1;
    private static final int CO_NAME_PARAMETER_INDEX = 1;
    private static final int CO_ALIAS_PARAMETER_INDEX = 2;

    private static final int CO_NAME_PARAMETER_UPDATE = 1;
    private static final int CO_ALIAS_PARAMETER_UPDATE = 2;
    private static final int CO_ID_PARAMETER_UPDATE = 3;

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

    @Override
    public void addNewCountry(Country country) {
        String insertQuery = "Insert into Country (CO_NAME, CO_ALIAS) values (?, ?)";

        try(PreparedStatement preparedStatement = getConnection().prepareStatement(insertQuery)) {
            preparedStatement.setString(CO_NAME_PARAMETER_INDEX, country.getName());
            preparedStatement.setString(CO_ALIAS_PARAMETER_INDEX, country.getAlias());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean deleteCountry(int id) {
        if (findById(id) != null) {
            String deleteQuery = "DELETE FROM Country where CO_ID = ?";
            try(PreparedStatement preparedStatement = getConnection().prepareStatement(deleteQuery)) {
                preparedStatement.setInt(CO_ID_PARAMETER_INDEX, id);
                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void updateCountry(Country country) {
        if (country.getId() != null && findById(country.getId()) != null) {
            String updateQuery = "Update Country " +
                            "SET CO_NAME = ?, " +
                            "CO_ALIAS = ? " +
                            "WHERE CO_ID = ?";

            try(PreparedStatement preparedStatement = getConnection().prepareStatement(updateQuery)) {
                preparedStatement.setString(CO_NAME_PARAMETER_UPDATE, country.getName());
                preparedStatement.setString(CO_ALIAS_PARAMETER_UPDATE, country.getAlias());
                preparedStatement.setInt(CO_ID_PARAMETER_UPDATE, country.getId());

                preparedStatement.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else {
            addNewCountry(country);
        }
    }

    private Country mapResultSetToCountry(ResultSet resultSet) throws SQLException {
        return new Country(resultSet.getInt("CO_ID"), resultSet.getString("CO_NAME"), resultSet.getString("CO_ALIAS"));
    }

    private Connection getConnection() {
        return new DatabaseConnection().getConnection();
    }

}
