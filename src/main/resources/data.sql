INSERT INTO users(id,email,first_name,last_name,role,ssn,user_name) VALUES (101,'bilel@gmail.com','Bilel','Maamouri','admin','ssn123456','bilelm' );
INSERT INTO users(id,email,first_name,last_name,role,ssn,user_name) VALUES (102,'syrine@gmail.com','Syrine','chehida','admin','ssn123457','syrinech' );
INSERT INTO users(id,email,first_name,last_name,role,ssn,user_name) VALUES (103,'foulen@gmail.com','Foulen','Ben Foulen','User','ssn123458','foulen' );

INSERT INTO orders(order_id,order_description,user_id) VALUES (2001,'Order 1',101);
INSERT INTO orders(order_id,order_description,user_id) VALUES (2002,'Order 2',102);
INSERT INTO orders(order_id,order_description,user_id) VALUES (2003,'Order 3',102);