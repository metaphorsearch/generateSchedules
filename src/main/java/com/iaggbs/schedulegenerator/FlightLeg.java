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
    String operatedBy;
    String seq;
    ZonedDateTime originDate;
    String originAirportCode;
    String originCityCode;
    ZonedDateTime originLocalDateTime;
    String destinationAirportCode;
    String destinationCityCode;
    ZonedDateTime destinationLocalDateTime;
    String originAirportWithDate ;
    String originCityWithDate ;
    String destinationAirportWithDate ;
    String destinationCityWithDate ;

    public FlightLeg(String id, String recordType, String flightNumber, String operatedBy, String seq,
                     ZonedDateTime originDate, String originAirportCode, String originCityCode,
                     ZonedDateTime originLocalDateTime, String destinationAirportCode,
                     String destinationCityCode, ZonedDateTime destinationLocalDateTime) {

        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        this.id = id;
        this.recordType = recordType;
        this.flightNumber = flightNumber;
        this.operatedBy = operatedBy;
        this.seq = seq;
        this.originDate = originDate;
        this.originAirportCode = originAirportCode ;
        this.originCityCode = originCityCode ;
        this.originLocalDateTime = originLocalDateTime;
        this.destinationAirportCode = destinationAirportCode ;
        this.destinationCityCode = destinationCityCode;
        this.destinationLocalDateTime = destinationLocalDateTime;
        this.originAirportWithDate = originAirportCode.concat(originDate.format(dayFormatter));
        this.originCityWithDate = originCityCode.concat(originDate.format(dayFormatter));
        this.destinationAirportWithDate = destinationAirportCode.concat(originDate.format(dayFormatter));
        this.destinationCityWithDate = destinationCityCode.concat(originDate.format(dayFormatter));


    }

    @Override
    public String toString() {
        return "FlightLeg{" +
                "id='" + id + '\'' +
                ", recordType='" + recordType + '\'' +
                ", operationFlightNumber='" + flightNumber + '\'' +
                ", operatedBy='" + operatedBy + '\'' +
                ", legSequence='" + seq + '\'' +
                ", originDate=" + originDate +
                ", originAirportCode='" + originAirportCode + '\'' +
                ", originCityCode='" + originCityCode + '\'' +
                ", originLocalDateTime=" + originLocalDateTime +
                ", destinationAirportCode='" + destinationAirportCode + '\'' +
                ", destinationCityCode='" + destinationCityCode + '\'' +
                ", destinationLocalDateTime=" + destinationLocalDateTime +
                ", originAirportWithDate=" + originAirportWithDate +
                ", originCityWithDate=" + originCityWithDate +
                ", destinationAirportWithDate=" + destinationAirportWithDate +
                ", destinationCityWithDate=" + destinationCityWithDate +
                '}';
    }

    public SolrInputDocument toSolrDocument(){

        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", id);
        doc.addField("recordType", recordType);
        doc.addField("operationFlightNumber", flightNumber);
        doc.addField("operatedBy", operatedBy);
        doc.addField("legSequence", seq);
        doc.addField("originDate", originDate.format(DateTimeFormatter.ISO_INSTANT));
        doc.addField("originAirportCode", originAirportCode);
        doc.addField("originCityCode", originCityCode);
        doc.addField("originLocalDateTime", originLocalDateTime.format(DateTimeFormatter.ISO_INSTANT));
        doc.addField("destinationAirportCode", destinationAirportCode);
        doc.addField("destinationCityCode", destinationCityCode);
        doc.addField("destinationLocalDateTime", destinationLocalDateTime.format(DateTimeFormatter.ISO_INSTANT));
        doc.addField("originAirportWithDate", originAirportWithDate);
        doc.addField("originCityWithDate", originCityWithDate);
        doc.addField("destinationAirportWithDate", destinationAirportWithDate);
        doc.addField("destinationCityWithDate", destinationCityWithDate);

        return doc ;
    }
}
