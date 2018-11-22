package com.example.elie.clienttaxiapp.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.os.Build;
import android.Manifest;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.location.*;
import android.content.Context;
import android.content.pm.PackageManager;
import android.annotation.SuppressLint;

import java.lang.String;
import java.util.List;

import com.example.elie.clienttaxiapp.Model.Model.Backend.Backend_Factory;
import com.example.elie.clienttaxiapp.Model.Model.DS.FireBase_DBManager;
import com.example.elie.clienttaxiapp.Model.Model.Entities.ClientRequest;
import com.example.elie.clienttaxiapp.R;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    //region ***** Fields ******
    EditText ID;
    Backend_Factory backend_factory=new Backend_Factory();
    EditText Name;
    EditText Mail;
    EditText Phone;
    EditText Destination;
    Button GetTaxi;

    double Departure_latitude=52;
    double Departure_longitude=25;
    double Arrival_latitude=52;
    double Arrival_longitude=25;
    LocationManager locationManager;
    LocationListener locationListener;

    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FindViews();
    }

    /***
     * Function : FindViews
     * Explication : The function makes the link between the Fields of the Activity and the elements of the layout
     * and set the events for the elements that need to subscribe to an event . Here the button . I also implements the
     * location manager and the location listener .
     */
    private void FindViews()

    {
        ID= (EditText) findViewById(R.id.ID);
        Name= (EditText) findViewById(R.id.Name);
        Phone= (EditText) findViewById(R.id.Tel);
        Mail= (EditText) findViewById(R.id.Mail);
        Destination= (EditText) findViewById(R.id.Dest);
        GetTaxi= (Button) findViewById(R.id.Send);
        GetTaxi.setOnClickListener(this);
        locationManager= (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location)
            {
                Departure_latitude=location.getLatitude();
                Departure_longitude=location.getLongitude();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        } ;

    }

    //region ***** GoogleMaps *****
    private void getLocation()
    {

        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, 5);
        }

        else
        {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10,0,locationListener);

        }
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if (requestCode == 5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0, locationListener);
            }
            else {


                Toast.makeText(this, "Until you grant the permission, we cannot display the location",
                        Toast.LENGTH_SHORT).show();

            }

        }

    }

    /***
     * Function to get the latitude and the longitude of the destination that the user entered
     * by using a Geocoder and its function getFromLocationName.
     */
    private void getDestination()
    {

        Geocoder gc = new Geocoder(this);
        try
        {
            if(gc.isPresent()){
                List<Address> list = gc.getFromLocationName(Destination.getText().toString(),1);
                Address address = list.get(0);
                Arrival_latitude = address.getLatitude();
                Arrival_longitude= address.getLongitude();
            }
        }

        catch (Exception exception )

        {
            Toast toast = Toast.makeText(this ,exception.toString(), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();

        }

    }

    //endregion


    /**** Function : getClient
     * Explication: This function creates the client that the fields are the data that the user enters in the View .
     * This makes the link between the Entities and the ViewController
     * @return A clientRequest with new data
     */

    public ClientRequest getClient()

    {
        ClientRequest client=new ClientRequest();
        int id= Integer.valueOf(ID.getText().toString());
        client.setId(id);
        client.setPhone(Phone.getText().toString());
        client.setMail(Mail.getText().toString());
        client.setName(Name.getText().toString());
        client.setDepartureLatitude(Departure_latitude);
        client.setDepartureLongitude(Departure_longitude);
        client.setArrivalLatitude(Arrival_latitude);
        client.setArrivalLongitude(Arrival_longitude);

        return client;
    }


    /***
     * Function : OnClick
     * @param v View
     * The function that occur when I click on the Button . I calls to  the Localisation functions and then I add the client
     *  to the FireBase . I send a Toast in order to get information to my user . Then all the view models come back to null
     */
    @Override
    public void onClick(View v)
    {
        getLocation();
        getDestination();
        ClientRequest c= getClient();
        FireBase_DBManager f = (FireBase_DBManager)backend_factory.getfactory();
        f.addClientRequest(c);
        Toast toast = Toast.makeText(this ,c.toString() , Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
        ID.setText("");
        Name.setText("");
        Destination.setText("");
        Phone.setText("");
        Mail.setText("");
    }

}
