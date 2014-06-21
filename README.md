I had a few tasks to do:

1) Create Interview Entity;
2) Create Interview factory. I want to be able to create several types of interview - without questions; only with standard questions; with user and standard questions.
3) Create dao, service and controller for Interview.
4) Create "addNewInterview" and "getInterviewByApplicantId" in service, controller and dao.
5) Create question entity, question information entity and question block entity.
Question information - with question, answer, mark, comment etc.
Question block - block of questions (in practice List<QuestionsInformation>) from each user.
Now we can also create standard questions for every user.
6) I merged my last task - "editAppointmentByAppointmentId" with this one. Now I think I can delete this branch from pull request.

Vadim Naumenko.