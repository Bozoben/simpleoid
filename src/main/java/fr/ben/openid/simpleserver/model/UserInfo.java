package fr.ben.openid.simpleserver.model;

/**
 * Some arbitrary data for userinfo ...
 */
public class UserInfo {

    String sub;

    String roles;


    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
