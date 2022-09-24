package g55301.webg6.pae.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserForTestRepository userRepository;

    @Test
    public void findByName() {
        UserForTest user = new UserForTest("ValidLogin", "MCD");
        userRepository.save(user);
        UserForTest found = userRepository.findByName(user.getName());
        assertEquals(user, found);
    }

    @Test
    public void findByLongLogin() {
        UserForTest user1 = new UserForTest("ValidLogin", "MCD");
        UserForTest user2 = new UserForTest("VeryLongLogin", "MCD");
        userRepository.save(user1);
        userRepository.save(user2);
        List<UserForTest> found = userRepository.findByLongLogin();
        assertEquals(1, found.size());
        assertEquals(user2, found.get(0));
    }
}