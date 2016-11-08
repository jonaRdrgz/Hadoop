USE `ClimateData`;
DROP procedure IF EXISTS `updateMaxXCountry`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateMaxXCountry`(pIdClimateVariable int , pIdCountry int)
BEGIN
	UPDATE MaxMinXCountry
    SET isMaxXContinent = 1 /*ya que si este atributo es 0 quiero deicr que
								no es maximo para ese continente*/
	WHERE idClimateVariable = pIdClimateVariable AND idCountry = pIdCountry;
    COMMIT;
END$$

DELIMITER ;
