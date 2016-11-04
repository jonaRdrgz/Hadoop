<?php
include('../../PHP/connectionClimateData.php');
include('DataPHP.php');

/*connection Database*/


/**/
function insertStatistic(){
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
                $country = utf8_decode(mysqli_real_escape_string($db, $value));

                $query  = 'call getIdCountry("'.$country.'")';
                $idCountry = $db->query($query);
                $idCountry = $idCountry ->fetch_row()[0];
            }
            else{
                $db = dbConnectMySQL ();
                $yearMax = str_replace("\n", "", $yearMaxXCountry[$mainKey][$key]);
                $yearMin = str_replace("\n", "",$yearMinXCountry[$mainKey][$key]);
                $stationMax = str_replace("\n", "",$stationMaxXCountry[$mainKey][$key]);
                $stationMin = str_replace("\n", "",$stationMinXCountry[$mainKey][$key]);

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

                $query = 'call insertStatistic('.$idCountry.', '.$key.', '.$yearMax.', '.$yearMin.', "'.$stationMax.'", "'.$stationMin.'");';
                $db ->query($query);
          
            }
            
        }
    }
}
/*call function insert statistic*/
insertStatistic();
















?>