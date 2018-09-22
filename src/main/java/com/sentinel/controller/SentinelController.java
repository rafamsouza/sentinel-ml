package com.sentinel.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sentinel.model.DNA;
import com.sentinel.service.SentinelService;

@RestController
public class SentinelController {

	@Autowired
	private SentinelService service;
	
	@RequestMapping(path="/mutant", method=RequestMethod.POST)
	public ResponseEntity<Boolean> isMutant(@RequestBody DNA dna) {
		char[][] dnaMatrix = service.createCompareMatrix(dna.getDna());
		Integer h = service.compareHorizontalDNA(dnaMatrix);
		Integer v = service.compareVerticalDNA(dnaMatrix);
		Integer d = service.compareDiagonalDNA(dnaMatrix);
		
		if(h + v + d >= 2) {
			dna.setType(1);
			service.storeDNASequence(dna);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		dna.setType(0);
		service.storeDNASequence(dna);
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
	
	@RequestMapping(path="/stats", method=RequestMethod.GET)
	public ResponseEntity<Map<String, String>> getStats(){
		return new ResponseEntity<Map<String, String>>(service.getStats(), HttpStatus.OK);
	}
	
}
