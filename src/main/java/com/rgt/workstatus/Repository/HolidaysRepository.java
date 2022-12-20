package com.rgt.workstatus.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.rgt.workstatus.Entity.Holidays;

@Repository
public interface HolidaysRepository extends JpaRepository<Holidays, Integer>,QuerydslPredicateExecutor<Holidays> {

	Holidays findByHolidayName(String name);

	Holidays findOneByHolidayId(Integer holidaysId);

	List<Holidays> findByHolidayNameContaining(String holidayName);
}
