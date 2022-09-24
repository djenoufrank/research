package webg55301quiz.webg5.web;

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
public class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc; // Permet de simuler le navigateur

    @Test
    public void testQuestionPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/detailQuestion?q=1")) // L’url à tester
    .andExpect(MockMvcResultMatchers.status().isOk()) // La page est retournée
    .andExpect(MockMvcResultMatchers.view().name("detailQuestion")) // Générée à partir du template welcome
    // Elle contient le texte attendu
    .andExpect(MockMvcResultMatchers.content().string(Matchers.stringContainsInOrder("Traversant une dizaine de pays, le Danube est le plus long fleuve","2020-08-12")));
    }
}
