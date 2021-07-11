/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import za.gov.sars.common.PersonType;
import za.gov.sars.common.SystemUserType;
import za.gov.sars.domain.Employee;
import za.gov.sars.service.EmployeeServiceLocal;

/**
 *
 * @author S2028389
 */
@ManagedBean
@ViewScoped
public class EmpProfileBean extends BaseBean
{
    @Autowired
    private EmployeeServiceLocal employeeService;
    
 
     
     private Employee employee;
   
     
     public void init()
     {
        // employee=employeeService.findById(getActiveUser().getId());
         employee=employeeService.listAll().get(employeeService.listAll().size()-1);
         
         
     }
     
     public void updateEmpProfile(Employee emp)
     {
         if(emp.getId()!=null)
         {
             employeeService.update(emp);
         }
         if(emp.getPersonType().equals(PersonType.SYSTEM_USER) && emp.getSystemUserType().equals(SystemUserType.ADMIN))
         {
                redirecting("adminDash");
         }
         else if(emp.getPersonType().equals(PersonType.EMPLOYEE )&& emp.getSystemUserType().equals(SystemUserType.ADMIN ))
                 {
                    redirecting("officeDash"); 
                 }
         else if(emp.getSystemUserType().equals(SystemUserType.PRINCIPAL))
         {
             redirecting("principalDash");
         }
         else if(emp.getSystemUserType().equals(SystemUserType.HOD))
         {
             redirecting("hodDash");
         }
        else if(emp.getSystemUserType().equals(SystemUserType.EDUCATOR))
         {
             redirecting("educatorDash");
         }
       
     }
     
     public void cancel(Employee emp)
     {
          if(emp.getPersonType().equals(PersonType.SYSTEM_USER) && emp.getSystemUserType().equals(SystemUserType.ADMIN))
         {
                redirecting("adminDash");
         }
         else if(emp.getPersonType().equals(PersonType.EMPLOYEE) && emp.getSystemUserType().equals(SystemUserType.ADMIN ))
                 {
                    redirecting("officeDash"); 
                 }
         else if(emp.getSystemUserType().equals(SystemUserType.PRINCIPAL))
         {
             redirecting("principalDash");
         }
         else if(emp.getSystemUserType().equals(SystemUserType.HOD))
         {
             redirecting("hodDash");
         }
        else if(emp.getSystemUserType().equals(SystemUserType.EDUCATOR))
         {
             redirecting("educatorDash");
         }
     }
     public Employee getEmployee()
     {
         return employee;
     }
     public void setEmployee(Employee employee)
     {
         this.employee=employee;
     }
    
}
