package com.impact.pokemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@RestController
public class PokemonController {

    private static final Logger logger = LoggerFactory.getLogger(PokemonController.class);

    @Resource
    private PokemonData data;

    //auto wiring service
    @Autowired
    private PokemonService pokemonService;


    //controller for the mapping
    @GetMapping("attack/{pokemonA}/{pokemonB}")
    public Map<String, Object> attack(@PathVariable String pokemonA,@PathVariable String pokemonB) throws IOException {

        logger.info("Requested pokemonA: {}, pokemonB: {}", pokemonA, pokemonB);
        return pokemonService.getWinner(pokemonA,pokemonB);

    }

}
