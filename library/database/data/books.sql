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