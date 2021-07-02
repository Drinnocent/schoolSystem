/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.mb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import za.gov.sars.common.AddressType;
import za.gov.sars.common.PersonType;
import za.gov.sars.common.SystemUserType;
import za.gov.sars.domain.Address;
import za.gov.sars.domain.ContactDetail;
import za.gov.sars.domain.Grade;
import za.gov.sars.domain.Student;
import za.gov.sars.domain.Subject;
import za.gov.sars.service.GradeService;
import za.gov.sars.service.StudentServiceLocal;
import za.gov.sars.service.SubjectService;

/**
 *
 * @author S2028389
 */
@ManagedBean
@ViewScoped
public class StudentBean extends BaseBean {
    @Autowired
    private StudentServiceLocal studentService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private SubjectService subjectService;
    
    
    private List<Student> students=new ArrayList<>();
    private List<PersonType> personTypes = new ArrayList<>();
    private List<SystemUserType> studentTypes = new ArrayList<>();
    private List<AddressType> addressTypes = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();
   
    
    private Student student;
    
    @PostConstruct
    public void init()
    {
        this.resetView(false).setList(true);
        students=studentService.listAll();
        personTypes=Arrays.asList(PersonType.values());
        addressTypes=Arrays.asList(AddressType.values());
        studentTypes=Arrays.asList(SystemUserType.values());
        subjects=subjectService.listAll();
       
        
    }
    
    
    public void addOrUpdate(Student stu){
        this.resetView(false).setAdd(true);
        if(stu != null){
         //  stu.setUpdateBy(getActiveUser().getFirstName() + " " + getActiveUser().getLastName());
            stu.setUpdatedDate(new Date());
            student = stu;
        }
        else{
            student = new Student();
          //  student.setCreatedBy(getActiveUser().getFirstName() + " " + getActiveUser().getLastName());
            student.setCreatedDate(new Date());
            
            Address physicalAddress = new Address();
            physicalAddress.setAddressType(AddressType.RESIDENTIAL);
          //  physicalAddress.setCreatedBy(getActiveUser().getFirstName() + " " + getActiveUser().getLastName());
            physicalAddress.setCreatedDate(new Date());
            
            Address postalAddress = new Address();
            postalAddress.setAddressType(AddressType.POSTAL);
          //  postalAddress.setCreatedBy(getActiveUser().getFirstName() + " " + getActiveUser().getLastName());
            postalAddress.setCreatedDate(new Date());
            
            student.getAddressList().add(physicalAddress);
            student.getAddressList().add(postalAddress);
            
            ContactDetail contactDetail = new ContactDetail();
         //   contactDetail.setCreatedBy(getActiveUser().getFirstName() + " " + getActiveUser().getLastName());
            contactDetail.setCreatedDate(new Date());
            
            students.add(0, student);
        }
    }
    
    public void save(Student stu){
        if( stu.getId() != null){
            studentService.update( stu);
        }
        else{
            studentService.save(student);
        }
        this.resetView(false).setList(true);
    }
    
    public void delete(Student stu){
        studentService.deleteById(stu.getId());
        synchronize(stu);
        this.resetView(false).setList(true);
    }
    
    public void synchronize(Student stu){
        Iterator<Student> studentList = students.iterator();
        while(studentList.hasNext()){
            if(studentList.next().getId().equals(stu.getId())){
                studentList.remove();
            }
        }
    }
    
    public void cancel(Student stu){
        if(stu.getId()==null){
        if(students.contains(stu)){
            students.remove(stu);
        }
        this.resetView(false).setList(true);
         }
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<PersonType> getPersonTypes() {
        return personTypes;
    }

    public void setPersonTypes(List<PersonType> personTypes) {
        this.personTypes = personTypes;
    }

    public List<SystemUserType> getStudentTypes() {
        return studentTypes;
    }

    public void setStudentTypes(List<SystemUserType> studentTypes) {
        this.studentTypes = studentTypes;
    }

    public List<AddressType> getAddressTypes() {
        return addressTypes;
    }

    public void setAddressTypes(List<AddressType> addressTypes) {
        this.addressTypes = addressTypes;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
       
 
    
}
