package com.example.elie.clienttaxiapp.Model.Entities.Entities;

import com.google.firebase.database.Exclude;

import java.io.Serializable;



public class ClientRequest implements Serializable

{
    private String  Name;
    private String  Mail;
    private String Phone;
    private int id;
    private ClientRequestStatus status;
    public String Destination;
    private double DepartureLongitude;
    private double DepartureLatitude;
    private double ArrivalLongitude;
    private double ArrivalLatitude;


     //region ****** Constructors *****

    public ClientRequest()
    {
        Name="Raphael";
        id=0;
        Phone="0584769854";
        status= ClientRequestStatus._Waiting;
        Mail="b@gmail";
        Destination="80 Avenue des Vosges, Strasbourg";
        DepartureLatitude=90;
        DepartureLongitude=0;
        ArrivalLatitude=89;
        ArrivalLongitude=2;
    }


    public ClientRequest(ClientRequest c)
    {
        Name=c.getName();
        id=c.getId();
        Phone=c.getPhone();
        status=c.getStatus();
        Mail=c.getMail();
        DepartureLatitude=c.getDepartureLatitude();
        DepartureLongitude=c.DepartureLongitude;
        ArrivalLatitude=c.ArrivalLatitude;
        ArrivalLongitude=c.ArrivalLongitude;
    }

    //endregion




    //region ***** Getters/Setters *****
    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getMail()
    {
        return Mail;
    }

    public String getPhone()
    {
        return Phone;
    }

    public void setPhone(String phone)
    {
        Phone = phone;
    }


    @Exclude
    public int getId()
    {
        return id;
    }

    public String getDestination()

    {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public ClientRequestStatus getStatus() {
        return status;
    }

    public void setStatus(ClientRequestStatus status) {
        this.status = status;
    }

    public double getDepartureLongitude() {
        return DepartureLongitude;
    }

    public void setDepartureLongitude(double departureLongitude) {
        DepartureLongitude = departureLongitude;
    }

    public double getDepartureLatitude() {
        return DepartureLatitude;
    }

    public void setDepartureLatitude(double departureLatitude) {
        DepartureLatitude=departureLatitude;

    }

    public double getArrivalLongitude() {
        return ArrivalLongitude;
    }

    public void setArrivalLongitude(double arrivalLongitude) {
        ArrivalLongitude = arrivalLongitude;
    }

    //To get simple representation of the client with Name and ID
    public String getRepresentation()
    {
        return "\n Mr "+getName()+" ID: "+getId();
    }

    public double getArrivalLatitude()
    {
        return ArrivalLatitude;
    }

    public void setArrivalLatitude(double arrivalLatitude) {
        ArrivalLatitude = arrivalLatitude;
    }

    @Override
    public String toString() {
        return "\n Mr "+getName()+" ID: "+getId()+"\n Your request  is being processed ";
    }

    //endregion
}



