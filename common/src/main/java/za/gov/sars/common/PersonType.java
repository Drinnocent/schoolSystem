/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.common;

/**
 *
 * @author S2028389
 */
public enum PersonType {

    SYSTEM_USER("System "),
    EMPLOYEE("Employee"),
    LEARNER("Learner");

    private final String displayname;

    PersonType(final String displayName) {
        this.displayname = displayName;
    }

    public String toString() {
        return this.displayname;
    }

}
