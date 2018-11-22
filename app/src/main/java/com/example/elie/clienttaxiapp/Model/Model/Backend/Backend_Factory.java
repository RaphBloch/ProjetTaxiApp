package com.example.elie.clienttaxiapp.Model.Model.Backend;

import com.example.elie.clienttaxiapp.Model.Model.DS.FireBase_DBManager;

public class Backend_Factory

{

    Backend instance=new FireBase_DBManager();


    /***
     * Function : getFactory
     * @return a unique instance of Backend that is FireBase_DBManager
     */
  public Backend getfactory()
    {
        if (instance == null)
            instance = new FireBase_DBManager();

        return instance;
    }

}
