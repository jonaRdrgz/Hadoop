<!DOCTYPE html>
<?php
  include ('assets/php/functionPHP.php');

  

?>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>METEO : Continents</title>
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/icon" href="assets/images/favicon.ico"/>
    <!-- Font Awesome -->
    <link href="assets/css/font-awesome.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <!-- Slick slider -->
    <link rel="stylesheet" type="text/css" href="assets/css/slick.css"/> 
    <!-- Fancybox slider -->
    <link rel="stylesheet" href="assets/css/jquery.fancybox.css" type="text/css" media="screen" /> 
    <!-- Animate css -->
    <link rel="stylesheet" type="text/css" href="assets/css/animate.css"/>  
     <!-- Theme color -->
    <link id="switcher" href="assets/css/theme-color/default.css" rel="stylesheet">

    <!-- Main Style -->
    <link href="style.css" rel="stylesheet">

    <!-- Extra Style -->
    <link href="style2.css" rel="stylesheet">

    <!-- Fonts -->
    <!-- Open Sans for body font -->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    <!-- Raleway for Title -->
    <link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
    <!-- Pacifico for 404 page   -->
    <link href='https://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body >

  <!-- BEGAIN PRELOADER -->
  <div id="preloader">
    <div class="loader">&nbsp;</div>
  </div>
  <!-- END PRELOADER -->

  <!-- SCROLL TOP BUTTON -->
    <a class="scrollToTop" href="#"><i class="fa fa-chevron-up"></i></a>
  <!-- END SCROLL TOP BUTTON -->

  <!-- Start menu section -->
  <section id="menu-area">
    <nav class="navbar navbar-default main-navbar" role="navigation">  
      <div class="container">
        <div class="navbar-header">
          <!-- FOR MOBILE VIEW COLLAPSED BUTTON -->
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <!-- LOGO -->                                               
           <a class="navbar-brand logo" href="index.php"><img src="assets/images/meteo.png" alt="logo"></a>                      
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul id="top-menu" class="nav navbar-nav main-nav menu-scroll">
            <li><a href="index.php">Home</a></li> 
            <li class="active"><a href="continents.php">Continents</a></li>
            <li><a href="countries.php">Countries</a></li>                    
            <li><a href="stats.php">Stats</a></li>
          </ul>                            
        </div><!--/.nav-collapse -->       
      </div>          
    </nav> 
  </section>
  <!-- End menu section -->

  <!-- Start about section -->
  <section id="about">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <!-- Start welcome area -->
          <div class="welcome-area">
            <div class="title-area">
              <h2 class="tittle">Welcome to <span>METEO</span></h2>
              <span class="tittle-line"></span>
              <p>METEO provides accurate, organized weather information</p>
            </div>
          </div>
          <!-- End welcome area -->
        </div>
      </div>
    </div>
  </section> 
  <!-- End about section -->

  <!-- Start call to action -->
  <section id="call-to-action">
    <img src="assets/images/sunny.jpg" style="width: 100%; height: 140px" alt="img">
    <div class="call-to-overlay">
      <div class="container">
        <div class="call-to-content wow fadeInUp">
          <h2>Search by continent</h2>
        </div>
      </div>
    </div> 
  </section>
  <!-- End call to action -->

  <div class="search-area" style="margin-top: 20px; margin-right: 440px; margin-bottom: 50px">
    <form action="" onkeyup="searchContinent()" onsubmit="getStatsByContinent()">
 

      <input style="width: 400px" list="list" id="search" name="search" type="text" placeholder="Enter continent name">
      <input id="search_submit" value="Rechercher" type="submit">

	   	<datalist id="list" name = "list">
	   	
		</datalist>
    </form>

  </div>
     
  <div id="navbar2" class="navbar-collapse collapse">
    <ul id="top-menu2" class="nav navbar-nav main-nav menu-scroll" style="margin-left: -200px; margin-bottom: 30px">
      <li><a href="#tempavg">Average by Decade</a></li> 
      <li><a href="#countrieshivalues">Countries with Highest Values</a></li>
      <li><a href="#countrieslowvalues">Countries with Lowest Values</a></li>
    </ul>                            
  </div>

  <!-- Start Yearly Max Values section -->
  <section id="tempavg">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="pricing-table-area">
            <div class="title-area" id = "continent" name = "continent">
        
              
              <h2 class="tittle">Average by Decade</h2>
              <span class="tittle-line"></span><br>
              <?php
                getDescriptionContinent();
              ?>
              <select onchange = "getStats(), getDescriptionContinent()" name = "climateVariable" id="climateVariable">

              <option value= 0  default >Select Climate Variable</option>
              <?php
                selectVariable();
              ?>

              </select>
              
            </div>

            <!-- service content -->
            <div class="pricing-table-content" id = 'stats' name = stats>
                   
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- End Yearly Max Values section -->

  <!-- Start Yearly Min Values section -->
  <section id="countrieshivalues">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="pricing-table-area">
            <div class="title-area">
              <h2 class="tittle" style="margin-top: 30px">Countries with Highest Values</h2>
              <span class="tittle-line"></span>
            </div>
            <!-- service content -->
            <div class="pricing-table-content">
             <?php
               maxCountryXContinent();
              ?>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- End Yearly Min Values Section -->

  <!-- Start Stations with Max Values section -->
  <section id="countrieslowvalues">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="pricing-table-area">
            <div class="title-area">
              <h2 class="tittle" style="margin-top: 30px">Countries with Lowest Values</h2>
              <span class="tittle-line"></span>
            </div>
            <!-- service content -->
            <div class="pricing-table-content">
            <?php
               minCountryXContinent();
              ?>

            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- End Station Maximum Values Section -->

  <!-- Start Footer -->    
  <footer id="footer">
    <div class="footer-top">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="footer-top-area">             
                <a class="footer-logo" href="#"><img src="assets/images/meteo.png" alt="Logo"></a>              
              <div class="footer-social">
                <a class="facebook" href="#"><span class="fa fa-facebook"></span></a>
                <a class="twitter" href="#"><span class="fa fa-twitter"></span></a>
                <a class="google-plus" href="#"><span class="fa fa-google-plus"></span></a>
                <a class="youtube" href="#"><span class="fa fa-youtube"></span></a>
                <a class="linkedin" href="#"><span class="fa fa-linkedin"></span></a>
                <a class="dribbble" href="#"><span class="fa fa-dribbble"></span></a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </footer>
  <!-- End Footer -->

  <!-- initialize jQuery Library --> 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <!-- Bootstrap -->
  <script src="assets/js/bootstrap.js"></script>
  <!-- Slick Slider -->
  <script type="text/javascript" src="assets/js/slick.js"></script>
  <!-- Counter -->
  <script type="text/javascript" src="assets/js/waypoints.js"></script>
  <script type="text/javascript" src="assets/js/jquery.counterup.js"></script>
  <!-- mixit slider -->
  <script type="text/javascript" src="assets/js/jquery.mixitup.js"></script>
  <!-- Add fancyBox -->        
  <script type="text/javascript" src="assets/js/jquery.fancybox.pack.js"></script>
  <!-- Wow animation -->
  <script type="text/javascript" src="assets/js/wow.js"></script> 

  <!-- Custom js -->
  <script type="text/javascript" src="assets/js/custom.js"></script>
  <script type="text/javascript" src="assets/js/searchClimateData.js"></script>
    
  </body>
</html>