package com.rgt.workstatus.DAO;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rgt.workstatus.Entity.Holidays;

public interface HolidaysDao {

	Holidays createHolidaysName(Holidays holidays);

	Holidays updateHolidays(Holidays holidays);

	Holidays deleteStatus(Holidays holidays);

	Holidays getHolidaysById(Integer holidaysId);

	Page<Holidays> getAllHolidays(Integer offset, Integer limit);

	List<Holidays> searchByHolidaysname(String search);
}
