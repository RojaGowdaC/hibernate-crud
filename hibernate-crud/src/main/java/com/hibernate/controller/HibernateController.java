package com.hibernate.controller;

import com.hibernate.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class HibernateController {
    protected SessionFactory sessionFactory;
    private void setup() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    private void exit() {
        sessionFactory.close();
    }

    private void create() {
        Student student = new Student();
        student.setName("Roja");

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(student);

        session.getTransaction().commit();
        session.close();
    }

    private void read() {
        Session session = sessionFactory.openSession();
        System.out.println(session.createQuery("FROM student").list());
        session.close();
    }

    private void update() {
        Student student = new Student();
        student.setName("Roja Gowda");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Student oldStudent = (Student) session.load(Student.class, 1);
        oldStudent.setName(student.getName());
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + oldStudent);
    }

    private void delete() {
        Student student = new Student();
        student.setId(2);
        @SuppressWarnings("unchecked")
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(student);

        session.getTransaction().commit();
        session.close();
    }
    public void performOperations() {
        Scanner sc = new Scanner(System.in);
        setup();
        while(true) {
            int option;
            System.out.println("Enter your choice : ");
            System.out.println("1. Create \n 2. read\n 3. Update\n 4. Delete");
            option = sc.nextInt();
            switch (option) {
                case 1:
                        create();
                        break;
                case 2:
                     read();
                     break;
                case 3:
                        update();
                        break;
                case 4:
                        delete();
                        break;
                default:
                        exit();
                        return;
            }
        }
    }
}
