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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.envers.Audited;
import za.gov.sars.common.SystemUserType;

/**
 *
 * @author S2028389
 */
@Entity
@Audited
@Table(name = "employee")
public class Employee extends Person {

    @Column(name = "employee_Id")
    private String employeeId;
    @Column(name = "sace_RegNumber")
    private String saceRegNumber;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "employee_grade", joinColumns = {
        @JoinColumn(name = "employee_id")}, inverseJoinColumns = {
        @JoinColumn(name = "grade_id")})
    private List<Grade> grades = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "employee_subject", joinColumns = {
        @JoinColumn(name = "employee_id")}, inverseJoinColumns = {
        @JoinColumn(name = "subject_id")})
    private List<Subject> subjects = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "employeeType")
    private SystemUserType employeeType;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getSaceRegNumber() {
        return saceRegNumber;
    }

    public void setSaceRegNumber(String saceRegNumber) {
        this.saceRegNumber = saceRegNumber;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<Subject> getSubject() {
        return subjects;
    }

    public void setSubject(List<Subject> subject) {
        this.subjects = subject;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public SystemUserType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(SystemUserType employeeType) {
        this.employeeType = employeeType;
    }

    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

}
