package com.example.elie.clienttaxiapp.Model.Entities.DS;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBase_DBManager

{
   public static void test()
   {
       FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hellod World");
   }
}
