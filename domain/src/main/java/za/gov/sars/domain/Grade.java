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
import org.hibernate.annotations.ManyToAny;
import org.hibernate.envers.Audited;

/**
 *
 * @author S2028389
 */
@Entity
@Audited
@Table(name="grade")
public class Grade extends BaseEntity{
    @Column(name="name")
    private String name;
    @Column(name="designation")
    private String designation;
   //@ManyToMany(mappedBy="grades",cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
    @ManyToMany(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
   private List<Student> students=new ArrayList<>();   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    
    
    
}
