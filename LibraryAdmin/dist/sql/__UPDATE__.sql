update members
set name = ?, phone = ?, email = ?
where
memberid = ?
;

update books
set author = ?, title = ?, quantity = ?
where
bookid = ?
;

update books
set quantity = ?
where
bookid = ?
;

update members
set balance = ?
where
memberid = ?
;

update authentication
set password = md5(?)
where
username = ?
;