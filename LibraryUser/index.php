<?php
//    http://localhost/php/LibraryUser/index.php

echo <<<EOT
<link rel="stylesheet" href="style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<body>
<div id="container">
EOT;

echo <<<EOT
<form method="get">
	<label id="cim">Könyvtári könyveim listája</label><br><br>
	<input type='integer' name='member' value='' placeholder='Tag kód'>
	<button id='gombQ' type='submit' name='btnQuery'><span>Lekérdezés</span></button>
</form>
EOT;

if(isset($_GET['btnQuery'])) {
	resTable();
}

function resTable(){
	//   mysqli_connect(host, username, password, dbname, port, socket)
	$conn = mysqli_connect('localhost','root','','library');

	if(!$conn){
		  echo "Hiba! A kapcsolódás sikertelen!";
	}

	mysqli_set_charset($conn, "utf-8");

	$mid = $_GET['member'];

	$sql = "
		select
			  b.author
			  ,b.title
			  ,MAX(DATE(t.time)) as takeout
			  ,DATE_ADD(MAX(DATE(t.time)),INTERVAL 30 day) as due
			  ,CASE WHEN CURRENT_DATE()>DATE_ADD(MAX(DATE(t.time)),INTERVAL 30 day) THEN 'Lejárt!' ELSE 'Élő' END as status
		from
			  transactions as t
		join members as m
			  on t.mid = m.memberid
		join books as b
			  on t.bid = b.bookid
		where
			t.mid = $mid
		group by
			  1,2
		having
			  sum(t.direction) < 0
		order by 
			  4
		;
	";

	$sql2 = "
		select
			  m.name
		from
			  members as m
		where
			 m.memberid = $mid
		;
	";

	$res2 = mysqli_query($conn, $sql2);
	if($mid==''){}  //if member_id field is not filled then do nothing
	else if (mysqli_num_rows($res2)>0) {
		while($sor2 = mysqli_fetch_assoc($res2)) {
			echo "<label>" . "Tag neve: " . $sor2['name'] . "</label>";
		}
	}

	$res = mysqli_query($conn, $sql);
	if($mid==''){  //if member_id field is not filled then write this message
		echo "<div id='uzenet'>Adj meg tag kódot!</div>";
	}
	else if (mysqli_num_rows($res2)==0) {  //if member_id is not exists then write this message
		echo "<div id='uzenet'>Nincs ilyen tag!</div>";
	}
	else if (mysqli_num_rows($res)>0) {
		  echo "<table border='1'>\n";
		  echo "<th>Szerző</th><th>Cím</th><th>Kölcsönzés</th><th>Lejárat</th><th>Státusz</th>\n";
		  while($sor = mysqli_fetch_assoc($res)) {
				echo "\n<tr>\n";
				echo "<td>" . $sor['author'] . "</td>";
				echo "<td>" . $sor['title'] . "</td>";
				echo "<td>" . $sor['takeout'] . "</td>";
				echo "<td>" . $sor['due'] . "</td>";
				echo "<td>" . $sor['status'] . "</td>";
				echo "\n</tr>\n";
		}
		  echo "</table>";
	 
	} else {
		echo "<div id='uzenet'>Nincs könyvtartozásod.</div>";
	}

	mysqli_close($conn);
}

echo "</div>";
echo "</body>";



