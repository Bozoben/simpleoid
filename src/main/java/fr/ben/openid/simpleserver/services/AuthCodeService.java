package fr.ben.openid.simpleserver.services;

import fr.ben.openid.simpleserver.model.AuthData;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Sert juste à générer un code d'autorisation, utilisable une fois.
 *
 */
@Component
public class AuthCodeService {

    private Map<String, AuthData> allCodes = new HashMap<String,AuthData>();

    /**
     * Produit un code et l'associe à un id utilisateur / url
     * @param authData Donnees authentification
     * @return un code
     */
    public String produceCode(AuthData authData) {
        String code = UUID.randomUUID().toString();
        allCodes.put(code,authData);
        return code;
    }

    /**
     * Renvoit des donnees d'authentification (id utilisateur, url) à partir d'un code, ou null
     * @param code Code autorisation
     * @return Donnees d'authentification
     */
    public AuthData consumeCode(String code) {
        AuthData result = null;
        if (allCodes.containsKey(code)) {
             result = allCodes.get(code);
             allCodes.remove(result);
        }
        return result;
    }
}
