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
import za.gov.sars.domain.Employee;
import za.gov.sars.domain.Facility;
import za.gov.sars.domain.Grade;
import za.gov.sars.domain.School;
import za.gov.sars.domain.Student;
import za.gov.sars.domain.Subject;
import za.gov.sars.service.EmployeeServiceLocal;
import za.gov.sars.service.FacilityServiceLocal;
import za.gov.sars.service.GradeServiceLocal;
import za.gov.sars.service.SchoolServiceLocal;
import za.gov.sars.service.StudentServiceLocal;
import za.gov.sars.service.SubjectServiceLocal;

/**
 *
 * @author S2028389
 */
@ManagedBean
@ViewScoped
public class SchoolBean extends BaseBean {
    
    @Autowired
    private SchoolServiceLocal schoolService;
    @Autowired
    private StudentServiceLocal studentService;
    @Autowired
    private EmployeeServiceLocal employeeService;
    @Autowired
    private FacilityServiceLocal facilityService;
    @Autowired
    private GradeServiceLocal gradeService;
    @Autowired
    private SubjectServiceLocal subjectService;
    
    
    private List<School> schools=new ArrayList<>();
    private List<Student> students=new ArrayList<>();
    private List<Employee> employees=new ArrayList<>();
    private List<Facility> facilities=new ArrayList<>();
    private List<Grade> grades=new ArrayList<>();
    private List<Subject> subjects=new ArrayList<>();
    
    private School school;
    
    
    @PostConstruct
    public void init()
    {
        this.resetView(false).setList(true);
        
        schools=schoolService.listAll();
        students=studentService.listAll();
        employees=employeeService.listAll();
        facilities=facilityService.listAll();
        grades=gradeService.listAll();
        subjects=subjectService.listAll();
    }
    
    
    
    public void addOrUpdate(School sc){
        this.resetView(false).setAdd(true);
        if(sc != null){
           // sc.setUpdateBy(getActiveUser().getFirstName() + " " + getActiveUser().getLastName());
           sc.setUpdatedDate(new Date());
          school = sc;
        }
        else{
            school = new School();
          //  school.setCreatedBy(getActiveUser().getFirstName() + " " + getActiveUser().getLastName());
            school.setCreatedDate(new Date());
            
          
            
            
            schools.add(0, school);
        }
    }
    
    public void save(School sc){
        if(sc.getId() != null){
            schoolService.update(sc);
        }
        else{
            schoolService.save(sc);
        }
        this.resetView(false).setList(true);
    }
    
    public void delete(School sc){
        schoolService.deleteById(sc.getId());
        synchronize(sc);
        this.resetView(false).setList(true);
    }
    
    public void synchronize(School sc){
        Iterator<School> schoolList = schools.iterator();
        while(schoolList.hasNext()){
            if(schoolList.next().getId().equals(sc.getId())){
                schoolList.remove();
            }
        }
    }
    
    public void cancel(School sc){
        if(schools.contains(sc)){
            schools.remove(sc);
        }
        this.resetView(false).setList(true);
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
    
    
    
}
