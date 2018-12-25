package com.project.gui.smartparking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends Activity {

    Spinner citySpinner;
    Spinner countySpinner;
    Spinner placeSpinner;
    Button btnContinue;


    private ArrayAdapter<City> cityArrayAdapter;
    private ArrayAdapter<County> countyArrayAdapter;
    private ArrayAdapter<Place> placeArrayAdapter;

    private ArrayList<City> cities;
    private ArrayList<County> counties;
    private ArrayList<Place> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        citySpinner = (Spinner) findViewById(R.id.spCity);
        countySpinner = (Spinner) findViewById(R.id.spCounty);
        placeSpinner =  (Spinner) findViewById(R.id.spMall);


        cities = new ArrayList<>();
        counties = new ArrayList<>();
        places = new ArrayList<>();

        createLists();

        cityArrayAdapter = new ArrayAdapter<City>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, cities);
        cityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityArrayAdapter);

        countyArrayAdapter = new ArrayAdapter<County>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, counties);
        countyArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countySpinner.setAdapter(countyArrayAdapter);

        placeArrayAdapter = new ArrayAdapter<Place>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, places);
        placeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        placeSpinner.setAdapter(placeArrayAdapter);

        citySpinner.setOnItemSelectedListener(country_listener);
        countySpinner.setOnItemSelectedListener(county_listener);
        placeSpinner.setOnItemSelectedListener(place_listener);


        btnContinue = findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,AvailableSpacesActivity.class);
                startActivity(intent);
            }
        });

    }

    private AdapterView.OnItemSelectedListener country_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                final City City = (City) citySpinner.getItemAtPosition(position);
                ArrayList<County> tempStates = new ArrayList<>();

                tempStates.add(new County(0, new City(0, "Choose a City"), "Choose a County"));

                for (County singleCounty : counties) {
                    if (singleCounty.getCity().getCityID() == City.getCityID()) {
                        tempStates.add(singleCounty);
                    }
                }

                countyArrayAdapter = new ArrayAdapter<County>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, tempStates);
                countyArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                countySpinner.setAdapter(countyArrayAdapter);
            }

            placeArrayAdapter = new ArrayAdapter<Place>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, new ArrayList<Place>());
            placeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            placeSpinner.setAdapter(placeArrayAdapter);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener county_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                final County County = (County) countySpinner.getItemAtPosition(position);
                ArrayList<Place> tempplaces = new ArrayList<>();

                City City = new City(0, "Choose a City");
                County firstState = new County(0, City, "Choose a County");
                tempplaces.add(new Place(0, City, firstState, "Choose a Place"));

                for (Place singlePlace : places) {
                    if (singlePlace.getState().getCountyID() == County.getCountyID()) {
                        tempplaces.add(singlePlace);
                    }
                }

                placeArrayAdapter = new ArrayAdapter<Place>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, tempplaces);
                placeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                placeSpinner.setAdapter(placeArrayAdapter);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener place_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void createLists() {
        City city0 = new City(0, "Choose a City");
        City city1 = new City(1, "Muğla");
        City city2 = new City(2, "İstanbul");

        cities.add(new City(0, "Choose a City"));
        cities.add(new City(1, "Muğla"));
        cities.add(new City(2, "İstanbul"));

        County county0 = new County(0, city0, "Choose a County");
        County county1 = new County(1, city1, "Menteşe");
        County county2 = new County(2, city1, "Marmaris");
        County county3 = new County(3, city2, "Kadıköy");
        County county4 = new County(4, city2, "Taksim");

        counties.add(county0);
        counties.add(county1);
        counties.add(county2);
        counties.add(county3);
        counties.add(county4);

        places.add(new Place(0, city0, county0, "Choose a Place"));
        places.add(new Place(1, city1, county1, "PrimeMall"));
        places.add(new Place(2, city1, county1, "Rüyapark"));
        places.add(new Place(3, city1, county2, "Optimum"));
        places.add(new Place(4, city2, county2, "AnkaMall"));
        places.add(new Place(5, city2, county3, "Palladium"));
        places.add(new Place(6, city2, county3, "Kent"));
        places.add(new Place(7, city2, county4, "Onder"));
        places.add(new Place(8, city1, county4, "Kötekli"));
    }

    private class City implements Comparable<City> {

        private int cityID;
        private String cityName;


        public City(int cityID, String cityName) {
            this.cityID = cityID;
            this.cityName = cityName;
        }

        public int getCityID() {
            return cityID;
        }

        public String getCityName() {
            return cityName;
        }

        @Override
        public String toString() {
            return cityName;
        }


        @Override
        public int compareTo(City another) {
            return this.getCityID() - another.getCityID();//ascending order
//            return another.getCityID()-this.getCityID();//descending  order
        }
    }

    private class County implements Comparable<County> {

        private int CountyID;
        private City City;
        private String CountyName;

        public County(int CountyID, City City, String CountyName) {
            this.CountyID = CountyID;
            this.City = City;
            this.CountyName = CountyName;
        }

        public int getCountyID() {
            return CountyID;
        }

        public City getCity() {
            return City;
        }


        public String getStateName() {
            return CountyName;
        }

        @Override
        public String toString() {
            return CountyName;
        }

        @Override
        public int compareTo(County another) {
            return this.getCountyID() - another.getCountyID();//ascending order
//            return another.getCountyID()-this.getCountyID();//descending order
        }
    }

    private class Place implements Comparable<Place> {

        private int placeID;
        private City City;
        private County County;
        private String placeName;

        public Place(int placeID, City City, County County, String placeName) {
            this.placeID = placeID;
            this.City = City;
            this.County = County;
            this.placeName = placeName;
        }

        public int getPlaceID() {
            return placeID;
        }

        public City getCity() {
            return City;
        }

        public County getState() {
            return County;
        }

        public String getPlaceName() {
            return placeName;
        }

        @Override
        public String toString() {
            return placeName;
        }

        @Override
        public int compareTo(Place another) {
            return this.placeID - another.getPlaceID();//ascending order
//            return another.getPlaceID() - this.placeID;//descending order
        }

    }
}
