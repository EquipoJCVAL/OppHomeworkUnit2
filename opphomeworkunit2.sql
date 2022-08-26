drop schema if exists opphomeworkunit2;
create schema  opphomeworkunit2;
use OppHomeworkUnit2;

create table salesRep (
name varchar(30),
salesrep_id int,
primary key(name)
);

create table lead_ (
lead_id int auto_increment,
lead_name varchar(30),
lead_phone_number int,
lead_email varchar(250),
lead_company_name varchar(250),
lead_salesrep_name varchar(30),
primary key(lead_id),
foreign key(lead_salesrep_name) REFERENCES salesRep(name)
);

create table contact (
contact_id int auto_increment,
contact_name varchar(30),
contact_phone_number int,
contact_email varchar(250),
contact_company_name varchar(250),
primary key(contact_id)
);

create table opportunity (
opportunity_id int auto_increment,
product varchar(250),
quantity int,
decision_maker varchar(30),
status varchar(250),
opp_salesrep_name varchar(30),
primary key(opportunity_id),
foreign key(opportunity_id) REFERENCES lead_(lead_id),
foreign key(opp_salesrep_name) REFERENCES salesRep(name),
foreign key(decision_maker) REFERENCES contact(contact_name)
);

create table account (
account_id int auto_increment,
industry varchar(250),
employeeCount int,
city varchar(250),
country varchar(250),
account_contact_id varchar(30),
account_opptunity_id int,
primary key(account_id),
foreign key(account_contact_id) REFERENCES contact(contact_id),
foreign key(account_opptunity_id) REFERENCES opportunity(opportunity_id)
);





