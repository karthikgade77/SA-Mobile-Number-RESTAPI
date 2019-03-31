package com.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.exception.ProcessingException;
import com.phone.PhoneEntry;
import com.phone.Statistics;
import com.repository.StatisticsRepository;

@Component("phoneDataFileProcessor")
public class PhoneDataFileProcessor implements Processor{

	@Autowired
	private Validator phoneNumberValidator;
	
	
	@Autowired
	private StatisticsRepository statisticsRepository;

	@Override
	public Statistics processFile(MultipartFile phoneNumberFile) throws Exception {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(phoneNumberFile.getInputStream()));
		List<PhoneEntry> validPhoneEntries = new ArrayList<>();
		List<PhoneEntry> invalidPhoneEntries = new ArrayList<>();
		Statistics statistics = new Statistics();
		String line = null;

		try {
			String headerLine = reader.readLine();
			while ((line = reader.readLine()) != null) {
				String changeReason = null;
				String phoneId = line.split(",")[0];
				String number = line.split(",")[1];
				
				if (phoneNumberValidator.isNonNumeric(number)) {
					number = number.replaceAll("[^\\d]", "");
					changeReason = "Removed non numeric characters";
				} else if (number.startsWith("27")) {
					number = number.substring(2);
					changeReason = "Removed the international code 27";
				}

				boolean isValid = phoneNumberValidator.validate(number);
				if (isValid) {
					validPhoneEntries.add(new PhoneEntry(Long.valueOf(phoneId), number, "VALID", changeReason));
				} else {
					invalidPhoneEntries.add(new PhoneEntry(Long.valueOf(phoneId), number, "INVALID", changeReason));
				}

			}
			statistics.setFileName(phoneNumberFile.getOriginalFilename());
			statistics.setValidCount(validPhoneEntries.size());
			statistics.setInvalidCount(invalidPhoneEntries.size());
			statistics.getPhoneEntries().addAll(validPhoneEntries);
			statistics.getPhoneEntries().addAll(invalidPhoneEntries);
			statistics.setUpdateCount(validPhoneEntries.stream().filter(p -> p.getFix() == null).count());
			statisticsRepository.save(statistics);
		}catch(DataAccessException de)
		{
			throw new ProcessingException("Error while modifying the database");
		}
		catch (Exception ex) {
			throw new ProcessingException(ex.getMessage());
		}

		return statistics;
	}

}
