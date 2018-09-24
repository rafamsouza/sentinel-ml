package com.sentinel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sentinel.model.DNA;
import com.sentinel.service.SentinelService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SentinelApplicationTests {
	
	@Autowired
	private SentinelService service;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void whenIsHuman_thenReturnTrue() {
		DNA dna = new DNA();
		dna.setDna(new String[] {"TTGCCA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
		char[][] dnaMatrix;
		try {
			dnaMatrix = service.createCompareMatrix(dna.getDna());
			Integer d = service.compareDiagonalDNA(dnaMatrix);
			Integer v = service.compareVerticalDNA(dnaMatrix);
			Integer h = service.compareHorizontalDNA(dnaMatrix);
			
			assertThat(d+v+h).isLessThan(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void whenIsMutant_thenReturnTrue() {
		DNA dna = new DNA();
		dna.setDna(new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
		char[][] dnaMatrix;
		try {
			dnaMatrix = service.createCompareMatrix(dna.getDna());
			Integer d = service.compareDiagonalDNA(dnaMatrix);
			Integer v = service.compareVerticalDNA(dnaMatrix);
			Integer h = service.compareHorizontalDNA(dnaMatrix);
			
			assertThat(d+v+h).isGreaterThanOrEqualTo(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkDiagonalComparisonTrue() {
		DNA dna = new DNA();
		dna.setDna(new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
		char[][] dnaMatrix;
		try {
			dnaMatrix = service.createCompareMatrix(dna.getDna());
			Integer d = service.compareDiagonalDNA(dnaMatrix);
			assertThat(d).isEqualTo(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkVerticalComparisonTrue() {
		DNA dna = new DNA();
		dna.setDna(new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
		char[][] dnaMatrix;
		try {
			dnaMatrix = service.createCompareMatrix(dna.getDna());
			Integer v = service.compareVerticalDNA(dnaMatrix);
			assertThat(v).isEqualTo(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void checkHorizontalComparisonTrue() {
		DNA dna = new DNA();
		dna.setDna(new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
		char[][] dnaMatrix;
		try {
			dnaMatrix = service.createCompareMatrix(dna.getDna());
			Integer h = service.compareHorizontalDNA(dnaMatrix);
			assertThat(h).isEqualTo(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
