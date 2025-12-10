package com.nimblix.SchoolPEPProject.Service;

import com.nimblix.SchoolPEPProject.Request.TeacherRegistrationRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface TeacherService {
    Map<String, String> registerTeacher(TeacherRegistrationRequest request);
}
