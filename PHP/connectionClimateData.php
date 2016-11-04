<?php

function dbConnectMySQL (){
 	$mysqli = new mysqli("localhost", "root", "root", "ClimateData");
	if ($mysqli->connect_errno) {
    	echo "Failed to connect to MySQL: (" . $mysqli->connect_errno . ") " . $mysqli->connect_error;
	}
	return $mysqli;
}






?>