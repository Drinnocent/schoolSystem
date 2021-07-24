/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.service;

import java.util.List;
import za.gov.sars.domain.SchoolSystemUser;

/**
 *
 * @author S2028389
 */
public interface SystemUserServiceLocal {

    public SchoolSystemUser save(SchoolSystemUser user);

    public SchoolSystemUser logUserIn(String username, String password);

    public boolean isUserExisting(String username);

    SchoolSystemUser findById(Long Id);

    SchoolSystemUser update(SchoolSystemUser systemUser);

    void deleteById(Long id);

    void deleteAll();

    List<SchoolSystemUser> listAll();

}
