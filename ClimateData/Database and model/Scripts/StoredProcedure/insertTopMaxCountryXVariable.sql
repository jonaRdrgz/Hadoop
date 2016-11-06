USE `ClimateData`;
DROP procedure IF EXISTS `insertTopMaxCountryXVariable`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertTopMaxCountryXVariable`(pIdClimateVariable int, pIdCountry int,
												pValueTopMaxCountryXVariable float)
BEGIN
	INSERT INTO TopMaxCountryXVariable(idClimateVariable, idCountry, valueTopMaxCountryXVariable)
    VALUES (pIdClimateVariable, pIdCountry, pValueTopMaxCountryXVariable);
END$$

DELIMITER ;

