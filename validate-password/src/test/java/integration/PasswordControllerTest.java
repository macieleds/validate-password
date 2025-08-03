package integration;

import com.edisonmaciel.password.validator.PasswordValidatorApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PasswordValidatorApplication.class)
@AutoConfigureMockMvc
class PasswordControllerTest {

    private static final String PASSWORD_VALIDATION_ENDPOINT = "/password";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnOkWhenCallingPasswordValidationEndpointWithValidPassword() throws Exception {
        var validPasswordJson = """
            {
              "password": "AbTp9!fok"
            }
            """;

        mockMvc.perform(post(PASSWORD_VALIDATION_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(validPasswordJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.path").value(PASSWORD_VALIDATION_ENDPOINT));
    }
}