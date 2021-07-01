/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

/**
 *
 * @author S2028389
 */
@Entity
@Audited
@Table(name="assessment")
public class Assessment extends BaseEntity
{
   
    @Column(name="assess_status")
    private String status;
    @Column(name="student_Mark")
    private double studentmark;
    @Column(name="pass_Mark")
    private double passMark;
    @Column(name="total_Mark")
    private double totalMark;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getStudentmark() {
        return studentmark;
    }

    public void setStudentmark(double studentmark) {
        this.studentmark = studentmark;
    }

    public double getPassMark() {
        return passMark;
    }

    public void setPassMark(double passMark) {
        this.passMark = passMark;
    }

    public double getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(double totalMark) {
        this.totalMark = totalMark;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }
    private Date releaseDate;
    private Date dueDate;
    private Date submissionDate;
    private String assessmentType;
    
    
}
