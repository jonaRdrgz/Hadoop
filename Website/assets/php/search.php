<?php



	include ('functionPHP.php');
	@session_start();
	$db = dbConnectMySQL ();
	$db2 = dbConnectMySQL ();
	$tag = $_POST["tag"];

	if($tag == 'descriptionContinent'){

		$descriptionContinent = trim($_POST['descriptionContinent']);
		$query = 'call getIdContinent("'.$descriptionContinent.'")';
		$idContinent = $db ->query($query);

		if(mysqli_num_rows($idContinent) > 0 ){
			@session_start();
			
	  		$_SESSION['idContinent'] = $idContinent ->fetch_row()[0];
	  		$query = 'call getDescriptionContinent('.$_SESSION['idContinent'].')';
	  		$descriptionContinent = $db2 ->query($query);
	  		$_SESSION['descriptionContinent'] = $descriptionContinent ->fetch_row()[0];
	  	}
	  	else{
	  		@session_start();
	  		$_SESSION['descriptionContinent'] = '';
	  		$_SESSION['idContinent'] = -1;
	  	}
		echo json_encode(" ");
	}

	if($tag == 'descriptionCountry'){

		$descriptionCountry = trim($_POST['descriptionCountry']);
		$query = 'call getIdCountry("'.$descriptionCountry.'")';
		$idCountry = $db ->query($query);
		if(mysqli_num_rows($idCountry) > 0 ){
			@session_start();

	  		$_SESSION['idCountry'] = $idCountry ->fetch_row()[0];
	  		$query = 'call getDescriptionCountry('.$_SESSION['idCountry'].')';
	  		$descriptionCountry = $db2 ->query($query);
	  		$_SESSION['descriptionCountry'] = utf8_encode($descriptionCountry ->fetch_row()[0]);
	  	}
	  	else{
	  		@session_start();
	  		$_SESSION['descriptionCountry'] = "";
	  		$_SESSION['idCountry'] = -1;
	  	}
		echo json_encode(" ");
	}


	if($tag == 'searchContinent'){
		$search = utf8_decode(mysqli_real_escape_string($db, $_POST["search"]));
		$search = $search."%";
		$query = 'call searchContinent("'.$search.'")';
	    $searchContinent = $db ->query($query);
	    $searchContinentData = "";

	    while ($row = $searchContinent ->fetch_row()){
	    	
	   		$searchContinentData = $searchContinentData.'<option value = "'.$row[0].'">'."\n";
	    }

	    echo json_encode($searchContinentData);

	}

	if($tag == 'searchCountry'){
		$search = utf8_decode(mysqli_real_escape_string($db, $_POST["search"]));
		$search = $search."%";
		$query = 'call searchCountry("'.$search.'")';

	    $searchCountry = $db ->query($query);
	    $searchCountrytData = "";

	    while ($row = $searchCountry ->fetch_row()){
	    	$value = utf8_encode($row[0]);
	   		$searchCountryData = $searchCountryData.'<option value = "'.$value.'">'."\n";

	    }
	
	    echo json_encode($searchCountryData);

	}


	if($tag == 'continentAverageRange'){

		@session_start();
  		$idContinent = $_SESSION['idContinent'];
		$idClimateVariable = $_POST['idClimateVariable'];
		$climateVariable = $db ->query('call getDescriptionClimateVariable('.$idClimateVariable.')');
		$climateVariable = $climateVariable ->fetch_row()[0];
		$statsAverageRange = dynamicCodeContinentAverageRange($idClimateVariable, $climateVariable, $idContinent, $idContinent);
		echo json_encode($statsAverageRange);
	}


	if($tag == 'TopMaxCountryStats')
	{
		$idClimateVariable = $_POST['idClimateVariable'];
		$climateVariable = $db ->query('call getDescriptionClimateVariable('.$idClimateVariable.')');
		$climateVariable = $climateVariable ->fetch_row()[0];
		$TopMaxCountryStats = getTopMaxCountryStatsXClimateVariable($idClimateVariable);
		echo json_encode($TopMaxCountryStats);

	}

	if($tag == 'TopMinCountryStats')
	{
		$idClimateVariable = $_POST['idClimateVariable'];
		$climateVariable = $db ->query('call getDescriptionClimateVariable('.$idClimateVariable.')');
		$climateVariable = $climateVariable ->fetch_row()[0];
		$TopMinCountryStats = getTopMinCountryStatsXClimateVariable($idClimateVariable);
		echo json_encode($TopMinCountryStats);

	}

?>