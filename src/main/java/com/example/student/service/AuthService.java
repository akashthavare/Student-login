package com.example.student.service;

import com.example.student.dto.LoginRequest;
import com.example.student.dto.SignupRequest;
import com.example.student.entity.Student;
import com.example.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final StudentRepository repo;

    public AuthService(StudentRepository repo) {
        this.repo = repo;
    }

    public void register(SignupRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPassword(request.getPassword());

        repo.save(student);
    }

    public boolean login(LoginRequest request) {
        Optional<Student> user = repo.findByEmail(request.getEmail());

        return user.isPresent() &&
               user.get().getPassword().equals(request.getPassword());
    }
}
