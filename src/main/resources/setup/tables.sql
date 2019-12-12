CREATE TABLE BOOK_DETAILS
(
book_id int NOT NULL AUTO_INCREMENT,
booktitle varchar(255) NOT NULL,
isbn int NOT NULL,
discription varchar(500) NOT NULL,
author varchar(255) NOT NULL,
subject_type varchar(255) NOT NULL,
book_availiable boolean,
PRIMARY KEY (book_id)
);

CREATE TABLE MEMBER_DETAILS
(
member_id int NOT NULL AUTO_INCREMENT,
first_name varchar(255) NOT NULL,
last_name varchar(255) NOT NULL,
phone_num varchar(255) NOT NULL,
email_id varchar(255) NOT NULL,
address varchar(255) NOT NULL,
unique_Id varchar(255) NOT NULL,
Id_type varchar(255) NOT NULL,
member_type varchar(255) NOT NULL,
PRIMARY KEY (member_id)
);

CREATE TABLE LOGIN_DETAILS
(
member_id int NOT NULL, 
loginId varchar(255) NOT NULL,
lpassword varchar(255) NOT NULL,
active_falg varchar(255) NOT NULL,
login_type varchar(255) NOT NULL,
PRIMARY KEY (member_id, loginId),
FOREIGN KEY (member_id) references member_details(member_id)
);


CREATE TABLE Library_store
(
issueId int NOT NULL AUTO_INCREMENT,
book_id int,
member_id int,
issuer_id varchar(255) NOT NULL,
issue_date timestamp DEFAULT '1970-01-01 00:00:00',
return_date timestamp DEFAULT '1970-01-01 00:00:00',
last_date timestamp DEFAULT '1970-01-01 00:00:00',
returned_flag boolean,
lock_flag boolean,
PRIMARY KEY (issueId),
FOREIGN KEY (member_id) references member_details(member_id),
FOREIGN KEY (book_id) references BOOK_DETAILS(book_id)
#FOREIGN KEY (issuer_id) references LOGIN_DETAILS(loginId)
);

CREATE TABLE EVENTS
(
event_Id int NOT NULL AUTO_INCREMENT,
start_date timestamp,
end_date timestamp,
event_name varchar(255) NOT NULL,
event_details varchar(20000) NOT NULL,
contacts varchar(255) NOT NULL,
PRIMARY KEY (event_Id)
);
