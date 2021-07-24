/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.service;

import java.util.Date;
import za.gov.sars.domain.Assessment;
import za.gov.sars.persistence.AssessmentRepository;
import za.gov.sars.persistence.EmployeeRepository;
import za.gov.sars.persistence.FacilityRepository;
import za.gov.sars.persistence.StudentRepository;

/**
 *
 * @author S2028389
 */
public class AssessmentHelper {
    public static void addAssessment(AssessmentRepository assessmentRepository,EmployeeRepository employeeRepository,StudentRepository studentRepository,FacilityRepository facilityRepository)
    {
        Assessment assessment=new Assessment();
        assessment.setCreatedBy("Dr");
        assessment.setCreatedDate(new Date());
        assessment.setName("Maths Asss");
        assessment.setDescription("Class test");
        assessment.setPassMark(80.00);
        assessment.setStudentmark(50);
         assessment.setFacility(facilityRepository.findAll().get(facilityRepository.findAll().size()-1));
        assessment.setStudent(studentRepository.findAll().get(studentRepository.findAll().size()-2));
        assessment.setEducator(employeeRepository.findAll().get(employeeRepository.findAll().size()-1));
        assessmentRepository.save(assessment);
        
         Assessment assessment2=new Assessment();
        assessment2.setCreatedBy("Dr");
        assessment2.setCreatedDate(new Date());
        assessment2.setName("Physics Asss");
        assessment2.setDescription("Class test");
        assessment2.setPassMark(80.00);
        assessment2.setStudentmark(50);
         assessment2.setFacility(facilityRepository.findAll().get(facilityRepository.findAll().size()-1));
        assessment2.setStudent(studentRepository.findAll().get(studentRepository.findAll().size()-1));
        assessment2.setEducator(employeeRepository.findAll().get(employeeRepository.findAll().size()-1));
        assessmentRepository.save(assessment2);
        
         Assessment assessment3=new Assessment();
        assessment3.setCreatedBy("Dr");
        assessment3.setCreatedDate(new Date());
        assessment3.setName("English Asss");
        assessment3.setDescription("Class test");
        assessment3.setPassMark(80.00);
        assessment3.setStudentmark(50);
         assessment3.setFacility(facilityRepository.findAll().get(facilityRepository.findAll().size()-1));
        assessment3.setStudent(studentRepository.findAll().get(studentRepository.findAll().size()-2));
        assessment3.setEducator(employeeRepository.findAll().get(employeeRepository.findAll().size()-1));
        assessmentRepository.save(assessment3);
        
    }
    
}
