package com.iaggbs.schedulegenerator;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Runner {
    public static void main(String[] args) throws IOException, SolrServerException {

        runIndexing();

    }

    private static void runIndexing() throws IOException, SolrServerException {
        SolrClient solr;
        solr = new HttpSolrClient.Builder("http://localhost:8983/solr/schedule")
                    .withConnectionTimeout(10000)
                    .withSocketTimeout(60000)
                    .build();
        ZonedDateTime now = ZonedDateTime.now() ;
        ZonedDateTime tom = now.withNano(0).withSecond(0).withMinute(0).withHour(0).plusDays(1);
        int numberOfCalendarDays = 1 ;
        int numberOfCities = 3 ;
        int numberOfRoutesPerDay = 10;
        int numberOfFlightsPerRoutePerDay = 1 ;
        int flightCount = 1 ;
        int idCount = 1 ;
        int hour = 5 ;

      GenerateSchedule generator = new GenerateSchedule(numberOfCities);

        for (int i = 0; i < numberOfCalendarDays; i++) {
            for (int j = 0; j < numberOfRoutesPerDay; j++) {
                for (int k = 0; k < numberOfFlightsPerRoutePerDay; k++) {
                    solr.add(generator.generateFlight("record" + idCount, "ba" + flightCount, tom,
                            hour).toSolrDocument());
                    idCount++;
                    hour = hour + 4;
                    flightCount++;
                }
                hour = 5;
            }
            flightCount = 1;
            tom = tom.plusDays(1);
        }



    }

}
