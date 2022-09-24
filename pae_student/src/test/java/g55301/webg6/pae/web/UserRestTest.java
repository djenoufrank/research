package g55301.webg6.pae.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import g55301.webg6.pae.business.UserForTestService;
import g55301.webg6.pae.model.UserForTest;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserForTestRest.class)
public class UserRestTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserForTestService userService;

    
      @Test
      public void getUserByName() throws Exception {
      String name = "MCD";
      UserForTest user = new UserForTest("ValidLogin", name);
      Mockito.when(userService.getUserByName(name)).thenReturn(user);
      mvc.perform(MockMvcRequestBuilders.get("/api/user/MCD"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name));
      }
     
}