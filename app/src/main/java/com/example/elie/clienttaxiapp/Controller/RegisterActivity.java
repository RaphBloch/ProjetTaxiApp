package com.example.elie.clienttaxiapp.Controller;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.os.Build;
import android.Manifest;
import android.text.TextUtils;
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
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.elie.clienttaxiapp.Model.Model.Backend.Backend;
import com.example.elie.clienttaxiapp.Model.Model.Backend.Backend_Factory;
import com.example.elie.clienttaxiapp.Model.Model.DS.FireBase_DBManager;
import com.example.elie.clienttaxiapp.Model.Model.Entities.ClientRequest;
import com.example.elie.clienttaxiapp.R;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import javax.security.auth.Destroyable;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    //region ***** Fields ******
    EditText ID;
    static  FireBase_DBManager f = (FireBase_DBManager)

            Backend_Factory.getFactory();
    EditText Name;
    EditText Mail;
    EditText Phone;
    //EditText Destination;
    Button GetTaxi;

    double Departure_latitude=52;
    double Departure_longitude=25;
    double Arrival_latitude=52;
    double Arrival_longitude=25;
    LocationManager locationManager;
    LocationListener locationListener;
     String Destination;

    Location locationA = new Location("A");//= new Location(from);
    //Location locationB = new Location("B") ;//= new Location(to);

    private PlaceAutocompleteFragment placeAutocompleteFragment1;


    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FindViews();
        getLocation();

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
        placeAutocompleteFragment1 = (PlaceAutocompleteFragment)getFragmentManager().findFragmentById( R.id.place_autocomplete_fragment1 );
        placeAutocompleteFragment1.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                locationA.setLatitude(place.getLatLng().latitude);
                locationA.setLongitude(place.getLatLng().longitude);
                Destination= place.getAddress().toString();

                // .getAddress().toString();//get place details here
            }

            @Override
            public void onError(Status status) {

            }
        });

        //Destination= (EditText) findViewById(R.id.Dest);
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
    /*private void getDestination()
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

    }*/

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
        client.setDestination(Destination);
        client.setArrivalLatitude(locationA.getLatitude());
        client.setArrivalLongitude(locationA.getLongitude());
        client.setDataTime(Calendar.getInstance().getTime().getTime());

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

        //getDestination();
        //Toast.makeText(this,Destination+String.valueOf(locationA.getLatitude()),Toast.LENGTH_LONG).show();

        login();
        ClientRequest c= getClient();

        new myTask().execute(c);
        Toast toast = Toast.makeText(this ,c.toString() , Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
        ID.setText("");
        Name.setText("");
        //Destination.setText("");
        Phone.setText("");
        Mail.setText("");

    }

    private void login(){
        if (TextUtils.isEmpty(ID.getText().toString().trim())||TextUtils.isEmpty(Name.getText().toString().trim())||
                TextUtils.isEmpty(Phone.getText().toString().trim())||TextUtils.isEmpty(Mail.getText().toString().trim()))
            //|| TextUtils.isEmpty(Destination.getText().toString().trim()))
        {
            ID.setError("Fields can't be Empty");
            Name.setError("Fields can't be Empty");
            Phone.setError("Fields can't be Empty");
            Mail.setError("Fields can't be Empty");
           // Destination.setError("Fields can't be Empty");
        }
        else if (!emailValidator(Mail.getText().toString()))
        {
            Mail.setError("Please Enter Valid Email Address");
        }
        /*else if (!phoneValidator(Phone.getText().toString()))
        {
            Toast.makeText(this,"nbr :"+Integer.valueOf(Phone.getText().toString()),Toast.LENGTH_SHORT).show();
            Phone.setError("Please Enter Valid Phone Number (minimun 10 number) !");
        }*/
        else
        {
           // Toast.makeText(this ,"Login Successful",Toast.LENGTH_SHORT).show();
        }
    }

    //Email Validation Using Regex
    public boolean emailValidator (String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN ="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[_A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean phoneValidator (String phonenumber)
    {
       return (Integer.parseInt(phonenumber) == 9 );


    }

    private  class myTask extends AsyncTask<ClientRequest,Void,Void>
    {
        @Override
        protected Void doInBackground(ClientRequest... clientRequests)
        {

            f.addClientToFireBase(clientRequests[0], new FireBase_DBManager.Action<String>() {
                @Override
                public void OnSuccess(String obj) {
                    Toast.makeText(getBaseContext(), "Name : " + obj ,Toast.LENGTH_SHORT).show();
                }

                @Override
                public void OnProgress(String status, double percent) {


                }

                @Override
                public void OnFailure(Exception exception) {
                    Toast.makeText(getBaseContext(), "Error : " + exception ,Toast.LENGTH_SHORT).show();

                }
            });

            return null;
        }
    }

}


