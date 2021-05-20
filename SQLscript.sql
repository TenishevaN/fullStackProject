
CREATE DATABASE IF NOT EXISTS db_Entrepreneurship CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE db_Entrepreneurship;

CREATE TABLE IF NOT EXISTS users(
id int NOT NULL auto_increment,
username varchar(1000),
email varchar(100),
PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS todo(
id int NOT NULL auto_increment,
id_username int,
description varchar(1000),  
target_Date datetime,
is_Done boolean, 
PRIMARY KEY(id),
FOREIGN KEY (id_username) REFERENCES users(id)
);


CREATE TABLE IF NOT EXISTS CUSTOMER(
id int NOT NULL auto_increment,
name varchar(100),
inn varchar(50),
comment varchar(1000),
props varchar(1000),
id_username int,
PRIMARY KEY(id),
FOREIGN KEY (id_username) REFERENCES users(id)
);


CREATE TABLE IF NOT EXISTS CITY(
id int NOT NULL auto_increment,
name varchar(100),
PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS CONTRACT(
id int NOT NULL auto_increment,
contract_number  varchar(100),
id_city int,
data_start date,
data_end date,
terms_of_payment enum('HOURLY_RATE', 'FIXED_RATE'),
rate double,
comment varchar(1000),
id_customer int,
id_username int,
PRIMARY KEY(id),
FOREIGN KEY (id_username) REFERENCES users(id),
FOREIGN KEY (id_city)  REFERENCES CITY (id) ON DELETE CASCADE,
FOREIGN KEY (id_customer)  REFERENCES CUSTOMER (id) ON DELETE CASCADE  
);

CREATE TABLE IF NOT EXISTS ACT(
id int NOT NULL auto_increment,
act_number int NOT NULL,
id_contract int,
id_invoice int,
date datetime,
paid boolean, 
comment varchar(1000),
sum double,
id_username int,
PRIMARY KEY(id),
FOREIGN KEY (id_contract)  REFERENCES CONTRACT (id) ON DELETE CASCADE,
FOREIGN KEY (id_username) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE  IF NOT EXISTS ACT_TABLE(
id int NOT NULL auto_increment,
id_act int,
quantity double,
price double,
measure enum('hour'),
description varchar(100),
PRIMARY KEY(id),
 FOREIGN KEY (id_act)  REFERENCES ACT(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS INVOICE(
id int NOT NULL auto_increment,
invoice_number int NOT NULL,
id_contract int,
date datetime,
sum double,
paid boolean DEFAULT false,
comment varchar(1000),
id_username int,
PRIMARY KEY(id),
FOREIGN KEY (id_contract)  REFERENCES CONTRACT (id) ON DELETE CASCADE,
FOREIGN KEY (id_username) REFERENCES users(id) ON DELETE CASCADE 
);

CREATE TABLE  IF NOT EXISTS INVOICE_TABLE(
id int NOT NULL auto_increment,
id_invoice int,
quantity double,
price double,
measure enum('hour'),
description varchar(100),
PRIMARY KEY(id),
FOREIGN KEY (id_invoice)  REFERENCES Invoice (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS PAYMENT_ORDER(
id  int NOT NULL auto_increment,
date datetime,
operation enum('INCOME_TAX', 'ERUs'),
sum double NOT NULL,
rate int,
to_pay double, 
description varchar(1000),
comment varchar(1000),
paid boolean,
id_username int,
PRIMARY KEY(id),
FOREIGN KEY (id_username) REFERENCES users(id) ON DELETE CASCADE 
);


CREATE TABLE IF NOT EXISTS FUNDS_RECEIPT(
id int NOT NULL auto_increment,
id_invoice int,
id_username int,
date datetime,
sum double,
comment varchar(1000),
PRIMARY KEY(id),
FOREIGN KEY (id_username) REFERENCES users(id) ON DELETE CASCADE 
);

INSERT INTO users(username, email) VALUES('admin', 'servicemailtest2021@gmail.com');
INSERT INTO users(username, email) VALUES('nataliia', 'servicemailtest2021@gmail.com');

INSERT INTO CITY(name) VALUES('Kiev');
INSERT INTO CITY(name) VALUES('Moscow');
INSERT INTO CITY(name) VALUES('Cherkasy');
INSERT INTO CITY(name) VALUES('Odessa');

INSERT INTO CUSTOMER(name, inn, comment, props, id_username) VALUES('Customer1', 1111111111, 'comments', 'EDRPOY 123456 KPP 4454656', 1);
INSERT INTO CUSTOMER(name, inn, comment, props, id_username) VALUES('Customer2', 1111111111, 'comments', 'EDRPOY 123456 KPP 4454656', 2);
INSERT INTO CUSTOMER(name, inn, comment, props, id_username) VALUES('Customer3', 1111111111, 'comments', 'EDRPOY 123456 KPP 4454656', 1);

INSERT INTO CONTRACT(contract_number, id_customer, id_city, data_start, data_end, terms_of_payment, rate, comment, id_username) VALUES('081119/1',  1, 1, 	'2018-01-01', 	'2020-12-30', 'HOURLY_RATE', 300, 'just comments', 1);
INSERT INTO CONTRACT(contract_number, id_customer, id_city, data_start, data_end, terms_of_payment, rate, comment, id_username) VALUES('081119/2',  1, 1, 	'2018-01-01', 	'2020-12-30', 'HOURLY_RATE', 350, 'just comments2', 1);
INSERT INTO CONTRACT(contract_number, id_customer, id_city, data_start, data_end, terms_of_payment, rate, comment, id_username) VALUES('081119/3', 3,  3, 	'2018-01-01', 	'2020-12-30', 'HOURLY_RATE', 350, 'just comments2', 1);

INSERT INTO ACT(act_number, id_contract, id_invoice, date, paid, comment, id_username, sum) VALUES(1,  1, 1, '2020-12-09', false, 'comment', 1, 300);
INSERT INTO ACT(act_number, id_contract, id_invoice, date, paid, comment, id_username, sum) VALUES(2,  1, 1, '2020-12-09', true, 'comment2', 1, 5000);
INSERT INTO ACT(act_number, id_contract, id_invoice, date, paid, comment, id_username, sum) VALUES(3,  2, 2, '2020-12-19', true, 'comment3', 2, 6000);

INSERT INTO ACT_TABLE(id_act, quantity, price, measure, description) VALUES(1, 1, 300, 'hour', 'task1');
INSERT INTO ACT_TABLE(id_act, quantity, price, measure, description) VALUES(1, 2, 400, 'hour', 'task2');
INSERT INTO ACT_TABLE(id_act, quantity, price, measure, description) VALUES(1, 5, 500, 'hour', 'task3');
INSERT INTO ACT_TABLE(id_act, quantity, price, measure, description) VALUES(2, 1, 2300, 'hour', 'task21');
INSERT INTO ACT_TABLE(id_act, quantity, price, measure, description) VALUES(2, 1, 2400, 'hour', 'task22');
INSERT INTO ACT_TABLE(id_act, quantity, price, measure, description) VALUES(2, 1, 2500, 'hour', 'task23');
INSERT INTO ACT_TABLE(id_act, quantity, price, measure, description) VALUES(3, 1, 300, 'hour', 'task1');

INSERT INTO INVOICE(invoice_number, id_contract,  date,  comment, id_username, sum) VALUES(1,  1, '2020-12-09',  'comment', 1, 3600);
INSERT INTO INVOICE(invoice_number, id_contract,  date,  comment, id_username, sum) VALUES(2,  1, '2020-12-09',  'comment2', 1, 7200);

INSERT INTO INVOICE_TABLE(id_invoice, quantity, price, measure, description) VALUES(1, 1, 300, 'hour', 'task1');
INSERT INTO INVOICE_TABLE(id_invoice, quantity, price, measure, description) VALUES(1, 2, 400, 'hour', 'task2');
INSERT INTO INVOICE_TABLE(id_invoice, quantity, price, measure, description) VALUES(1, 5, 500, 'hour', 'task3');
INSERT INTO INVOICE_TABLE(id_invoice, quantity, price, measure, description) VALUES(2, 1, 2300, 'hour', 'task21');
INSERT INTO INVOICE_TABLE(id_invoice, quantity, price, measure, description) VALUES(2, 1, 2400, 'hour', 'task22');
INSERT INTO INVOICE_TABLE(id_invoice, quantity, price, measure, description) VALUES(2, 1, 2500, 'hour', 'task23');

INSERT INTO FUNDS_RECEIPT(id_invoice, date, sum, comment, id_username) VALUES(1, '2021-04-10', 25000, 'funds receipt ', 1);
INSERT INTO FUNDS_RECEIPT(id_invoice, date, sum, comment, id_username) VALUES(2, '2021-04-11',13000, 'funds receipt ', 1);




