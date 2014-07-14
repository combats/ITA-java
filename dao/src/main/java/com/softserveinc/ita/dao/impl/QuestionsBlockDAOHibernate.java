package com.softserveinc.ita.dao.impl;

import com.softserveinc.ita.dao.QuestionsBlockDAO;
import com.softserveinc.ita.entity.Appointment;
import com.softserveinc.ita.entity.QuestionInformation;
import com.softserveinc.ita.entity.QuestionsBlock;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: Вадим
 * Date: 11.07.14
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class QuestionsBlockDAOHibernate implements QuestionsBlockDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public QuestionsBlock getQuestionsBlockFromInterviewByQuestionsBlockId(String questionsBlockId) {
        return (QuestionsBlock) sessionFactory.getCurrentSession().load(QuestionsBlock.class, questionsBlockId);
    }

    @Override
    public String updateQuestionsBlock(QuestionsBlock questionsBlock) {
        sessionFactory.getCurrentSession().update(questionsBlock);
        return questionsBlock.getId();
    }

    @Override
    public QuestionsBlock getQuestionsBlockByInterviewIdAndUserId(String userID, String appointmentId) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(QuestionsBlock.class)
                .setProjection(Projections.projectionList().add(Projections.property("UserId"), userID).add(Projections.property("InterviewId"), appointmentId));
        String id = (String) criteria.setProjection(Projections.property("id")).uniqueResult();
        return (QuestionsBlock) sessionFactory.getCurrentSession().load(QuestionsBlock.class, id);
    }

    @Override
    public QuestionsBlock getStandardQuestionsBlockFromInterview(String interviewId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public QuestionsBlock getStandardQuestionsBlock() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setStandardQuestionsBlock(QuestionsBlock standardQuestionsBlock) {
    }

}
