USE `ClimateData`;
DROP procedure IF EXISTS `searchContinent`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `searchContinent`(pDescriptionContinent varchar(45))
BEGIN
	SELECT descriptionContinent 
    FROM Continent 
    WHERE descriptionContinent LIKE pDescriptionContinent;
END$$

DELIMITER ;
