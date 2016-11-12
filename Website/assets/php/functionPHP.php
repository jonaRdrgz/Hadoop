<?php
include('connectionClimateData.php');
function showStats()
{

	$html = dynamicCode();
	echo $html;
}

function selectVariable()
{
	$db = dbConnectMySQL ();
	$query = 'call getClimateVariable();';
	$allClimateVariable = $db ->query($query);

	while($row = $allClimateVariable ->fetch_row()){
		echo '<option value  = '.$row[0].' >'.$row[1].'</option>';
	}
	

}


function dynamicCode(){

	$html = '             <li class="wow slideInUp">
	'.'                    <div class="single-price">
	'.'                      <h4 class="price-header" style="font-size: 18px">Average Temperature</h4>
	'.'                      <span class="price-amount" style="font-size: 18px">------</span>
	'.'                      <p style="font-size: 14px">Value</p>
	'.'                    </div>
	'.'                  </li>
	'.'                  <li class="wow slideInUp">
	'.'                    <div class="single-price">
	'.'                      <h4 class="price-header" style="font-size: 18px">Average Maximum Temperature</h4>
	'.'                      <span class="price-amount" style="font-size: 18px">------</span>
	'.'                      <p style="font-size: 14px">Value</p>
	'.'                    </div>
	'.'                  </li>
	'.'                  <li class="wow slideInUp">
	'.'                    <div class="single-price">
	'.'                      <h4 class="price-header" style="font-size: 18px">Average Minimum Temperature</h4>
	'.'                      <span class="price-amount" style="font-size: 18px">------</span>
	'.'                      <p style="font-size: 14px">Value</p>
	'.'                    </div>
	'.'                  </li>
	'.'                  <li class="wow slideInUp">
	'.'                    <div class="single-price">
	'.'                      <h4 class="price-header" style="font-size: 18px">Rain/Snow Total</h4>
	'.'                      <span class="price-amount" style="font-size: 18px">------</span>
	'.'                      <p style="font-size: 14px">Value</p>
	'.'                    </div>
	'.'                  </li>
	'.'                  <li class="wow slideInUp">
	'.'                    <div class="single-price">
	'.'                      <h4 class="price-header" style="font-size: 18px">Average Wind Speed</h4>
	'.'                      <span class="price-amount" style="font-size: 18px">------</span>
	'.'                      <p style="font-size: 14px">Value</p>
	'.'                    </div>
	'.'                  </li>
	'.'                  <li class="wow slideInUp">
	'.'                    <div class="single-price">
	'.'                      <h4 class="price-header" style="font-size: 18px">Rainy Days</h4>
	'.'                      <span class="price-amount" style="font-size: 18px">------</span>
	'.'                      <p style="font-size: 14px">Value</p>
	'.'                    </div>
	'.'                  </li>
	'.'                  <li class="wow slideInUp">
	'.'                    <div class="single-price">
	'.'                      <h4 class="price-header" style="font-size: 18px">Snow Days</h4>
	'.'                      <span class="price-amount" style="font-size: 18px">------</span>
	'.'                      <p style="font-size: 14px">Value</p>
	'.'                    </div>
	'.'                  </li>
	'.'                  <li class="wow slideInUp">
	'.'                    <div class="single-price">
	'.'                      <h4 class="price-header" style="font-size: 18px">Storm Days</h4>
	'.'                      <span class="price-amount" style="font-size: 18px">------</span>
	'.'                      <p style="font-size: 14px">Value</p>
	'.'                    </div>
	'.'                  </li>
	'.'                  <li class="wow slideInUp">
	'.'                    <div class="single-price">
	'.'                      <h4 class="price-header" style="font-size: 18px">Foggy Days</h4>
	'.'                      <span class="price-amount" style="font-size: 18px">------</span>
	'.'                      <p style="font-size: 14px">Value</p>
	'.'                    </div>
	'.'                  </li>
	'.'                  <li class="wow slideInUp">
	'.'                    <div class="single-price">
	'.'                      <h4 class="price-header" style="font-size: 18px">Tornado Days</h4>
	'.'                      <span class="price-amount" style="font-size: 18px">------</span>
	'.'                      <p style="font-size: 14px">Value</p>
	'.'                    </div>
	'.'                  </li>
	'.'                  <li class="wow slideInUp">
	'.'                    <div class="single-price">
	'.'                      <h4 class="price-header" style="font-size: 18px">Hail Days</h4>
	'.'                      <span class="price-amount" style="font-size: 18px">------</span>
	'.'                      <p style="font-size: 14px">Value</p>
	'.'                    </div>
	'.'                  </li>';

	return $html;

}


function dynamicCodeContinentAverageRange($pIdClimateVariable, $pClimateVariable, $pValueClimateVariable , $pIdContinent)
{


	$range = ["1929-1939", "1940-1949", "1950-1959", "1960-1969", "1970-1979", "1980-1989", "1990-1999", "2000-2009", "2010-2016"];
	$db = dbConnectMySQL ();
	$query = 'call getStatsAverageXContinent('.$pIdContinent.', '.$pIdClimateVariable.');';

	$allData = $db ->query($query);

	if(mysqli_num_rows($allData) > 0 ){
		$row = $allData ->fetch_row();
	}
	else{
		$row = [];
	}
	
	@session_start();
	$descriptionContinent =	$_SESSION['descriptionContinent'];
	$html = '<h2 class="tittle">'.$descriptionContinent.'</h2> <ul class="price-table">';

	$NaN = 'NaN';
	
		foreach ($range as $key => $value) {

			if (in_array($value,$row)){
				if($row[0] != -999999){
					$NaN = $row[0];
				}
				$html = $html. '<li class="wow slideInUp">
		'.'                    <div class="single-price">
		'.'                      <h4 class="price-header" style="font-size: 18px">'.$row[1].'</h4>
		'.'                      <span class="price-amount" style="font-size: 18px">'.$pClimateVariable.'</span>
		'.'                      <p style="font-size: 14px">'.$NaN.'</p>
		'.'                    </div>
		'.'                  </li>';
				$row = $allData ->fetch_row();
			}
			else{
				$html = $html.'<li class="wow slideInUp">
			
		'.'                    <div class="single-price">
		'.'                      <h4 class="price-header" style="font-size: 18px">'.$value.'</h4>
		'.'                      <span class="price-amount" style="font-size: 18px">'.$pClimateVariable.'</span>
		'.'                      <p style="font-size: 14px">'.$NaN.'</p>
		'.'                    </div>
		'.'                  </li>';
			}
		}



	$html = $html.'               </ul>';

	return $html;
}


function maxCountryXContinent()
{
	$db = dbConnectMySQL ();
	@session_start();
	$idContinent = $_SESSION['idContinent'];
	if (is_null($idContinent)){$idContinent = -1; $_SESSION['idContinent'] = -1;}		
	$query = 'call getMaxCountryXContinent('.$idContinent.');';
	$allData = $db ->query($query);
	$html = '<ul class="price-table">';

	if (mysqli_num_rows($allData) > 0){
		$climateVariable = ["Average Temperature", "Average Maximum Temperature","Average Minimum Temperature", "Rain/Snow Total"
				, "Average Wind Speed", "Rainy Days", "Snow Days", "Storm Days", "Foggy Days", "Tornado Days", "Hail Days"];
		$i = 0;
		while ($row = $allData ->fetch_row()) {
			$html = $html. '<li class="wow slideInUp">
		'.'                    <div class="single-price">
		'.'                      <h4 class="price-header" style="font-size: 18px">'.$climateVariable[$i].'</h4>
		'.'                      <span class="price-amount" style="font-size: 11px">'.$row[2].'</span>
		'.'                      <p style="font-size: 14px">'.$row[1].'</p>
		'.'                    </div>
		'.'                  </li>';
		$i++;
		}
	}
	else{
		$html = $html . dynamicCode();
	}

	$html = $html.'               </ul>';

	echo $html;
}

function minCountryXContinent()
{
	$db = dbConnectMySQL ();
	@session_start();
	$idContinent = $_SESSION['idContinent'];
	if (is_null($idContinent)){$idContinent = -1; $_SESSION['idContinent'] = -1;}		
	$query = 'call getMinCountryXContinent('.$idContinent.');';
	$allData = $db ->query($query);
	$html = '<ul class="price-table">';

	if (mysqli_num_rows($allData) > 0){
		$climateVariable = ["Average Temperature", "Average Maximum Temperature","Average Minimum Temperature", "Rain/Snow Total"
				, "Average Wind Speed", "Rainy Days", "Snow Days", "Storm Days", "Foggy Days", "Tornado Days", "Hail Days"];
		$i = 0;
		while ($row = $allData ->fetch_row()) {
			$html = $html. '<li class="wow slideInUp">
		'.'                    <div class="single-price">
		'.'                      <h4 class="price-header" style="font-size: 18px">'.$climateVariable[$i].'</h4>
		'.'                      <span class="price-amount" style="font-size: 11px">'.$row[2].'</span>
		'.'                      <p style="font-size: 14px">'.$row[1].'</p>
		'.'                    </div>
		'.'                  </li>';
		$i++;
		}
	}
	else{
		$html = $html . dynamicCode();
	}

	$html = $html.'               </ul>';

	echo $html;
}

function getTopMaxCountryStatsXClimateVariable($idClimateVariable)
{
	$db = dbConnectMySQL ();
	$range = ['1°', '2°', '3°','4°', '5°', '6°', '7°', '8°', '9°', '10°'];
	$query = 'call getTopMaxCountryXClimateVariable('.$idClimateVariable.');';
	$allData = $db ->query($query);
	$html =  '<ul class="price-table">';
  

	if (mysqli_num_rows($allData) > 0){
		
		$i = 0;
		while ($row = $allData ->fetch_row()) {
			$html = $html. '<li class="wow slideInUp">
		'.'                    <div class="single-price">
		'.'                      <h4 class="price-header" style="font-size: 18px">'.$range[$i].'</h4>
		'.'                      <span class="price-amount" style="font-size: 11px">'.$row[1].'</span>
		'.'                      <p style="font-size: 14px">'.$row[0].'</p>
		'.'                    </div>
		'.'                  </li>';
		$i++;
		}
	}
	else{
		$html = $html . dynamicCode();
	}

	$html = $html.'               </ul>';

	return $html;
}

function getTopMinCountryStatsXClimateVariable($idClimateVariable)
{

	$db = dbConnectMySQL ();
	$range = ['1°', '2°', '3°','4°', '5°', '6°', '7°', '8°', '9°', '10°'];
	$query = 'call getTopMinCountryXClimateVariable('.$idClimateVariable.');';
	$allData = $db ->query($query);
	$html =  '<ul class="price-table">';
  

	if (mysqli_num_rows($allData) > 0){
		
		$i = 0;
		while ($row = $allData ->fetch_row()) {
			$html = $html. '<li class="wow slideInUp">
		'.'                    <div class="single-price">
		'.'                      <h4 class="price-header" style="font-size: 18px">'.$range[$i].'</h4>
		'.'                      <span class="price-amount" style="font-size: 11px">'.$row[1].'</span>
		'.'                      <p style="font-size: 14px">'.$row[0].'</p>
		'.'                    </div>
		'.'                  </li>';
		$i++;
		}
	}
	else{
		$html = $html . dynamicCode();
	}

	$html = $html.'               </ul>';

	return $html;
}

function getDescriptionContinent()
{
	@session_start();
	$descriptionContinent =	$_SESSION['descriptionContinent'];
	echo '<h2 class="tittle">'.$descriptionContinent.'</h2>';
}

function getDescriptionCountry()
{
	@session_start();
	$descriptionCountry =	$_SESSION['descriptionCountry'];
	echo '<h2 class="tittle">'.$descriptionCountry.'</h2>';
}

function yearMaxXCountry()
{
	$db = dbConnectMySQL ();
	@session_start();
	$idCountry = $_SESSION['idCountry'];
	if (is_null($idCountry)){$idCountry = -1; $_SESSION['idCountry'] = -1;}	
	$query = 'call getYearMaxXCountry('.$idCountry.');';
	$allData = $db ->query($query);
	$html = '<ul class="price-table">';

	if (mysqli_num_rows($allData) > 0){
		$climateVariable = ["Average Temperature", "Average Maximum Temperature","Average Minimum Temperature", "Rain/Snow Total"
				, "Average Wind Speed", "Rainy Days", "Snow Days", "Storm Days", "Foggy Days", "Tornado Days", "Hail Days"];
		$i = 0;
		
		while ($row = $allData ->fetch_row()) {
			$NaN = "------";
			$NaN1 = "Value"; 
			if ($row[1] != -1){$NaN = $row[1]; $NaN1 = $row[2];}
			$html = $html. '<li class="wow slideInUp">
		'.'                    <div class="single-price">
		'.'                      <h4 class="price-header" style="font-size: 18px">'.$climateVariable[$i].'</h4>
		'.'                      <span class="price-amount" style="font-size: 18px">'.$NaN.'</span>
		'.'                      <p style="font-size: 18px">'.$NaN1.'</p>
		'.'                    </div>
		'.'                  </li>';
		$i++;
		}
	}
	else{

		$html = $html . dynamicCode();
	}

	$html = $html.'               </ul>';

	echo $html;
}

function yearMinXCountry()
{
	$db = dbConnectMySQL ();
	@session_start();
	$idCountry = $_SESSION['idCountry'];
	if (is_null($idCountry)){$idCountry = -1; $_SESSION['idCountry'] = -1;}	
	$query = 'call getYearMinXCountry('.$idCountry.');';
	$allData = $db ->query($query);
	$html = '<ul class="price-table">';

	if (mysqli_num_rows($allData) > 0){
		$climateVariable = ["Average Temperature", "Average Maximum Temperature","Average Minimum Temperature", "Rain/Snow Total"
				, "Average Wind Speed", "Rainy Days", "Snow Days", "Storm Days", "Foggy Days", "Tornado Days", "Hail Days"];
		$i = 0;
		while ($row = $allData ->fetch_row()) {
			$NaN = "------";
			$NaN1 = "Value"; 
			if ($row[1] != -1){$NaN = $row[1]; $NaN1 = $row[2];}
			$html = $html. '<li class="wow slideInUp">
		'.'                    <div class="single-price">
		'.'                      <h4 class="price-header" style="font-size: 18px">'.$climateVariable[$i].'</h4>
		'.'                      <span class="price-amount" style="font-size: 18px">'.$NaN.'</span>
		'.'                      <p style="font-size: 18px">'.$NaN1.'</p>
		'.'                    </div>
		'.'                  </li>';
			$i++;
		}
	}
	else{

		$html = $html . dynamicCode();
	}

	$html = $html.'               </ul>';

	echo $html;
}

function stationMaxXCountry()
{
	$db = dbConnectMySQL ();
	@session_start();
	$idCountry = $_SESSION['idCountry'];
	if (is_null($idCountry)){$idCountry = -1; $_SESSION['idCountry'] = -1;}		
	$query = 'call getStationMaxXCountry('.$idCountry.');';
	$allData = $db ->query($query);
	$html = '<ul class="price-table">';

	if (mysqli_num_rows($allData) > 0){
		$climateVariable = ["Average Temperature", "Average Maximum Temperature","Average Minimum Temperature", "Rain/Snow Total"
				, "Average Wind Speed", "Rainy Days", "Snow Days", "Storm Days", "Foggy Days", "Tornado Days", "Hail Days"];
		$i = 0;
		while ($row = $allData ->fetch_row()) {
			$NaN = "------";
			$NaN1 = "Value"; 
			if ($row[1] != -1){$NaN = $row[1]; $NaN1 = $row[2];}
			$html = $html. '<li class="wow slideInUp">
		'.'                    <div class="single-price">
		'.'                      <h4 class="price-header" style="font-size: 18px">'.$climateVariable[$i].'</h4>
		'.'                      <span class="price-amount" style="font-size: 18px">'.$NaN.'</span>
		'.'                      <p style="font-size: 18px">'.$NaN1.'</p>
		'.'                    </div>
		'.'                  </li>';
		$i++;
		}
	}
	else{

		$html = $html . dynamicCode();
	}

	$html = $html.'               </ul>';

	echo $html;
}

function stationMinXCountry()
{
	$db = dbConnectMySQL ();
	@session_start();
	$idCountry = $_SESSION['idCountry'];	
	if (is_null($idCountry)){$idCountry = -1; $_SESSION['idCountry'] = -1;}	
	$query = 'call getStationMinXCountry('.$idCountry.');';
	$allData = $db ->query($query);
	$html = '<ul class="price-table">';

	if (mysqli_num_rows($allData) > 0){
		$climateVariable = ["Average Temperature", "Average Maximum Temperature","Average Minimum Temperature", "Rain/Snow Total"
				, "Average Wind Speed", "Rainy Days", "Snow Days", "Storm Days", "Foggy Days", "Tornado Days", "Hail Days"];
		$i = 0;
		while ($row = $allData ->fetch_row()) {
			$NaN = "------";
			$NaN1 = "Value"; 
			if ($row[1] != -1){$NaN = $row[1]; $NaN1 = $row[2];}
			$html = $html. '<li class="wow slideInUp">
		'.'                    <div class="single-price">
		'.'                      <h4 class="price-header" style="font-size: 18px">'.$climateVariable[$i].'</h4>
		'.'                      <span class="price-amount" style="font-size: 18px">'.$NaN.'</span>
		'.'                      <p style="font-size: 18px">'.$NaN1.'</p>
		'.'                    </div>
		'.'                  </li>';
		$i++;
		}
	}
	else{

		$html = $html . dynamicCode();
	}

	$html = $html.'               </ul>';

	echo $html;
}





































?>