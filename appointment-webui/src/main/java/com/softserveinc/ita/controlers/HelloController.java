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
                add(new Group(Group.Status.IN_PROGRESS, "id1", Group.Course.DEVOPS, "kv001"));
                add(new Group(Group.Status.RECRUITMENT, "id2", Group.Course.JAVA, "kv002"));
                add(new Group(Group.Status.RECRUITMENT, "id3", Group.Course.JAVA, "kv003"));
                add(new Group(Group.Status.IN_PROGRESS, "id4", Group.Course.SHARP, "kv004"));
            }
        };
        for (Group group : groups) {
            if (id.equals(group.getGroupID())) {
                return group;
            }
        }
        return new Group(Group.Status.IN_PROGRESS, "id0", Group.Course.DEVOPS,"kv001");
    }

    @RequestMapping(value = "/groups/{status}", method = RequestMethod.GET)
    public
    @ResponseBody
    ArrayList<Group> getGroupsByStatus(@PathVariable String status) {
        ArrayList<Group> groups = new ArrayList() {
            {
                add(new Group(Group.Status.IN_PROGRESS, "id1", Group.Course.DEVOPS,"kv001"));
                add(new Group(Group.Status.RECRUITMENT, "id2", Group.Course.SHARP,"kv041"));
                add(new Group(Group.Status.RECRUITMENT, "id3", Group.Course.JAVA,"kv021"));
                add(new Group(Group.Status.IN_PROGRESS, "id4", Group.Course.SHARP,"kv012"));
                add(new Group(Group.Status.RECRUITMENT, "id5", Group.Course.JAVASCRIPT,"kv054"));
                add(new Group(Group.Status.IN_PROGRESS, "id6", Group.Course.JAVASCRIPT,"kv061"));
                add(new Group(Group.Status.RECRUITMENT, "id7", Group.Course.DEVOPS,"kv068"));
                add(new Group(Group.Status.RECRUITMENT, "id8", Group.Course.JAVA,"kv075"));
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String redirect(){
        return "userList";
    }

}

