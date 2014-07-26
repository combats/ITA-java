

INSERT INTO interview.Roles (Id, Name) VALUES ('1', 'ADMIN');
INSERT INTO interview.Roles (Id, Name) VALUES ('2', 'USER');
INSERT INTO interview.Roles (Id, Name) VALUES ('3', 'HR');

INSERT INTO interview.Users (Id, Active, Email, Name, Password, Phone, Surname, role_Id) VALUES ('40288199476e716901476e73c90c0000', 1, 'lucky@strike.com', 'PutinX', 'eed346aede1c64193b74d7264a809c3e', '0634942253', 'Hccc', null);
INSERT INTO interview.Users (Id, Active, Email, Name, Password, Phone, Surname, role_Id) VALUES ('40288199476e716901476e744ac20001', 1, 'voryi@ybivai.com', 'Jon', 'eed346aede1c64193b74d7264a809c3e', '0634942253', 'Smith', null);
INSERT INTO interview.Users (Id, Active, Email, Name, Password, Phone, Surname, role_Id) VALUES ('40288199476e716901476e74b9fc0003', 1, 'voryi2@ybivai.com', 'Mike', 'eed346aede1c64193b74d7264a809c3e', '0634942253', 'Tyson', null);
INSERT INTO interview.Users (Id, Active, Email, Name, Password, Phone, Surname, role_Id) VALUES ('40288199476e716901476e7511360005', 1, 'voryi3@ybivai.com', 'Alla', 'eed346aede1c64193b74d7264a809c3e', '0634942253', 'Cruper', null);


INSERT INTO interview.Course (Id, ImageReference, Name) VALUES ('297ea2cd476e53d001476e540a5e0111', 'TestRefOne2', 'TestCourseOne1');
INSERT INTO interview.Course (Id, ImageReference, Name) VALUES ('297ea2cd476e53d001476e540a5f0012', 'TestRefTwo', 'TestCourseTwo');
INSERT INTO interview.Course (Id, ImageReference, Name) VALUES ('297ea2cd476e53d001476e540a600013', 'TestRefThree', 'TestCourseThree');
INSERT INTO interview.Course (Id, ImageReference, Name) VALUES ('297ea2cd476e53d001476e540f350015', 'TestRef', 'TestCourseName');
INSERT INTO interview.Course (Id, ImageReference, Name) VALUES ('297ea2cd476e53d001476e541500001c', '', 'TestCourse');



INSERT INTO interview.Interview (interview_id, InterviewType) VALUES ('1', 0);
INSERT INTO interview.Interview (interview_id, InterviewType) VALUES ('2', 0);
