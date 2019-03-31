package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.phone.Statistics;

@Transactional
public interface StatisticsRepository extends CrudRepository<Statistics, Long>{
	
	

}
