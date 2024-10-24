package com.impact.pokemon;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;

import javax.annotation.Resource;
import java.util.Map;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PokemonControllerTest {

    private final TestRestTemplate rest;

    @Resource
    private PokemonData data;

    private final Utility utility = new Utility();


    private static final Logger logger = LoggerFactory.getLogger(PokemonController.class);


    PokemonControllerTest(@LocalServerPort int port) {
        rest = new TestRestTemplate(new RestTemplateBuilder().rootUri(format("http://localhost:%d", port)));
    }

    //Test method for happy path
    @Test
    void testAttackPicksWinnerWithHitPoints() {
        String pokemonA = "Raticate";
        String pokemonB = "Metapod";
        Map<String, Object> response = rest.getForObject("/attack/"+pokemonA+"/"+pokemonB, Map.class);
        assertEquals(2, response.size());
        assertEquals("Raticate", response.get("winner"));
        assertEquals(55, response.get("hitPoints"));

    }

    //Test method for non existing pokemons
    @Test
    void testAttackWithNonExistingPokemon(){
        String pokemonA = "ABC";
        String pokemonB = "DEF";
        Map<String, Object> response = rest.getForObject("/attack/"+pokemonA+"/"+pokemonB, Map.class);
        assertEquals(2, response.size());
        assertEquals("None", response.get("winner"));
        assertEquals(-1000, response.get("hitPoints"));

    }

    //Test method for effectiveness modifier
    @Test
    void testEffectivenessModifier() {

        assertEquals(2.0,utility.getEffectivenessModifier("Fire","Grass"));
        assertEquals(0.5,utility.getEffectivenessModifier("Fire","Water"));
        assertEquals(1.0,utility.getEffectivenessModifier("Fire","Electric"));
        assertEquals(2.0,utility.getEffectivenessModifier("Water","Fire"));
        assertEquals(0.5,utility.getEffectivenessModifier("Water","Electric"));
        assertEquals(1.0,utility.getEffectivenessModifier("Water","Grass"));
        assertEquals(2.0,utility.getEffectivenessModifier("Grass","Electric"));
        assertEquals(0.5,utility.getEffectivenessModifier("Grass","Fire"));
        assertEquals(1.0,utility.getEffectivenessModifier("Grass","Water"));
        assertEquals(2.0,utility.getEffectivenessModifier("Electric","Water"));
        assertEquals(0.5,utility.getEffectivenessModifier("Electric","Grass"));
        assertEquals(1.0,utility.getEffectivenessModifier("Electric","Fire"));

    }

    //Test method to check for validating if all the records of csv file are created as objects
    @Test
    void testPokemonPool(){
        int totalRecords = 487;
        int pokemonMapSize = data.getAllPokemons().size();
        assertEquals(totalRecords,pokemonMapSize);
    }

    //Test method to check for correct pokemon object based on name
    @Test
    void testSinglePokemon() {
        String pokemon = "Venusaur";
        Pokemon testPokemon = data.getPokemon(pokemon);
        assertEquals(pokemon,testPokemon.getName());
    }
}
