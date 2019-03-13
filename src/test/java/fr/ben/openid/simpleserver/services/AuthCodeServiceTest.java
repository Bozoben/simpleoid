package fr.ben.openid.simpleserver.services;

import fr.ben.openid.simpleserver.model.AuthData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AuthCodeServiceTest {
    private AuthCodeService authService = new AuthCodeService();

    @Test
    public void testProduce() {
        String code = authService.produceCode(new AuthData("john", null));
        Assert.assertNotNull(code);
    }

    @Test
    public void testConsumeNull() {
        AuthData authData = authService.consumeCode(null);
        Assert.assertNull(authData);
    }

    @Test
    public void testConsume() {
        String code = authService.produceCode(new AuthData("john", null));
        AuthData authData = authService.consumeCode(code);
        Assert.assertEquals("john", authData.getUserid());

    }

}
