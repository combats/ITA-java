package com.softserveinc.ita.DAO;

import com.softserveinc.ita.entity.Applicant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("applicantDAOImpl")
public class ApplicantDAOImpl implements ApplicantDAO {
    @Override
    public List<Applicant> getApplicants() {
        List<Applicant> applicants = new ArrayList<>();
        applicants.add(new Applicant("id1"));
        applicants.add(new Applicant("id2"));
        applicants.add(new Applicant("idX"));

        return applicants;
    }
}
