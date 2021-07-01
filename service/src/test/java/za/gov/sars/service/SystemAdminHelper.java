/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.service;

import java.util.Date;
import za.gov.sars.common.PersonType;
import za.gov.sars.common.SystemUserType;
import za.gov.sars.domain.Employee;
import za.gov.sars.persistence.EmployeeRepository;

/**
 *
 * @author S2028389
 */
public class SystemAdminHelper {

    public static void addAdmin(EmployeeRepository employeeRepository) {

        Employee employee1 = new Employee();

        employee1.setCreatedBy("Albert");
        employee1.setCreatedDate(new Date());
        employee1.setFirstName("Admin1");
        employee1.setLastName("Admin1");
        employee1.setIdentityNumber("198702106394087");
        employee1.setEmployeeId("DI121415");
        employee1.setPersonType(PersonType.SYSTEM_USER);
        employee1.setSaceRegNumber("0201044");
        employee1.setEmployeeType(SystemUserType.ADMIN);

        employeeRepository.save(employee1);

        Employee employee2 = new Employee();
        employee2.setCreatedBy("Doc");
        employee2.setCreatedDate(new Date());
        employee2.setFirstName("Admin2");
        employee2.setLastName("Admin2");
        employee2.setIdentityNumber("0002106394087");
        employee2.setEmployeeId("L1445415");
        employee2.setPersonType(PersonType.SYSTEM_USER);
        employee2.setSaceRegNumber("0201044");
        employee2.setEmployeeType(SystemUserType.ADMIN);
        employeeRepository.save(employee2);

    }

}
