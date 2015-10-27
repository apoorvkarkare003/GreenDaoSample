package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class ExampleDaoGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "in.apoorv003.greendao.db");

        addSchema(schema);


        new DaoGenerator().generateAll(schema, "app/src/main/java");
    }

    private static void addSchema(Schema schema) {

        Entity movie = schema.addEntity("Movie");
        movie.addIdProperty().autoincrement().primaryKey();
        movie.addStringProperty("name").notNull();

        Entity director = schema.addEntity("Director");
        director.addIdProperty().autoincrement().primaryKey();
        director.addStringProperty("name").notNull();

        Entity actor = schema.addEntity("Actor");
        actor.addIdProperty().autoincrement().primaryKey();
        actor.addStringProperty("name").notNull();


        Entity productionHouse = schema.addEntity("ProductionHouse");
        productionHouse.addIdProperty().autoincrement().primaryKey();
        productionHouse.addStringProperty("name").notNull();

        Property movieId = movie.addLongProperty("movieId").notNull().getProperty();
        Property directorId = movie.addLongProperty("directorId").notNull().getProperty();
        Property productionId = movie.addLongProperty("productionId").notNull().getProperty();
        Property actorId = actor.addLongProperty("actorId").notNull().getProperty();
        Property actorMovieId = actor.addLongProperty("actorMovieId").notNull().getProperty();

        movie.addToOne(director, movieId);
        movie.addToOne(productionHouse, movieId);
        actor.addToOne(movie, actorId);

        director.addToMany(movie, directorId);
        productionHouse.addToMany(movie, productionId);
        movie.addToMany(actor, actorMovieId);

    }


}
