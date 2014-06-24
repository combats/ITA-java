package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.DaoGroupBaseTest;
import com.softserveinc.ita.dao.GroupDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DaoGroupTest extends DaoGroupBaseTest {

    @Autowired
    private GroupDao groupDao;

    @Test
    public void getGroupByStatusAndExpectRightList(){

    }
}
