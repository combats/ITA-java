package com.softserveinc.ita.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(Suite.class)
@Suite.SuiteClasses({UserDAOTests.class, ApplicantDAOTests.class, AppointmentDAOTests.class})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public abstract class BaseDAOTest {
}
