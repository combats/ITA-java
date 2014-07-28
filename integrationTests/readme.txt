How to run integration tests locally?

Very easy. First of all, you need to run TomCat for module that you want to test.
Do not forget to specify correct Application context for each module for artifact:

1) interview-sheduller: /appointments;
2) applicants-manager: /applicants;
3) user-manager: /users;
4) interviewFactory: /interviews.

Do not start at TomCat all modules at once, you may get an OutOfMemory error.
To avoid this error, run each test one by one. Choose correct module for each test:

1) ApplicantIntegrationTests: applicant-manager;
2) AppointmentIntegrationTests: applicant-manager, user-manager, interview-sheduller;
3) InterviewManagerIntegrationTests: interviewFactory;
4) UserIntegrationTests: user-manager.

Locally all tests pass successfully. But I temporarily excluded these tests from maven.