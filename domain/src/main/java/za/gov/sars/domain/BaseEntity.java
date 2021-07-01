/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.envers.Audited;

/**
 *
 * @author S2028389
 */
@MappedSuperclass
public class BaseEntity  implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id",nullable=false,unique=true,updatable=true,insertable=false)
    private Long id;
    @Audited
    @Column(name="created_by",updatable=false,nullable=false,length=50)
    private String createdBy;
    @Audited
    @Column(name="created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Audited
    @Column(name="updated_by",length=50)
    private String  updateBy;
    @Audited
    @Column(name="updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate(){
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }


    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdatedDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    
    
    
}
