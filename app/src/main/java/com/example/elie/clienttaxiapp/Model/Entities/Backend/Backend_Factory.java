package com.example.elie.clienttaxiapp.Model.Entities.Backend;

import com.example.elie.clienttaxiapp.Model.Entities.DS.FireBase_DBManager;
import com.example.elie.clienttaxiapp.Model.Entities.Entities.ClientRequest;

public class Backend_Factory

{

    Backend instance=new FireBase_DBManager();


    public Backend getfactory()
    {
        if (instance == null)
            instance = new FireBase_DBManager();

        return instance;
    }

}
