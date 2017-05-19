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
