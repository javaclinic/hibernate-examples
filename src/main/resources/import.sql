DELETE FROM hibernate_optimistic_locking_user;

INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES ('03ec320e-e60a-483b-887e-a6f1f5353db6','John Doe','john@email.com','2001-01-01 00:00:00.000',true,0);
INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES ('a5932f5e-72b1-48b5-91f1-6a9e25eacd52','Jane Doe','jane@email.com','2002-01-01 00:00:00.000',true,0);
INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES ('3d13a080-2525-483f-aebc-fd4f65dea8c9','Jack Doe','jack@email.com','2003-01-01 00:00:00.000',false,0);
INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES ('1c92db15-688d-48fb-8aff-0d6c9b8e2415','Jill Doe','jill@email.com','2004-01-01 00:00:00.000',false,0);
INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES ('620c443e-2ee8-4f01-88ff-6e91caa6d6b3','Jenn Doe','jenn@email.com','2005-01-01 00:00:00.000',true,0);
INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES ('61a9aee6-0257-4b39-8a9a-bae68b9553da','Jean Doe','jean@email.com','2010-01-01 00:00:00.000',false,0);
INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES ('12a872ec-e1b0-412b-9b1f-fc20154919fa','Joni Doe','joni@email.com','2012-01-01 00:00:00.000',true,0);
INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES ('425b606f-0dd0-44ce-94f9-6d248c5aa206','Jeff Doe','jeff@email.com','2014-01-01 00:00:00.000',false,0);
INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES ('6e86d9b3-9d49-4489-bcbd-6607bb35d7bf','Jena Doe','jena@email.com','2000-01-01 00:00:00.000',true,0);

