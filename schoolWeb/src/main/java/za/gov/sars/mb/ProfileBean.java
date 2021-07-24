/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import za.gov.sars.domain.Employee;
import za.gov.sars.service.EmployeeService;
import za.gov.sars.service.SystemUserService;

/**
 *
 * @author S2028389
 */
@ManagedBean
@ViewScoped
public class ProfileBean  extends BaseBean{
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SystemUserService loginService;
    
    private Employee employee;
    
    @PostConstruct
    public void init()
    {
        employee=employeeService.listAll().get(employeeService.listAll().size()-1);
    }
    
}
