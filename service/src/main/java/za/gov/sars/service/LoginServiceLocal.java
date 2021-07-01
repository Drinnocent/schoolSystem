/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.service;

import java.util.List;
import za.gov.sars.domain.SystemUser;

/**
 *
 * @author S2028389
 */
public interface LoginServiceLocal
{
    public SystemUser logUserIn(String username, String password);
    public boolean isUserExisting(String username);
    SystemUser findById(Long Id);
    SystemUser update(SystemUser systemUser);
    void deleteById(Long id);
    void deleteAll();
    List<SystemUser> listAll();
    
}
