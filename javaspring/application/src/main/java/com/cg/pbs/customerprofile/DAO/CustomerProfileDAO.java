package com.cg.pbs.customerprofile.DAO;

import java.util.List;

import com.cg.pbs.customerprofile.models.Tables;

public interface CustomerProfileDAO {
	public List<Tables> getAvailableTables();
}
