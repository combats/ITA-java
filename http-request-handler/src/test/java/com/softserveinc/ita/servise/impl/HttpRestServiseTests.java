package com.softserveinc.ita.servise.impl;

import com.softserveinc.ita.BaseHttpReqTest;
import com.softserveinc.ita.User;
import com.softserveinc.ita.servise.HttpRestService;
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
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class HttpRestServiseTests extends BaseHttpReqTest {



    private MockRestServiceServer mockServer;

    @Autowired
    private HttpRestService service;

    @Autowired
    private JsonUtil utilJson;


    private User user;
    private List<Integer> listID;
    private  String subclass;

    @Before
    public void setUp() {

        mockServer = MockRestServiceServer.createServer(service.getRestTemplate()); // (1)
        user = new User(23,"Mike","Jonson");
        listID = new LinkedList<Integer>();
        subclass = user.getClass().getSimpleName().toLowerCase();
        listID.add(new Integer(1));
        listID.add(new Integer(2));
        listID.add(new Integer(3));
        listID.add(new Integer(4));
        listID.add(new Integer(5));
        listID.add(new Integer(6));


    }

     @Test
    public void testGetAllObjectsID() throws Exception {

        mockServer.expect(requestTo(service.getBaseUrl()+"/"+subclass+"/"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(utilJson.toJson(listID), MediaType.TEXT_PLAIN));

        assertEquals(listID, service.getAllObjectsID(user.getClass()));
        mockServer.verify();


    }


    @Test
    public void testGetObjectByID() throws Exception {
        mockServer.expect(requestTo(service.getBaseUrl()+"/"+subclass+"/id="+user.getId()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(utilJson.toJson(user), MediaType.TEXT_PLAIN));

        assertEquals(user, service.getObjectByID(user.getId(),user.getClass()));
        mockServer.verify();

    }
}