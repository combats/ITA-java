package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.User;
import com.softserveinc.ita.exception.UserIDNotFoundUserDaoMockException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest extends BaseUserDaoTest {

    @Autowired
    private UserDao userDao;

    /** Such strange construction with using reflection
     * are needed because I am testing void method with
     * JUnit rather than Mockito (all attempts to use Mockito
     * failed)
    **/
    @Test
    public void testDeleteUser() throws Exception {
        String muserID = "123";

        Set<String> keySet = new HashSet<>();
        Set<String> expected = new HashSet<>();
        expected.add("121");
        expected.add("122");

        userDao.deleteUser(muserID);

        userDao.getClass().getDeclaredField("db");

        Field fld = null;

        try {

            fld = userDao.getClass().getDeclaredField("db");
            fld.setAccessible(true);
            Map<String, User> mockDB = (Map<String, User>)fld.get(userDao);
            keySet = mockDB.keySet();

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expected, keySet);
    }

    @Test(expected = UserIDNotFoundUserDaoMockException.class)
    public void testDeleteUserAndExpectedIsException() throws UserIDNotFoundUserDaoMockException {
        String muserID = "124";

        userDao.deleteUser(muserID);
    }
}
