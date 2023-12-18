package dev.service;

import dev.domain.Student;
import dev.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
public List<Student> getAllStudents() throws SQLException {
    return studentRepository.getAll();
}
    public Student getOne(int id) throws SQLException{
        return studentRepository.get(id);
    }

    public void add(Student student) throws SQLException {
        studentRepository.save(student);
    }
    public void edit(Student student) throws SQLException{
         studentRepository.edit(student);
    }
    public void delete(int id) throws SQLException{
        studentRepository.delete(id);
    }

}
