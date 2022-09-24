insert into Task(task_id,description) values ('EXERCICE 1','erp');
insert into Task(task_id,description) values ('EXERCICE 2','developpement C');
insert into Task(task_id,description) values ('EXERCICE 3',' java');
insert into Task(task_id,description) values ('EXERCICE 4','système');


insert into Student(number,name)  values(27,'jlc');
insert into Student(number,name)  values(2,'pbt');
insert into Student(number,name)  values(7,'mcd');
insert into Student(number,name)  values(1,'abs');
insert into Student(number,name)  values(22,'nvs');
insert into Student(number,name)  values(4,'mwa');

insert into student_task(number,task_id,fait) values(27,'EXERCICE 1',true);
insert into student_task(number,task_id,fait) values(2,'EXERCICE 1',false);
insert into student_task(number,task_id,fait) values(7,'EXERCICE 1',false);
insert into student_task(number,task_id,fait) values(1,'EXERCICE 1',true);
insert into student_task(number,task_id,fait) values(22,'EXERCICE 1',true);
insert into student_task(number,task_id,fait) values(4,'EXERCICE 1',false);
insert into student_task(number,task_id,fait) values(27,'EXERCICE 2',true);
insert into student_task(number,task_id,fait) values(2,'EXERCICE 2',false);
insert into student_task(number,task_id,fait) values(7,'EXERCICE 2',false);
insert into student_task(number,task_id,fait) values(1,'EXERCICE 2',true);
insert into student_task(number,task_id,fait) values(22,'EXERCICE 2',false);
insert into student_task(number,task_id,fait) values(4,'EXERCICE 2',true);
insert into student_task(number,task_id,fait) values(27,'EXERCICE 3',false);
insert into student_task(number,task_id,fait) values(2,'EXERCICE 3',true);
insert into student_task(number,task_id,fait) values(7,'EXERCICE 3',false);
insert into student_task(number,task_id,fait) values(1,'EXERCICE 3',false);
insert into student_task(number,task_id,fait) values(22,'EXERCICE 3',true);
insert into student_task(number,task_id,fait) values(4,'EXERCICE 3',false);
insert into student_task(number,task_id,fait) values(27,'EXERCICE 4',false);
insert into student_task(number,task_id,fait) values(2,'EXERCICE 4',true);
insert into student_task(number,task_id,fait) values(7,'EXERCICE 4',true);
insert into student_task(number,task_id,fait) values(1,'EXERCICE 4',false);
insert into student_task(number,task_id,fait) values(22,'EXERCICE 4',true);
insert into student_task(number,task_id,fait) values(4,'EXERCICE 4',false);