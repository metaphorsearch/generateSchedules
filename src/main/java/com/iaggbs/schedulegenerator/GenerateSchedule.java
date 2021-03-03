package com.iaggbs.schedulegenerator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenerateSchedule {

    private List<String> cityList ;
    public GenerateSchedule(int cityNum) {
        this.cityList = generateCityList(cityNum);
    }

    public FlightLeg generateFlight(String recordId, String flight, ZonedDateTime date, int hour){
        // (int) Math.random() * cityList.size()
        int oIndex = (int) (Math.random() * cityList.size()) ;
        int dIndex = (int) (Math.random() * cityList.size()) ;
        if(oIndex == dIndex && oIndex+1 < cityList.size()) {
            dIndex = oIndex+1 ;
        } else if(oIndex == dIndex){
            dIndex = oIndex-1 ;
        }

        String id = recordId  ;
        String recordType = "flight-leg" ;
        String flightNumber = flight ;
        String seq = "1" ;
        ZonedDateTime originDate = date ;
        String originAirportCode = this.cityList.get(oIndex) ;
        String originCityCode = this.cityList.get(oIndex) ;
        ZonedDateTime originLocalDateTime = date.plusHours(hour) ;
        String destinationAirportCode  = this.cityList.get(dIndex);
        String destinationCityCode  = this.cityList.get(dIndex);
        ZonedDateTime destinationLocalDateTime = originLocalDateTime.plusHours(2);

        return new FlightLeg(id, recordType, flightNumber, seq, originDate, originAirportCode,
                originCityCode,originLocalDateTime, destinationAirportCode, destinationCityCode,
                destinationLocalDateTime  );
    }
    public List<String> generateCityList(int num){
        String[] chars =  {"A", "B", "C", "D", "W", "X", "Y", "Z", "T"};
        List cityList = new ArrayList() ;
        int count = 0 ;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    cityList.add(chars[i]+chars[j]+chars[k]) ;
                    count++ ;
                    if (count >= num) return cityList;
                }
            }
        }

        return cityList ;
    }


}
