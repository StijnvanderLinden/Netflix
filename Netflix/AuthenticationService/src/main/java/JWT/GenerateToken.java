package JWT;


import java.util.HashMap;
import org.eclipse.microprofile.jwt.Claims;

/**
 * A simple utility class to generate and print a JWT token string to stdout. Can be run with:
 * mvn exec:java -Dexec.mainClass=org.acme.security.jwt.GenerateToken -Dexec.classpathScope=test
 */
public class GenerateToken {

    public static String generate() throws Exception {
        String claimsJson = "/JwtClaims.json";

        HashMap<String, Long> timeClaims = new HashMap<>();
            long duration = 300;
            long exp = TokenUtils.currentTimeInSecs() + duration;
            timeClaims.put(Claims.exp.name(), exp);
        return TokenUtils.generateTokenString(claimsJson, timeClaims);
    }
}
