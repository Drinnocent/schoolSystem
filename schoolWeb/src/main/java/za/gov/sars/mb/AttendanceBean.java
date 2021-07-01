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
import za.gov.sars.domain.Attendance;
import za.gov.sars.domain.Employee;
import za.gov.sars.domain.Facility;
import za.gov.sars.domain.Grade;
import za.gov.sars.domain.Student;
import za.gov.sars.domain.Subject;
import za.gov.sars.service.AttendanceServiceLocal;
import za.gov.sars.service.EmployeeServiceLocal;
import za.gov.sars.service.FacilityServiceLocal;
import za.gov.sars.service.GradeServiceLocal;
import za.gov.sars.service.StudentServiceLocal;
import za.gov.sars.service.SubjectServiceLocal;

/**
 *
 * @author S2028389
 */
@ManagedBean
@ViewScoped
public class AttendanceBean extends BaseBean{
    
    @Autowired
    private AttendanceServiceLocal attendanceService;
    @Autowired
    private StudentServiceLocal studentService;
    @Autowired
    private SubjectServiceLocal subjectService;
    @Autowired
    private FacilityServiceLocal facilityService;
    @Autowired
    private GradeServiceLocal gradeService;
    @Autowired
    private EmployeeServiceLocal employeeService;
    
   List<Attendance> attendances=new ArrayList<>();
   List<Student> students=new ArrayList<>();
   private Subject subject;
   private Facility facility;
   private Grade grade;
   private Employee employee;
   
    
    private Attendance attendance;
    
    @PostConstruct
    public void init()
    {
        this.resetView(false).setList(true);
        
        attendances=attendanceService.listAll();
        students=studentService.listAll();
        subject=subjectService.findById(Long.MIN_VALUE);
        facility=facilityService.findById(Long.MIN_VALUE);
        grade=gradeService.findById(Long.MIN_VALUE);
        employee=employeeService.findById(Long.MIN_VALUE);
        
        
    }
    
    
    
    
    public void addOrUpdate(Attendance att){
        this.resetView(false).setAdd(true);
        if(att != null){
          //  att.setUpdateBy(getActiveUser().getFirstName() + " " + getActiveUser().getLastName());
            att.setUpdatedDate(new Date());
            attendance = att;
        }
        else{
           attendance = new Attendance();
           // attendance.setCreatedBy(getActiveUser().getFirstName() + " " + getActiveUser().getLastName());
           attendance.setCreatedDate(new Date());
            
           
      
            
            
           attendances.add(0, attendance);
        }
    }
    
    public void save(Attendance att){
        if(att.getId() != null){
            attendanceService.update(att);
        }
        else{
            attendanceService.save(att);
        }
        this.resetView(false).setList(true);
    }
    
    public void delete(Attendance att){
        attendanceService.deleteById(att.getId());
        synchronize(att);
        this.resetView(false).setList(true);
    }
    
    public void synchronize(Attendance att){
        Iterator<Attendance> attendanceList =attendances.iterator();
        while(attendanceList.hasNext()){
            if(attendanceList.next().getId().equals(att.getId())){
                attendanceList.remove();
            }
        }
    }
    
    public void cancel(Attendance att){
        if(attendances.contains(att)){
            attendances.remove(att);
        }
        this.resetView(false).setList(true);
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
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

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }
    
    
}
