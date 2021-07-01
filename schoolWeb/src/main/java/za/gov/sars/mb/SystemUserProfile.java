/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import za.gov.sars.domain.Employee;
import za.gov.sars.service.EmployeeServiceLocal;

/**
 *
 * @author S2028389
 */
@ManagedBean
@ViewScoped
public class SystemUserProfile extends BaseBean {
    @Autowired
    private EmployeeServiceLocal employeeService;
    
    private Employee employee;
    
    
    
    public void init()
    {
        
    }
}
