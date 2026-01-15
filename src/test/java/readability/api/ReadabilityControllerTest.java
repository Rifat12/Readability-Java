package readability.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ReadabilityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void analyzeSingleTextWithScoreSelection() throws Exception {
        Map<String, Object> payload = Map.of(
                "text", "This is a test.",
                "scores", List.of("ARI", "CL"));

        mockMvc.perform(post("/api/readability")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.counts.words").value(4))
                .andExpect(jsonPath("$.counts.sentences").value(1))
                .andExpect(jsonPath("$.scores.ARI").exists())
                .andExpect(jsonPath("$.scores.CL").exists())
                .andExpect(jsonPath("$.scores.FK").doesNotExist());
    }

    @Test
    void analyzeBatchTexts() throws Exception {
        Map<String, Object> payload = Map.of(
                "items", List.of(
                        Map.of("text", "This is a test."),
                        Map.of("text", "Another short sentence.")));

        mockMvc.perform(post("/api/readability/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results.length()").value(2))
                .andExpect(jsonPath("$.results[0].counts.words").exists());
    }

    @Test
    void historyIncludesStoredAnalysis() throws Exception {
        Map<String, Object> payload = Map.of("text", "Store this text.");

        mockMvc.perform(post("/api/readability")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/readability/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results[0].id").exists())
                .andExpect(jsonPath("$.results[0].text").value("Store this text."));
    }
}
