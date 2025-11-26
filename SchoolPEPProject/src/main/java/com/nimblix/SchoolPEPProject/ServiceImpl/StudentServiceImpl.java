package com.nimblix.SchoolPEPProject.ServiceImpl;

import com.nimblix.SchoolPEPProject.Model.Student;
import com.nimblix.SchoolPEPProject.Repository.StudentRepository;
import com.nimblix.SchoolPEPProject.Request.StudentLoginRequest;
import com.nimblix.SchoolPEPProject.Request.StudentRegistrationRequest;
import com.nimblix.SchoolPEPProject.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

      private final PasswordEncoder passwordEncoder;
      private final StudentRepository studentRepository;


      @Override
    public ResponseEntity<?> registerStudent(StudentRegistrationRequest request) {

        // Validate password match
        if (!request.getPassword().equals(request.getReEnterPassword())) {
            return ResponseEntity.badRequest().body("Password and Re-Enter Password do not match!");
        }

        // Encode password
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Create and save student
        Student student = new Student();
        student.setFullName(request.getFullName());
        student.setEmail(request.getEmail());
        student.setPassword(encodedPassword);

        studentRepository.save(student);

        // Return success message
        return ResponseEntity.ok("Registration Successful");
    }


	@Override
	public ResponseEntity<?> loginStudent(StudentLoginRequest request) {
		Student student = studentRepository.findByEmail(request.getEmail());
		if(student.equals(null)) {
			return ResponseEntity.badRequest().body("Entered Email is not registered");
		}
		// Encode password
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        
        if(student.getFullName().equals(request.getFullName()) && student.getPassword().equals(encodedPassword)) {
        	return ResponseEntity.ok("Login Successful");
        }
        return ResponseEntity.badRequest().body("Email Id or Password is incorrect!");
	}

}
