package fr.ben.openid.simpleserver.services;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import fr.ben.openid.simpleserver.model.AuthData;
import fr.ben.openid.simpleserver.model.TokenResponse;
import org.springframework.stereotype.Component;


import java.security.SecureRandom;
import java.util.Date;
import java.util.UUID;

/**
 * Service de génération des données "Token"
 */
@Component
public class TokenService {


    private final byte[] sharedSecret;


    public TokenService() {
        // Pour les besoins des tests
        SecureRandom random = new SecureRandom();
        sharedSecret = new byte[32];
        random.nextBytes(sharedSecret);
    }

    /**
     * Renvoit les données utiles pour le endpoint /token
     * @param authData
     * @return Données de token
     */
    public TokenResponse buildTokenData(AuthData authData) {
        TokenResponse result = new TokenResponse();

        // No use of access token here - je le genere
        result.setAccessToken(UUID.randomUUID().toString());

        result.setIdToken(generateIdToken(authData));
        result.setExpires(new Date().getTime() + 60 * 1000);
        return result;
    }


    /**
     * Construit un token jwt.
     * @param authData Donnees d'authentification
     * @return Token sous forme de chaine de caractères
     */
    private String generateIdToken(AuthData authData) {
        try {
            JWSSigner signer = new MACSigner(sharedSecret);
            // Prepare JWT with claims set
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(authData.getUserid())
                    .issuer("https://mysuper.openid.fake")
                    .expirationTime(new Date(new Date().getTime() + 60 * 1000))
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            // Apply the HMAC
            signedJWT.sign(signer);


            return signedJWT.serialize();
        } catch (JOSEException e) {
            e.printStackTrace();
        }

        return null;


    }
}
