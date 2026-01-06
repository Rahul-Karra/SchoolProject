package com.nimblix.SchoolPEPProject.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileResponse {

    private StudentData student;
    private ParentData parent;
    private ViewedByData viewedBy;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentData {
        private Long id;
        private String fullName;
        private String email;
        private Long classId;
        private String section;
        private String status;
        private String createdTime;
        private Long schoolId;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ParentData {
        private Long parentId;
        private String fullName;
        private String emailId;
        private String contactNumber;
        private String address;
        private String role;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ViewedByData {
        private String fullName;
        private String designation;
        private String roleName;
    }
}
