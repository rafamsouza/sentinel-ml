package com.sentinel.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sentinel.model.DNA;
import com.sentinel.repository.SentinelRepository;

@Service
public class SentinelService {
	
	@Autowired
	private SentinelRepository repository;

	public Integer compareHorizontalDNA(char[][] dnaMatrix) {
		for(int i=0; i < dnaMatrix.length; i++) {
			int count = 0;
			int ocur = 1;
			
			for(int j=0; j < dnaMatrix.length; j++) {
				if(j < dnaMatrix.length-1 && dnaMatrix[i][j] == dnaMatrix[i][j+1]) {
					ocur++;
				} else {
					if(ocur > count) {
						count = ocur;
					}
					ocur = 1;
				}
			}
			if(count >= 4) {
				return 1;
			}
		}
		return 0;
	}
	
	public Integer compareVerticalDNA(char[][] dnaMatrix) {
		for(int i=0; i < dnaMatrix.length; i++) {
			int count = 0;
			int ocur = 1;
			
			for(int j=0; j < dnaMatrix.length; j++) {
				if(j < dnaMatrix.length-1 && dnaMatrix[j][i] == dnaMatrix[j+1][i]) {
					ocur++;
				} else {
					if(ocur > count) {
						count = ocur;
					}
					ocur = 1;
				}
			}
			if(count >= 4) {
				return 1;
			}
		}
		return 0;
	}
	
	public Integer compareDiagonalDNA(char[][] dnaMatrix) {
		int count = 0;
		int ocur = 1;
		
		for(int i=0; i < dnaMatrix.length; i++) {
			if(i < dnaMatrix.length-1 && dnaMatrix[i][i] == dnaMatrix[i+1][i+1]) {
				ocur++;
			} else {
				if(ocur > count) {
					count = ocur;
				}
				ocur = 1;
			}
			if(count >= 4) {
				return 1;
			}
		}
		return 0;
	}
	
	public char[][] createCompareMatrix(String[] dna) throws Exception {
		char[][] dnaMatrix = new char[6][6];
		
		for(int i=0; i < dna.length; i++) {
			if(!dna[i].matches("[ATCG]*")) {
				throw new Exception("Not a valid DNA sequence");
			}
			char[] ca = dna[i].toCharArray();
			for(int j=0; j < ca.length; j++) {
				dnaMatrix[i][j] = ca[i];
			}
		}
				
		return dnaMatrix;
	}
	
	public void storeDNASequence(DNA dna) {
		repository.save(dna);
	}
	
	public Map<String, String> getStats() {
		List<DNA> resultList = repository.findAll();
		int mutant = 0;
		int human = 0;
		
		if(resultList == null) {
			return new HashMap<>();
		}
		
		for(DNA dna : resultList) {
			if(dna.getType() == 1) {
				mutant++;
			} else {
				human++;
			}
		}
		
		float ratio = (float) mutant/human;
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.CEILING);
		
		Map<String, String> stats = new LinkedHashMap<>();
		stats.put("count_mutant_dna", mutant+"");
		stats.put("count_human_dna", human+"");
		stats.put("ratio", df.format(ratio));
		
		return stats;
	}
}
