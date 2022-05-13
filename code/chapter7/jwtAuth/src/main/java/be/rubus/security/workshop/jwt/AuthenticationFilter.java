package be.rubus.security.workshop.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;

import javax.enterprise.inject.spi.CDI;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Scanner;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/resources/*")
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        boolean validToken = false;
        String token = getToken(httpRequest);
        if (token != null) {
            validToken = processToken(token);
        }
        if (validToken) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private boolean processToken(String token) {
        boolean result = false;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            String apiKey = signedJWT.getHeader().getKeyID();

            // Use this apiKey to select the correct privateKey
            // We are allowed to use the header without verification

            String publicContent = readFile("public.jwk");
            RSAKey publicKey = (RSAKey) JWK.parse(publicContent);

            RSASSAVerifier verifier = new RSASSAVerifier(publicKey);

            if (signedJWT.verify(verifier)) {

                // Retrieve the JWT claims...
                result = true;
                UserToken userToken = CDI.current().select(UserToken.class).get();
                userToken.setSubject(signedJWT.getJWTClaimsSet().getSubject());
            }
        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getToken(HttpServletRequest httpRequest) {
        String token = null;
        String authorization = httpRequest.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
        }
        return token;
    }

    private static String readFile(String fileName) {
        InputStream keys = SecureEnd.class.getClassLoader().getResourceAsStream(fileName);
        return new Scanner(keys).useDelimiter("\\Z").next();
    }
}
