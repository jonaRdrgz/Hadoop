USE `ClimateData`;
DROP procedure IF EXISTS `getMinCountryXContinent`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getMinCountryXContinent`(pIdContinent int)
BEGIN
	SELECT CV.descriptionClimateVariable ,MXM.valueMinXCountry, C.descriptionCountry FROM  MaxMinXCountry AS MXM, Country AS C, ClimateVariable AS CV
	WHERE  C.idContinentCountry = pIdContinent 
    AND C.idCountry = MXM.idCountry AND MXM.isMinXContinent = 1 
    AND CV.idClimateVariable = MXM.idClimateVariable 
	ORDER BY MXM.idClimateVariable;
END$$

DELIMITER ;
