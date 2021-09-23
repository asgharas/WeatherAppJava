package com.asghar.myweather;

public class Weather {
    private String weatherStateName;
    private String weatherStateAbbreviation;
    private String windDirectionCompass;
    private String date;
    private double minTemp;
    private double maxTemp;
    private double theTemp;
    private double windSpeed;
    private String windDir;
    private double airPressure;
    private double humidity;
    private double visibility;

    public Weather(String weatherStateName, String weatherStateAbbreviation, String windDirectionCompass,
                   String date, double minTemp, double maxTemp, double theTemp, double windSpeed, String windDir,
                   double airPressure, double humidity, double visibility) {
        this.weatherStateName = weatherStateName;
        this.weatherStateAbbreviation = weatherStateAbbreviation;
        this.windDirectionCompass = windDirectionCompass;
        this.date = date;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.theTemp = theTemp;
        this.windSpeed = windSpeed;
        this.windDir = windDir;
        this.airPressure = airPressure;
        this.humidity = humidity;
        this.visibility = visibility;
    }

    public String getWeatherStateName() {
        return weatherStateName;
    }

    public String getWeatherStateAbbreviation() {
        return weatherStateAbbreviation;
    }

    public String getWindDirectionCompass() {
        return windDirectionCompass;
    }

    public String getDate() {
        return date;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getTheTemp() {
        return theTemp;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getWindDir() {
        return windDir;
    }

    public double getAirPressure() {
        return airPressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getVisibility() {
        return visibility;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "weatherStateName='" + weatherStateName + '\'' +
                ", weatherStateAbbreviation='" + weatherStateAbbreviation + '\'' +
                ", windDirectionCompass='" + windDirectionCompass + '\'' +
                ", date='" + date + '\'' +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                ", theTemp=" + theTemp +
                ", windSpeed=" + windSpeed +
                ", windDir=" + windDir +
                ", airPressure=" + airPressure +
                ", humidity=" + humidity +
                ", visibility=" + visibility +
                '}';
    }
}
