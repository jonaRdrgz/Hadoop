USE `ClimateData`;
DROP procedure IF EXISTS `updateMinXCountry`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateMinXCountry`(pIdClimateVariable int , pIdCountry int)
BEGIN
	UPDATE MaxMinXCountry
    SET isMinXContinent = 1 /*ya que si este atributo es 0 quiero deicr que
								no es minimo para ese continente*/
	WHERE idClimateVariable = pIdClimateVariable AND idCountry = pIdCountry;
    COMMIT;
END$$

DELIMITER ;