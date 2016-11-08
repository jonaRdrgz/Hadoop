USE `ClimateData`;
DROP procedure IF EXISTS `getTopMinCountryXClimateVariable`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getTopMinCountryXClimateVariable`(pIdClimateVariable int)
BEGIN
	SELECT TM.valueTopMinCountryXClimateVariable, C.descriptionCountry
	FROM TopMinCountryXClimateVariable AS TM, Country AS C
    WHERE TM.idClimateVariable = pIdClimateVariable AND TM.idCountry = C.idCountry
    ORDER BY TM.valueTopMinCountryXClimateVariable ASC;
END$$

DELIMITER ;
