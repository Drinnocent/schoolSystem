/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import za.gov.sars.domain.Facility;
import za.gov.sars.domain.Grade;
import za.gov.sars.domain.Subject;
import za.gov.sars.service.FacilityServiceLocal;
import za.gov.sars.service.GradeServiceLocal;
import za.gov.sars.service.SubjectServiceLocal;

/**
 *
 * @author S2028389
 */
@ManagedBean
@ViewScoped
public class FacilityBean extends BaseBean {
    @Autowired
    private FacilityServiceLocal facilityService;
    @Autowired
    private GradeServiceLocal gradeService;
    
    @Autowired
    private SubjectServiceLocal subjectService;
    
    private Facility facility;
    
     private List<Facility> facilities=new ArrayList<>();
     private List<Grade> grades=new ArrayList<>();
     private List<Subject> subjects=new ArrayList<>();
    
    @PostConstruct
    public void init()
    {
        this.resetView(false).setList(true);
        facilities=facilityService.listAll();
        grades=gradeService.listAll();
        subjects=subjectService.listAll();
    }
    
    
    
    public void addOrUpdate(Facility fac){
        this.resetView(false).setAdd(true);
        if(fac != null){
           // fac.setUpdateBy(getActiveUser().getFirstName() + " " + getActiveUser().getLastName());
            fac.setUpdatedDate(new Date());
            facility = fac;
        }
        else{
            facility = new Facility();
           // facility.setCreatedBy(getActiveUser().getFirstName() + " " + getActiveUser().getLastName());
            facility.setCreatedDate(new Date());
            
            
            facilities.add(0, facility);
        }
    }
    
    public void save(Facility fac){
        if(fac.getId() != null){
            facilityService.update(fac);
        }
        else{
            facilityService.save(fac);
        }
        this.resetView(false).setList(true);
    }
    
    public void delete(Facility fac){
        facilityService.deleteById(fac.getId());
        synchronize(fac);
        this.resetView(false).setList(true);
    }
    
    public void synchronize(Facility emp){
        Iterator<Facility> facilityList = facilities.iterator();
        while(facilityList.hasNext()){
            if(facilityList.next().getId().equals(emp.getId())){
                facilityList.remove();
            }
        }
    }
    
    public void cancel(Facility fac){
        if(facilities.contains(fac)){
            facilities.remove(fac);
        }
        this.resetView(false).setList(true);
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
    
    
    
}
