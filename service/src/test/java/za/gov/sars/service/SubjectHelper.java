/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.service;

import java.util.Date;
import za.gov.sars.domain.Subject;
import za.gov.sars.persistence.SubjectRepository;

/**
 *
 * @author S2028389
 */
public class SubjectHelper {

    public static void addSubject(SubjectRepository subjectRepository) {
        
        Subject subject1 = new Subject();
        subject1.setCreatedBy("Test");
        subject1.setCreatedDate(new Date());
        subject1.setName("Mathematics ");
        subject1.setCode("Math130");
        subjectRepository.save(subject1);

        Subject subject2 = new Subject();
        subject2.setCreatedBy("Test");
        subject2.setCreatedDate(new Date());
        subject2.setName("Computer Science ");
        subject2.setCode("comp304");
        subjectRepository.save(subject2);

        Subject subject3 = new Subject();
        subject3.setCreatedBy("Test");
        subject3.setCreatedDate(new Date());
        subject3.setName("ISTN ");
        subject3.setCode("ISTN313");
        subjectRepository.save(subject3);

    }

}
