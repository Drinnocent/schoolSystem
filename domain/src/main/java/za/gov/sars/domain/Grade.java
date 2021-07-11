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
@Table(name = "grade")
public class Grade extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "designation")
    private String designation;
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Student student;
    @OneToMany(mappedBy = "grade", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    List<Subject> subject = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Facility facility;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private School school;
    @OneToMany(mappedBy = "grade", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Assessment> assessments = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

}
