package com.impact.pokemon;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * !! Feel free to change everything about this !!
 * This could be a class to hold all the Pokemon objects loaded from CSV,
 * but there are many ways to do it.
 */
@Component
public class PokemonData {
    HashMap<String,Pokemon> pokemons = new HashMap<String,Pokemon>();


    //parsing the CSV file and storing values in a map of pokemons
    //key being the name of the pokemon and value being the pokemon object
    PokemonData() throws IOException {

        try (InputStream is = getClass().getResourceAsStream("/data/pokemon.csv")) {
            assert is != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                String line,header;
                header = reader.readLine();
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    String name = values[1];
                    String type = values[2];
                    int total = Integer.parseInt(values[3]);
                    int hitPoints = Integer.parseInt(values[4]);
                    int attack = Integer.parseInt(values[5]);
                    int defense = Integer.parseInt(values[6]);
                    int specialAttack = Integer.parseInt(values[7]);
                    int specialDefense = Integer.parseInt(values[8]);
                    int speed = Integer.parseInt(values[9]);
                    int generation = Integer.parseInt(values[10]);
                    boolean legendary = Boolean.parseBoolean(values[11]);

                    Pokemon pokemon = new Pokemon(name,type,total,hitPoints,attack,defense,specialAttack,specialDefense,speed,generation,legendary);

                    pokemons.put(name,pokemon);

                }

            }
        }

    }

    //method to return list of all pokemons
    public HashMap<String,Pokemon> getAllPokemons(){
        return pokemons;
    }

    //method to return a pokemon based on name
    public Pokemon getPokemon(String name) {
        return pokemons.get(name);
    }

}
