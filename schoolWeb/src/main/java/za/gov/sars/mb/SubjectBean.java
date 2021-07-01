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
    
    List<Subject> subjects=new ArrayList<>();
    List<Assessment> assessments=new ArrayList<>();
    List<Student> students=new ArrayList<>();
    private Grade grade;
    
    private Subject subject;
    private Employee employee;
    
    
    @PostConstruct
    public void init()
    {
        this.resetView(false).setList(true);
        
        subjects=subjectService.listAll();
        assessments=assessmentService.listAll();
        students=studentService.listAll();
        grade=gradeService.findById(Long.MIN_VALUE);
          
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
        if(subjects.contains(sub)){
            subjects.remove(sub);
        }
        this.resetView(false).setList(true);
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

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
   public void subjectListener() {
        if (employee.getEmployeeType().equals(SystemUserType.EDUCATOR) || employee.getEmployeeType().equals(SystemUserType.HOD) || employee.getEmployeeType().equals(SystemUserType.PRINCIPAL)) {
            setVisible(true);
        } else {
            setVisible(false);
        }
    } 
    
  
    
}
