USE `ClimateData`;
DROP procedure IF EXISTS `insertAverageXContinent`;

DELIMITER $$
USE `ClimateData`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertAverageXContinent`(pIdClimateVariable int, pIdContinent int,
											pRangeAverageXContinent varchar(45), pValueAverageXContinent float)
BEGIN
	INSERT INTO AverageXContinent(idClimateVariable, idContinent, rangeAverageXContinent, valueAverageXContinent)
    VALUES (pIdClimateVariable, pIdContinent, pRangeAverageXContinent, pValueAverageXContinent);
END$$

DELIMITER ;

