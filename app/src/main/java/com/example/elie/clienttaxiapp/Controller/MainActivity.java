
package com.example.elie.clienttaxiapp.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

import com.example.elie.clienttaxiapp.R;


public   class MainActivity extends AppCompatActivity
{


    /*//region ***** Fields ******
    EditText ID;
    Backend_Factory backend_factory=new Backend_Factory();
    EditText Name;
    EditText Mail;
    EditText Phone;
    EditText Destination;
    Button GetTaxi;
    double latitude=52;
    double longitude=25;
    LocationManager locationManager;
    LocationListener locationListener;

  //endregion*/
    private Button enterBtn;



    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterBtn = (Button) findViewById(R.id.enterBtn);

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (MainActivity.this , RegisterActivity.class);
                startActivity(intent);
                Toast toast = Toast.makeText(MainActivity.this,"Welcome",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();

            }
        });
    }
}









