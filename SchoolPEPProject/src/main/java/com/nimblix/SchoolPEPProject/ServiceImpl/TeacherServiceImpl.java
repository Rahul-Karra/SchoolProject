package com.nimblix.SchoolPEPProject.ServiceImpl;

import com.nimblix.SchoolPEPProject.Constants.SchoolConstants;
import com.nimblix.SchoolPEPProject.Model.Role;
import com.nimblix.SchoolPEPProject.Model.Teacher;
import com.nimblix.SchoolPEPProject.Model.User;
import com.nimblix.SchoolPEPProject.Repository.RoleRepository;
import com.nimblix.SchoolPEPProject.Repository.TeacherRepository;
import com.nimblix.SchoolPEPProject.Repository.UserRepository;
import com.nimblix.SchoolPEPProject.Request.TeacherRegistrationRequest;
import com.nimblix.SchoolPEPProject.Service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Map<String, String> registerTeacher(TeacherRegistrationRequest request) {

        Map<String, String> response = new HashMap<>();

        if (request.getFirstName() == null || request.getFirstName().isBlank() ||
                request.getEmail() == null || request.getEmail().isBlank() ||
                request.getPassword() == null || request.getPassword().isBlank()) {

            response.put(SchoolConstants.MESSAGE, "Missing required fields (firstName, email, password)");
            return response;
        }

        if (userRepository.existsByEmailId(request.getEmail())) {
            response.put(SchoolConstants.MESSAGE, "Email already registered");
            return response;
        }

        if (teacherRepository.existsByEmailId(request.getEmail())) {
            response.put(SchoolConstants.MESSAGE, "Teacher already exists with this email");
            return response;
        }

        String fullName = request.getPrefix() + " " +
                request.getFirstName() + " " +
                (request.getLastName() != null ? request.getLastName() : "");

        User user = new User();
        user.setFullName(fullName.trim());
        user.setEmailId(request.getEmail());
        user.setPassword(request.getPassword());
        user.setDesignation(SchoolConstants.TEACHER_ROLE);
        user.setStatus(SchoolConstants.ACTIVE);
        user.setMobile(null);
        user.setIsLogin(false);

        Role teacherRole = roleRepository.findByRoleName(SchoolConstants.TEACHER_ROLE);
        user.setRole(teacherRole);

        userRepository.save(user);

        Teacher teacher = new Teacher();
        teacher.setFullName(fullName.trim());
        teacher.setEmailId(request.getEmail());
        teacher.setSchoolId(1L); // TODO: pass schoolId from admin login

        teacherRepository.save(teacher);

        response.put(SchoolConstants.MESSAGE, "Teacher Registered Successfully!");
        return response;
    }
}
