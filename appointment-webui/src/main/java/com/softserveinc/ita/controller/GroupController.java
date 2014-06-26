package com.softserveinc.ita.controller;

import com.softserveinc.ita.entity.Course;
import com.softserveinc.ita.entity.Group;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class GroupController {

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome() {
        return "groups";
    }

    @RequestMapping(value = "/groups/{status}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    ArrayList<Group> getGroupsByStatus(@PathVariable String status) {
        ArrayList<Group> groups = new ArrayList<Group>();
        groups.add(new Group(Group.Status.IN_PROCESS, "id1", new Course("Java", "pen-java.png"), "kv001"));
        groups.add(new Group(Group.Status.OFFERING, "id2", new Course("Sharp", "pen-net.png"), "kv041"));
        groups.add(new Group(Group.Status.BOARDING, "id3", new Course("Java", "pen-java.png"), "kv021"));
        groups.add(new Group(Group.Status.IN_PROCESS, "id4", new Course("Sharp", "pen-net.png"), "kv012"));
        groups.add(new Group(Group.Status.OFFERING, "id5", new Course("Java Script", "pen-net.png"), "kv054"));
        groups.add(new Group(Group.Status.BOARDING, "id6", new Course("Java Script", "pen-net.png"), "kv061"));
        groups.add(new Group(Group.Status.OFFERING, "id7", new Course("DevOps", "pen-devops.png"), "kv068"));
        groups.add(new Group(Group.Status.OFFERING, "id8", new Course("DevOps", "pen-devops.png"), "kv075"));
        groups.add(new Group(Group.Status.BOARDING, "id9", new Course("Java", "pen-java.png"), "kv041"));
        groups.add(new Group(Group.Status.IN_PROCESS, "id10", new Course("Sharp", "pen-net.png"), "kv064"));
        groups.add(new Group(Group.Status.OFFERING, "id11", new Course("Java Script", "pen-net.png"), "kv123"));
        groups.add(new Group(Group.Status.IN_PROCESS, "id12", new Course("Java Script", "pen-net.png"), "kv532"));
        groups.add(new Group(Group.Status.BOARDING, "id13", new Course("DevOps", "pen-devops.png"), "kv0753"));
        groups.add(new Group(Group.Status.OFFERING, "id14", new Course("DevOps", "pen-devops.png"), "kv112"));
        ArrayList<Group> chosenByStatusGroups = new ArrayList<Group>();
        for (Group group : groups) {
            if (status.equals(group.getGroupStatus().getName())) {
                chosenByStatusGroups.add(group);
            }
        }
        return chosenByStatusGroups;
    }


    @RequestMapping(value = "/groups/list/{groupId}", method = RequestMethod.GET)
    public String redirect(@PathVariable String groupId) {
        return "userList";
    }

    @RequestMapping(value = "/groups/create", method = RequestMethod.GET)
    public String redirect() {
        return "createGroup";
    }
}

