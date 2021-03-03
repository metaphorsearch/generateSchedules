package com.iaggbs.schedulegenerator;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FlightLeg {

    String id;
    String recordType;
    String flightNumber;
    String seq;
    ZonedDateTime originDate;
    String originAirportCode;
    String originCityCode;
    ZonedDateTime originLocalDateTime;
    String destinationAirportCode;
    String destinationCityCode;
    ZonedDateTime destinationLocalDateTime;

    public FlightLeg(String id, String recordType, String flightNumber, String seq,
                     ZonedDateTime originDate, String originAirportCode, String originCityCode,
                     ZonedDateTime originLocalDateTime, String destinationAirportCode,
                     String destinationCityCode, ZonedDateTime destinationLocalDateTime) {
    this.id = id;
    this.recordType = recordType;
    this.flightNumber = flightNumber;
    this.seq = seq;
    this.originDate = originDate;
    this.originAirportCode = originAirportCode ;
    this.originCityCode = originCityCode ;
    this.originLocalDateTime = originLocalDateTime;
    this.destinationAirportCode = destinationAirportCode ;
    this.destinationCityCode = destinationCityCode;
    this.destinationLocalDateTime = destinationLocalDateTime;

    }

    @Override
    public String toString() {
        return "FlightLeg{" +
                "id='" + id + '\'' +
                ", recordType='" + recordType + '\'' +
                ", operationFlightNumber='" + flightNumber + '\'' +
                ", legSequence='" + seq + '\'' +
                ", originDate=" + originDate +
                ", originAirportCode='" + originAirportCode + '\'' +
                ", originCityCode='" + originCityCode + '\'' +
                ", originLocalDateTime=" + originLocalDateTime +
                ", DestinationAirportCode='" + destinationAirportCode + '\'' +
                ", DestinationCityCode='" + destinationCityCode + '\'' +
                ", DestinationLocalDateTime=" + destinationLocalDateTime +
                '}';
    }

    public SolrInputDocument toSolrDocument(){

        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", id);
        doc.addField("recordType", recordType);
        doc.addField("operationFlightNumber", flightNumber);
        doc.addField("legSequence", seq);
        doc.addField("originDate", originDate.format(DateTimeFormatter.ISO_INSTANT));
        doc.addField("originAirportCode", originAirportCode);
        doc.addField("originCityCode", originCityCode);
        doc.addField("originLocalDateTime", originLocalDateTime.format(DateTimeFormatter.ISO_INSTANT));
        doc.addField("DestinationAirportCode", destinationAirportCode);
        doc.addField("DestinationCityCode", destinationCityCode);
        doc.addField("DestinationLocalDateTime", destinationLocalDateTime.format(DateTimeFormatter.ISO_INSTANT));

        return doc ;
    }
}
