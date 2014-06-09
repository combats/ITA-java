package com.softserveinc.ita.servise.impl;
import com.softserveinc.ita.BaseHttpReqTest;
import com.softserveinc.ita.User;
import com.softserveinc.ita.servise.HttpRequestHandler;
import com.softserveinc.ita.servise.exeption.HttpHandlerRequestException;
import com.softserveinc.ita.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class HttpRequestHandlerTests extends BaseHttpReqTest {



    private MockRestServiceServer mockServer;

    @Autowired
    private HttpRequestHandler service;

    @Autowired
    private JsonUtil utilJson;

    @Autowired
    private User user;

    private List<String> listID;
    private  String subclass;

    @Before
    public void setUp() {

        mockServer = MockRestServiceServer.createServer(service.getRestTemplate()); // (1)

        listID = new LinkedList<>();
        subclass = user.getClass().getSimpleName().toLowerCase();
        listID.add("1");
        listID.add("2");
        listID.add("3");
        listID.add("4");
        listID.add("5");
        listID.add("6");


    }

     @Test
    public void testGetAllObjectsID() throws Exception {

        mockServer.expect(requestTo(service.getBaseUrl()+"/"+subclass+"s_id"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(utilJson.toJson(listID), MediaType.TEXT_PLAIN));

        assertEquals(listID, service.getAllObjectsID(user.getClass()));
        mockServer.verify();


    }


    @Test
    public void testGetObjectByID() throws Exception {
        mockServer.expect(requestTo(service.getBaseUrl()+"/"+subclass+"s/"+user.getId()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(utilJson.toJson(user),MediaType.APPLICATION_JSON));

        assertEquals(user, service.getObjectByID(user.getId(),user.getClass()) );
        mockServer.verify();

    }

    @Test (expected = HttpHandlerRequestException.class )
    public void testGetObjectByIdAndReturnException() throws Exception {
        mockServer.expect(requestTo(service.getBaseUrl()+"/"+subclass+"s/"+user.getId()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());


        //noinspection unchecked
        service.getObjectByID(user.getId(), user.getClass());


        mockServer.verify();

    }




}