CREATE DEFINER=`root`@`localhost` PROCEDURE `getStatsAverageXContinent`(pIdContinent int , pIdClimateVariable int )
BEGIN
	SELECT valueAverageXContinent, rangeAverageXContinent
    FROM AverageXContinent
    WHERE idContinent = pIdContinent and idClimateVariable = pIdClimateVariable;
END