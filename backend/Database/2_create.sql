CREATE TABLE smitproovjs.contact
(
    id       serial       NOT NULL,
    first_name     varchar(255) NOT NULL,
    last_name     varchar(255) NOT NULL,
    codename varchar(255) NOT NULL,
    phone    varchar(255) NOT NULL,
    CONSTRAINT contact_pk PRIMARY KEY (id)

);