/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.mb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import za.gov.sars.common.AddressType;
import za.gov.sars.common.Gender;
import za.gov.sars.common.PersonType;
import za.gov.sars.common.SystemUserStatus;
import za.gov.sars.common.SystemUserType;
import za.gov.sars.domain.Address;
import za.gov.sars.domain.ContactDetail;
import za.gov.sars.domain.Employee;
import za.gov.sars.domain.School;
import za.gov.sars.domain.Student;
import za.gov.sars.domain.SchoolSystemUser;
import za.gov.sars.service.EmployeeServiceLocal;
import za.gov.sars.service.SchoolServiceLocal;
import za.gov.sars.service.StudentServiceLocal;
import za.gov.sars.service.SystemUserServiceLocal;

/**
 *
 * @author S2028389
 */
@ManagedBean
@ViewScoped
public class SystemUserBean extends BaseBean {
    @Autowired
    private SystemUserServiceLocal systemUserService;
    @Autowired
    private EmployeeServiceLocal employeeService;
    @Autowired
    private StudentServiceLocal studebtService;
    @Autowired
    private SchoolServiceLocal schoolUserService;
    
    private List<SchoolSystemUser> systemUsers=new ArrayList(); 
    private List<Employee> employees=new ArrayList(); 
    private List<Student> students=new ArrayList(); 
    
    private List<SystemUserType> systemUserType;
    private List<AddressType> addressType; 
    private List<Gender> gender;
    private List<SystemUserStatus> systemUserStatus;
    
    private SchoolSystemUser systemUser;
    private Student student;
    private SchoolSystemUser user;
    private Employee employee;
    private School school;
    
    private ActiveUser activeUser;
    
    @PostConstruct
    public void init() {
        this.resetView(false).setList(true);
        systemUsers=systemUserService.listAll();
        employees = employeeService.listAll();
        students=studebtService.listAll();
       
        addressType = Arrays.asList(AddressType.values());
        systemUserType = Arrays.asList(SystemUserType.values());
        gender=Arrays.asList(Gender.values());
        systemUserStatus=Arrays.asList(SystemUserStatus.values());
        school=schoolUserService.listAll().get(schoolUserService.listAll().size()-1);

    }
      public void addOrUpdate(SchoolSystemUser syst) {
        this.resetView(false).setAdd(true);
         if(syst!=null)
         {
             if(user.getSystemUserType().equals(SystemUserType.EMPLOYEE))
             {
                 //employee=employeeService.findEmployeeByEmployeeNum(user.getIdentifier());
             }
             else if(user.getSystemUserType().equals(SystemUserType.SYSTEM_ADMIN))
             {
                // employee=employeeService.findEmployeeByEmployeeNum(user.getIdentifier());
             }
             else  if(user.getSystemUserType().equals(SystemUserType.LEARNER))
             {
                 //student=studebtService.findStudentByStudentNum(user.getIdentifier());
             }
             user.setUpdateBy(getActiveUser().getIdentifier());
             user.setUpdatedDate(new Date());
             systemUser=user;
         }
         else
         {
             systemUser=new SchoolSystemUser();
             systemUser.setCreatedBy(getActiveUser().getIdentifier());
             systemUser.setCreatedDate(new Date());
             systemUser.setSchool(school);
             systemUser.setPersonType(PersonType.SYSTEM_USER);
             systemUsers.add(0,systemUser);
         }

      }
      public void save(SchoolSystemUser user)
      {
          if(user.getPassword().equals(user.getConfirmPassword()))
          {
              if(user.getId()!=null)
              {
                  user.setUpdateBy(getActiveUser().getIdentifier());
                  user.setUpdatedDate(new Date());
                  systemUserService.update(user);
              }
              else
              {
                  if(userExist(user))
                  {
                      if(user.getSystemUserType().equals(SystemUserType.EMPLOYEE)||user.getSystemUserType().equals(SystemUserType.SYSTEM_ADMIN))
                      {
                          employee=employeeService.findEmployeeByEmployeeNum(user.getIdentifier());
                          user.setFirstName(employee.getFirstName());
                          user.setLastName(employee.getLastName());
                          user.setIdentityNumber(employee.getIdentityNumber());
                          user.setGenderType(employee.getGenderType());
                          
                          for(Address address:employee.getAddressList())
                          {
                              Address newAddress=new Address();
                              newAddress.setCreatedBy(getActiveUser().getIdentifier());
                              newAddress.setCreatedDate(new Date());
                              newAddress.setAddressType(address.getAddressType());
                              newAddress.setAddressLine1(address.getAddressLine1());
                              newAddress.setAddressLine2(address.getAddressLine2());
                              newAddress.setStreet(address.getStreet());
                              newAddress.setArea(address.getArea());
                              newAddress.setCode(address.getCode());
                              user.addAddress(newAddress);
                          }
                          ContactDetail contact1=new ContactDetail();
                          contact1.setCreatedBy(getActiveUser().getIdentifier());
                          contact1.setCreatedDate(new Date());
                          contact1.setCellphone_number(employee.getContactDetail().getCellphone_number());
                          contact1.setTellphone_number(employee.getContactDetail().getTellphone_number());
                          contact1.setEmailAddress(employee.getContactDetail().getEmailAddress());
                          user.setContactDetail(contact1);
                          
                          systemUserService.save(user);
                          
                          
                      }
                      else if(user.getSystemUserType().equals(SystemUserType.LEARNER))
                      {
                          student=studebtService.findStudentByStudentNum(user.getIdentifier());
                          user.setFirstName(student.getFirstName());
                          user.setLastName(student.getLastName());
                          user.setIdentityNumber(student.getIdentityNumber());
                          user.setGenderType(student.getGenderType());
                          
                          for(Address address:student.getAddressList())
                          {
                              Address newAddress=new Address();
                              newAddress.setCreatedBy(getActiveUser().getIdentifier());
                              newAddress.setCreatedDate(new Date());
                              newAddress.setAddressType(address.getAddressType());
                              newAddress.setAddressLine1(address.getAddressLine1());
                              newAddress.setAddressLine2(address.getAddressLine2());
                              newAddress.setStreet(address.getStreet());
                              newAddress.setArea(address.getArea());
                              newAddress.setCode(address.getCode());
                              user.addAddress(newAddress);
                          }
                          ContactDetail contact1=new ContactDetail();
                          contact1.setCreatedBy(getActiveUser().getIdentifier());
                          contact1.setCreatedDate(new Date());
                          contact1.setCellphone_number(student.getContactDetail().getCellphone_number());
                          contact1.setTellphone_number(student.getContactDetail().getTellphone_number());
                          contact1.setEmailAddress(student.getContactDetail().getEmailAddress());
                          user.setContactDetail(contact1);
                          
                          systemUserService.save(user);
                          
                          
                      }
                  }
              }
          }
      }
      public List<String> getIdentifiers()
      {
          List<String> allIdentifiers=new ArrayList();
          for(SchoolSystemUser user:systemUserService.listAll())
          {
              allIdentifiers.add(user.getIdentifier());
          }
          return allIdentifiers;
      }
      public void systemUserTypeListener()
      {
          if(systemUser.getSystemUserType().equals(SystemUserType.EMPLOYEE)||systemUser.getSystemUserType().equals(SystemUserType.SYSTEM_ADMIN))
          {
               setVisible(false);
              setVisiblePage(true);
             
          }
          else if(systemUser.getSystemUserType().equals(SystemUserType.LEARNER))
          {
              setVisiblePage(false);
              setVisible(true);
          }
      }
      public boolean userExist(SchoolSystemUser user)
      {
          for(SchoolSystemUser syst:systemUserService.listAll())
          {
              if(user.getIdentifier().equals(syst.getIdentifier()))
              {
                  return true;
              }
          }
          return false;
      }

    public List<SchoolSystemUser> getSystemUsers() {
        return systemUsers;
    }

    public void setSystemUsers(List<SchoolSystemUser> systemUsers) {
        this.systemUsers = systemUsers;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<SystemUserType> getSystemUserType() {
        return systemUserType;
    }

    public void setSystemUserType(List<SystemUserType> systemUserType) {
        this.systemUserType = systemUserType;
    }

    public List<AddressType> getAddressType() {
        return addressType;
    }

    public void setAddressType(List<AddressType> addressType) {
        this.addressType = addressType;
    }

    public List<Gender> getGender() {
        return gender;
    }

    public void setGender(List<Gender> gender) {
        this.gender = gender;
    }

    public List<SystemUserStatus> getSystemUserStatus() {
        return systemUserStatus;
    }

    public void setSystemUserStatus(List<SystemUserStatus> systemUserStatus) {
        this.systemUserStatus = systemUserStatus;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public SchoolSystemUser getUser() {
        return user;
    }

    public void setUser(SchoolSystemUser user) {
        this.user = user;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public SchoolSystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SchoolSystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public ActiveUser getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(ActiveUser activeUser) {
        this.activeUser = activeUser;
    }
    
      
      
    
    
    
}
