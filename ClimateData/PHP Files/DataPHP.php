<?php
function yearMAxXCountry(){
	/*abre output de hadoop para luego convertirlo en un array*/
	$text= fopen("../Output Hadoop/CountryYearMax.txt", "r");
	$allData = [];

	while(!feof($text)) {

		$linea = fgets($text);

		$arrayData = explode("\t", $linea); // lo trasforma en array , el separador es 'tabulacion'

		if (count($arrayData) >1){
			/*si es el ultima cambio de linea no lo inserta*/
			array_push($allData, $arrayData);
		}	
		else {}

	}

	fclose($text);

	return $allData;

}

function yearMinXCountry(){
	/*abre output de hadoop para luego convertirlo en un array*/
	$text= fopen("../Output Hadoop/CountryYearMin.txt", "r");
	$allData = [];

	while(!feof($text)) {

		$linea = fgets($text);

		$arrayData = explode("\t", $linea); // lo trasforma en array , el separador es 'tabulacion'

		if (count($arrayData) >1){
			/*si es el ultima cambio de linea no lo inserta*/
			array_push($allData, $arrayData);
		}	
		else {}

	}

	fclose($text);

	return $allData;

}

function stationMaxXCountry(){
	/*abre output de hadoop para luego convertirlo en un array*/
	$text= fopen("../Output Hadoop/CountryStationMax.txt", "r");
	$allData = [];

	while(!feof($text)) {

		$linea = fgets($text);

		$arrayData = explode("\t", $linea); // lo trasforma en array , el separador es 'tabulacion'

		if (count($arrayData) >1){
			/*si es el ultima cambio de linea no lo inserta*/
			array_push($allData, $arrayData);
		}	
		else {}

	}

	fclose($text);

	return $allData;

}

function stationMinXCountry(){
	/*abre output de hadoop para luego convertirlo en un array*/
	$text= fopen("../Output Hadoop/CountryStationMin.txt", "r");
	$allData = [];

	while(!feof($text)) {

		$linea = fgets($text);

		$arrayData = explode("\t", $linea); // lo trasforma en array , el separador es 'tabulacion'

		if (count($arrayData) >1){
			/*si es el ultima cambio de linea no lo inserta*/
			array_push($allData, $arrayData);
		}	
		else {}

	}

	fclose($text);

	return $allData;

}

function countryMaxXContinent(){
	/*abre output de hadoop para luego convertirlo en un array*/
	$text= fopen("../Output Hadoop/ContinentCountryMax.txt", "r");
	$allData = [];

	while(!feof($text)) {

		$linea = fgets($text);

		$arrayData = explode("\t", $linea); // lo trasforma en array , el separador es 'tabulacion'

		if (count($arrayData) >1){
			/*si es el ultima cambio de linea no lo inserta*/
			array_push($allData, $arrayData);
		}	
		else {}

	}

	fclose($text);

	return $allData;

}

function countryMinXContinent(){
	/*abre output de hadoop para luego convertirlo en un array*/
	$text= fopen("../Output Hadoop/ContinentCountryMin.txt", "r");
	$allData = [];

	while(!feof($text)) {

		$linea = fgets($text);

		$arrayData = explode("\t", $linea); // lo trasforma en array , el separador es 'tabulacion'

		if (count($arrayData) >1){
			/*si es el ultima cambio de linea no lo inserta*/
			array_push($allData, $arrayData);
		}	
		else {}

	}

	fclose($text);

	return $allData;

}

?>