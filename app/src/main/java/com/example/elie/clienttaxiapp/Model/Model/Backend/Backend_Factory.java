package com.example.elie.clienttaxiapp.Model.Model.Backend;

import com.example.elie.clienttaxiapp.Model.Model.DS.FireBase_DBManager;

public final   class Backend_Factory

{



    /***
     * Function : getFactory
     * @return a unique instance of FireBase_DBManager
     */
  public static Backend getFactory()
    {
        return FireBase_DBManager.getFireBase_dbManager();
    }

}
