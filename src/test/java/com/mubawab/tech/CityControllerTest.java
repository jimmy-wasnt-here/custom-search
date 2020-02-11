package com.mubawab.tech;

import com.mubawab.tech.dto.AdDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith( SpringRunner.class )
@SpringBootTest
public class CityControllerTest {
	@Autowired
	CityController controller;

	@Test
	public void findAds() {
		String search1 = "villas for rent in rabat";
		String search2 = "appartments for sale in marrakech";
		List<AdDto> villas = controller.findAds(Optional.of(search1));
		List<AdDto> apartments = controller.findAds(Optional.of(search2));

		for (AdDto ad : villas){
			assertTrue(ad.getCategory().equalsIgnoreCase("For rent"));
			assertTrue(ad.getSubCategory().equalsIgnoreCase("villas"));
			assertTrue(ad.getCity().equalsIgnoreCase("rabat"));
		}

		for (AdDto ad : apartments) {
			assertTrue(ad.getCategory().equalsIgnoreCase("For sale"));
			assertTrue(ad.getSubCategory().equalsIgnoreCase("appartments"));
			assertTrue(ad.getCity().equalsIgnoreCase("marrakech"));
		}
	}

	@Test
	public void findAdsWithWrongLocation(){

		String search1 = "appartments in Marrakech MhamidTown";
		String search2 = "appartments in rabat MhamidTown";

		List<AdDto> trueSearch = controller.findAds(Optional.of(search1));
		List<AdDto> fakeSearch = controller.findAds(Optional.of(search2));

		assertTrue(fakeSearch.isEmpty());
		assertFalse(trueSearch.isEmpty());

		for (AdDto ad : trueSearch){
			assertEquals("Marrakech", ad.getCity());
			assertEquals("MhamidTown", ad.getTown());
		}
	}

	@Test
	public void findAdsIfTextIsEmpty() {
		String search1 = "";
		List<AdDto> emptySearch = controller.findAds(Optional.of(search1));
		assertTrue(emptySearch.size() > 0);
		assertTrue(emptySearch.get(1).getTitle().length() > 0);
	}

	@Test
	public void findAdsIgnoringCase(){
		String search1 = "Appartments in Casablanca";
		String search2 = "appartments in casablanca";
		String search3 = "APPARTMENTS IN CASABLANCA";
		String search4 = "aPpArtMentS IN caSABlAnCA";

		List<AdDto> ads1 = controller.findAds(Optional.of(search1));
		List<AdDto> ads2 = controller.findAds(java.util.Optional.of(search2));
		List<AdDto> ads3 = controller.findAds(java.util.Optional.of(search3));
		List<AdDto> ads4 = controller.findAds(java.util.Optional.of(search4));

		assertEquals(ads1.size(), ads2.size());
		assertEquals(ads1.size(), ads3.size());
		assertEquals(ads1.size(), ads4.size());

		assertTrue(ads1.get(0).getDescription().equalsIgnoreCase(ads2.get(0).getDescription()));
		assertTrue(ads1.get(0).getDescription().equalsIgnoreCase(ads3.get(0).getDescription()));
		assertTrue(ads1.get(0).getDescription().equalsIgnoreCase(ads4.get(0).getDescription()));
	}
}