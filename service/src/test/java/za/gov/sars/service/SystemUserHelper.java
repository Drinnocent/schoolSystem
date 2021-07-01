/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.service;

import java.util.Date;
import za.gov.sars.common.PersonType;
import za.gov.sars.common.SystemUserStatus;
import za.gov.sars.common.SystemUserType;
import za.gov.sars.domain.Employee;
import za.gov.sars.domain.SystemUser;
import za.gov.sars.persistence.EmployeeRepository;
import za.gov.sars.persistence.LoginRepository;

/**
 *
 * @author S2028389
 */
public class SystemUserHelper {

    public static void addSystemUser(LoginRepository loginRepository, EmployeeRepository employeeRepository) {
        int adminCount = 0;
        for (Employee employee : employeeRepository.findAll()) {
            if (employee.getPersonType().equals(PersonType.SYSTEM_USER) && employee.getPersonType().equals(SystemUserType.ADMIN)) {
                SystemUser systemUser = new SystemUser();

                systemUser.setCreatedBy("Test");
                systemUser.setCreatedDate(new Date());
                systemUser.setFirstName(employee.getFirstName());
                systemUser.setLastName(employee.getLastName());
                systemUser.setIdentityNumber(employee.getIdentityNumber());
                systemUser.setPersonType(employee.getPersonType());
                systemUser.setSystemUserType(employee.getEmployeeType());
                systemUser.setSystemUserStatus(SystemUserStatus.ACTIVE);
                systemUser.setUsername("admin" + adminCount);
                systemUser.setPassword("admin" + adminCount);
                systemUser.setChangePassword(false);
                loginRepository.save(systemUser);
                adminCount++;

            }

        }

        int empCount = 0;

        for (Employee employee : employeeRepository.findAll()) {
            if (employee.getPersonType().equals(PersonType.EMPLOYEE) || employee.getPersonType().equals(SystemUserType.ADMIN) || employee.getPersonType().equals(SystemUserType.EDUCATOR) || employee.getPersonType().equals(SystemUserType.HOD) || employee.getPersonType().equals(SystemUserType.PRINCIPAL)) {
                SystemUser systemUser = new SystemUser();

                systemUser.setCreatedBy("Test");
                systemUser.setCreatedDate(new Date());
                systemUser.setFirstName(employee.getFirstName());
                systemUser.setLastName(employee.getLastName());
                systemUser.setIdentityNumber(employee.getIdentityNumber());
                systemUser.setPersonType(employee.getPersonType());
                systemUser.setSystemUserType(employee.getEmployeeType());
                systemUser.setSystemUserStatus(SystemUserStatus.ACTIVE);
                systemUser.setUsername("user" + empCount);
                systemUser.setPassword("password" + empCount);
                systemUser.setConfirmPassword("password" + empCount);
                systemUser.setChangePassword(false);
                loginRepository.save(systemUser);
                empCount++;

            }
        }
    }

}
