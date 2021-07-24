/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import za.gov.sars.common.SystemUserType;
import za.gov.sars.domain.Employee;
import za.gov.sars.domain.School;
import za.gov.sars.domain.Student;
import za.gov.sars.service.EmployeeServiceLocal;
import za.gov.sars.service.SchoolService;
import za.gov.sars.service.SchoolServiceLocal;
import za.gov.sars.service.StudentServiceLocal;
import za.gov.sars.service.SystemUserServiceLocal;

/**
 *
 * @author S2028389
 */
@ManagedBean
@ViewScoped
public class LandingBean extends BaseBean {
    @Autowired
    private SchoolServiceLocal schoolService;
    @Autowired
    private EmployeeServiceLocal employeeService;
    @Autowired
    private StudentServiceLocal studentService;
    
    Employee staff;
    Student student ;
    School school;
    
    @PostConstruct 
    public void init()
    {
       school=schoolService.listAll().get(schoolService.listAll().size()-1);
       if(getActiveUser().getSystemUserType().equals(SystemUserType.EMPLOYEE) || getActiveUser().getSystemUserType().equals(SystemUserType.SYSTEM_ADMIN))
       {
           staff=employeeService.findEmployeeByEmployeeNum(getActiveUser().getIdentifier());
       }
       else if(getActiveUser().getSystemUserType().equals(SystemUserType.LEARNER))
               {
                   student=studentService.findStudentByStudentNum(getActiveUser().getIdentifier());
               }
    }

    public Employee getStaff() {
        return staff;
    }

    public void setStaff(Employee staff) {
        this.staff = staff;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
    
    
}
