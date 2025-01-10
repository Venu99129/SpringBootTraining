package com.JapDetailed.week3_assignment.entities;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 25)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_professors_mapping")
    private Set<Professor> professors;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_subjects_mapping")
    private Set<Subject> subjects;

    @OneToOne(mappedBy = "student" , cascade = CascadeType.ALL)
    private AdmissionRecord admissionRecord;

}
