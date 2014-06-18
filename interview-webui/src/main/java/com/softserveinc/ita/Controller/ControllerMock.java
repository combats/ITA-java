package com.softserveinc.ita.Controller;

import com.softserveinc.ita.entity.Group;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/groups")
public class ControllerMock {

    @RequestMapping(value = "{groupStatus}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ArrayList<Group> getGroupsByStatus() {
        ArrayList<Group> groups = new ArrayList<Group>() {
            {
                add(new Group(Group.Status.STATUS1, "1", Group.Course.JAVA));
                add(new Group(Group.Status.STATUS1, "2", Group.Course.PYTHON));
                add(new Group(Group.Status.STATUS1, "3", Group.Course.JAVA));
                add(new Group(Group.Status.STATUS1, "4", Group.Course.SHARP));
            }
        };
        return groups;
    }
}
