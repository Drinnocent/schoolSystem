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
import za.gov.sars.domain.Employee;
import za.gov.sars.domain.Grade;
import za.gov.sars.domain.Student;
import za.gov.sars.service.GradeServiceLocal;
import za.gov.sars.service.StudentServiceLocal;

/**
 *
 * @author S2028389
 */
@ManagedBean
@ViewScoped
public class GradeBean extends BaseBean{
    
    @Autowired
    private GradeServiceLocal gradeService;
    @Autowired
    private StudentServiceLocal studentService;
    
    private List<Grade> grades=new ArrayList<>();
    private List<Student> students=new ArrayList<>();
    
    private Grade grade;
    //Just added
    private Employee employee;
    @PostConstruct
    public void init()
    {
        this.resetView(false).setList(true);
        grades=gradeService.listAll();
        students=studentService.listAll();
    }
    
    
    public void addOrUpdate(Grade gr){
        this.resetView(false).setAdd(true);
        if(gr != null){
          //  gr.setUpdateBy(getActiveUser().getFirstName() + " " + getActiveUser().getLastName());
           gr.setUpdatedDate(new Date());
            grade = gr;
        }
        else{
            grade = new Grade();
          //  grade.setCreatedBy(getActiveUser().getFirstName() + " " + getActiveUser().getLastName());
            grade.setCreatedDate(new Date());
            
            
            grades.add(0, grade);
        }
    }
    
    
    public void save(Grade gr){
        if(gr.getId() != null){
            gradeService.update(gr);
        }
        else{
            gradeService.save(gr);
        }
        this.resetView(false).setList(true);
    }
    
    public void delete(Grade gr){
        gradeService.deleteById(gr.getId());
        synchronize(gr);
        this.resetView(false).setList(true);
    }
    
    public void synchronize(Grade gr){
        Iterator<Grade> gradeList = grades.iterator();
        while(gradeList.hasNext()){
            if(gradeList.next().getId().equals(gr.getId())){
                gradeList.remove();
            }
        }
    }
    
    public void cancel(Grade gr){
        if(grades.contains(gr)){
            grades.remove(gr);
        }
        this.resetView(false).setList(true);
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
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

     public void gradeListener() {
        if (employee.getEmployeeType().equals(SystemUserType.EDUCATOR) || employee.getEmployeeType().equals(SystemUserType.HOD) || employee.getEmployeeType().equals(SystemUserType.PRINCIPAL))  {
            setVisible(true);
       } else {
          setVisible(false);
       }
    }
    
    
    
}
