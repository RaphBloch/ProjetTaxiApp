
package com.example.elie.clienttaxiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.elie.clienttaxiapp.Model.Entities.DataSource.FireBase_DBManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello World");

        FireBase_DBManager.test();
    }
}
