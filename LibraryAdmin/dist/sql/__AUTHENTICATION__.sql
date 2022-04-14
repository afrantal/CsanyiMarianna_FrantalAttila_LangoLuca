SELECT count(authid) from authentication
where username = ? and password = md5(?);

