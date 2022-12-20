package com.rgt.workstatus.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rgt.workstatus.Domain.Holidays;
import com.rgt.workstatus.Service.HolidaysService;
import com.rgt.workstatus.Util.Response;

@RestController
@RequestMapping(value = "/holidays")
public class HolidaysController {

	@Autowired
	private HolidaysService holidaysService;
	Response response = new Response();
	private final static Logger logger = LoggerFactory.getLogger(HolidaysController.class);

	@PostMapping(value = "/create")
	public Response addHolidays(@RequestBody Holidays holidays) {
		logger.debug("addHolidays:INVOKED");
		Holidays holiday = holidaysService.createHolidaysName(holidays);
		try {
			if (holiday != null) {
				response.setStatus(true);
				response.setMsg("Holidays details are saved successfully !!");
			}

		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}

	@GetMapping(value = "/all")
	public @ResponseBody List<Holidays> getAllHolidays(
			@RequestParam(value = "pageNumber", required = false) Integer offset,
			@RequestParam(value = "limit", required = false) Integer limit) {
		logger.debug("getAllHolidays: INVOKED");
		return holidaysService.getAllHolidays(offset, limit);

	}

	@GetMapping(value = "/search/{search}")
	public List<Holidays> getHolidaysBySearch(@PathVariable(value = "search") String search) {
		return holidaysService.searchByHolidaysname(search);
	}

	@GetMapping(value = "/getBy/{holidaysId}")
	public Holidays getHolidaysById(@PathVariable(value = "holidaysId") Integer holidaysId) {
		logger.debug("showHoliday:INVOKED");
		return holidaysService.getHolidaysById(holidaysId);

	}

	@PutMapping(value = "/update/{id}")
	public Response updateHolidays(@Validated @RequestBody Holidays holidays) {
		logger.debug("updateHolidays:INVOKED");
		Holidays holiday = holidaysService.updateHolidays(holidays);
		try {

			if (holiday != null) {
				response.setStatus(true);
				response.setMsg("Holidays details are updated successfully !!");
			}

		} catch (IllegalArgumentException e) {
			response.setStatus(false);
			response.setMsg(e.getMessage());
		}
		return response;
	}

	@DeleteMapping(value = "/delete/{holidaysId}")
	public Response deleteHolidays(@PathVariable("holidaysId") Integer holidaysId) {
		logger.debug("deleteHolidays:INVOKED");
		if (holidaysId != 0) {
			Holidays holiday = null;
			holiday = holidaysService.getHolidaysById(holidaysId);
			holiday.setStatus(false);
			holidaysService.deleteStatus(holiday);
			if (holiday != null) {
				response.setStatus(true);
				response.setMsg("holidays details deleted !!");
			}
		}
		return response;
	}
}
