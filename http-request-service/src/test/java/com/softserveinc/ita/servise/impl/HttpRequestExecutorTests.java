package com.softserveinc.ita.servise.impl;
import com.softserveinc.ita.BaseHttpReqTest;
import com.softserveinc.ita.User;
import com.softserveinc.ita.service.HttpRequestExecutor;
import com.softserveinc.ita.service.exception.HttpRequestException;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class HttpRequestExecutorTests extends BaseHttpReqTest {

    @Autowired
    private HttpRequestExecutor service;

    @Autowired
    private JsonUtil utilJson;

    @Autowired
    private User user;

    private MockRestServiceServer mockServer;

    private List<String> listID;

    private  String subclass;

    @Before
    public void setUp() {

        mockServer = MockRestServiceServer.createServer(service.getRestTemplate());

        subclass = user.getClass().getSimpleName().toLowerCase();

        listID = new ArrayList<>();
        listID.add("1");
        listID.add("2");
        listID.add("3");
        listID.add("4");
        listID.add("5");
        listID.add("6");
    }

     @Test
    public void testGetAllObjectsIDShouldRetrurnValidListID() throws Exception {

        mockServer.expect(requestTo(service.getBaseUrl()+"/"+subclass+"s_id"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(utilJson.toJson(listID), MediaType.APPLICATION_JSON));

        assertEquals(listID, service.getAllObjectsID(user.getClass()));
        mockServer.verify();
    }


    @Test
    public void testGetObjectByIDShouldReturnValidObject() throws Exception {
        mockServer.expect(requestTo(service.getBaseUrl()+"/"+subclass+"s/"+user.getId()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(utilJson.toJson(user),MediaType.APPLICATION_JSON));

        assertEquals(user, service.getObjectByID(user.getId(),user.getClass()) );
        mockServer.verify();

    }

    @Test (expected = HttpRequestException.class )
    public void testGetObjectByIdShouldReturnException() throws Exception {
        mockServer.expect(requestTo(service.getBaseUrl()+"/"+subclass+"s/"+user.getId()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());

        //noinspection unchecked
        service.getObjectByID(user.getId(), user.getClass());
        mockServer.verify();
    }




}