function searchContinent()
{	
	$search = $("#search").val();

    $.ajax({

        type:  "POST",
        url:   "/Hadoop/Website/assets/php/search.php",
        dataType: "json",
        data: {"search": $search , 'tag': 'searchContinent'},
        
                    
        beforeSend: function(){
            //Lo que se hace antes de enviar el formulario
        },
        success: function(result){
               $("#list").html(result)

        },
        error:  function(xhr,err){ 
            alert(xhr.responseText);
            
        }
    });
    
}


function searchCountry()
{	
	$search = $("#search").val();

    $.ajax({

        type:  "POST",
        url:   "/Hadoop/Website/assets/php/search.php",
        dataType: "json",
        data: {"search": $search , 'tag': 'searchCountry'},
        
                    
        beforeSend: function(){
            //Lo que se hace antes de enviar el formulario
        },
        success: function(result){
        	
               $("#list").html(result)

        },
        error:  function(xhr,err){ 
            alert(xhr.responseText);
            
        }
    });
    
}

function getStats()
{
    $idClimateVariable = $("#climateVariable").val();

    $.ajax({

        type:  "POST",
        url:   "/Hadoop/Website/assets/php/search.php",
        dataType: "json",
        data: {"idClimateVariable": $idClimateVariable , 'tag': 'continentAverageRange'},
        
                    
        beforeSend: function(){
            //Lo que se hace antes de enviar el formulario
        },
        success: function(result){
                    
               $("#stats").html(result)

        },
        error:  function(xhr,err){ 
            alert(xhr.responseText);
            
        }
    });   
}

function getStatsByContinent()
{   
    $descriptionContinent = $('#search').val();

                    
    $.ajax({

        type:  "POST",
        url:   "/Hadoop/Website/assets/php/search.php",
        dataType: "json",
        data: {"descriptionContinent": $descriptionContinent , 'tag': 'descriptionContinent'},
        
        beforeSend: function(){
            //Lo que se hace antes de enviar el formulario

        },
        success: function($result){
       

        },
        error:  function(xhr,err){ 
            alert(xhr.responseText);
            
        }
    });  

    
}

function getStatsByCountry()
{   
    $descriptionCountry = $('#search').val();

    $.ajax({

        type:  "POST",
        url:   "/Hadoop/Website/assets/php/search.php",
        dataType: "json",
        data: {"descriptionCountry": $descriptionCountry , 'tag': 'descriptionCountry'},
        
        beforeSend: function(){
            //Lo que se hace antes de enviar el formulario

        },
        success: function($result){
       

        },
        error:  function(xhr,err){ 
            alert(xhr.responseText);
            
        }
    });  

    
}

function getTopMaxCountryStats()
{
    $idClimateVariable = $("#climateVariable").val();

    $.ajax({

        type:  "POST",
        url:   "/Hadoop/Website/assets/php/search.php",
        dataType: "json",
        data: {"idClimateVariable": $idClimateVariable , 'tag': 'TopMaxCountryStats'},
        
                    
        beforeSend: function(){
            //Lo que se hace antes de enviar el formulario
        },
        success: function($result){
                    
               $("#MaxStats").html($result)


        },
        error:  function(xhr,err){ 
            alert(xhr.responseText);
            
        }
    });   
}

function getTopMinCountryStats()
{
    $idClimateVariable = $("#climateVariable").val();

    $.ajax({

        type:  "POST",
        url:   "/Hadoop/Website/assets/php/search.php",
        dataType: "json",
        data: {"idClimateVariable": $idClimateVariable , 'tag': 'TopMinCountryStats'},
        
                    
        beforeSend: function(){
            //Lo que se hace antes de enviar el formulario
        },
        success: function($result){
                    
               $("#MinStats").html($result)


        },
        error:  function(xhr,err){ 
            alert(xhr.responseText);
            
        }
    });   
}

















