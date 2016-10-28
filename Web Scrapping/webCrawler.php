<?php

/*$url = 'http://genbetadev.com';
$html = file_get_contents($url);

preg_match_all('<a href="(.*?)"',$html,$matches);

print_r($matches);*/
require 'simple_html_dom.php';

function inLinkContinent($url){
	$html = file_get_html( $url );

	$info = [];
	$posts = $html->find('li h3');
	foreach( $posts as $post ){
		$link = $post->find('a',0);
		$ref = $link->attr['href'];
		$title = $link->innertext;
		$info[$title]= $url.$ref;
	}

	return $info;
}



function inLinkCountry($url){
	$html = file_get_html( $url );

	$info = [];
	$posts = $html->find('div[class=DobleList] li');
	foreach( $posts as $post ){
		$link = $post->find('a',0);
		$ref = $link->attr['href'];
		$title = $link->innertext;
		$info[$title] = $ref;
	}

	return $info;
}
function inLinkStation($url, $info,$dominio){
	$html = file_get_html( $url );
	$posts = $html->find('div[class=DobleList] li');
	foreach( $posts as $post ){
		$infoAux = [];
		$link = $post->find('a',0);
		$url = $link->attr['href'];
		$title = $link->innertext;
		array_push($infoAux, $title);
		array_push($infoAux, $url);

		array_push($info, $infoAux);
	}
	if($nextLink = $html->find('li[class=siguiente] a',0)){
		$link2 = $nextLink->attr['href'];
		return inLinkStation($dominio.$link2,$info,$dominio);
	}
	else{
		return $info;
	}
}

function inLinkYear($url){
	$html = file_get_html( $url );

	$info = [];
	$posts = $html->find('div[style=margin-top:5px;] tr');
	$arrayEmpty = array("-","-","-","-","-","-","-","-","-","-","-");
	foreach( $posts as $post ){
		$infoAux = [];
		$tablas2 = $post->find('td');

		$i = 1;
		foreach ($tablas2 as $tabla2 ) {
			if($i==1){
				$tagRef = $tabla2->find('strong',0);
				$title = $tagRef->innertext;
				array_push($infoAux, $title);
				$i++;
			}
			else{
				array_push($infoAux, $tabla2->innertext);
			}
		}
		$arrayInfo = array_slice($infoAux, 1);
		if($infoAux != [] and  $arrayInfo != $arrayEmpty){
			array_push($info, $infoAux);
			//print_r(array_slice($infoAux, 1));
		}
		
	}
	return $info;
}

function scrapping(){
	$url = 'http://en.tutiempo.net/climate';
	$servidor = 'http://en.tutiempo.net/';
	//$simbols = ["!","¡","¿","?","%","[","]","*","+","~","{","}"];
	$continent = inLinkContinent($url);
	foreach ($continent as $key => $value) {
		$country = 	inLinkCountry($value);
		foreach ($country as $key2 => $value2) {
			$station = inLinkStation($url.$value2,[],$servidor);
			foreach ($station as $key3 => $value3) {
				$year = inLinkYear($url.$value3[1]);
				foreach ($year as $key4 => $value4) {
					$tabla ="";
					foreach ($value4 as $key5 => $value5) {
						$tabla .= $value5."#";
					}

					$result = fopen("result.txt", "a");
					fputs($result,$key."#".$key2."#".$value3[0]."#".$tabla."\n" );
					fclose($result);
				}
				
			}	
		}
	}
}
//print_r(inLinkStation('http://en.tutiempo.net/climate/austria.html',[],'http://en.tutiempo.net/'));

//print_r(inLinkYear('http://en.tutiempo.net/climate/ws-136110.html'));

function inAntarctica(){
	$url = 'http://en.tutiempo.net/climate';
	$servidor = 'http://en.tutiempo.net/';
	//$simbols = ["!","¡","¿","?","%","[","]","*","+","~","{","}"];
	$station = inLinkStation('http://en.tutiempo.net/climate/antarctica.html',[],$servidor);
	foreach ($station as $key3 => $value3) {
		$year = inLinkYear($url.$value3[1]);
		foreach ($year as $key4 => $value4) {
			$tabla ="";
			foreach ($value4 as $key5 => $value5) {
				$tabla .= $value5."#";
			}

			$result = fopen("result.txt", "a");
			fputs($result,"Antarctica"."#".$value3[0]."#".$tabla."\n" );
			fclose($result);
		}
		
	}
}

/*
Main
*/
scrapping();
inAntarctica();
		
?>