USE `ClimateData`;
DROP procedure IF EXISTS `getMaxCountryXContinent`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getMaxCountryXContinent`(pIdContinent int)
BEGIN
	SELECT CV.descriptionClimateVariable ,MXM.valueMaxXCountry, C.descriptionCountry FROM  MaxMinXCountry AS MXM, Country AS C, ClimateVariable AS CV
	WHERE  C.idContinentCountry = pIdContinent 
    AND C.idCountry = MXM.idCountry AND MXM.isMaxXContinent = 1 
    AND CV.idClimateVariable = MXM.idClimateVariable 
	ORDER BY MXM.idClimateVariable;
END$$

DELIMITER ;