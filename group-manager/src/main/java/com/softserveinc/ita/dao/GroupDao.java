package com.softserveinc.ita.dao;

import com.softserveinc.ita.entity.Group;
import java.util.ArrayList;

public interface GroupDao {
    ArrayList<Group> getGroupsByStatus(String groupStatus);
}
