USE `ClimateData`;
DROP procedure IF EXISTS `searchCountry`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `searchCountry`(pDescriptionCountry varchar(45))
BEGIN
	SELECT descriptionCountry 
    FROM Country 
    WHERE descriptionCountry LIKE pDescriptionCountry ;
END$$

DELIMITER ;

