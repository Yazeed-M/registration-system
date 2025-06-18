package com.project.registration_system.components.user.dtos;

public class MessageDto {
        private String courseCode;
        private String courseName;
        private Long userId;

        public MessageDto(){}

        public MessageDto(String courseCode, String courseName, Long userId){
            this.courseCode= courseCode;
            this.courseName=courseName;
            this.userId=userId;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public void setCourseCode(String courseCode) {
            this.courseCode = courseCode;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
}
