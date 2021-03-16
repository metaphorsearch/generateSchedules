package com.iaggbs.schedulegenerator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenerateSchedule {

    public List<String> cityList ;
    public List<String> hubCityList ;

    public GenerateSchedule() {
        this.cityList = generateCityList();

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
        String operatedBy = flight.substring(0,1);

        return new FlightLeg(id, recordType, flightNumber, operatedBy, seq, originDate, originAirportCode,
                originCityCode,originLocalDateTime, destinationAirportCode, destinationCityCode,
                destinationLocalDateTime  );
    }

    public FlightLeg generateFlight2(String recordId, String originCity, String destinationCity, ZonedDateTime date, int hour){


        String id = recordId  ;
        String recordType = "flight-leg" ;
        String flightNumber = originCity+"-"+destinationCity+"-"+hour ;
        String seq = "1" ;
        ZonedDateTime originDate = date ;
        String originAirportCode = originCity ;
        String originCityCode = originCity ;
        ZonedDateTime originLocalDateTime = date.plusHours(hour) ;
        String destinationAirportCode  = destinationCity;
        String destinationCityCode  = destinationCity;
        ZonedDateTime destinationLocalDateTime = originLocalDateTime.plusHours(2);
        String operatedBy = flightNumber.substring(0,1);

        FlightLeg flightLeg = new FlightLeg(id, recordType, flightNumber, operatedBy, seq, originDate, originAirportCode,
                originCityCode,originLocalDateTime, destinationAirportCode, destinationCityCode,
                destinationLocalDateTime  );
       // System.out.println(flightLeg.toString());

        return flightLeg ;
    }
    public List<String> generateCityList(){
        String[] chars =  {"A", "B", "C", "D", "E", "F", "G", "H"};
        this.cityList = new ArrayList() ;
        this.hubCityList = new ArrayList() ;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    cityList.add(chars[i]+chars[j]+chars[k]) ;
                    if(chars[i] == chars[j] && chars[j] == chars[k]) {
                        hubCityList.add(chars[i]+chars[j]+chars[k]);
                    }
                }
            }
        }

        return this.cityList ;
    }


}
