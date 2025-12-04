package com.nimblix.SchoolPEPProject.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @Entity
    @Table(name = "Comment")
    @Getter
    @Setter
    @NoArgsConstructor
    public class Comment {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String message;

        @ManyToOne
        @JoinColumn(name = "task_id")
        private Task task;

}
