package com.vojislav.budgetingapp;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BudgetingAppApplicationTests {

	@Test
	void contextLoads() throws ParseException {
		 Date a=DateUtils.parseDate("2021-04-23","yyyy-MM-dd");
		 System.out.println(a);
		 
	}

}
