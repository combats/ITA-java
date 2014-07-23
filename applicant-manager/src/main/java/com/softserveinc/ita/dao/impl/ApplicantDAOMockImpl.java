package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.entity.Applicant;
import com.softserveinc.ita.entity.ApplicantBenchmark;
import com.softserveinc.ita.entity.Group;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ApplicantDAOMockImpl implements ApplicantDAO {
    private Map<String, Group> db;
    private Map<String, ApplicantBenchmark> applicantsInAGroup;
    private Group group;
    private AtomicInteger idAutoGeneration = new AtomicInteger();
    private Hashtable<String, Applicant> applicants = new Hashtable<String, Applicant>();
    private List<Applicant> allApplicants = new ArrayList<>();

    public ApplicantDAOMockImpl() {

        Applicant app1 = new Applicant("Name", "Lastname", "email@mail.com", "1234567894568", 98736513843543L);
        app1.setId("id1");
        applicants.put("id1", app1);
        Applicant app2 = new Applicant("Andrey", "Makarevich", "email@mail.com", "1234567894568", 98736513843543L);
        app2.setId("id2");
        applicants.put("id2", app2);
        Applicant app3 = new Applicant("Alexandr", "Drux", "email@mail.com", "1234567894568", 98736513843543L);
        app3.setId("id3");
        applicants.put("id3", app3);
        Applicant app4 = new Applicant("Alexandr", "Masliakov", "email@mail.com", "1234567894568", 98736513843543L);
        app4.setId("id4");
        applicants.put("id4", app4);
        Applicant app5 = new Applicant("Michael", "Jackson", "email@mail.com", "1234567894568", 98736513843543L);
        app5.setId("id5");
        applicants.put("id5", app5);
        Applicant app6 = new Applicant("Albert", "Einstein", "email@mail.com", "1234567894568", 98736513843543L);
        app6.setId("id6");
        applicants.put("id6", app6);
        Applicant app7 = new Applicant("Sub", "Zero", "email@mail.com", "1234567894568", 98736513843543L);
        app7.setId("id7");
        applicants.put("id7", app7);
        Applicant app8 = new Applicant("Anatoliy", "Vasserman", "email@mail.com", "1234567894568", 98736513843543L);
        app8.setId("id8");
        applicants.put("id8", app8);

        allApplicants.add(app1);
        allApplicants.add(app2);
        allApplicants.add(app3);

        db = new HashMap<>();
        applicantsInAGroup = new HashMap<String, ApplicantBenchmark>();
        group = new Group("1");
        Applicant applicant1 = new Applicant("123");
        Applicant applicant2 = new Applicant("124");
        Applicant applicant3 = new Applicant("125");
        applicantsInAGroup.put(applicant1.getId(), new ApplicantBenchmark());
        applicantsInAGroup.put(applicant2.getId(), new ApplicantBenchmark());
        applicantsInAGroup.put(applicant3.getId(), new ApplicantBenchmark());
        group.setApplicants(applicantsInAGroup);
        db.put(group.getGroupID(), group);
    }

    @Override
    public List<Applicant> getApplicants() {
        return allApplicants;
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
        applicant.setId(newApplicantId);
        applicants.put(newApplicantId, applicant);
        return applicant;
    }

    @Override
    public Applicant editApplicant(Applicant applicant) {
        String applicantForEditId = applicant.getId();
        applicants.put(applicantForEditId, applicant);
        return applicant;
    }
}