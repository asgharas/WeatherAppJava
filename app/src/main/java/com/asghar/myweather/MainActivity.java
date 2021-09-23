package com.asghar.myweather;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Locale;


public class MainActivity extends AppCompatActivity implements DownProsData.OnDataAvailable {

    private EditText searchInput;
    private ImageView searchBtn;
    private ImageView weatherImage;
    private TextView theTemp;
    private TextView minTemp;
    private TextView maxTemp;
    private TextView wind;
    private TextView humidity;
    private TextView visibility;
    private TextView cityTextView;

    private String imageURL = "https://www.metaweather.com/static/img/weather/png/%s.png"; //https://www.metaweather.com/static/img/weather/png/64/c.png
    public static String url = "https://www.metaweather.com/api/location/search/?";
    private static final String TAG = "MainActivity";
    private DownProsData downProsData;

    private String searchString;
    private SharedPreferences sharedPreferences;

    public static final String SEARCH_STRING = "SEARCH_STRING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchInput = findViewById(R.id.location_input_field);
        searchBtn = findViewById(R.id.search_btn);
        weatherImage = findViewById(R.id.weather_image);
        theTemp = findViewById(R.id.temprature);
        minTemp = findViewById(R.id.low_textview);
        maxTemp = findViewById(R.id.high_textview);
        wind = findViewById(R.id.wind);
        humidity = findViewById(R.id.humidity);
        visibility = findViewById(R.id.visibility);
        cityTextView = findViewById(R.id.city_name);
        cityTextView.setText(R.string.loading);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        searchString = sharedPreferences.getString(SEARCH_STRING, "");
        if (searchString == null || searchString.trim().length() == 0){
            processData(url + "lattlong=36.96,-122.02");
        } else {
            processData(searchString);
        }


        searchBtn.setOnClickListener(view -> {
            searchString = searchInput.getText().toString();
            processData(searchString);
        });

       }

    @Override
    public void onDataAvailable(Weather weather, String cityName) {
        Log.d(TAG, "onDataAvailable: City: " +  cityName);
        Log.d(TAG, "onDataAvailable: Weather: " + weather.toString());
        Log.d(TAG, "onDataAvailable: Weather Abbr:" + weather.getWeatherStateAbbreviation());

        Picasso.get().load(String.format(imageURL, weather.getWeatherStateAbbreviation())).into(weatherImage);
        cityTextView.setText(cityName);
        theTemp.setText(String.format(Locale.ENGLISH, "%.1f%s", weather.getTheTemp(), getString(R.string.degree_centigrade)));
        maxTemp.setText(String.format(Locale.ENGLISH, "%s: %.1f%s", getString(R.string.high_temp), weather.getMaxTemp(), getString(R.string.degree_centigrade)));
        minTemp.setText(String.format(Locale.ENGLISH, "%s: %.1f%s", getString(R.string.low_temp), weather.getMinTemp(), getString(R.string.degree_centigrade)));
        wind.setText(String.format(Locale.ENGLISH, "%s %s %.1f mph", getString(R.string.wind_emoji), weather.getWindDir(), weather.getWindSpeed()));
        humidity.setText(String.format(Locale.ENGLISH, "%s %.1f%s", getString(R.string.water), weather.getHumidity(), "%"));
        visibility.setText(String.format(Locale.ENGLISH, "%s %.1f miles", getString(R.string.eye_emoji), weather.getVisibility()));


    }

    private void processData(String link){
        String temp = "";
        if (link.trim().length() != 0){
            try {
                String[] parts = link.trim().split(",");
                double lat = Double.parseDouble(parts[0]);
                double lon = Double.parseDouble(parts[1]);
                temp = "lattlong=" + lat + "," + lon;
            }catch (NumberFormatException e){
                temp = "query=" + link;
            }
        } else {
            Toast.makeText(this, "Enter in Correct Format e.g London or  123.4,31.5", Toast.LENGTH_SHORT).show();
        }

        sharedPreferences.edit().putString(SEARCH_STRING, searchString).apply();
        downProsData = new DownProsData();
        downProsData.setCallBack(this);
        downProsData.execute(url + temp);

    }
}