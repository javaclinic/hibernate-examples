DELETE FROM hibernate_optimistic_locking_user;

INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES (x'03ec320ee60a483b887ea6f1f5353db6','John Doe','john@email.com','2001-01-01 00:00:00.000',true,0);
INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES (x'a5932f5e72b148b591f16a9e25eacd52','Jane Doe','jane@email.com','2002-01-01 00:00:00.000',true,0);
INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES (x'3d13a0802525483faebcfd4f65dea8c9','Jack Doe','jack@email.com','2003-01-01 00:00:00.000',false,0);
INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES (x'1c92db15688d48fb8aff0d6c9b8e2415','Jill Doe','jill@email.com','2004-01-01 00:00:00.000',false,0);
INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES (x'620c443e2ee84f0188ff6e91caa6d6b3','Jenn Doe','jenn@email.com','2005-01-01 00:00:00.000',true,0);
INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES (x'61a9aee602574b398a9abae68b9553da','Jean Doe','jean@email.com','2010-01-01 00:00:00.000',false,0);
INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES (x'12a872ece1b0412b9b1ffc20154919fa','Joni Doe','joni@email.com','2012-01-01 00:00:00.000',true,0);
INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES (x'425b606f0dd044ce94f96d248c5aa206','Jeff Doe','jeff@email.com','2014-01-01 00:00:00.000',false,0);
INSERT INTO hibernate_optimistic_locking_user (id,name,email,created,active,version) VALUES (x'6e86d9b39d494489bcbd6607bb35d7bf','Jena Doe','jena@email.com','2000-01-01 00:00:00.000',true,0);

