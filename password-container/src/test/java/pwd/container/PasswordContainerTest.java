package pwd.container;

import com.gh.pwd.container.PasswordContainer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordContainerTest {
    static PasswordContainer pwc;

    static String SHORT_LENGTH_PASSWORD  = "TEST_14";
    static String MIDDLE_LENGTH_PASSWORD = "TEST_USER_PASSWORD%&8";
    static String LONG_LENGTH_PASSWORD   = "VERY_LONG_PASSWORD_FOR_ME_AND_MORE%&8";

    @BeforeAll
    static void initPwc() {
        pwc = new PasswordContainer();
    }

    @Test
    public void generateAndRetrieveShortLengthPasswordFromBase64() {
        assertEquals(SHORT_LENGTH_PASSWORD, pwc.decryptFromBase64(pwc.encryptToBase64(SHORT_LENGTH_PASSWORD)));
    }

    @Test
    public void generateAndRetrieveMiddleLengthPasswordFromBase64() {
        assertEquals(MIDDLE_LENGTH_PASSWORD, pwc.decryptFromBase64(pwc.encryptToBase64(MIDDLE_LENGTH_PASSWORD)));
    }

    @Test
    public void generateAndRetrieveLongLengthPasswordFromBase64() {
        assertEquals(LONG_LENGTH_PASSWORD, pwc.decryptFromBase64(pwc.encryptToBase64(LONG_LENGTH_PASSWORD)));
    }

    @Test
    public void generateAndRetrieveShortLengthFromHexString() {
        assertEquals(SHORT_LENGTH_PASSWORD, pwc.decryptFromHexString(pwc.encryptToHexString(SHORT_LENGTH_PASSWORD)));
    }

    @Test
    public void generateAndRetrieveMiddleLengthFromHexString() {
        assertEquals(MIDDLE_LENGTH_PASSWORD, pwc.decryptFromHexString(pwc.encryptToHexString(MIDDLE_LENGTH_PASSWORD)));
    }

    @Test
    public void generateAndRetrieveLongLengthFromHexString() {
        assertEquals(LONG_LENGTH_PASSWORD, pwc.decryptFromHexString(pwc.encryptToHexString(LONG_LENGTH_PASSWORD)));
    }
}
