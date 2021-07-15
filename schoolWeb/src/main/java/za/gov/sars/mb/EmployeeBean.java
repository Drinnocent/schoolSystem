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
import za.gov.sars.common.EmployeeType;
import za.gov.sars.common.SystemUserType;
import za.gov.sars.common.PersonType;
import za.gov.sars.domain.Address;
import za.gov.sars.domain.ContactDetail;
import za.gov.sars.domain.Employee;
import za.gov.sars.domain.Grade;
import za.gov.sars.domain.Subject;
import za.gov.sars.service.EmployeeServiceLocal;
import za.gov.sars.service.GradeServiceLocal;
import za.gov.sars.service.SubjectServiceLocal;

/**
 *
 * @author S2028389
 * @ViewScopedit only managed by view scope and only available to that page
 */
@ManagedBean
@ViewScoped
public class EmployeeBean extends BaseBean {

    @Autowired
    private EmployeeServiceLocal employeeService;
    @Autowired
    private GradeServiceLocal gradeservice;
    @Autowired
    private SubjectServiceLocal subjectService;

    private List<Employee> employees = new ArrayList<>();
    private List<PersonType> personType = new ArrayList<>();
    private List<EmployeeType> employeeType = new ArrayList<>();
    private List<AddressType> addressType = new ArrayList<>();
    private List<Grade> grades = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();

    private Employee employee;

    @PostConstruct
    public void init() {
        this.resetView(false).setList(true);
        employees = employeeService.listAll();
        personType = Arrays.asList(PersonType.values());
        employeeType = Arrays.asList(EmployeeType.values());
        addressType = Arrays.asList(AddressType.values());
        grades = gradeservice.listAll();
        subjects = subjectService.listAll();

    }

    public void addOrUpdate(Employee emp) {
        this.resetView(false).setAdd(true);
        if (emp != null) {
            // emp.setUpdateBy(getActiveUser().getFirstName()+" "+getActiveUser().getLastName());
            emp.setUpdateBy("admin");
            emp.setUpdatedDate(new Date());
            employee = emp;

        } else {
            employee = new Employee();
            // employee.setCreatedBy(getActiveUser().getFirstName()+" "+getActiveUser().getLastName());
            employee.setCreatedBy("admin");
            employee.setCreatedDate(new Date());

            Address physicalAdress = new Address();
            physicalAdress.setAddressType(AddressType.RESIDENTIAL);
            //  physicalAdress.setCreatedBy(getActiveUser().getFirstName()+" "+getActiveUser().getLastName());
            physicalAdress.setCreatedBy("admin");
            physicalAdress.setCreatedDate(new Date());

            Address postalAdress = new Address();
            postalAdress.setAddressType(AddressType.POSTAL);
            // postalAdress.setCreatedBy(getActiveUser().getFirstName()+" "+getActiveUser().getLastName());
            physicalAdress.setCreatedBy("admin");
            postalAdress.setCreatedDate(new Date());

            employee.getAddressList().add(physicalAdress);
            employee.getAddressList().add(postalAdress);

            ContactDetail contactDetail = new ContactDetail();
            //  contactDetail.setCreatedBy(getActiveUser().getFirstName()+" "+getActiveUser().getLastName());
            contactDetail.setCreatedBy("admin");
            contactDetail.setCreatedDate(new Date());

            employees.add(0, employee);

        }
    }

    public void save(Employee emp) {
        if (emp.getId() != null) {
            employeeService.update(emp);
        } else {
            employeeService.save(emp);
        }
        this.resetView(false).setList(true);
    }

    public void delete(Employee emp) {
        employeeService.deleteById(emp.getId());
        synchronize(emp);
        this.resetView(false).setList(true);
    }

    public void synchronize(Employee emp) {
        Iterator<Employee> employeeList = employees.iterator();
        while (employeeList.hasNext()) {
            if (employeeList.next().getId().equals(emp.getId())) {
                employeeList.remove();

            }
        }

    }

    public void cancel(Employee emp) {
        if (emp.getId() == null) {
            if (employees.contains(emp)) {
                employees.remove(emp);
            }
        }

        this.resetView(false).setList(true);

    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<PersonType> getPersonType() {
        return personType;
    }

    public void setPersonType(List<PersonType> personType) {
        this.personType = personType;
    }

    public List<EmployeeType> getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(List<EmployeeType> employeeType) {
        this.employeeType = employeeType;
    }

    public List<AddressType> getAddressType() {
        return addressType;
    }

    public void setAddressType(List<AddressType> addressType) {
        this.addressType = addressType;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void employeeTypeListener() {
        if (employee.getEmployeeType().equals(EmployeeType.EDUCATOR) || employee.getEmployeeType().equals(EmployeeType.HOD) || employee.getEmployeeType().equals(EmployeeType.PRINCIPAL)) {
            setVisible(true);
        } else {
            setVisible(false);
        }
    }
}
