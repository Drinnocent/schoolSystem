/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.service;

import java.util.Date;
import za.gov.sars.domain.Grade;
import za.gov.sars.persistence.GradeRepository;

/**
 *
 * @author S2028389
 */
public class GradeHelper {

    public static void addGrade(GradeRepository gradeRepository) {
        Grade grade1 = new Grade();
        grade1.setCreatedBy("Test");
        grade1.setCreatedDate(new Date());
        grade1.setName("Grade 8");
        grade1.setDesignation("B");
        gradeRepository.save(grade1);

        Grade grade2 = new Grade();
        grade2.setCreatedBy("Test");
        grade2.setCreatedDate(new Date());
        grade2.setName("Grade 9");
        grade2.setDesignation("F");
        gradeRepository.save(grade2);

        Grade grade3 = new Grade();
        grade3.setCreatedBy("Test");
        grade3.setCreatedDate(new Date());
        grade3.setName("Grade 10");
        grade3.setDesignation("F");
        gradeRepository.save(grade3);
        
         Grade grade4 = new Grade();
        grade4.setCreatedBy("Test");
        grade4.setCreatedDate(new Date());
        grade4.setName("Grade 11");
        grade4.setDesignation("D");
        gradeRepository.save(grade4);
        
         Grade grade5 = new Grade();
        grade5.setCreatedBy("Test");
        grade5.setCreatedDate(new Date());
        grade5.setName("Grade 11");
        grade5.setDesignation("L");
        gradeRepository.save(grade5);
        
        

    }
    /* public static void addEmployeeToGrade(GradeRepository gradeRepository, EmployeeRepository employeeRepository)
     {
     for(Grade grade:gradeRepository.findAll())
     {
     for(Employee employee:employeeRepository.findAll())
     {
     if(employee.getPersonType().equals(PersonType.EMPLOYEE))
     {
     if(employee.getEmployeeType().equals(SystemUserType.EDUCATOR)|| employee.getEmployeeType().equals(SystemUserType.HOD) || employee.getEmployeeType().equals(SystemUserType.PRINCIPAL))
     {
                   
     //grade
     }
     }
     }
     }
     }**/
}
