package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.model.Character;
import com.example.demo.model.Droid;
import com.example.demo.model.Episode;
import com.example.demo.model.Human;
import com.example.demo.model.Starship;

@Controller
public class StarWarController {

    private final Map<String, Human> humans = new HashMap<>();
    private final Map<String, Droid> droids = new HashMap<>();
    private final Map<Integer, Starship> starships = new HashMap<>();

    public StarWarController() {
        Human luke = new Human(
                "1000",
                "Luke Skywalker",
                List.of(Episode.NEWHOPE, Episode.EMPIRE, Episode.JEDI),
                new ArrayList<>(),
                1.75
        );

        Droid r2d2 = new Droid(
                "2001",
                "R2-D2",
                List.of(Episode.NEWHOPE, Episode.EMPIRE, Episode.JEDI),
                new ArrayList<>(),
                "Astromech"
        );

        Starship falcon = new Starship(3000, "Millennium Falcon", 34.37);

        humans.put(luke.getId(), luke);
        droids.put(r2d2.getId(), r2d2);
        starships.put(falcon.getId(), falcon);
    }

    @QueryMapping
    public List<Human> humans() {
        return new ArrayList<>(humans.values());
    }

    @QueryMapping
    public List<Starship> starships() {
        return new ArrayList<>(starships.values());
    }

    @QueryMapping
    public Character character(@Argument String id) {
        if (humans.containsKey(id)) return humans.get(id);
        if (droids.containsKey(id)) return droids.get(id);
        return null;
    }

    @QueryMapping
    public Human human(@Argument String id) {
        return humans.get(id);
    }

    @QueryMapping
    public Droid droid(@Argument String id) {
        return droids.get(id);
    }

    @MutationMapping
    public Human createHuman(@Argument String id,
                             @Argument String name,
                             @Argument Float height) {
        Human human = new Human(
                id,
                name,
                new ArrayList<>(),
                new ArrayList<>(),
                height != null ? height : 0.0
        );
        humans.put(id, human);
        return human;
    }

    @MutationMapping
    public Droid createDroid(@Argument String id,
                             @Argument String name,
                             @Argument String primaryFunction) {
        Droid droid = new Droid(
                id,
                name,
                new ArrayList<>(),
                new ArrayList<>(),
                primaryFunction
        );
        droids.put(id, droid);
        return droid;
    }

    @MutationMapping
    public Starship createStarship(@Argument Integer id,
                                   @Argument String name,
                                   @Argument Double length) {
        Starship starship = new Starship(
                id,
                name,
                length != null ? length : 0.0
        );
        starships.put(id, starship);
        return starship;
    }

    @MutationMapping
    public Character addFriend(@Argument String characterId,
                               @Argument String friendId) {
        Character character = character(characterId);
        Character friend = character(friendId);

        if (character == null || friend == null) {
            return null;
        }

        if (character.getFriends() == null) {
            throw new IllegalStateException("friends não pode ser nulo");
        }
        if (friend.getFriends() == null) {
            throw new IllegalStateException("friends não pode ser nulo");
        }

        character.getFriends().add(friend);
        friend.getFriends().add(character);

        return character;
    }
}