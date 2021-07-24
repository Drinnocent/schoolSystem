/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.service;

import java.util.Date;
import za.gov.sars.common.AddressType;
import za.gov.sars.domain.Address;
import za.gov.sars.domain.ContactDetail;
import za.gov.sars.domain.School;
import za.gov.sars.persistence.EmployeeRepository;
import za.gov.sars.persistence.SchoolRepository;
import za.gov.sars.persistence.StudentRepository;

/**
 *
 * @author S2028389
 */
public class SchoolHelper {
    public static void addSchool(SchoolRepository schoolRepository,EmployeeRepository employeeRepository ,StudentRepository Studentrepository)
    {
        School school=new School();
        school.setCreatedBy("Test");
        school.setCreatedDate(new Date());
        school.setName("Thanduyise");
        school.setRegistrationNumber("44254");
        school.setSchoolWebsite("www.Thanduyise.co.za");
        
        ContactDetail contact1 = new ContactDetail();
        contact1.setCreatedBy("Abelo");
        contact1.setCreatedDate(new Date());
        contact1.setCellphone_number("0685541774");
        contact1.setEmailAddress("dco@");
        contact1.setTellphone_number("031412547");
        school.addContact(contact1);

        Address addr1 = new Address();
        addr1.setCreatedBy("Luke");
        addr1.setCreatedDate(new Date());
        addr1.setAddressType(AddressType.RESIDENTIAL);
        addr1.setAddressLine1("Joseph Nduli 165");
        addr1.setArea("Mpangeni");
        addr1.setCode("3880");
        addr1.setStreet("Bhubhesi");

        Address addr2 = new Address();
        addr2.setCreatedBy("Lucas");
        addr2.setCreatedDate(new Date());
        addr2.setAddressType(AddressType.POSTAL);
        addr2.setAddressLine2("P.o Box 77684");
        addr2.setArea("Mpangeni");
        addr2.setCode("3880");
        addr2.setStreet("Bhubhesi");

        school.addAddress(addr1);
        school.addAddress(addr2);
        
        schoolRepository.save(school);
        
    }
}
