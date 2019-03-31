package com.service;

import java.io.BufferedReader;

import org.springframework.web.multipart.MultipartFile;

import com.phone.Statistics;

public interface Processor {


	Statistics processFile(MultipartFile phoneNumberFile) throws Exception;

}
