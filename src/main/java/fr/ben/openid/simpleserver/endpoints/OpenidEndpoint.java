package fr.ben.openid.simpleserver.endpoints;

import fr.ben.openid.simpleserver.model.AuthData;
import fr.ben.openid.simpleserver.model.TokenResponse;
import fr.ben.openid.simpleserver.services.AuthCodeService;
import fr.ben.openid.simpleserver.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Optional;

/**
 * Expose trois endpoints pour simuler un échange openid connect
 */
@Controller
public class OpenidEndpoint {
    @Autowired
    private AuthCodeService authService;

    @Autowired
    private TokenService tokenService;

    @Value("${fr.ben.openid.defaultuid}")
    private String defaultUid;

    @RequestMapping("/authorize")
    public String authorize(@RequestParam(name="redirect_url") String redirectUrl, @RequestParam(name="uid",required=false) String uid) throws UnsupportedEncodingException {

        // Generate auth code and add it to "redirect_url"
        String originalUrl = URLDecoder.decode(redirectUrl, "utf-8");
        String code = authService.produceCode(new AuthData(Optional.ofNullable(uid).orElse(defaultUid), originalUrl));

        String redirectTo = UriComponentsBuilder.fromHttpUrl(originalUrl).queryParam("code", code).build().toString();

        // TODO more fun : add client_id and redirect_url validation

        // Redirect
        return "redirect:" + redirectTo;
    }

    @RequestMapping("/token")
    public ResponseEntity<TokenResponse> token(@RequestParam String code, @RequestParam String redirect_uri) {

        AuthData authData = authService.consumeCode(code);
        if (authData == null)
            return new ResponseEntity("Invalid code", HttpStatus.BAD_REQUEST);

        // TODO Check redirect_url vs code vs client_id
        return new ResponseEntity<>(tokenService.buildTokenData(authData), HttpStatus.OK);
    }

    @RequestMapping("/userinfo")
    public ResponseEntity userInfo(@RequestHeader String authorization) {
        // TODO : check access token
        return ResponseEntity.ok("To be implemented");
    }



}
