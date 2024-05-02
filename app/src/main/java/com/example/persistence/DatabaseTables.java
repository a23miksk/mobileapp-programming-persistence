package com.example.persistence;

public class DatabaseTables {
    static class Planet {
        static final String TABLE_NAME = "planet";
        static final String COLUMN_NAME_ID = "id";
        static final String COLUMN_NAME_NAME = "name";
        static final String COLUMN_NAME_RADIUS = "radius";
        static final String COLUMN_NAME_HABITABLE = "habitable";
    }

    static final String SQL_CREATE_TABLE_PLANET =
            "CREATE TABLE " + Planet.TABLE_NAME + " (" +
                    Planet.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    Planet.COLUMN_NAME_NAME + " TEXT," +
                    Planet.COLUMN_NAME_RADIUS + " INTEGER," +
                    Planet.COLUMN_NAME_HABITABLE + " Text)";
    static final String SQL_DELETE_TABLE_PLANET =
            "DROP TABLE IF EXISTS " + Planet.TABLE_NAME;


}
