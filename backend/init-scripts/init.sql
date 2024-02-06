CREATE SCHEMA IF NOT EXISTS smitproovjs ;
SELECT 'Creating schema smitproovjs' AS status;
GRANT ALL ON SCHEMA smitproovjs TO postgres;
GRANT ALL ON SCHEMA smitproovjs TO PUBLIC;
CREATE TABLE smitproovjs.contact
(
    id       serial       NOT NULL,
    first_name     varchar(255) NOT NULL,
    last_name     varchar(255) NOT NULL,
    codename varchar(255) NOT NULL,
    phone    varchar(255) NOT NULL,
    CONSTRAINT contact_pk PRIMARY KEY (id)

);
INSERT INTO smitproovjs.contact (id, first_name, last_name, codename, phone) VALUES (default,'Isabella', 'Hawthorne', 'IZ', '33728016443');
INSERT INTO smitproovjs.contact (id, first_name, last_name, codename, phone) VALUES (default,'Maxwell', 'Sterling', 'xs', '3547392700');
INSERT INTO smitproovjs.contact (id, first_name, last_name, codename, phone) VALUES (default,'Seraphina', 'Cruz', 'James', '46349027446');
INSERT INTO smitproovjs.contact (id, first_name, last_name, codename, phone) VALUES (default,'Samantha ', 'Rodriguez ', 'Shadowfox', '33349027446');
INSERT INTO smitproovjs.contact (id, first_name, last_name, codename, phone) VALUES (default,'Benjamin ', 'Chang ', 'Nightfall', '49349027446254');
INSERT INTO smitproovjs.contact (id, first_name, last_name, codename, phone) VALUES (default,'Emily ', 'Patel ', 'Eclipse_Star', '34349027446');