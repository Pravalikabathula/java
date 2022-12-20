package com.rgt.workstatus.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rgt.workstatus.DAO.HolidaysDao;
import com.rgt.workstatus.Domain.Holidays;
import com.rgt.workstatus.Domain.Roles;
import com.rgt.workstatus.objectcopier.Objectcopier;

@Service
public class HolidaysServiceImplementation implements HolidaysService {

	@Autowired
	HolidaysDao holidaysDao;

	private final static Logger logger = LoggerFactory.getLogger(HolidaysServiceImplementation.class);

	@Transactional
	public Holidays createHolidaysName(Holidays holidays) {
		logger.debug("createHolidaysname:INVOKED");

		Holidays holiday = toDomain(holidaysDao.createHolidaysName(toEntity(holidays)));

		return holiday;
	}

	@Transactional
	public Holidays updateHolidays(Holidays holidays) {
		logger.debug("updateHolidays:INVOKED");

		Holidays holidayss = toDomain(holidaysDao.updateHolidays(toEntity(holidays)));

		return holidayss;
	}

	@Transactional
	public Holidays deleteStatus(Holidays holidays) {
		return toDomain(holidaysDao.deleteStatus(toEntity(holidays)));
	}

	@Transactional
	public Holidays getHolidaysById(Integer holidaysId) {
		com.rgt.workstatus.Entity.Holidays holidays = holidaysDao.getHolidaysById(holidaysId);
		if (holidays.getStatus() == false) {
			throw new IllegalArgumentException("Holidays detaile already deleted , Please try another one !!");
		}
		if (holidays.getHolidayId() == null) {
			throw new IllegalArgumentException("Holidays detaile are not found , Please try another one !!");
		}
		Holidays holidayss = toDomain(holidays);
		return holidayss;
	}

	@Transactional
	public List<Holidays> getAllHolidays(Integer offset, Integer limit) {
		Page<com.rgt.workstatus.Entity.Holidays> lists = holidaysDao.getAllHolidays(offset, limit);
		List<Holidays> list = new ArrayList<Holidays>();
		lists.forEach(holidays -> {
			Holidays holidayss = toDomain(holidays);
			list.add(holidayss);
		});
		return list;

	}

	@Transactional
	public List<Holidays> searchByHolidaysname(String search) {
		List<com.rgt.workstatus.Entity.Holidays> lists = holidaysDao.searchByHolidaysname(search);
		List<Holidays> list = new ArrayList<Holidays>();
		lists.forEach(holidays -> {
			Holidays holidayss = toDomain(holidays);
			list.add(holidayss);
		});
		return list;
	}

	public com.rgt.workstatus.Entity.Holidays toEntity(Holidays holidays) {
		com.rgt.workstatus.Entity.Holidays entity = null;
		if (holidays != null) {
			entity = new com.rgt.workstatus.Entity.Holidays();
			Objectcopier.copyObject(holidays, entity);
		}
		return entity;
	}

	public Holidays toDomain(com.rgt.workstatus.Entity.Holidays holidays) {
		Holidays domain = null;
		if (holidays != null) {
			domain = new Holidays();
			Objectcopier.copyObject(holidays, domain);
		}
		return domain;
	}
}
