/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.gov.sars.domain.Assessment;

/**
 *
 * @author S2028389
 */
@Repository
public interface AssessmentRepository extends JpaRepository<Assessment,Long> {
    
}
