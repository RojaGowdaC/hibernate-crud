package com.hibernate.main;

import com.hibernate.controller.HibernateController;

public class MainActivity {

    public static void main(String[] args) {
        HibernateController hibernateController = new HibernateController();
        hibernateController.performOperations();
    }
}