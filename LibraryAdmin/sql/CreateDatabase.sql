DROP DATABASE if exists library;
CREATE DATABASE library 
	DEFAULT CHARACTER SET utf8 
		COLLATE utf8_hungarian_ci;

CREATE TABLE library.members (
  memberid int AUTO_INCREMENT,
  name varchar(50),
  phone varchar(20),
  email varchar(50),
  balance int DEFAULT 0,
  CONSTRAINT pk_members PRIMARY KEY (memberid)
);

INSERT INTO library.members (`memberid`, `name`, `phone`, `email`, `balance`) VALUES
(1, 'Kis Baba', '+70123456700', 'bkis@gmail.com', 0),
(2, 'Róbert Gida', '+36207654321', 'rgida@yahoo.com', 0),
(3, 'Gombóc Artúr', '+36301212123', 'agomboc@tcom.de', 0),
(4, 'Mekk  Elek', '+36202222222', 'emekk@alkotas.hu', 0),
(5, 'Robinson Cruso', '+36401111111', 'rcruso@valami.com', 0),
(6, 'Makk Marci', '+36201231231', 'makk@valami.hu', 0),
(7, 'Róka Koma', '+36201234567', 'rkoma@vilag.hu', 0),
(8, 'Törp Papa', '+36202222222', 'ptorp@apraja.com', 0)
;


CREATE TABLE library.books (
  bookid int AUTO_INCREMENT,
  author varchar(50),
  title varchar(50),
  quantity int,
  CONSTRAINT pk_books PRIMARY KEY (bookid)
);

INSERT INTO library.books (`bookid`, `author`, `title`, `quantity`) VALUES
(1, 'Alan Alexander Milne', 'Micimackó', 11),
(2, 'Fekete István', 'Tüskevár', 15),
(3, 'Jókai Mór', 'A névtelen vár', 10),
(4, 'Gabriel García Márquez', 'Száz év magány', 4),
(5, 'Gárdonyi Géza', 'Egri csillagok', 12),
(6, 'Antoine de Saint-Exupéry', 'A kis herceg', 14),
(7, 'Stanisław Lem', 'Magellán-felhő', 3),
(8, 'Mikszáth Kálmán', 'A fekete város', 8),
(9, 'Jókai Mór', 'A kőszívű ember fiai', 18),
(10, 'Fekete István', 'Vuk', 9),
(11, 'Molnár Ferenc','A Pál utcai fiúk', 22)
;

CREATE TABLE library.transactions (
  transactionid int AUTO_INCREMENT,
  time datetime,
  direction int,
  bid int,
  mid int,
  CONSTRAINT pk_transactions PRIMARY KEY (transactionid),
  CONSTRAINT fk_transactions FOREIGN KEY (bid) REFERENCES books(bookid),
  CONSTRAINT fk2_transactions FOREIGN KEY (mid) REFERENCES members(memberid)
);


CREATE TABLE library.authentication (
    authid int AUTO_INCREMENT,
    username varchar(50) unique,
    password varchar(50),
  CONSTRAINT pk_authentication PRIMARY KEY (authid)
);

INSERT INTO library.authentication (username, password) VALUES ('admin',md5('admin'));

