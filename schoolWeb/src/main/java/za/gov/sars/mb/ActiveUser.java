/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.mb;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.springframework.web.context.annotation.SessionScope;
import za.gov.sars.common.PersonType;

/**
 *
 * @author S2028389
 */
//will manage who is the active user
@ManagedBean(eager=true) //used through the system and eager true because it will used throughout
//jsf manages bean
//work 
@SessionScope  //manages how user have been online
public class ActiveUser implements Serializable{
    
    private String username;
    private StringBuilder displayName;
    private boolean menuActivationIndicator = Boolean.TRUE;
    private boolean userLoginIndicator = false;
    private String userRole;
    private boolean templateUploadIndicator;
    private String adminRole;
    private String userSessionId;
    private String firstName;
    private String lastName;
    private String activeStatus;
    private boolean learner;
    private boolean admin;
    private boolean educator;
    private boolean principal;
    private boolean clerk;
    private boolean headOfDepartment;
    private boolean generalWorker;
    private PersonType personType;
    
    public void resetRole(boolean validity){
        setAdmin(validity);
        setClerk(validity);
        setEducator(validity);
        setGeneralWorker(validity);
        setHeadOfDepartment(validity);
        setLearner(validity);
        setPrincipal(validity);
    }
      

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public StringBuilder getDisplayName() {
        return displayName;
    }

    public void setDisplayName(StringBuilder displayName) {
        this.displayName = displayName;
    }

    public boolean isMenuActivationIndicator() {
        return menuActivationIndicator;
    }

    public void setMenuActivationIndicator(boolean menuActivationIndicator) {
        this.menuActivationIndicator = menuActivationIndicator;
    }

    public boolean isUserLoginIndicator() {
        return userLoginIndicator;
    }

    public void setUserLoginIndicator(boolean userLoginIndicator) {
        this.userLoginIndicator = userLoginIndicator;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public boolean isTemplateUploadIndicator() {
        return templateUploadIndicator;
    }

    public void setTemplateUploadIndicator(boolean templateUploadIndicator) {
        this.templateUploadIndicator = templateUploadIndicator;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

    public String getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(String userSessionId) {
        this.userSessionId = userSessionId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isLearner() {
        return learner;
    }

    public void setLearner(boolean learner) {
        this.learner = learner;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isEducator() {
        return educator;
    }

    public void setEducator(boolean educator) {
        this.educator = educator;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public boolean isClerk() {
        return clerk;
    }

    public void setClerk(boolean clerk) {
        this.clerk = clerk;
    }

    public boolean isHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(boolean headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public boolean isGeneralWorker() {
        return generalWorker;
    }

    public void setGeneralWorker(boolean generalWorker) {
        this.generalWorker = generalWorker;
    }
    
}
