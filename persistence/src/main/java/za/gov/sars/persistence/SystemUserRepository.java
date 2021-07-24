/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.gov.sars.domain.SchoolSystemUser;

/**
 *
 * @author S2028389
 */
@Repository
public interface SystemUserRepository extends JpaRepository<SchoolSystemUser,Long>{
    //@Query("SELECT e FROM SystemUser e WHERE e.username")
    
    @Query("SELECT e FROM  SchoolSystemUser e WHERE e.identifier=:identifier")
    public SchoolSystemUser findSystemUserbyIdentfier(@Param("identifier") String identifier);
    
}
