<?php
$db_name="poke1";
$user="root";
$pass="";
$server="localhost";

$fruit='apple';
$conn=mysqli_connect($server,$user,$pass,$db_name);
if(!$conn)
{
	echo " connection error".mysqli_connect_error();
}
 /*else
	echo "Working properly";*/
?>
