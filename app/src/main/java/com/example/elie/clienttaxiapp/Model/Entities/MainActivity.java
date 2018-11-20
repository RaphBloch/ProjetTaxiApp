
package com.example.elie.clienttaxiapp.Model.Entities;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Build;
import android.Manifest;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.location.*;
import android.content.Context;
import android.content.pm.PackageManager;
import android.annotation.SuppressLint;
import org.json.*;
import java.lang.String;
import java.util.Locale;
import java.util.List;

import com.example.elie.clienttaxiapp.Model.Entities.DS.FireBase_DBManager;
import com.example.elie.clienttaxiapp.Model.Entities.Entities.ClientRequest;
import com.example.elie.clienttaxiapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




public   class MainActivity extends AppCompatActivity implements View.OnClickListener
{


    //region ***** Fields ******
    EditText ID;
    EditText Name;
    EditText Mail;
    EditText Phone;
    EditText Destination;
    Button GetTaxi;
    double latitude=52;
    double longitude=25;
    LocationManager locationManager;
    LocationListener locationListener;

  //endregion



    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FindViews();
    }

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
              latitude=location.getLatitude();
              longitude=location.getLongitude();
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

    /*public static GeoPoint getGeoPointFromAddress(String locationAddress) {
        GeoPoint locationPoint = null;
        String locationAddres = locationAddress.replaceAll(" ", "%20");
        String str = "http://maps.googleapis.com/maps/api/geocode/json?address="
                + locationAddres + "&sensor=true";

        String ss = readWebService(str);
        JSONObject json;
        try {

            String lat, lon;
            json = new JSONObject(ss);
            JSONObject geoMetryObject = new JSONObject();
            JSONObject locations = new JSONObject();
            JSONArray jarr = json.getJSONArray("results");
            int i;
            for (i = 0; i < jarr.length(); i++) {
                json = jarr.getJSONObject(i);
                geoMetryObject = json.getJSONObject("geometry");
                locations = geoMetryObject.getJSONObject("location");
                lat = locations.getString("lat");
                lon = locations.getString("lng");

                locationPoint = Utils.getGeoPoint(Double.parseDouble(lat),
                        Double.parseDouble(lon));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locationPoint;

    }*/

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




  public ClientRequest getClient()

    {
      ClientRequest client=new ClientRequest();

      int id= Integer.valueOf(ID.getText().toString());
      client.setId(id);
      client.setPhone(Phone.getText().toString());
      client.setMail(Mail.getText().toString());
      client.setName(Name.getText().toString());
      client.setDepartureLatitude(latitude);
      client.setDepartureLongitude(longitude);
      client.setArrivalLatitude(0);
      client.setArrivalLongitude(0);

      return client;
   }




    @Override
    public void onClick(View v)
    {
        getLocation();
        ClientRequest c= getClient();
        FireBase_DBManager f = new FireBase_DBManager();
        f.addClientRequest(c);
    }


    //Hello WORLD
}







