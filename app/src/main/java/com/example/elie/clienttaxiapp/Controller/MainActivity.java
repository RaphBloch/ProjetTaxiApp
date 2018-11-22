
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


    //region ***** Fields *****
    private Button enterBtn;

    //endregion


    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterBtn = (Button) findViewById(R.id.enterBtn);



        /***
         * Function : setOnClickListener
         * @param  a new View.OnClickListener .
         * The button subscribes to a OnClickListener with a new event
         *  that is an intent that brings me to my Register Activity
         */
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









