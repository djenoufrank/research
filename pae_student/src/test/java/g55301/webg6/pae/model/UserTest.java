package g55301.webg6.pae.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import g55301.webg6.pae.BeanValidationUtil;
import g55301.webg6.pae.model.UserForTest;

@SpringBootTest
public class UserTest {

    @Autowired
    private BeanValidationUtil<UserForTest> validator;

    @Test
    public void loginValid() {
    UserForTest user = new UserForTest("ValidLogin", "Name");
    validator.assertIsValid(user);
    }

    @Test
    public void loginSizeLessThan6Error() {
    UserForTest user = new UserForTest("Login", "Name");
    validator.assertHasError(user, "login", Size.class);
    }

    @Test
    public void nameValid() {
    UserForTest user = new UserForTest("ValidLogin", "Name");
    validator.assertIsValid(user);
    }

    @Test
    public void nameBlankError() {
    UserForTest user = new UserForTest("ValidLogin", "");
    validator.assertHasError(user, "name", NotBlank.class);
    }

}
