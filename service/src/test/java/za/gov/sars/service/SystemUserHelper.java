/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.service;

import java.util.Date;
import za.gov.sars.common.EmployeeType;
import za.gov.sars.common.PersonType;
import za.gov.sars.common.SystemUserStatus;
import za.gov.sars.common.SystemUserType;
import za.gov.sars.domain.Employee;
import za.gov.sars.domain.SchoolSystemUser;
import za.gov.sars.persistence.EmployeeRepository;
import za.gov.sars.persistence.SystemUserRepository;

/**
 *
 * @author S2028389
 */
public class SystemUserHelper {

    public static void addSystemUser(SystemUserRepository systemUserRepository, EmployeeRepository employeeRepository) {
        int adminCount = 0;
        for (Employee employee : employeeRepository.findAll()) {
            if (employee.getPersonType().equals(PersonType.SYSTEM_USER) && employee.getPersonType().equals(EmployeeType.ADMIN)) {
                SchoolSystemUser systemUser = new SchoolSystemUser();

                systemUser.setCreatedBy("Test");
                systemUser.setCreatedDate(new Date());
                systemUser.setFirstName(employee.getFirstName());
                systemUser.setLastName(employee.getLastName());
                systemUser.setIdentityNumber(employee.getIdentityNumber());
                systemUser.setPersonType(employee.getPersonType());
                systemUser.setSystemUserType(systemUser.getSystemUserType());
                systemUser.setSystemUserStatus(SystemUserStatus.ACTIVE);
                systemUser.setIdentifier(employee.getEmployeeId());
                systemUser.setGenderType(systemUser.getGenderType());
                systemUser.setUsername("admin" + adminCount);
                systemUser.setPassword("admin" + adminCount);
                systemUser.setChangePassword(false);
                systemUserRepository.save(systemUser);
                adminCount++;

            }

        }

        int empCount = 0;

        for (Employee employee : employeeRepository.findAll()) {
            if (employee.getPersonType().equals(PersonType.EMPLOYEE) || employee.getPersonType().equals(EmployeeType.ADMIN) || employee.getPersonType().equals(EmployeeType.EDUCATOR) || employee.getPersonType().equals(EmployeeType.HOD) || employee.getPersonType().equals(EmployeeType.PRINCIPAL)) {
                SchoolSystemUser systemUser = new SchoolSystemUser();

                systemUser.setCreatedBy("Test");
                systemUser.setCreatedDate(new Date());
                systemUser.setFirstName(employee.getFirstName());
                systemUser.setLastName(employee.getLastName());
                systemUser.setIdentityNumber(employee.getIdentityNumber());
                systemUser.setPersonType(employee.getPersonType());
                systemUser.setSystemUserType(systemUser.getSystemUserType());
                systemUser.setSystemUserStatus(SystemUserStatus.ACTIVE);
                systemUser.setIdentifier(employee.getEmployeeId());
                systemUser.setGenderType(systemUser.getGenderType());
                systemUser.setUsername("user" + empCount);
                systemUser.setPassword("password" + empCount);
                systemUser.setConfirmPassword("password" + empCount);
                systemUser.setChangePassword(false);
                systemUserRepository.save(systemUser);
                empCount++;

            }
        }
    }

}
