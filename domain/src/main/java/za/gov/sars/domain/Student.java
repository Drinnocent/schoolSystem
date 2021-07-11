/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.envers.Audited;


/**
 *
 * @author S2028389
 */
@Entity
@Audited
@Table(name = "student")
public class Student extends Person {

    @Column(name = "Student_Number")
    private String studentNumber;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "student_subject", joinColumns = {
        @JoinColumn(name = "student_id")}, inverseJoinColumns = {
        @JoinColumn(name = "subject_id")})
    private List<Subject> subjects = new ArrayList<>();
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Grade grade;

    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Assessment> assessments = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Attendance> attendances = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private School school;
   

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

}
