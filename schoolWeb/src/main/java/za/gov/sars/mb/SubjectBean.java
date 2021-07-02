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
import za.gov.sars.common.SystemUserType;
import za.gov.sars.domain.Assessment;
import za.gov.sars.domain.Employee;
import za.gov.sars.domain.Grade;
import za.gov.sars.domain.Student;
import za.gov.sars.domain.Subject;
import za.gov.sars.service.AssessmentServiceLocal;
import za.gov.sars.service.EmployeeServiceLocal;
import za.gov.sars.service.GradeServiceLocal;
import za.gov.sars.service.StudentServiceLocal;
import za.gov.sars.service.SubjectServiceLocal;

/**
 *
 * @author S2028389
 */
@ManagedBean
@ViewScoped
public class SubjectBean extends BaseBean{
    @Autowired
    private SubjectServiceLocal subjectService;
    @Autowired
    private AssessmentServiceLocal assessmentService;
    @Autowired
    private StudentServiceLocal studentService;
    @Autowired
    private GradeServiceLocal gradeService;
    @Autowired
    private EmployeeServiceLocal employeeService;
    
   private  List<Subject> subjects=new ArrayList<>();
    private List<Assessment> assessments=new ArrayList<>();
   private  List<Student> students=new ArrayList<>();
    private List <Grade> grades;
    private List<Employee> employee;
    
    private Subject subject;
    
    
    
    @PostConstruct
    public void init()
    {
        this.resetView(false).setList(true);
        
        subjects=subjectService.listAll();
        assessments=assessmentService.listAll();
        students=studentService.listAll();
        grades=gradeService.listAll();
        employee=employeeService.listAll();
          
    }
    
    
    public void addOrUpdate(Subject sub){
        this.resetView(false).setAdd(true);
        if(sub != null){
          // sub.setUpdateBy(getActiveUser().getFirstName() + " " + getActiveUser().getLastName());
            sub.setUpdatedDate(new Date());
            subject= sub;
        }
        else{
            subject = new Subject();
          //  subject.setCreatedBy(getActiveUser().getFirstName() + " " + getActiveUser().getLastName());
            subject.setCreatedDate(new Date());
                 
            subjects.add(0, subject);
        }
    }
    
    public void save(Subject sub){
        if(sub.getId() != null){
            subjectService.update(sub);
        }
        else{
            subjectService.save(sub);
        }
        this.resetView(false).setList(true);
    }
   
    public void delete(Subject sub){
        subjectService.deleteById(sub.getId());
        synchronize(sub);
        this.resetView(false).setList(true);
    }
    
    public void synchronize(Subject sub){
        Iterator<Subject> subjectList = subjects.iterator();
        while(subjectList.hasNext()){
            if(subjectList.next().getId().equals(sub.getId())){
                subjectList.remove();
            }
        }
    }
    
    public void cancel(Subject sub){
        if(sub.getId()==null){
        if(subjects.contains(sub)){
            subjects.remove(sub);
        }
        this.resetView(false).setList(true);
        }
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
  
    
    
  
    
}
