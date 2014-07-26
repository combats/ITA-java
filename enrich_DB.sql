

INSERT INTO interview.Roles (Id, Name) VALUES ('1', 'ADMIN');
INSERT INTO interview.Roles (Id, Name) VALUES ('2', 'USER');
INSERT INTO interview.Roles (Id, Name) VALUES ('3', 'HR');

INSERT INTO interview.Users (Id, Active, Email, Name, Password, Phone, Surname, role_Id) VALUES ('40288199476e716901476e73c90c0000', 1, 'lucky@strike.com', 'PutinX', 'eed346aede1c64193b74d7264a809c3e', '0634942253', 'Hccc', '1');
INSERT INTO interview.Users (Id, Active, Email, Name, Password, Phone, Surname, role_Id) VALUES ('40288199476e716901476e744ac20001', 1, 'voryi@ybivai.com', 'Jon', 'eed346aede1c64193b74d7264a809c3e', '0634942253', 'Smith', '2');
INSERT INTO interview.Users (Id, Active, Email, Name, Password, Phone, Surname, role_Id) VALUES ('40288199476e716901476e74b9fc0003', 1, 'voryi2@ybivai.com', 'Mike', 'eed346aede1c64193b74d7264a809c3e', '0634942253', 'Tyson', '3');
INSERT INTO interview.Users (Id, Active, Email, Name, Password, Phone, Surname, role_Id) VALUES ('40288199476e716901476e7511360005', 1, 'voryi3@ybivai.com', 'Alla', 'eed346aede1c64193b74d7264a809c3e', '0634942253', 'Cruper', '1');
INSERT INTO interview.Users (Id, Active, Email, Name, Password, Phone, Surname, role_Id) VALUES ('40288199477288a50147729125b00000', 1, 'dod@dod.com', 'Douche', 'eed346aede1c64193b74d7264a809c3e', '0634942253', 'Bag', 1);

INSERT INTO interview.Applicants (Id, Email, GroupId, Name, Surname) VALUES ('1', 'Joe@gmail.com', 'id1', 'Joe1', 'Terison1');
INSERT INTO interview.Applicants (Id, Email, GroupId, Name, Surname) VALUES ('2', 'ivan@gmail.com', 'id1', 'Joe2', 'Terison2');
INSERT INTO interview.Applicants (Id, Email, GroupId, Name, Surname) VALUES ('3', 'rastaman@gmail.com', 'id1', 'Joe3', 'Terison3');
INSERT INTO interview.Applicants (Id, Email, GroupId, Name, Surname) VALUES ('4', 'vasiliy@gmail.com', 'id1', 'Joe4', 'Terison4');
INSERT INTO interview.Applicants (Id, Email, GroupId, Name, Surname) VALUES ('5', 'nikolay@gmail.com', 'id1', 'Joe5', 'Terison5');
INSERT INTO interview.Applicants (Id, Email, GroupId, Name, Surname) VALUES ('6', 'denis@gmail.com', 'id1', 'Joe6', 'Terison6');
INSERT INTO interview.Applicants (Id, Email, GroupId, Name, Surname) VALUES ('7', 'andrey@gmail.com', 'id1', 'Joe7', 'Terison7');
INSERT INTO interview.Applicants (Id, Email, GroupId, Name, Surname) VALUES ('8', 'anton@gmail.com', 'id1', 'Joe8', 'Terison8');

INSERT INTO interview.Course (Id, ImageReference, Name) VALUES ('297ea2cd476e53d001476e540a5e0111', 'TestRefOne2', 'TestCourseOne1');
INSERT INTO interview.Course (Id, ImageReference, Name) VALUES ('297ea2cd476e53d001476e540a5f0012', 'TestRefTwo', 'TestCourseTwo');
INSERT INTO interview.Course (Id, ImageReference, Name) VALUES ('297ea2cd476e53d001476e540a600013', 'TestRefThree', 'TestCourseThree');
INSERT INTO interview.Course (Id, ImageReference, Name) VALUES ('297ea2cd476e53d001476e540f350015', 'TestRef', 'TestCourseName');
INSERT INTO interview.Course (Id, ImageReference, Name) VALUES ('297ea2cd476e53d001476e541500001c', '', 'TestCourse');



INSERT INTO interview.Interview (interview_id, InterviewType) VALUES ('1', 0);
INSERT INTO interview.Interview (interview_id, InterviewType) VALUES ('2', 0);
