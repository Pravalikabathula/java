package com.rgt.workstatus.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.rgt.workstatus.Entity.Holidays;
import com.rgt.workstatus.Entity.QHolidays;
import com.rgt.workstatus.Repository.HolidaysRepository;

@Repository
public class HolidaysdaoImplementation implements HolidaysDao {

	@Autowired
	private HolidaysRepository holidaysRepository;

	@Override
	public Holidays createHolidaysName(Holidays holidays) {
		if (holidays == null) {
			throw new IllegalArgumentException("Holidays cannot be null");

		}

		if (holidays.getHolidayName() == null || holidays.getHolidayName().trim().length() == 0) {
			throw new IllegalArgumentException("Holidays cannot be null");

		}

		if (holidaysRepository.findByHolidayName(holidays.getHolidayName()) != null) {
			throw new IllegalArgumentException(
					"Holidays name " + holidays.getHolidayName() + " already exisist with us");
		}
		return holidaysRepository.save(holidays);
	}

	@Override
	public Holidays updateHolidays(Holidays holidays) {
		Holidays update = holidaysRepository.findOneByHolidayId(holidays.getHolidayId());
		if (holidays.getHolidayName() == null) {
			throw new IllegalArgumentException("Holidays name cannot be null");
		}
		if (holidays.getHolidayName() != null && holidays.getHolidayName().trim().length() > 0) {
			if (update.getHolidayName().equalsIgnoreCase(holidays.getHolidayName())) {
				BooleanExpression holiday = QHolidays.holidays.holidayName.equalsIgnoreCase(holidays.getHolidayName());
				if (holidaysRepository.findOne(holiday) == null) {
					throw new IllegalArgumentException(
							"Holidays Name " + holidays.getHolidayId() + " already exists with us !!");
				}
			} else {
				update.setHolidayId(holidays.getHolidayId());
			}
		}
		update.setHolidayName(holidays.getHolidayName());
		update.setStatus(holidays.getStatus());
		return holidaysRepository.save(update);
	}

	@Override
	public Holidays getHolidaysById(Integer holidaysId) {
		Holidays holidays = holidaysRepository.findById(holidaysId).orElseThrow(
				() -> new IllegalArgumentException("Holidays detaile are not found , Please try another one !!"));
		return holidays;
	}

	@Override
	public Page<Holidays> getAllHolidays(Integer offset, Integer limit) {
		Pageable pageable = PageRequest.of(--offset, limit);
		return holidaysRepository.findAll(pageable);
	}

	@Override
	public List<Holidays> searchByHolidaysname(String search) {

		return holidaysRepository.findByHolidayNameContaining(search);
	}

	@Override
	public Holidays deleteStatus(Holidays holidays) {
		Holidays deleteStatus = holidaysRepository.findById(holidays.getHolidayId()).orElseThrow(
				() -> new IllegalArgumentException("Holidays detaile are already deleted or not avaliable..!!"));
		if (deleteStatus.getStatus() == false) {
			throw new IllegalArgumentException("Holidays details are already deleted,please try another one...!!");
		}
		deleteStatus.setStatus(holidays.getStatus());
		return holidaysRepository.save(deleteStatus);
	}

}
