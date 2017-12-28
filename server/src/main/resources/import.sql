insert into user_db (id, email, password) values (1, 'john.doe@gmail.com', 'password');

insert into user_db (id, email, password) values (2, 'john.doe2@gmail.com', 'password');

insert into user_db (id, email, password) values (3, 'test', 'test');
insert into user_db (id, email, password) values (4, 'testweb', 'test');
insert into user_db_authorities (user_db_id, authorities) values (1, 'ROLE_USER');
insert into user_db_authorities (user_db_id, authorities) values (2, 'ROLE_USER');
insert into user_db_authorities (user_db_id, authorities) values (3, 'ROLE_WEB');
insert into user_db_authorities (user_db_id, authorities) values (3, 'ROLE_USER');
insert into user_db_authorities (user_db_id, authorities) values (4, 'ROLE_WEB');

insert into city (id, name) values (1, 'Łódź');
insert into city (id, name) values (2, 'Gdynia');
insert into city (id, name) values (3, 'Warszawa');
insert into city (id, name) values (4, 'Katowice');

insert into coordinates (id, latitude, longitude) values (1,10,20);
insert into coordinates (id, latitude, longitude) values (2,10,10);

insert into rating (id, value) values (1, 1);
insert into rating (id, value) values (2, 2);
insert into rating (id, value) values (3, 3);
insert into rating (id, value) values (4, 4);
insert into rating (id, value) values (5, 5);


insert into hotel_detail (id, name, city_id, description,address, coordinates_id,rating_id,picture_path) values (1, 'hotelName1',1,'Hotel Senator jest obiektem czterogwiazdkowym. To gwarancja komfortu pokoi hotelowych i jakości obsługi  oraz wysokiego bezpieczeństwa naszych Gości. Budynek Hotelu Senator jest budowlą historyczną dlatego jego architektura i wnętrza są niepowtarzalne. <br/> <br/> Do dyspozycji naszych Klientów jest całodobowa recepcja (pracownicy recepcji posługują się językiem angielskim)','hotel_1_address',1,4, 'image\a.jpg');
insert into hotel_detail (id, name, city_id, description,address, coordinates_id,rating_id,picture_path) values (2, 'hotelName2',1,'hotel_2_description','hotel_2_address', 2,5, 'image\b.jpg');


insert into hotel_facilities (id, name) values (1,'Parking');
insert into hotel_facilities (id, name) values (2,'Basen');
insert into hotel_facilities (id, name) values (3,'Siłownia');
insert into hotel_facilities (id, name) values (4,'Sauna');


insert into hotel_detail_hotel_facilities (hotel_detail_id ,hotel_facilities_id) values (1,1);
insert into hotel_detail_hotel_facilities (hotel_detail_id ,hotel_facilities_id) values (1,2);
insert into hotel_detail_hotel_facilities (hotel_detail_id ,hotel_facilities_id) values (2,1);

insert into food_offer (id, name) values (1,'Śniadanie');
insert into food_offer (id, name) values (2,'Obiad');
insert into food_offer (id, name) values (3,'Kolacja');

insert into hotel_detail_food_offer (hotel_detail_id ,food_offer_id) values (1,1);
insert into hotel_detail_food_offer (hotel_detail_id ,food_offer_id) values (1,2);
insert into hotel_detail_food_offer (hotel_detail_id ,food_offer_id) values (2,1);


insert into hotel (id, hotel_detail_id, owner_id) values (1, 1, 3);
insert into hotel (id, hotel_detail_id, owner_id) values (2, 2, 4);


insert into room (id,name,description,price,size, hotel_id ) values (1,'room_1_name','room_1_description',100,2, 1);
insert into room (id,name,description,price,size, hotel_id ) values (2,'room_2_name','room_2_description',100,2, 1);
insert into room (id,name,description,price,size, hotel_id ) values (3,'room_3_name','room_3_description',400,2, 2 );

insert into hotel_room_list (hotel_id, room_list_id) values (1,1);
insert into hotel_room_list (hotel_id, room_list_id) values (1,2);
insert into hotel_room_list (hotel_id, room_list_id) values (2,3);



insert into room_facilities (id, name) values (1,'Telewizor');
insert into room_facilities (id, name) values (2,'WiFi');
insert into room_facilities (id, name) values (3,'Łaźienka');

insert into room_room_facilities (room_id ,room_facilities_id) values (1,1);
insert into room_room_facilities (room_id ,room_facilities_id) values (1,2);
insert into room_room_facilities (room_id ,room_facilities_id) values (1,3);
insert into room_room_facilities (room_id ,room_facilities_id) values (2,1);
insert into room_room_facilities (room_id ,room_facilities_id) values (3,1);
insert into room_room_facilities (room_id ,room_facilities_id) values (3,2);

DROP TABLE oauth_client_details;
DROP TABLE oauth_client_token;
DROP TABLE oauth_access_token;

DROP TABLE oauth_refresh_token;
DROP TABLE oauth_code;
DROP TABLE oauth_approvals;
DROP TABLE ClientDetails;


create table oauth_client_details (  client_id VARCHAR(256) PRIMARY KEY,  resource_ids VARCHAR(256),  client_secret VARCHAR(256),  scope VARCHAR(256),  authorized_grant_types VARCHAR(256),  web_server_redirect_uri VARCHAR(256), authorities VARCHAR(256),  access_token_validity INTEGER,  refresh_token_validity INTEGER,  additional_information VARCHAR(4096),  autoapprove VARCHAR(256));

create table oauth_client_token (  token_id VARCHAR(256),  token BYTEA,  authentication_id VARCHAR(256) PRIMARY KEY,  user_name VARCHAR(256),  client_id VARCHAR(256));

create table oauth_access_token (  token_id VARCHAR(256),  token BYTEA ,  authentication_id VARCHAR(256) PRIMARY KEY,  user_name VARCHAR(256),  client_id VARCHAR(256),  authentication BYTEA,  refresh_token VARCHAR(256));

create table oauth_refresh_token (  token_id VARCHAR(256),  token BYTEA,  authentication BYTEA);

create table oauth_code (  code VARCHAR(256), authentication INTEGER);

create table oauth_approvals (	userId VARCHAR(256),	clientId VARCHAR(256),	scope VARCHAR(256),	status VARCHAR(10),	expiresAt TIMESTAMP,	lastModifiedAt TIMESTAMP);


-- customized oauth_client_details table
create table ClientDetails (  appId VARCHAR(256) PRIMARY KEY,  resourceIds VARCHAR(256),  appSecret VARCHAR(256),  scope VARCHAR(256),  grantTypes VARCHAR(256),  redirectUrl VARCHAR(256),  authorities VARCHAR(256),  access_token_validity INTEGER,  refresh_token_validity INTEGER,  additionalInformation VARCHAR(4096),  autoApproveScopes VARCHAR(256));


insert into oauth_client_details (client_id, client_secret, scope, authorized_grant_types) values ('client', 'secret', 'read,write', 'password,refresh_token');

--insert into user_authorities (user_id, authorities) values (1, 'ROLE_USER');

--insert into user_authorities (user_id, authorities) values (2, 'ROLE_USER');
