package com.sda.jdbc.zadanie3;

import com.sda.jdbc.dao.CountryDao;
import com.sda.jdbc.dao.CountryDaoImpl;

public class Main {
    public static void main(String[] args) {
        CountryDao countryDao = new CountryDaoImpl();
        System.out.println(countryDao.getAll());
    }
}
