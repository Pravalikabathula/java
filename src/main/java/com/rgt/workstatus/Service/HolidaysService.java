package com.rgt.workstatus.Service;

import java.util.List;

import com.rgt.workstatus.Domain.Holidays;

public interface HolidaysService {

	Holidays createHolidaysName(Holidays holidays);

	Holidays updateHolidays(Holidays holidays);

	Holidays deleteStatus(Holidays holidays);

	Holidays getHolidaysById(Integer holidaysId);

	List<Holidays> getAllHolidays(Integer offset, Integer limit);

	List<Holidays> searchByHolidaysname(String search);
}
