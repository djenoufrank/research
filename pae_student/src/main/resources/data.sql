

insert into Course(course_id,title,credits) values ('JLC','cour J.Lechien',6);
insert into Course(course_id,title,credits) values ('dev1','developpement',2);
insert into Course(course_id,title,credits) values ('dev2','developpement java',4);
insert into Course(course_id,title,credits) values ('dev4','developpement c++',4);


insert into Student(student_id,name,gender,section)  values(27,'prof','MALE','GESTION');
insert into Student(student_id,name,gender,section)  values(2,'pbt','MALE','RESEAU');

insert into student_course(student_id,course_id) values(27,'JLC');

INSERT INTO User (username,password,enabled) values ('40001','{noop}40001',true);
INSERT INTO Authority (id,username,authority) values (2,'40001','STUDENT');

 INSERT INTO User (username,password,enabled) values
    ('mcd','{bcrypt}$2a$10$YqviTSsx3..moyikdVrhm.tr8woUqFBqf4egwuMRGj/qVkqFlNyp6',true);