package com.sello.coindispenser.repositoryy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.sello.coindispenser.handleexceptions.DatabaseError;
import com.sello.coindispenser.handleexceptions.ErrorCode;
import com.sello.coindispenser.handleexceptions.ErrorType;
import com.sello.coindispenser.reposiory.databaserowmapper.DatabaseRowMapper;
import com.sello.coindispenser.repository.dto.CoinDispenserDTO;



@Repository
public class CoinsDispenserRepositoryImpl implements CoinDispenserRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoinsDispenserRepositoryImpl.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String SELECT_ALL_COINS = "SELECT ID, COIN_TYPE, QTY_REMAINING FROM COIN WHERE QTY_REMAINING > 0 ORDER BY COIN_TYPE ";

	private static final String UPDATE_PROCESSED_COINS = "UPDATE COIN SET QTY_REMAINING= QTY_REMAINING-:PROCESSED_QUANTITY WHERE COIN_TYPE= :COIN_TYPE ";


	@Override
	public void updateDispensedQtyWithProcessedQty(Map<Float, Integer> processedCoinsQtyMap) {
		// TODO Auto-generated method stub
		if (processedCoinsQtyMap != null && !processedCoinsQtyMap.isEmpty()) {

			List<SqlParameterSource> namedParameterList = new ArrayList<>();

			for (Map.Entry<Float, Integer> entry : processedCoinsQtyMap.entrySet()) {
				SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("COIN_TYPE", entry.getKey())
						.addValue("PROCESSED_QUANTITY", entry.getValue());

				namedParameterList.add(namedParameters);
			}
			SqlParameterSource[] parameterArray = namedParameterList
					.toArray(new SqlParameterSource[namedParameterList.size()]);

			int[] itemsProcessed = namedParameterJdbcTemplate.batchUpdate(UPDATE_PROCESSED_COINS, parameterArray);
			
			if(itemsProcessed == null || ! (itemsProcessed.length > 0)) {
				
	             throw new DatabaseError(HttpStatus.INTERNAL_SERVER_ERROR,ErrorCode.COINDISPENSE_002_DB, ErrorType.APPLICATION_ERROR, "Processing Failed");
			}
		}
	}

	@Override
	public List<CoinDispenserDTO> getAvailableCoinsList(boolean isMinNumberOfCoins) {
		// TODO Auto-generated method stub
		try {
			String sortOrder = isMinNumberOfCoins ? "DESC" : "ASC";
			return namedParameterJdbcTemplate.query(SELECT_ALL_COINS, new DatabaseRowMapper());
		} catch (DataAccessException dae) {
			 LOGGER.error("getAvailableCoins error while fetching the records ", dae);
             throw new DatabaseError(HttpStatus.INTERNAL_SERVER_ERROR,ErrorCode.COINDISPENSE_002_DB, ErrorType.APPLICATION_ERROR, "Exception while accessing the data");
		}
	
	}

	
}
