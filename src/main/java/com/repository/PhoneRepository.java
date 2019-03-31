package com.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.phone.PhoneEntry;

@Transactional
public interface PhoneRepository extends CrudRepository<PhoneEntry, Long>{

}
