<?php
include('../../PHP/connectionClimateData.php');
include('DataPHP.php');

/*connection Database*/


/*inserta  las estadisticas de los años y estaciones con los valores macimos y minimos */
function insertStatistic()
{
    $yearMaxXCountry = yearMaxXCountry();
    $yearMinXCountry = yearMinXCountry();
    $stationMaxXCountry = stationMaxXCountry();
    $stationMinXCountry = stationMinXCountry();
    //$izcar = "Côte d\'Ivoire";
    //$izcar = utf8_decode(mysqli_real_escape_string())

    foreach ($yearMinXCountry as $mainKey => $variable) {
        foreach ($variable as $key => $value) {
            if($key == 0){
                $db = dbConnectMySQL ();
                $country = utf8_decode(mysqli_real_escape_string($db, $value[0])); // $value[0], ya que se sabe que el elemento [0] es igual a una pais 

                $query  = 'call getIdCountry("'.$country.'")';
                
                $idCountry = $db->query($query);
                $idCountry = $idCountry ->fetch_row()[0];

            }
            else{
                $db = dbConnectMySQL ();
                $yearMax = str_replace("\n", "", $yearMaxXCountry[$mainKey][$key][0]);
                $yearMin = str_replace("\n", "",$yearMinXCountry[$mainKey][$key][0]);
                $stationMax = str_replace("\n", "",$stationMaxXCountry[$mainKey][$key][0]);
                $stationMin = str_replace("\n", "",$stationMinXCountry[$mainKey][$key][0]);

                /*se le asigna el valor del maximo y minimo ya que el formato es [0] -> year, [1] -> value*/
                $valueMax = str_replace("\n", "",$yearMaxXCountry[$mainKey][$key][1]);
                $valueMin = str_replace("\n", "",$yearMinXCountry[$mainKey][$key][1]);

                if ($yearMax == "EMPTY" ){
                    $yearMax = -1;
                }

                if ($yearMin == "EMPTY"){
                    $yearMin = -1;
                }

                if ($stationMax == "EMPTY"){
                    $stationMax = '-1';
                }

                if ($stationMin == "EMPTY"){
                    $stationMin = '-1';
                }

                $query = 'call insertStatistic('.$key.', '.$idCountry.', '.$yearMax.', '.$yearMin.', "'.$stationMax.'", "'.$stationMin.'", '.$valueMax.', '.$valueMin.');'."\n";

                $db ->query($query);
          
            }
            
        }
    }
}

function insertTopMaxCountryXVariable()
{
    $topMax = topTenCountryMax();
    foreach ($topMax as $mainKey => $variable) {
        
        if ($mainKey != 0){

            foreach ($variable as $key => $value) {

                if ($key != 10){

                    $db = dbConnectMySQL ();
                    $db2 = dbConnectMySQL ();
                    $country = utf8_decode(mysqli_real_escape_string($db, $value[0])); // $variable[$mainkey][0], ya que se sabe que el elemento [0] es igual a una pais 
                    $valueMax = $value[1];

                    $query  = 'call getIdCountry("'.$country.'")';
                        
                    $idCountry = $db->query($query);
                    $idCountry = $idCountry ->fetch_row()[0];
                    $query = 'call insertTopMaxCountryXVariable('.$mainKey.', '.$idCountry.', '.$valueMax.');';   
                    $db2 ->query($query);
                }
            }
           
        }
    }
}

function insertTopMinCountryXVariable()
{
    $topMax = topTenCountryMin();
    foreach ($topMax as $mainKey => $variable) {
        
        if ($mainKey != 0){

            foreach ($variable as $key => $value) {

                if ($key != 0){

                    $db = dbConnectMySQL ();
                    $db2 = dbConnectMySQL ();
                    $country = utf8_decode(mysqli_real_escape_string($db, $value[0])); // $variable[$mainkey][0], ya que se sabe que el elemento [0] es igual a una pais 
                    $valueMin = $value[1];

                    $query  = 'call getIdCountry("'.$country.'")';
                        
                    $idCountry = $db->query($query);
                    $idCountry = $idCountry ->fetch_row()[0];
                    $query = 'call insertTopMinCountryXVariable('.$mainKey.', '.$idCountry.', '.$valueMin.');';   
                    $db2 ->query($query);
                }
            }
           
        }
    }
}

function insertAverageXContinent()
{
	$average = averageXContinent();

    foreach ($average as $mainKey => $mainVariable) {

    	$range = "-1";
    	//ciclo por el rango 
		foreach ($mainVariable as $key => $variable) {

			if ($key != 0){
				$idContinent = -1;
				
				//ciclo por el promedio general por continente
				foreach ($variable as $key2 => $value) {
					if ($key2 != 0){
						/*
						format =>
						$key ->idClimateVariable
						$value -> valueAverageXCountry
						$range -> range => "1929-1939"...
						$idContinent -> idContinent jajaj xD
						*/
						$average =  str_replace("\n", "", $value);
						if ($average == "NaN"){
							$average = -999999;
						}





						$db = dbConnectMySQL ();
						$query = 'call insertAverageXContinent('.$key2.', '.$idContinent.', "'.$range.'", '.$average.');';   
                    	$db ->query($query);

			

					
					}

					else{

						$db = dbConnectMySQL ();
	                    $continent = utf8_decode(mysqli_real_escape_string($db, $value)); 

	                    $query  = 'call getIdContinent("'.$continent.'")';
						$idContinent = $db ->query($query);
						$idContinent = $idContinent ->fetch_row()[0];
					}
				}

			}

			else{

				$range = $variable;
			
			}
		}
    }
}
function insertMaxMinCountryXContinent()
{
    $countryMin = countryMinXContinent();
    $countryMax = countryMaxXContinent();

    foreach ($countryMin as $mainKey => $variable) {
        
        foreach ($variable as $key => $value) {

            if ($key != 0){

                $db = dbConnectMySQL ();
                $db2 = dbConnectMySQL ();
                $country = utf8_decode(mysqli_real_escape_string($db, $value[0])); // $variable[$mainkey][0], ya que se sabe que el elemento [0] es igual a una pais 
               

                $query  = 'call getIdCountry("'.$country.'")';
                    
                $idCountry = $db->query($query);
                $idCountry = $idCountry ->fetch_row()[0];
                $query = 'call updateMinXCountry('.$key.', '.$idCountry.');'."\n";   //$key = idClimateVariable
                 $db2 ->query($query);
            }
        }

    }

    foreach ($countryMax as $mainKey => $variable) {
        
        foreach ($variable as $key => $value) {

            if ($key != 0){

                $db = dbConnectMySQL ();
                $db2 = dbConnectMySQL ();
                $country = utf8_decode(mysqli_real_escape_string($db, $value[0])); // $variable[$mainkey][0], ya que se sabe que el elemento [0] es igual a una pais 

                $query  = 'call getIdCountry("'.$country.'")';
                    
                $idCountry = $db->query($query);
                $idCountry = $idCountry ->fetch_row()[0];
                $query = 'call updateMaxXCountry('.$key.', '.$idCountry.');'."\n";   //$key = idClimateVariable
                $db2 ->query($query);
            }
        }

    }
}



/*call function insert statistic*/
insertStatistic();
insertTopMaxCountryXVariable();
insertTopMinCountryXVariable();
insertAverageXContinent();
insertMaxMinCountryXContinent();

?>
