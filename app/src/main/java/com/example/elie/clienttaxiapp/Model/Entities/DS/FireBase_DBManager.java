package com.example.elie.clienttaxiapp.Model.Entities.DS;

import android.support.annotation.NonNull;

import com.example.elie.clienttaxiapp.Model.Entities.Backend.Backend;
import com.example.elie.clienttaxiapp.Model.Entities.Entities.ClientRequest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBase_DBManager implements Backend

{

    /***
     * Interface Action
     * @param <T> template : type of object for the implementation of the interface
     *      OnSuccess : It receives the name of the client that is the key
     *      OnFailure : It tells us if there is a failure in the loading and throws exception
     *      OnProgress : It tells us the progress of the load of the data with a message
     *
     */
    public interface Action<T>
    {
        void OnSuccess(T obj);

        void OnProgress(String status,double percent);

        void OnFailure(Exception exception);
    }

    public interface NotifyDataChange<T>
    {
        void OnDataChanged(T obj);

        void OnFailure(Exception exception);
    }


    // creation of my databaseReference
     static DatabaseReference ClientsRef;
     static
    {
        FirebaseDatabase data=FirebaseDatabase.getInstance();
        //The reference of my data of clients is Clients
        ClientsRef= data.getReference("Clients");
    }

    /***
     * Function : addClientRequest
     * @param clientRequest the client to add
     * Meaning : calls the function that add the cleint to the firebase
     */
 @Override
    public   void addClientRequest(final  ClientRequest clientRequest)
    {
        addClientToFireBase(clientRequest, new Action<String>() {
            @Override
            public void OnSuccess(String obj) {

            }

            @Override
            public void OnProgress(String status, double percent) {

            }

            @Override
            public void OnFailure(Exception exception) {

            }
        });
    }


    /***
     * Function : addClienttoFireBase
     *
     * @param client A clientRequest
     * @param action The interface of the firebase
     *
     *  The function must add a clientReauest to my firebase.
     *
     *  Explication :    There is the implementation of the the 3 functions of the interface.
     *      OnSuccess : It receives the name of the client that is the key
     *      OnFailure : It tells us if there is a failure in the loading and throws exception
     *      OnProgress : It tells us the progress of the load of the data with a message

     */
    private static void addClientToFireBase(final ClientRequest client,final Action<String> action)
    {
        String key=String.valueOf(client.getId());
        ClientsRef.child(key).setValue(client).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                action.OnSuccess(client.getName());
                action.OnProgress("Load Clientrequest data",100);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                action.OnFailure(e);
                action.OnProgress("Error loading data",100);
            }
        });
    }







}
