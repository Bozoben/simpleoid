package fr.ben.openid.simpleserver.services;

import com.nimbusds.jose.JWSObject;
import fr.ben.openid.simpleserver.model.AuthData;
import fr.ben.openid.simpleserver.model.TokenResponse;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenServiceTest {

    @Autowired
    private TokenService service;

    @Test
    public void testBuildTokenData() throws ParseException {
        TokenResponse data = service.buildTokenData(new AuthData("john","http://lookatme.com"));
        Assert.assertNotNull(data.getAccessToken());
        Assert.assertNotNull(data.getIdToken());

        // No signature verification here ...
        JSONObject parsed = JWSObject.parse(data.getIdToken()).getPayload().toJSONObject();
        Assert.assertEquals("john",parsed.getAsString("sub"));
    }
}
