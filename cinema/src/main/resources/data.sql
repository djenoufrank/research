INSERT INTO critic (critic_name,password,enabled) values ('bob','{noop}bob',true);
INSERT INTO critic (critic_name,password,enabled) values ('alice','{noop}alice',true);

INSERT INTO  movie(movie_id,title) values (1,  'Les Animaux Fantastiques : Les Secrets de Dumbledore');
INSERT INTO  movie(movie_id,title) values (2,  'Top Gun : Maverick');
INSERT INTO  movie(movie_id,title) values (3,  'Doctor Strange in the Multiverse of Madness');
INSERT INTO  movie(movie_id,title) values (4,  'Jurassic World : Le Monde d''après');
INSERT INTO  movie(movie_id,title) values (5,  'Buzz l''éclair');

INSERT INTO  review(review_id, movie_id, critic_name, rate) values (1, 1, 'bob', 3);
INSERT INTO  review(review_id, movie_id, critic_name, rate) values (2, 2, 'bob', 2);
INSERT INTO  review(review_id, movie_id, critic_name, rate) values (3, 5, 'bob', 5);
INSERT INTO  review(review_id, movie_id, critic_name, rate) values (4, 3, 'alice', 4);
INSERT INTO  review(review_id, movie_id, critic_name, rate) values (5, 1, 'alice', 5);

INSERT INTO  Authority(id,username,authority) values (1,'bob','CRITIC');
INSERT INTO  Authority(id,username,authority) values (2,'alice','GUEST');