package com.example.elie.clienttaxiapp.Model.Model.Backend;

import com.example.elie.clienttaxiapp.Model.Model.Entities.ClientRequest;

public  interface Backend
{

    public interface Action<T>
    {
        void OnSuccess(T obj);

        void OnProgress(String status,double percent);

        void OnFailure(Exception exception);
    }



    public    void addClientToFireBase(final ClientRequest c, final Action<String> action);
}
