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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

/**
 *
 * @author S2028389
 */
@Entity
@Audited
@Table(name = "subject")
public class Subject extends BaseEntity {

    @Column(name = "subject_Name")
    private String name;
    @Column(name = "subject_Code")
    private String Code;
    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Employee> employees = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }
    @Column(name = "subject_Id")
    private long subjectId;
    @Column(name = "grade_Id")
    private long gradeId;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
