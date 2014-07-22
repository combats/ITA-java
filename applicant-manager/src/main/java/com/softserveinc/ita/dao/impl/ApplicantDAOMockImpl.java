package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.ApplicantDAO;
import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.Group;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ApplicantDAOMockImpl implements ApplicantDAO {
    private Map<String, Group> db;
    private List<Integer> applicantsInAGroup;
    private Group group;
    private AtomicInteger idAutoGeneration = new AtomicInteger(50);
    private Hashtable<String, Applicant> applicants = new Hashtable<String, Applicant>();
    private List<Applicant> allApplicants = new ArrayList<>();

    public ApplicantDAOMockImpl() {
        Applicant app1 = new Applicant("1", "Alexander", "Druz", "9379992",
                "druz@gmail.com", 616527516676L);
        Applicant app2 =
                new Applicant("2", "Andrey", "Makarevich", "0671233215",
                        "makarevich@gmail.com", 616527516676L);
        Applicant app3 =
                new Applicant("3", "Anatoliy", "Vasserman", "111111",
                        "vasserman@gmail.com", 616527516676L);
        Applicant app4 =
                new Applicant("4", "Nikita", "Dzhigurda", "1319758",
                        "dzhigurda@gmail.com", 616527516676L);
        Applicant app5 =
                new Applicant("5", "Alexandr", "Maslyakov", "368413",
                        "kvn@gmail.com", 616527516676L);
        Applicant app6 =
                new Applicant("6", "Michael", "Jackson", "7894395",
                        "MJ@gmail.com", 616527516676L);
        Applicant app7 =
                new Applicant("7", "Tim", "Howard", "16357453",
                        "Howard@gmail.com", 616527516676L);
        Applicant app8 =
                new Applicant("8", "Albert", "Einstein", "16357453",
                        "a.einsteind@gmail.com", 616527516676L);
        Collections.addAll(allApplicants, app1, app2, app3, app4, app5, app6, app7);

        applicants.put("1", app1);
        applicants.put("2", app2);
        applicants.put("3", app3);
        applicants.put("4", app4);
        applicants.put("5", app5);
        applicants.put("6", app6);
        applicants.put("7", app7);
        applicants.put("8", app8);
        db = new HashMap<>();
        applicantsInAGroup = new ArrayList<>();
//        group = new Group("1", null, "KV", 10);
        applicantsInAGroup.add(1);
        applicantsInAGroup.add(2);
        applicantsInAGroup.add(3);
//        group.setApplicantsInGroup(applicantsInAGroup);
//        db.put(group.getID(), group);
    }

    @Override
    public List<Applicant> getApplicants() {
        return allApplicants;
    }

    @Override
    public List<Integer> getApplicantsByGroupID(String groupID) {

//        if (db.containsKey(groupID)) {
//            return (db.get(groupID)).getApplicantsInGroup();
//        } else {
        return new ArrayList<>();
//        }
    }

    @Override
    public Applicant getApplicantById(String applicantId) {
        return applicants.get(applicantId);
    }

    @Override
    public Applicant addNewApplicant(Applicant applicant) {
        /**
         *  because of Applicant pojo doesn't contain any fields except id now, validation
         *  for not having equal object in DB has not been implemented
         **/
        String newApplicantId = String.valueOf(idAutoGeneration.incrementAndGet());
        applicant.setID(newApplicantId);
        applicants.put(newApplicantId, applicant);
        return applicant;
    }

    @Override
    public Applicant editApplicant(Applicant applicant) {
        String applicantForEditId = applicant.getID();
        applicants.put(applicantForEditId, applicant);
        return applicant;
    }
}