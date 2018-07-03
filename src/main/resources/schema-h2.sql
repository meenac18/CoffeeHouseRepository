CREATE TABLE CUSTOMER(customerid int auto_increment primary key, cname varchar2(50) not null,mbl number(20) not null );

CREATE UNIQUE INDEX "UK_CUSTOMER_TABLE" ON "CUSTOMER" (CNAME,MBL);



CREATE TABLE COFFEE(productId int auto_increment primary key, coffeename varchar2(50) not null,desc varchar2(20) not null , serves number(3), price number(3));