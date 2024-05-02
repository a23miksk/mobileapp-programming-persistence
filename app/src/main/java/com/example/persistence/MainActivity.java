package com.example.persistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();

        Button buttonWrite = findViewById(R.id.write);
        Button buttonRead = findViewById(R.id.read);

        buttonWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText planetName = (EditText) findViewById(R.id.planetNamn);
                EditText planetRadius = (EditText) findViewById(R.id.planetRadius);
                EditText planetHabitable= (EditText) findViewById(R.id.planetBebolig);

                addPlanet(planetName.getText().toString(), planetRadius.getText().toString(), planetHabitable.getText().toString());


            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView listText = (TextView) findViewById(R.id.listText);
                List<String> planets = getPlanets();
                StringBuilder builder = new StringBuilder();
                for (String planet : planets) {
                    builder.append(planet).append("\n");
                }
                listText.setText(builder.toString());
            }
        });
    }

    private long addPlanet(String name, String radius, String habitable) {
        ContentValues values = new ContentValues();
        values.put(DatabaseTables.Planet.COLUMN_NAME_NAME, name);
        values.put(DatabaseTables.Planet.COLUMN_NAME_RADIUS, radius);
        values.put(DatabaseTables.Planet.COLUMN_NAME_HABITABLE, habitable);
        return database.insert(DatabaseTables.Planet.TABLE_NAME, null, values);
    }

    private List<String> getPlanets() {
        Cursor cursor = database.query(DatabaseTables.Planet.TABLE_NAME, new String[] {
                DatabaseTables.Planet.COLUMN_NAME_ID,
                DatabaseTables.Planet.COLUMN_NAME_NAME,
                DatabaseTables.Planet.COLUMN_NAME_RADIUS,
                DatabaseTables.Planet.COLUMN_NAME_HABITABLE
        }, null, null, null, null, null);
        List<String> planets = new ArrayList<>();
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Planet.COLUMN_NAME_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Planet.COLUMN_NAME_NAME));
            String radius = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Planet.COLUMN_NAME_RADIUS));
            String habitable = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Planet.COLUMN_NAME_HABITABLE));
            planets.add(String.format("Name: %s, Radius: %s, Bebolig: %s", name, radius, habitable));
        }
        cursor.close();
        return planets;
    }
}
