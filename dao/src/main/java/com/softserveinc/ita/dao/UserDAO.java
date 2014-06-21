package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.User;

/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 21.06.14
 * Time: 15:13
 * To change this template use File | Settings | File Templates.
 */
public interface UserDAO {
    User getUserByID (String UserID);
}
