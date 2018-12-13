<?php
include 'initial.php';
$type=$_POST["data"];
#$type="water";
$query1="Select * from pktype where ptype='$type';";
$result1=mysqli_query($conn,$query1);
if(mysqli_fetch_assoc($result1)==true){
$query="Select distinct pb.pkname,pb.pkimage from pokebasic pb,pktype pt where pt.ptype='$type' and pt.pkid=pb.pkid;";
$result=mysqli_query($conn,$query);
while(($row=mysqli_fetch_assoc($result))==true){
	$data[]=$row;
}
echo json_encode($data);}
else
	json_encode("PLEASE ENTER THE PROPER NAME");
?>