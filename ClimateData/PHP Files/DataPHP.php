<?php
function yearMAxXCountry(){
	/*abre output de hadoop para luego convertirlo en un array*/
	$text= fopen("../Output Hadoop/CountryYearMax.txt", "r");
	$allData = [];

	while(!feof($text)) {

		$linea = fgets($text);

		$arrayData = explode("\t", $linea); // lo trasforma en array , el separador es 'tabulacion'

		foreach ($arrayData as $key => $value) {
			$arrayData[$key] = explode(";", $value);
		}


		if (count($arrayData) >1){
			/*si es el ultima cambio de linea no lo inserta*/
			array_push($allData, $arrayData);
		}	
		 

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

		foreach ($arrayData as $key => $value) {
			$arrayData[$key] = explode(";", $value); // se hace una lista para dividir el año del valor dado a ese año 
		}



		if (count($arrayData) >1){
			/*si es el ultima cambio de linea no lo inserta*/
			array_push($allData, $arrayData);
		}	
		 

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

		foreach ($arrayData as $key => $value) {
			$arrayData[$key] = explode(";", $value); // se hace una lista para dividir el año del valor dado a ese año 
		}

		if (count($arrayData) >1){
			/*si es el ultima cambio de linea no lo inserta*/
			array_push($allData, $arrayData);
		}	
		 

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

		foreach ($arrayData as $key => $value) {
			$arrayData[$key] = explode(";", $value); // se hace una lista para dividir el año del valor dado a ese año 
		}

		if (count($arrayData) >1){
			/*si es el ultima cambio de linea no lo inserta*/
			array_push($allData, $arrayData);
		}	
		 

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

		foreach ($arrayData as $key => $value) {
			$arrayData[$key] = explode(";", $value); // se hace una lista para dividir el año del valor dado a ese año 
		}

		if (count($arrayData) >1){
			/*si es el ultima cambio de linea no lo inserta*/
			array_push($allData, $arrayData);
		}	
		 

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

		foreach ($arrayData as $key => $value) {
			$arrayData[$key] = explode(";", $value); // se hace una lista para dividir el año del valor dado a ese año 
		}
		if (count($arrayData) >1){
			/*si es el ultima cambio de linea no lo inserta*/
			array_push($allData, $arrayData);
		}	
		 

	}

	fclose($text);

	return $allData;

}

function topTenCountryMax()
{
	/*abre output de hadoop para luego convertirlo en un array*/
	$text= fopen("../Output Hadoop/TopTenCountryMax.txt", "r");
	$allData = [];

	while(!feof($text)) {

		$linea = fgets($text);

		$arrayData = explode("\t", $linea); // lo trasforma en array , el separador es 'tabulacion'

		foreach ($arrayData as $key => $value) {
			$arrayData[$key] = explode(",", $value); // se hace una lista para dividir el año del valor dado a ese año 

			foreach($arrayData[$key] as $key2 => $value2){
				$arrayData[$key][$key2] = explode(";", $value2);
			}
		}
		if (count($arrayData) > 1){
			/*si es el ultima cambio de linea no lo inserta*/
			$allData =  $arrayData;
		}	
		 

	}

	fclose($text);

	return $allData;

}

function topTenCountryMin()
{
	/*abre output de hadoop para luego convertirlo en un array*/
	$text= fopen("../Output Hadoop/TopTenCountryMin.txt", "r");
	$allData = [];

	while(!feof($text)) {

		$linea = fgets($text);

		$arrayData = explode("\t", $linea); // lo trasforma en array , el separador es 'tabulacion'

		foreach ($arrayData as $key => $value) {
			$arrayData[$key] = explode(",", $value); // se hace una lista para dividir el año del valor dado a ese año 

			foreach($arrayData[$key] as $key2 => $value2){
				$arrayData[$key][$key2] = explode(";", $value2);
			}
		}
		if (count($arrayData) > 1){
			/*si es el ultima cambio de linea no lo inserta*/
			$allData =  $arrayData;
		}	
		 

	}

	fclose($text);

	return $allData;

}

function averageXContinent()
{
	


	// rango de fecha , se declaran para poder hacer las indexaciones entre los archivos mas flesxible y reutilizar el codigo
	$range = ["1929-1939", "1940-1949", "1950-1959", "1960-1969", "1970-1979", "1980-1989", "1990-1999", "2000-2009", "2010-2016"];

	$allData = [];

	foreach ($range as $key => $value) {

		$filename = "../Output Hadoop/Range/MoyenneContinent".$value.".txt";

		$text = fopen($filename, "r");

		$allDataXRange = [$value];
	

		while(!feof($text)) {

			$linea = fgets($text);

			$arrayData = explode("\t", $linea); // lo trasforma en array , el separador es 'tabulacion'

			if (count($arrayData) > 1){
				/*si es el ultima cambio de linea no lo inserta*/

				array_push($allDataXRange, $arrayData);
			}	
			 

		}
		array_push($allData, $allDataXRange);
		fclose($text);
	}

	return $allData;
}



?>