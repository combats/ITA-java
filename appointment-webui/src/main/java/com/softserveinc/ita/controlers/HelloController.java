package com.softserveinc.ita.controlers;

import com.softserveinc.ita.entity.Group;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class HelloController {
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome() {
        return "groups";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Group getGroupById(@PathVariable String id) {
        ArrayList<Group> groups = new ArrayList() {
            {
                add(new Group(Group.Status.STATUS1, "id1", Group.Course.DEVOPS));
                add(new Group(Group.Status.STATUS2, "id2", Group.Course.JAVA));
                add(new Group(Group.Status.STATUS2, "id3", Group.Course.JAVA));
                add(new Group(Group.Status.STATUS1, "id4", Group.Course.SHARP));
            }
        };
        for (Group group : groups) {
            if (id.equals(group.getGroupID())) {
                return group;
            }
        }
        return new Group(Group.Status.STATUS1, "id0", Group.Course.DEVOPS);
    }

    @RequestMapping(value = "/groups/{status}", method = RequestMethod.GET)
    public
    @ResponseBody
    ArrayList<Group> getGroupsByStatus(@PathVariable String status) {
        ArrayList<Group> groups = new ArrayList() {
            {
                add(new Group(Group.Status.STATUS1, "id1", Group.Course.DEVOPS));
                add(new Group(Group.Status.STATUS2, "id2", Group.Course.SHARP));
                add(new Group(Group.Status.STATUS2, "id3", Group.Course.JAVA));
                add(new Group(Group.Status.STATUS1, "id4", Group.Course.SHARP));
                add(new Group(Group.Status.STATUS2, "id5", Group.Course.JAVASCRIPT));
                add(new Group(Group.Status.STATUS1, "id6", Group.Course.JAVASCRIPT));
                add(new Group(Group.Status.STATUS2, "id7", Group.Course.DEVOPS));
                add(new Group(Group.Status.STATUS2, "id8", Group.Course.JAVA));
            }
        };
        ArrayList<Group> chosenByStatusGroups = new ArrayList<Group>();
        for (Group group : groups) {
            if (status.equals(group.getGroupStatus().getName())) {
                chosenByStatusGroups.add(group);
            }
        }
        return chosenByStatusGroups;
    }

}

