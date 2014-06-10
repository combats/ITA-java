package service;

import com.softserveinc.ita.entity.User;

/**
 * Created by Anton on 04.06.2014.
 */

//interface for future business logic realization.
public interface UserService {
    User getUserByID (String UserID);
}
