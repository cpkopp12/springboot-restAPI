INSERT INTO user_details (id,birthdate,name)
VALUES (10001, current_date(), 'Cam');

INSERT INTO user_details (id,birthdate,name)
VALUES (10002, current_date(), 'Mary');

INSERT INTO user_details (id,birthdate,name)
VALUES (10003, current_date(), 'Ranga');

INSERT INTO post (id,description,user_id)
VALUES (20001, 'I want to learn aws', 10001);

INSERT INTO post (id,description,user_id)
VALUES (20002, 'I want to learn devops', 10001);

INSERT INTO post (id,description,user_id)
VALUES (20003, 'I want to learn python', 10002);

INSERT INTO post (id,description,user_id)
VALUES (20004, 'I want to learn ruby', 10003);