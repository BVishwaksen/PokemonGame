package com.impact.pokemon;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class PokemonService {

    @Resource
    private PokemonData data;

    //method to calculate the winner
    public Map<String, Object> getWinner(String pokemonA,String pokemonB) {

        String winner;
        int finalScore;

        Utility utility = new Utility();

        //getting the pokemon objects based on the name
        try {


            Pokemon pokemonOne = data.getPokemon(StringUtils.capitalize(pokemonA));
            Pokemon pokemonTwo = data.getPokemon(StringUtils.capitalize(pokemonB));

            //deciding the first pokemon to attack based on speed
            if (pokemonOne.getSpeed() < pokemonTwo.getSpeed()) {
                Pokemon temp = pokemonOne;
                pokemonOne = pokemonTwo;
                pokemonTwo = temp;
            } else if (pokemonOne.getSpeed() == pokemonTwo.getSpeed()) {
                if (Math.random() < 0.5) {
                    Pokemon temp = pokemonOne;
                    pokemonOne = pokemonTwo;
                    pokemonTwo = temp;
                }
            }

            int hitPointsOne = pokemonOne.getHitPoints();
            int hitPointsTwo = pokemonTwo.getHitPoints();

            double emOne = utility.getEffectivenessModifier(pokemonOne.getType(), pokemonTwo.getType());
            double emTwo = utility.getEffectivenessModifier(pokemonTwo.getType(), pokemonOne.getType());

            //hit point calculation
            while (hitPointsOne > 0 && hitPointsTwo > 0) {
                hitPointsTwo = (int) (hitPointsTwo - 50 * (pokemonOne.getAttack() / pokemonTwo.getDefense()) * emOne);
                hitPointsOne = (int) (hitPointsOne - 50 * (pokemonTwo.getAttack() / pokemonOne.getDefense()) * emTwo);
            }

            if (hitPointsOne > 0) {
                winner = pokemonOne.getName();
                finalScore = hitPointsOne;
            } else if (hitPointsTwo > 0) {
                winner = pokemonTwo.getName();
                finalScore = hitPointsTwo;
            } else {
                winner = "This game is a draw";
                finalScore = 0;
            }

            //returning the final result

            return Map.of(
                    "winner", winner,
                    "hitPoints", finalScore);

        } catch (Exception e) {
            return Map.of(
                    "winner", "None",
                    "hitPoints", -1000);
        }
    }

}
