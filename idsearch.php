<?php
include 'initial.php';
$pid=$_POST["data"];
 #$pid=1;
$query1="Select * from pokebasic where pkid=$pid";
$result1=mysqli_query($conn,$query1);
if(mysqli_fetch_assoc($result1)==true){
$query="Select distinct * from pokebasic pbs inner join pokestat pst on pbs.pkid=pst.pkid inner join extra ext on pbs.pkid=ext.pkid inner join pktype pkt on pkt.pkid=pbs.pkid where pbs.pkid=$pid";
$result=mysqli_query($conn,$query);
while(($row=mysqli_fetch_assoc($result))==true){
	$data[]=$row;
}
 echo json_encode($data);
}
else
	json_encode("PLEASE ENTER THE PROPER NAME");
?>