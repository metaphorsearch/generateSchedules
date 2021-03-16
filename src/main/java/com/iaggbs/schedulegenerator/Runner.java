package com.iaggbs.schedulegenerator;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Runner {
    public static void main(String[] args) throws IOException, SolrServerException {

        System.out.println("Starting to index");
        System.out.println(new Date().toString());
       // runIndexing();
        runIndexing2();
        System.out.println(new Date().toString());
        System.out.println("Finished Indexing");

    }

    private static void runIndexing() throws IOException, SolrServerException {
        SolrClient solr;
        solr = new HttpSolrClient.Builder("http://localhost:8983/solr/schedule")
                    .withConnectionTimeout(10000)
                    .withSocketTimeout(60000)
                    .build();
        ZonedDateTime now = ZonedDateTime.now() ;
        ZonedDateTime tom = now.withNano(0).withSecond(0).withMinute(0).withHour(0).plusDays(1);
        int numberOfCalendarDays = 10 ;
        int numberOfCities = 3 ;
        int numberOfRoutesPerDay = 10;
        int numberOfFlightsPerRoutePerDay = 1 ;
        int flightCount = 1 ;
        int idCount = 1 ;
        int hour = 5 ;

      GenerateSchedule generator = new GenerateSchedule();



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


    private static void runIndexing2() throws IOException, SolrServerException {
        SolrClient solr;
        solr = new HttpSolrClient.Builder("http://localhost:8983/solr/schedule")
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
        ZonedDateTime now = ZonedDateTime.now() ;
        ZonedDateTime tom = now.withNano(0).withSecond(0).withMinute(0).withHour(0).plusDays(1);
        int numberOfCalendarDays = 365 ;
        int numberOfFlightsPerDay = 3 ;
        int idCount = 1 ;
        int hour = 5 ;
        int hubNumberOfFlightsPerDay = 15
                ;
        Collection<SolrInputDocument> batchDocs = new ArrayList() ;

        GenerateSchedule generator = new GenerateSchedule();
        // for each day
           // for each hub city (AAA, BBB , CCC etc)
             // for each city in a hub (ABC, ACC, etc)
               // for each time slot

        for (int i = 0; i < numberOfCalendarDays; i++) {
            for (int j = 0; j < generator.hubCityList.size(); j++) {
                for (int k = 0; k < generator.cityList.size(); k++) {
                    for (int l = 0; l < numberOfFlightsPerDay; l++) {

                        if (!generator.hubCityList.get(j).equals(generator.cityList.get(k)) &&
                                generator.hubCityList.get(j).substring(0, 1).equals(generator.cityList.get(k).substring(0, 1))) {
                            //out
                            batchDocs.add(generator.generateFlight2("record" + idCount,
                                    generator.hubCityList.get(j), generator.cityList.get(k), tom, hour).toSolrDocument());

                            idCount++;
                            //return
                            batchDocs.add(generator.generateFlight2("record" + idCount,
                                    generator.cityList.get(k), generator.hubCityList.get(j), tom, hour).toSolrDocument());

                            idCount++;
                            hour=hour+7;
                        }
                    }
                    hour=5;
                }

            }
            solr.add(batchDocs);
            batchDocs.clear();
            tom = tom.plusDays(1);
        }




        // generate hub - hub flights
        // for each day
        // for each hub city (AAA, BBB , CCC etc)
        // for each hub time slot
        // for each hub city (AAA, BBB , CCC etc)

        tom = now.withNano(0).withSecond(0).withMinute(0).withHour(0).plusDays(1);
        for (int i = 0; i < numberOfCalendarDays; i++) {
            for (int j = 0; j < generator.hubCityList.size(); j++) {
                    for (int l = 0; l < hubNumberOfFlightsPerDay; l++) {
                        for (int k = 0; k < generator.hubCityList.size(); k++) {

                            if (!generator.hubCityList.get(j).equals(generator.hubCityList.get(k))) {
                                //hub to hub flight
                                batchDocs.add(generator.generateFlight2("record" + idCount,
                                        generator.hubCityList.get(j), generator.hubCityList.get(k), tom, hour).toSolrDocument());

                                idCount++;

                            }
                        }
                        hour=hour+1;
                }
                hour=5;
            }
            solr.add(batchDocs);
            batchDocs.clear();
            tom = tom.plusDays(1);
        }


    }

}
