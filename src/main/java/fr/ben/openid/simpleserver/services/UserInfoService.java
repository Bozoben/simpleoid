package fr.ben.openid.simpleserver.services;

import fr.ben.openid.simpleserver.model.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserInfoService {

    public UserInfo getUserInfo(String userid) {
        UserInfo result = new UserInfo();
        result.setSub(userid);
        result.setRoles("USER,FAKE,ANOTHERONE");
        return result;
    }
}
