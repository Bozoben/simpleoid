package fr.ben.openid.simpleserver.model;

public class AuthData {

    private String userid;

    private String redirectUrl;


    public AuthData(String userid, String redirectUrl) {
        this.userid = userid;
        this.redirectUrl = redirectUrl;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
