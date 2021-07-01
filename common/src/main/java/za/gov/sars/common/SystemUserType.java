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
public enum SystemUserType {

    GENERAL_WORKER("General Staff"),
    LEARNER("Learner"),
    EDUCATOR("Educator"),
    PRINCIPAL("Principal"),
    HOD("Head of Department"),
    CLERK("Clerk"),
    ADMIN("Administrator");

    private final String displayname;

    SystemUserType(final String displayName) {
        this.displayname = displayName;
    }

    public String toString() {
        return this.displayname;
    }

}
