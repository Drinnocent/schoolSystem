/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.gov.sars.domain.SchoolSystemUser;
import za.gov.sars.persistence.SystemUserRepository;

/**
 *
 * @author S2028389
 */
@Service
public class SystemUserService implements SystemUserServiceLocal {

    @Autowired
    private SystemUserRepository loginRepository;

    public SchoolSystemUser save(SchoolSystemUser user) {
        return loginRepository.save(user);
    }

    @Override
    public SchoolSystemUser logUserIn(String username, String password) {
        SchoolSystemUser systemUser = null;
        if (isUserExisting(username)) {
            for (SchoolSystemUser user : loginRepository.findAll()) {
                if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                    systemUser = user;
                }
            }
        }
        return systemUser;
    }

    @Override
    public boolean isUserExisting(String username) {
        int count = 0;
        for (SchoolSystemUser user : loginRepository.findAll()) {
            if (user.getUsername().equals(username)) {
                count++;
            }
        }

        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public SchoolSystemUser findById(Long Id) {
        return loginRepository.getOne(Id);
    }

    @Override
    public SchoolSystemUser update(SchoolSystemUser systemUser) {
        return loginRepository.save(systemUser);
    }

    @Override
    public void deleteById(Long id) {
        loginRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        loginRepository.deleteAll();
    }

    @Override
    public List<SchoolSystemUser> listAll() {
        return loginRepository.findAll();
    }

}
