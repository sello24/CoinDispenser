package com.sello.coindispenser.reposiory.databaserowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sello.coindispenser.repository.dto.CoinDispenserDTO;


public class DatabaseRowMapper implements RowMapper<CoinDispenserDTO>{
	
	@Override
	public CoinDispenserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CoinDispenserDTO coinDispenserDTO = new CoinDispenserDTO();
		coinDispenserDTO.setId(rs.getInt("ID"));
		coinDispenserDTO.setType(rs.getFloat("COIN_TYPE"));
		coinDispenserDTO.setQuantityLeft(rs.getInt("QTY_REMAINING"));

		return coinDispenserDTO;
	}

}
