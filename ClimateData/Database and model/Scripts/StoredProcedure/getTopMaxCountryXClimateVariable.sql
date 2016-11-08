USE `ClimateData`;
DROP procedure IF EXISTS `getTopMaxCountryXClimateVariable`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getTopMaxCountryXClimateVariable`(pIdClimateVariable int)
BEGIN
	SELECT TM.valueTopMaxCountryXClimateVariable, C.descriptionCountry
	FROM TopMaxCountryXClimateVariable AS TM, Country AS C
    WHERE TM.idClimateVariable = pIdClimateVariable AND TM.idCountry = C.idCountry
    ORDER BY TM.valueTopMaxCountryXClimateVariable DESC;
END$$

DELIMITER ;
