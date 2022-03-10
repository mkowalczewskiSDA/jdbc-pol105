package com.sda.jdbc.zadanie3;

import com.sda.jdbc.dao.CountryDao;
import com.sda.jdbc.dao.CountryDaoImpl;
import com.sda.jdbc.przyklad4.Country;

public class Main {
    public static void main(String[] args) {
        CountryDao countryDao = new CountryDaoImpl();
        countryDao.getAll().forEach(System.out::println);
        System.out.println(countryDao.findById(3));
        countryDao.addNewCountry(new Country("Ukaine", "UA"));
        System.out.println(countryDao.deleteCountry(6));
        //Country country = countryDao.findById(1);
        //country.setName("Polska");
        countryDao.updateCountry(new Country("Polska", "PL"));
    }
}
