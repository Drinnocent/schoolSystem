/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import org.hibernate.envers.Audited;
import za.gov.sars.common.AddressType;

/**
 *
 * @author S2028389
 */
@Entity
@Audited
@Table(name="address")
public class Address extends BaseEntity {
    
    @Enumerated(EnumType.STRING)
    @Column(name="address_Type")
    private AddressType addressType;

  

    
    @Column(name="addressline_1")
    private String addressLine1;
    
    @Column(name="addressline_2")
    private String addressLine2;
    
    @Column(name="street")
    private String street;
    
    @Column(name="are")
    private String area;

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLIne) {
        this.addressLine1 = addressLIne;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLIne2) {
        this.addressLine2 = addressLIne2;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name="code")
    private String code;

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }
    
    
    
    
}
