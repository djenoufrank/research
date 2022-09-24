package g55301.webg6.pae.web;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class WelcomeControllerTest {

    @Autowired
    private MockMvc mockMvc; // Permet de simuler le navigateur

    @Test//ce meme test fonctionne ds lexamen du Quiz q jai fait. ici jai surement effacer des trucs ce qui fait q ca ne donne pas
    public void testWelcomePage() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/welcome?name=mcd")) // L’url à tester
    .andExpect(MockMvcResultMatchers.status().isOk()) // La page est retournée
    .andExpect(MockMvcResultMatchers.view().name("welcome")) // Générée à partir du template welcome
    // Elle contient le texte attendu
    .andExpect(MockMvcResultMatchers.content().string(Matchers.stringContainsInOrder("mcd")))//ce meme test fonctionne ds lexamen du Quiz q jai fait. ici jai surement effacer des trucs ce qui fait q ca ne donne pas
    ;
    }
}
