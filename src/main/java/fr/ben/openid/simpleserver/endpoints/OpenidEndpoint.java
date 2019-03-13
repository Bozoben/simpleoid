package fr.ben.openid.simpleserver.endpoints;

import fr.ben.openid.simpleserver.model.AuthData;
import fr.ben.openid.simpleserver.services.AuthCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Expose trois endpoints pour simuler un échange openid connect
 */
@Controller
public class OpenidEndpoint {
    @Autowired
    private AuthCodeService authService;

    @RequestMapping("/authorize")
    public String authorize(@RequestParam(name="redirect_url") String redirectUrl) {
        String redirectTo = redirectUrl + "&code=" + authService.produceCode(new AuthData("john", redirectUrl));

        return "redirect:" + redirectTo;
    }

    @RequestMapping("/token")
    public ResponseEntity token() {
        return ResponseEntity.ok("To be implemented");
    }

    @RequestMapping("/userinfo")
    public ResponseEntity userInfo() {

        return ResponseEntity.ok("To be implemented");
    }



}
