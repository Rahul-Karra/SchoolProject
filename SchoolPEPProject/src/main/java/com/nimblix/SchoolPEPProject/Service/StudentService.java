package com.nimblix.SchoolPEPProject.Service;

import com.nimblix.SchoolPEPProject.Model.Student;
import com.nimblix.SchoolPEPProject.Request.StudentRegistrationRequest;
import com.nimblix.SchoolPEPProject.Response.StudentProfileResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    ResponseEntity<?> registerStudent(StudentRegistrationRequest studentRegistrationRequest);

    Student getStudentListByStudentId(Long studentId);

    void deleteStudent(Long studentId);

    void updateStudentDetails(Long studentId, StudentRegistrationRequest request);

    StudentProfileResponse getStudentProfile(Long studentId, String viewerEmail);

//    void updateStudentDetails(Integer studentId, StudentRegistrationRequest request);
}
