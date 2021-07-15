/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import za.gov.sars.domain.Assessment;
import za.gov.sars.domain.Grade;
import za.gov.sars.domain.Student;
import za.gov.sars.domain.Subject;
import za.gov.sars.service.AssessmentServiceLocal;
import za.gov.sars.service.GradeServiceLocal;
import za.gov.sars.service.StudentServiceLocal;
import za.gov.sars.service.SubjectServiceLocal;

/**
 *
 * @author S2028389
 */
@ManagedBean
@ViewScoped
public class AssessmentBean extends BaseBean {

    @Autowired
    private AssessmentServiceLocal assessmentService;
    @Autowired
    private SubjectServiceLocal subjectService;
    @Autowired
    private StudentServiceLocal studentService;
    @Autowired
    private GradeServiceLocal gradeService;

    private List<Assessment> assessments = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();

    private Assessment assessment;

    @PostConstruct
    public void init() {
        this.resetView(false).setList(true);
        assessments = assessmentService.listAll();
        students = studentService.listAll();
        subjects = subjectService.listAll();

    }

    public void addOrUpdate(Assessment ass) {
        this.resetView(false).setAdd(true);
        if (ass != null) {
            // ass.setUpdateBy(getActiveUser().getFirstName() + " " + getActiveUser().getLastName());
            ass.setUpdatedDate(new Date());
            assessment = ass;
        } else {
            assessment = new Assessment();
            //  assessment.setCreatedBy(getActiveUser().getFirstName() + " " + getActiveUser().getLastName());
            assessment.setCreatedDate(new Date());

            assessments.add(0, assessment);
        }
    }

    public void save(Assessment emp) {
        if (emp.getId() != null) {
            assessmentService.update(emp);
        } else {
            assessmentService.save(emp);
        }
        this.resetView(false).setList(true);
    }

    public void delete(Assessment ass) {
        assessmentService.deleteById(ass.getId());
        synchronize(ass);
        this.resetView(false).setList(true);
    }

    public void synchronize(Assessment ass) {
        Iterator<Assessment> assessmentList = assessments.iterator();
        while (assessmentList.hasNext()) {
            if (assessmentList.next().getId().equals(ass.getId())) {
                assessmentList.remove();
            }
        }
    }

    public void cancel(Assessment ass) {
        if (assessments.contains(ass)) {
            assessments.remove(ass);
        }
        this.resetView(false).setList(true);
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

}
