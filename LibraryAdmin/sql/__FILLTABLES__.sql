select memberid, name, phone, email, balance from members order by 1 desc;
select bookid, author, title, quantity from books order by 1 desc;

select
      t.transactionid
      ,cast(t.time as varchar(20)) as time
      ,t.mid
      ,m.name
      ,t.bid
      ,b.author
      ,b.title
      ,t.direction
from
      transactions as t
join members as m
      on t.mid = m.memberid
join books as b
      on t.bid = b.bookid
order by 
      1 desc
;

select
      t.mid
      ,m.name
      ,t.bid
      ,b.author
      ,b.title
      ,sum(t.direction) as debit
from
      transactions as t
join members as m
      on t.mid = m.memberid
join books as b
      on t.bid = b.bookid
group by
      1,2,3,4,5
having
      sum(t.direction) < 0
order by 
      2,5
;