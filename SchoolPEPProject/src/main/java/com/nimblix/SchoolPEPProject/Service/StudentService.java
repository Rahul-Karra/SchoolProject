package com.nimblix.SchoolPEPProject.Service;

import com.nimblix.SchoolPEPProject.Request.StudentRegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    ResponseEntity<?> registerStudent(StudentRegistrationRequest studentRegistrationRequest);

    void getStudentListBySchoolId(Integer schoolId);
}
