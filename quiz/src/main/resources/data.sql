insert into Question (number,text) values  
(1,'Traversant une dizaine de pays, le Danube est le plus long fleuve d''Europe.'),
(3,'Le 4 juillet 1959, Neil Armstrong devient le premier homme à marcher sur la Lune.'),
(2,'La superficie des États-Unis est plus grande que celle de l''Antarctique.'),
(4,'David Bowie est mort deux jours après le lancement de son dernier album, Blackstar.'),
(5,'Le 16 janvier 2016, la NASA a annoncé avoir réussi à faire éclore la première fleur dans l''espace.'),
(6,'Dans le conte de Charles Perrault, la belle au bois dormant a deux enfants avec le prince.'),
(7,'Brian May, le guitariste de Queen, est détenteur d''un PhD en astrophysique.'),
(8,'Le père de BigFlo et Oli est un chanteur de salsa argentine.'),
 (9,'Once Upon a Time in... Hollywood signe la troisième collaboration entre Leonardo DiCaprio et Quentin Tarantino.'),
(10,'En 1983, le premier trophée de la Coupe du monde a été volé et n''a jamais été retrouvé.');

--  ALTER SEQUENCE question_seq restart 20;

 INSERT INTO Answer (answer_id,number,agree,date_added) VALUES -- number ici c'est le numero de question
 (1,1,true,'2020-08-12'),
 (2,1,false,'2020-08-11'),
 (3,1,true,'2020-08-11'),
 (4,2,true,'2020-05-30'),
 (5,2,true,'2020-05-30'),
 (6,2,true,'2020-05-25'),
 (7,2,true,'2020-05-22'),
 (8,2,true,'2020-06-01'),
 (9,3,true,'2020-08-19'),
 (10,10,true,'2020-08-17');

--  ALTER SEQUENCE answer_seq restart 20;