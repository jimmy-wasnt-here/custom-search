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

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExtraAdTests {
	/*
	* 1- La búsqueda no es restrictiva. SI se buscan riads en rabat y no hay ninguno,
	* devuelve todos los inmuebles de Rabat, ya que la ciudad coincide.
	* 2- En caso de poner una palabra inventada (ej: mamachicho) o una palabra que no coincida con nada,
	* devuelve todos los anuncios. DONE
	* 3- Si se busca más de una keyword del mismo tipo(ej: for sale y for rent, o casablanca y marrakech)
	* solo filtra por la primera keyword introducida(for sale, o casablanca)
	* */

	@Autowired
	CityController cityController;

	@Test
	public void useNonExistingParam(){
		String wrongParam = "mamachicho";
		List<AdDto> searchResult = cityController.findAds(Optional.of(wrongParam));
		assertTrue(searchResult.isEmpty());
	}

	@Test
	public void useSpacesAsParams(){
		String spaces1 = " ";
		String spaces2 = "  ";
		String spaces3 = "   ";

		List<AdDto> searchSpaces1 = cityController.findAds(Optional.of(spaces1));
		List<AdDto> searchSpaces2 = cityController.findAds(Optional.of(spaces2));
		List<AdDto> searchSpaces3 = cityController.findAds(Optional.of(spaces3));

		assertTrue(searchSpaces1.isEmpty());
		assertTrue(searchSpaces2.isEmpty());
		assertTrue(searchSpaces3.isEmpty());
	}

	@Test
	public void useMoreThanOneParamOfSameKind(){
		String searchTwoTowns = "in DerbFrihaTown or in HayAlfalahTown";
		String searchTwoSubcategories = "villas or houses";
		String searchTwoCities = "of marrakech or rabat";

		List<AdDto> searchTowns = cityController.findAds(Optional.of(searchTwoTowns));
		List<AdDto> searchSubcategories = cityController.findAds(Optional.of(searchTwoSubcategories));
		List<AdDto> searchCities = cityController.findAds(Optional.of(searchTwoCities));

		for (AdDto ad : searchTowns){
			String townName = ad.getTown();
			assertTrue(townName.equalsIgnoreCase("DerbFrihaTown") || townName.equalsIgnoreCase("HayAlfalahTown"));
		}

		for (AdDto ad : searchSubcategories){
			String subcategoryName = ad.getCategory();
			assertTrue(subcategoryName.equalsIgnoreCase("villas") || subcategoryName.equalsIgnoreCase("houses"));
		}

		for (AdDto ad : searchCities){
			String cityName = ad.getCity();
			assertTrue(cityName.equalsIgnoreCase("marrakech") || cityName.equalsIgnoreCase("rabat"));
		}
	}
}
