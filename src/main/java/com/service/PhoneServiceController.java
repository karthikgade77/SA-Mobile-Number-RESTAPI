package com.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exception.StatsNotFoundException;
import com.phone.Statistics;
import com.repository.StatisticsRepository;

@RestController
public class PhoneServiceController {
	
	@Autowired
	private Validator phoneNumberValidator;
	
	@Autowired
	private Processor phoneDataFileProcessor;
	
	@Autowired
	private StatisticsRepository statisticsRepository;
	
	private String responseTemplate = "The phone number %s is %s";
	@RequestMapping(value = "/validate", method = RequestMethod.GET, produces = "application/json")
	public String validate(@RequestParam String phoneNumber)
	{
		
		boolean isValid = false;
		if(phoneNumberValidator.isNonNumeric(phoneNumber))
		{
			phoneNumber = phoneNumber.replaceAll("[^\\d]", "");
		}
		if(phoneNumber.startsWith("27"))
		{
			isValid = phoneNumberValidator.validate(phoneNumber.substring(2));
		}
		else
		{
			isValid = phoneNumberValidator.validate(phoneNumber);
		}
	
		return isValid ? String.format(responseTemplate, phoneNumber, "VALID") 
				: String.format(responseTemplate, phoneNumber, "INVALID");
			
	}
	
	@RequestMapping(value = "/processPhoneNumbers", method = RequestMethod.POST, produces = "application/json")
	public Statistics processPhoneData(@RequestParam MultipartFile phoneNumberFile) throws Exception
	{

		if(phoneNumberFile.isEmpty())
		{
			throw new FileNotFoundException("Please provide phone numbers file");
		}

		return phoneDataFileProcessor.processFile(phoneNumberFile);
		
	}
	
	@RequestMapping(value = "/retrieveAllStats", method = RequestMethod.GET, produces = "application/json")
	public List<Statistics> retrieveAllStatistics()
	{
		List<Statistics> allStats = new ArrayList<>();

		for(Statistics stat: statisticsRepository.findAll())
		{
			allStats.add(stat);
		}
		return allStats;
	}
	
	@RequestMapping(value = "/getStatsById", method = RequestMethod.GET, produces = "application/json") 
	public Statistics getStatsById(@RequestParam long id) throws Exception
	{
		Optional<Statistics> statOptional = statisticsRepository.findById(id);
		if(statOptional.isPresent())
		{
			return statOptional.get();
		}
		throw new StatsNotFoundException("Statistics Not Available for id = "+id);
	}

}
