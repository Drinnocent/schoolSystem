/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

/**
 *
 * @author S2028389
 */
@Entity
@Audited
@Table(name="school")
public class School extends BaseEntity {
    @Column(name="school_name")
    private String name;
    @Column(name="school_website")
    private String schoolWebsite;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolWebsite() {
        return schoolWebsite;
    }

    public void setSchoolWebsite(String schoolWebsite) {
        this.schoolWebsite = schoolWebsite;
    }
    
    
}
