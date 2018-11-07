
package com.example.elie.clienttaxiapp.Model.Entities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;


import com.example.elie.clienttaxiapp.Model.Entities.DS.FireBase_DBManager;
import com.example.elie.clienttaxiapp.Model.Entities.Entities.ClientRequest;
import com.example.elie.clienttaxiapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button b;

    private void FindViews()
    {
        b=(Button)findViewById(R.id.button2);

        b.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindViews();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello World");
    }
}
