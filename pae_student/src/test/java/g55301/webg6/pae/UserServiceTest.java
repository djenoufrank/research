package g55301.webg6.pae;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import g55301.webg6.pae.business.UserForTestService;
import g55301.webg6.pae.model.UserForTest;
import g55301.webg6.pae.model.UserForTestRepository;

@SpringBootTest
public class UserServiceTest {

  @Autowired
private UserForTestService userService;

  @MockBean
private UserForTestRepository userRepository;

 @Test
 public void getUserByName() {
 String name = "MCD";
 UserForTest user = new UserForTest("ValidLogin", name);
 Mockito.when(userRepository.findByName(name)).thenReturn(user);
 UserForTest found = userService.getUserByName(name);
 assertEquals(found.getName(), name);
 }
 }