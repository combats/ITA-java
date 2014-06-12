package mvc;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.mvc.BaseMVCTest;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


public class UserTests extends BaseMVCTest {
    private MockMvc mockMvc;

    @Autowired
    private JsonUtil jsonUtil;

    @SuppressWarnings("SpringJavaAutowiringInspection")

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void testPostNewUserAndExpectIsCreated() throws Exception {
        User testUser = new User("3");
        String jsonUser = jsonUtil.toJson(testUser);
        mockMvc.perform(post("/users")
                .content(jsonUser)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testPostNewUserWithDuplicatedIDAndExpectInternalServerError() throws Exception {
        User testUser = new User("1");
        String jsonUser = jsonUtil.toJson(testUser);
        mockMvc.perform(post("/users")
                .content(jsonUser)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}