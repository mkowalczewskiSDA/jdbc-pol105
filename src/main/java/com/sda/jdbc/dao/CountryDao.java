package com.sda.jdbc.dao;

import com.sda.jdbc.przyklad4.Country;

import java.util.List;

public interface CountryDao {

    List<Country> getAll();
    //jeżeli nie znajdzie - zwróć null
    Country findById(int id);
    void addNewCountry(Country country);
    //probujemy usunac kraj, jeżeli się udało to true, jeżeli taki kraj nie istnieje
    //to false - zweryfikuj czy kraj istnieje zanim, spróbujesz go usunąć
    boolean deleteCountry(int id);

}
