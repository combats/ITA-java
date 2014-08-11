package com.softserveinc.com.tests;

import com.softserveinc.com.pageobjects.*;
import org.junit.Test;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class DemoTestCase extends BaseUIIntegrationTest{
    private static final String DEMO_ADM_NAME = "Yuriy";
    private static final String DEMO_ADM_SURNAME = "Mutniy";
    private static final String DEMO_ADM_PASSWORD = "qwerty";
    private static final String DEMO_ADM_EMAIL = "nutniy.yuriy666@gmail.com";
    private static final String DEMO_ADM_PHONE = "6663337770";
    private static final String DEMO_ADM_ROLE = "ADMIN";

    private static final String DEMO_USER_NAME = "Tyan";
    private static final String DEMO_USER_SURNAME = "Ololoyeva";
    private static final String DEMO_USER_PASSWORD = "ololoyeva";
    private static final String DEMO_USER_EMAIL = "ololoyeva.tyan@yandex.net";
    private static final String DEMO_USER_PHONE = "1234567890";
    private static final String DEMO_USER_ROLE = "HR";

    private static final String DEMO_HR_NAME = "Ololosha";
    private static final String DEMO_HR_SURNAME = "Fuuuuuuuuu";
    private static final String DEMO_HR_PASSWORD = "fufufu";
    private static final String DEMO_HR_EMAIL = "ololosha.destroyer2000helyeah@gmail.com";
    private static final String DEMO_HR_PHONE = "0987654321";
    private static final String DEMO_HR_ROLE = "USER";

    private static final String HR_QUESTION_1 = "Tell me about yourself.";
    private static final String HR_QUESTION_2 = "Why should I hire you?";
    private static final String HR_QUESTION_3 = "What are your strengths and weaknesses?";
    private static final String EXP_QUESTION_1 = "What is JVM? Why is Java called the ‘Platform Independent Programming Language’?";
    private static final String EXP_QUESTION_2 = "What does the ‘static’ keyword mean?";
    private static final String EXP_QUESTION_3 = "What is Autoboxing and Unboxing?";
    private static final String EXP_QUESTION_4 = "What is the difference between STRINGBUFFER and STRING?";
    private static final String EXP_QUESTION_5 = "What is Function Over-Riding and Over-Loading in Java?";
    private static final String EXP_QUESTION_6 = "What is Constructors, Constructor Overloading in Java and Copy-Constructor?";
    private static final String EXP_QUESTION_7 = "What is Java Exception Handling? What is the difference between Errors, Unchecked Exception and Checked Exception?";
    private static final Integer[] MARK = {1, 2, 3, 4};

    private static final String GROUP_NAME = "DP-7";
    private static final String ADDRESS = "49000, Karl Marks boulevard, 22";
    private static final String COURSE = "Java";
    private static final String CAPACITY = "10";
    private static final String START_BOARDING_DATE = "08/12/2014";
    private static final String START_DATE = "08/30/2014"; // must be bigger than boarding date
    private static final String START_TIME = "11:00";
    private static final String END_DATE = "12/25/2014";

    private List<String> names = new ArrayList<>();
    private List<String> emails = new ArrayList<>();
    private List<String> firstNames = new ArrayList<>();
    private List<String> secNames = new ArrayList<>();
    private List<String> bDays = new ArrayList<>();

    private static final String PHONE_NUMB = "1234567890";


    @Test
    public void testAddAdmin() {
        getDrv().get(getSiteBase().toString());

        UiUsersPage usersPage = new LogInPage(getDrv(), getSiteBase()).logIn(LOGIN, PASSWORD).goToUsers()
                .addUser(DEMO_ADM_NAME, DEMO_ADM_SURNAME, DEMO_ADM_PASSWORD, DEMO_ADM_EMAIL, DEMO_ADM_PHONE,
                         DEMO_ADM_ROLE);

        assertTrue(getDrv().getPageSource().contains(DEMO_ADM_NAME + " " + DEMO_ADM_SURNAME));
        assertTrue(UiUsersPage.isAtUsersPage(getDrv(), getSiteBase()));

        usersPage.logOut();
    }

    @Test
    public void testAddUser() {
        getDrv().get(getSiteBase().toString());

        UiUsersPage usersPage = new LogInPage(getDrv(), getSiteBase()).logIn(DEMO_ADM_EMAIL, DEMO_ADM_PASSWORD)
                .goToUsers()
                .addUser(DEMO_USER_NAME, DEMO_USER_SURNAME, DEMO_USER_PASSWORD, DEMO_USER_EMAIL,
                         DEMO_USER_PHONE, DEMO_USER_ROLE);

        assertTrue(getDrv().getPageSource().contains(DEMO_USER_NAME + " " + DEMO_USER_SURNAME));
        assertTrue(UiUsersPage.isAtUsersPage(getDrv(), getSiteBase()));

        usersPage.logOut();
    }

    @Test
    public void testAddHr() {
        getDrv().get(getSiteBase().toString());

        UiUsersPage usersPage = new LogInPage(getDrv(), getSiteBase()).logIn(DEMO_ADM_EMAIL, DEMO_ADM_PASSWORD)
                .goToUsers()
                .addUser(DEMO_HR_NAME, DEMO_HR_SURNAME, DEMO_HR_PASSWORD, DEMO_HR_EMAIL, DEMO_HR_PHONE, DEMO_HR_ROLE);

        assertTrue(getDrv().getPageSource().contains(DEMO_HR_NAME + " " + DEMO_HR_SURNAME));
        assertTrue(UiUsersPage.isAtUsersPage(getDrv(), getSiteBase()));

        usersPage.logOut();
    }

    @Test
    public void testAddQuestionAsHr() {
        getDrv().get(getSiteBase().toString());

        UiQuestionsPage questionsPage = new LogInPage(getDrv(), getSiteBase()).logIn(DEMO_HR_EMAIL, DEMO_HR_PASSWORD)
                .goToQuestions()
                .addQuestion(HR_QUESTION_1, MARK[2].toString())
                .addQuestion(HR_QUESTION_2, MARK[3].toString())
                .addQuestion(HR_QUESTION_3, MARK[1].toString());

        assertTrue(getDrv().getPageSource().contains(HR_QUESTION_1));
        assertTrue(getDrv().getPageSource().contains(HR_QUESTION_2));
        assertTrue(getDrv().getPageSource().contains(HR_QUESTION_3));

        assertTrue(getDrv().getPageSource().contains(HR_QUESTION_1));
        assertTrue(getDrv().getPageSource().contains(HR_QUESTION_2));
        assertTrue(getDrv().getPageSource().contains(HR_QUESTION_3));

        questionsPage.logOut();
    }

    @Test
    public void testAddQuestionAsExpert() {
        getDrv().get(getSiteBase().toString());

        UiQuestionsPage questionsPage = new LogInPage(getDrv(), getSiteBase()).logIn(DEMO_USER_EMAIL, DEMO_USER_PASSWORD)
                .goToQuestions()
                .addQuestion(EXP_QUESTION_1, MARK[3].toString())
                .addQuestion(EXP_QUESTION_2, MARK[3].toString())
                .addQuestion(EXP_QUESTION_3, MARK[3].toString())
                .addQuestion(EXP_QUESTION_4, MARK[2].toString())
                .addQuestion(EXP_QUESTION_5, MARK[2].toString())
                .addQuestion(EXP_QUESTION_6, MARK[2].toString())
                .addQuestion(EXP_QUESTION_7, MARK[3].toString());

        assertTrue(getDrv().getPageSource().contains(EXP_QUESTION_1));
        assertTrue(getDrv().getPageSource().contains(EXP_QUESTION_2));
        assertTrue(getDrv().getPageSource().contains(EXP_QUESTION_3));
        assertTrue(getDrv().getPageSource().contains(EXP_QUESTION_4));
        assertTrue(getDrv().getPageSource().contains(EXP_QUESTION_5));
        assertTrue(getDrv().getPageSource().contains(EXP_QUESTION_6));
        assertTrue(getDrv().getPageSource().contains(EXP_QUESTION_7));

        questionsPage.logOut();
    }

    @Test
    public void testAddGroupAsHr() {
        getDrv().get(getSiteBase().toString());

        UiGroupsPage groupsPage = new LogInPage(getDrv(), getSiteBase()).logIn(DEMO_HR_EMAIL, DEMO_HR_PASSWORD)
                .goToGroups()
                .addGroup(GROUP_NAME, ADDRESS, COURSE, CAPACITY, START_BOARDING_DATE, START_DATE, START_TIME, END_DATE);

        assertTrue(getDrv().getPageSource().contains(GROUP_NAME));
        assertTrue(UiGroupsPage.isAtGroupsPage(getDrv(), getSiteBase()));

        groupsPage.logOut();
    }

    @Test
    public void testAddApplicant() {
        getDrv().get(getSiteBase().toString());
        String extension = "docx";
        String resName = "CV/testCV." + extension;
        URL url = this.getClass().getResource("/" + resName);
        String ApplCv = url.toString();

        setApplicants(Integer.valueOf(CAPACITY));

//        new LogInPage(getDrv(), getSiteBase()).logIn(DEMO_HR_EMAIL, DEMO_HR_PASSWORD).goToGroups()
//                .goToApplicants(GROUP_NAME)
//                .createApplicant(firstNames.get(0), secNames.get(0), PHONE_NUMB, emails.get(0), bDays.get(0), ApplCv)
//                .createApplicant(firstNames.get(1), secNames.get(1), PHONE_NUMB, emails.get(1), bDays.get(1), ApplCv)
//                .createApplicant(firstNames.get(2), secNames.get(2), PHONE_NUMB, emails.get(2), bDays.get(2), ApplCv)
//                .createApplicant(firstNames.get(3), secNames.get(3), PHONE_NUMB, emails.get(3), bDays.get(3), ApplCv)
//                .createApplicant(firstNames.get(4), secNames.get(4), PHONE_NUMB, emails.get(4), bDays.get(4), ApplCv)
//                .createApplicant(firstNames.get(5), secNames.get(5), PHONE_NUMB, emails.get(5), bDays.get(5), ApplCv)
//                .createApplicant(firstNames.get(6), secNames.get(6), PHONE_NUMB, emails.get(6), bDays.get(6), ApplCv)
//                .createApplicant(firstNames.get(7), secNames.get(7), PHONE_NUMB, emails.get(7), bDays.get(7), ApplCv)
//                .createApplicant(firstNames.get(8), secNames.get(8), PHONE_NUMB, emails.get(8), bDays.get(8), ApplCv)
//                .createApplicant(firstNames.get(9), secNames.get(9), PHONE_NUMB, emails.get(9), bDays.get(9), ApplCv);








//
//        UIApplicantsPage applicant1 = new LogInPage(getDrv(), getSiteBase()).logIn(DEMO_HR_EMAIL, DEMO_HR_PASSWORD).goToGroups()
//                .goToApplicants(GROUP_NAME)
//                .createApplicant(firstNames.get(0), secNames.get(0), PHONE_NUMB, emails.get(0), bDays.get(0), ApplCv);
//        assertTrue(getDrv().getPageSource().contains(names.get(0)));
//        applicant1.logOut();
//
//        UIApplicantsPage applicant2 = new LogInPage(getDrv(), getSiteBase()).logIn(DEMO_HR_EMAIL, DEMO_HR_PASSWORD).goToGroups()
//                .goToApplicants(GROUP_NAME)
//                .createApplicant(firstNames.get(1), secNames.get(1), PHONE_NUMB, emails.get(1), bDays.get(1), ApplCv);
//        assertTrue(getDrv().getPageSource().contains(names.get(1)));
//        applicant2.logOut();

        UIApplicantsPage applicant3 = new LogInPage(getDrv(), getSiteBase()).logIn(DEMO_HR_EMAIL, DEMO_HR_PASSWORD).goToGroups()
                .goToApplicants(GROUP_NAME)
                .createApplicant(firstNames.get(2), secNames.get(2), PHONE_NUMB, emails.get(2), bDays.get(2), ApplCv);
        assertTrue(getDrv().getPageSource().contains(names.get(2)));
        applicant3.logOut();

        UIApplicantsPage applicant4 = new LogInPage(getDrv(), getSiteBase()).logIn(DEMO_HR_EMAIL, DEMO_HR_PASSWORD).goToGroups()
                .goToApplicants(GROUP_NAME)
                .createApplicant(firstNames.get(3), secNames.get(3), PHONE_NUMB, emails.get(3), bDays.get(3), ApplCv);
        assertTrue(getDrv().getPageSource().contains(names.get(3)));
        applicant4.logOut();

        UIApplicantsPage applicant5 = new LogInPage(getDrv(), getSiteBase()).logIn(DEMO_HR_EMAIL, DEMO_HR_PASSWORD).goToGroups()
                .goToApplicants(GROUP_NAME)
                .createApplicant(firstNames.get(4), secNames.get(4), PHONE_NUMB, emails.get(4), bDays.get(4), ApplCv);
        assertTrue(getDrv().getPageSource().contains(names.get(4)));
        applicant5.logOut();

        UIApplicantsPage applicant6 = new LogInPage(getDrv(), getSiteBase()).logIn(DEMO_HR_EMAIL, DEMO_HR_PASSWORD).goToGroups()
                .goToApplicants(GROUP_NAME)
                .createApplicant(firstNames.get(5), secNames.get(5), PHONE_NUMB, emails.get(5), bDays.get(5), ApplCv);
        assertTrue(getDrv().getPageSource().contains(names.get(5)));
        applicant6.logOut();

        UIApplicantsPage applicant7 = new LogInPage(getDrv(), getSiteBase()).logIn(DEMO_HR_EMAIL, DEMO_HR_PASSWORD).goToGroups()
                .goToApplicants(GROUP_NAME)
                .createApplicant(firstNames.get(6), secNames.get(6), PHONE_NUMB, emails.get(6), bDays.get(6), ApplCv);
        assertTrue(getDrv().getPageSource().contains(names.get(6)));
        applicant7.logOut();

        UIApplicantsPage applicant8 = new LogInPage(getDrv(), getSiteBase()).logIn(DEMO_HR_EMAIL, DEMO_HR_PASSWORD).goToGroups()
                .goToApplicants(GROUP_NAME)
                .createApplicant(firstNames.get(7), secNames.get(7), PHONE_NUMB, emails.get(7), bDays.get(7), ApplCv);
        assertTrue(getDrv().getPageSource().contains(names.get(7)));
        applicant8.logOut();

        UIApplicantsPage applicant9 = new LogInPage(getDrv(), getSiteBase()).logIn(DEMO_HR_EMAIL, DEMO_HR_PASSWORD).goToGroups()
                .goToApplicants(GROUP_NAME)
                .createApplicant(firstNames.get(8), secNames.get(8), PHONE_NUMB, emails.get(8), bDays.get(8), ApplCv);
        assertTrue(getDrv().getPageSource().contains(names.get(8)));
        applicant9.logOut();

        UIApplicantsPage applicant10 = new LogInPage(getDrv(), getSiteBase()).logIn(DEMO_HR_EMAIL, DEMO_HR_PASSWORD).goToGroups()
                .goToApplicants(GROUP_NAME)
                .createApplicant(firstNames.get(9), secNames.get(9), PHONE_NUMB, emails.get(9), bDays.get(9), ApplCv);
        assertTrue(getDrv().getPageSource().contains(names.get(9)));
        applicant10.logOut();


    }


    public void setApplicants(int capacity) {
        String name;
        String surname;
        String email;
        String bday;
        for(int i =0; i < capacity; i++) {
            name = FirstName.getRandomFirstName().toString();
            surname = Surname.getRandomSurname().toString();
            email = name + "." + surname + i + i + "@" + Email.getRandomEmail().toString() + ".com";

            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Calendar cal = Calendar.getInstance();
            bday = dateFormat.format(cal.getTime());

            firstNames.add(i, name);
            secNames.add(i, surname);
            bDays.add(i, bday);

            //
            names.add(i, name + " " + surname);
            emails.add(i, email);
            //
        }
    }


    /**
     * First name enum.
     */
    private enum FirstName {
        Nastya, Dima, Anastasia, Nikita, Dasha, Alex, Anna, Vlad, Olga, Andrey, Maria, Alexander, Julia, Artem, Alina,
        Sergey, Marina, Anton, Polina, Ivan;

        /**
         * Pick a random value of the FirstName enum.
         * @return a random first name.
         */
        public static FirstName getRandomFirstName() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }

    /**
     * Surname enum.
     */
    private enum Surname {
        Melnyk, Shevchenko, Boyko, Kovalenko, Bondarenko, Tkachenko, Kovalchuk, Kravchenko, Oliynyk, Shevchuk, Koval,
        Polishchuk, Bondar, Tkachuk, Moroz, Marchenko, Lysenko, Rudenko, Savchenko, Petrenko;

        public static Surname getRandomSurname() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }

    /**
     * Email enum.
     */
    private enum Email {
        yahoo, gmail, hotmail, aol, comcast, verizome, i, mail, bigmir, yandex;

        public static Email getRandomEmail() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }
}
