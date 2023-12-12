package dev.repository;

import dev.domain.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class StudentRepository {

    private SessionFactory sessionFactory;

    public StudentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public List<Student> getAll() throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query<Student> userQuery = session.createQuery("from Student", Student.class);
        return userQuery.getResultList();
    }
    public Student get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Student.class, id);
    }
    public void edit(Student student) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
            session.update(student);
    }

    public void delete(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Student student = get(id);
        session.delete(student);
    }

}
