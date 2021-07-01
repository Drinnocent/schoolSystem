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
@Table(name="facility")
public class Facility extends BaseEntity 
{
   
    @Column(name="facility_Name")
    private String name;
    @Column(name="facility_Location")
    private String location;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    
}
