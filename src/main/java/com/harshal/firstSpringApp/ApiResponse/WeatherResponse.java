package com.harshal.firstSpringApp.ApiResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {
    public Current current;
    private Location location;

    @Getter
    @Setter
    public class Current {
        public int temperature;
        public List<String> weather_descriptions;
        public String is_day;
        public int feelslike;
    }

    @Getter
    @Setter
    public class Location{
        public String name;
        public String country;
    }


}




