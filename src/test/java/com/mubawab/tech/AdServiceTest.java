package com.mubawab.tech;

import com.mubawab.tech.service.AdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdServiceTest {

	@Autowired
	AdService adService;

	@Test
	public void cleanParameters(){
		String[] search = {"appartments", "sale"};
		String[] params = {"appartments"};

		String[] result = adService.cleanFoundParameters(search, params);
		assertTrue(result[0].equalsIgnoreCase("sale"));
	}
}
