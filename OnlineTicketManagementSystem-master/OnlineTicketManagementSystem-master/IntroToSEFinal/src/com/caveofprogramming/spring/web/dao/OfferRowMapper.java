package com.caveofprogramming.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OfferRowMapper implements RowMapper<Offer> {

	@Override
	public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setAuthority(rs.getString("authority"));
		user.setEmail(rs.getString("email"));
		user.setEnabled(true);
		user.setName(rs.getString("name"));
		user.setUsername(rs.getString("username"));

		Offer offer = new Offer();
		offer.setId(rs.getInt("id"));
		offer.setTitle(rs.getString("title"));
		offer.setType(rs.getString("type"));
		offer.setVanue(rs.getString("vanue"));
		offer.setDate(rs.getString("date"));
		offer.setAvailable(rs.getInt("available"));
		offer.setTotal(rs.getInt("total"));
		offer.setUser(user);

		return offer;
	}

}
