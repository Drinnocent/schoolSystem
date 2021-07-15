/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.mb;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.RequestScope;
import za.gov.sars.common.EmployeeType;
import za.gov.sars.common.PersonType;
import za.gov.sars.common.SystemUserStatus;
import za.gov.sars.common.SystemUserType;
import za.gov.sars.domain.SystemUser;
import za.gov.sars.service.LoginServiceLocal;

/**
 *
 * @author S2028389
 */
@ManagedBean
@RequestScope
public class LoginBean extends BaseBean {

    @Autowired
    private LoginServiceLocal loginService;

    private String username;
    private String password;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    private boolean loggedIn;
    private boolean loginPanel;
    private boolean changePasswordPanel;
    private boolean resetPasswordPanel;

    private static final String LOGIN_PAGE = "/login.xhml";

    private SystemUser systemUser;

    public void init() {
        setLoginPanel(true);
        setChangePasswordPanel(false);
        setResetPasswordPanel(false);
        setLoggedIn(false);
    }

    public String doLogin(String username, String password) {
        /* if(isValidUser(username,password)){
         //getActiveUser().setUserLoginIndicator(Boolean.TRUE);
         systemUser = loginService.logUserIn(username, password);
         if(systemUser.getPersonType().equals(PersonType.SYSTEM_USER))
         {
         return "/adminConsole.xhtml?faces-redirect=true";
         }
         else if(systemUser.getPersonType().equals(PersonType.LEARNER))
         {
         return "/learnerConsole.xhtml?faces-redirect=true";
         }
         else
         {
         return "/employeeConsole.xhtml?faces-redirect=true";
         }
            
         }*/

        return "/adminConsole.xhtml?faces-redirect=true";
    }

    public void doLogout() {
        invalidateSession().redirect(LOGIN_PAGE);
    }

    public boolean isValidUser(String username, String password) {
        SystemUser user = loginService.logUserIn(username, password);

        if (user == null) {
            return false;
        }

        if (user.getSystemUserType().equals(EmployeeType.GENERAL_WORKER)) {
            return false;
        } else if (user.getSystemUserType().equals(EmployeeType.CLERK)) {
            return false;
        }

        if (user.getSystemUserStatus().equals(SystemUserStatus.INACTIVE)) {
            return false;
        } else if (user.getSystemUserStatus().equals(SystemUserStatus.SUSPEDEND)) {
            return false;
        }

        if (!password.equals(user.getPassword())) {
            return false;
        }
        if (!username.equals(user.getUsername())) {
            return false;
        }

        if (user.isChangePassword() && password.equals(user.getPassword()) && username.equals(user.getUsername())) {
            setSystemUser(user);
            setLoginPanel(false);
            setChangePasswordPanel(true);
            return false;
        }

        if (password.equals(user.getPassword()) && username.equals(user.getUsername())) {
            //getActiveUser().setUsername(user.getUsername());
            //().setPersonType(user.getPersonType());

            StringBuilder userDisplayName = new StringBuilder("[");
            userDisplayName.append(" ");
            userDisplayName.append(user.getFirstName());
            userDisplayName.append(" ");
            userDisplayName.append(user.getLastName());
            userDisplayName.append(" ");
            userDisplayName.append("]");

            /*
            
             // getActiveUser().setDisplayName(userDisplayName);
            
             // getActiveUser().setFirstName(user.getFirstName());
             getActiveUser().setLastName(user.getLastName());
             getActiveUser().setUserRole(user.getSystemUserType().toString());
             getActiveUser().setActiveStatus(user.getSystemUserStatus().toString());
             getActiveUser().setUserLoginIndicator(Boolean.TRUE);
            
             getActiveUser().resetRole(false);
             if(user.getSystemUserType().equals(SystemUserType.ADMIN)){
             getActiveUser().setAdmin(true);
             }
             else if(user.getSystemUserType().equals(SystemUserType.EDUCATOR)){
             getActiveUser().setEducator(true);
             }
             else if(user.getSystemUserType().equals(SystemUserType.HOD)){
             getActiveUser().setHeadOfDepartment(true);
             }
             else if(user.getSystemUserType().equals(SystemUserType.PRINCIPAL)){
             getActiveUser().setPrincipal(true);
             }
             else if(user.getSystemUserType().equals(SystemUserType.LEARNER)){
             getActiveUser().setLearner(true);
             }
             return true;**/
        }
        return false;
    }

    public void redirectToLogin() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(LOGIN_PAGE);
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LoginServiceLocal getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginServiceLocal loginService) {
        this.loginService = loginService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoginPanel() {
        return loginPanel;
    }

    public void setLoginPanel(boolean loginPanel) {
        this.loginPanel = loginPanel;
    }

    public boolean isChangePasswordPanel() {
        return changePasswordPanel;
    }

    public void setChangePasswordPanel(boolean changePasswordPanel) {
        this.changePasswordPanel = changePasswordPanel;
    }

    public boolean isResetPasswordPanel() {
        return resetPasswordPanel;
    }

    public void setResetPasswordPanel(boolean resetPasswordPanel) {
        this.resetPasswordPanel = resetPasswordPanel;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

}
