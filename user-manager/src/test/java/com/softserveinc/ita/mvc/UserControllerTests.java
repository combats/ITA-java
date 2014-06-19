package com.softserveinc.ita.mvc;

import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class UserControllerTests extends BaseMVCTest{
    private String userID;
    private MockMvc mockMvc;

    @Autowired
    private JsonUtil jsonUtil;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testDeleteUserByIDAndExpectedIsNoContent() throws Exception {
        userID = new String("121");
        mockMvc.perform(delete("/users" + "/" + userID))
               .andExpect(status().isNoContent());
    }

    @Test
    public void testDleteUserByIDAndExpectedDeletedSuccessfully() throws Exception {
        userID = new String("122");

        String goodResponse = jsonUtil.toJson("Deleted successfully User with userID: " + userID);

        mockMvc.perform(delete("/users" + "/" + userID))
               .andExpect(content().string(goodResponse));
    }

    @Test
    public void testDeleteUserByIdAndExpectedDeleteException() throws Exception {
        userID = new String("124");
        mockMvc.perform(delete("/users" + "/" + userID))
               .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteUserByIdDeleteExceptionMassageVerification() throws Exception {
        userID = new String("124");

        String badResponse = jsonUtil.toJson("Delete exception: there is no user with following userID: " + userID);

        mockMvc.perform(delete("/users" + "/" + userID))
                .andExpect(content().string(badResponse));
    }
}
