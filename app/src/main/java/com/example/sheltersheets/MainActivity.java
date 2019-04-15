/** Public class MainActivity functions as the main
 * in a normal java program. It first checks if the user is new
 * and if so launches activity NewUserUI to begin initializing data
 *
 * @author Madison Pickering
 */

package com.example.sheltersheets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity
{
    public final static String NEW_USER = "user";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //******************************************************
        //  the below needs to be changed to reflect data stored
        //  It is currently what it is for testing purposes
        //******************************************************
        User aUser = new User(); //should check to see if there is a user stored already
        Log.d("my", "MA. user name: " + aUser.getUserName());
        Log.d("my", "MA. user num donations: " + aUser.getNumDonations());
        Log.d("my", "MA. user fave shelters " + aUser.getFavShelters());
        Log.d("my", "MA. user wanted items " + aUser.getWantedItems());
        Log.d("my", "MA. user is admin " + aUser.getAdmin());
        if (aUser.isFirstTimeUser() == 1) //if first time user, run appropriate UI
        {
            Intent update = new Intent(this, NewUserUI.class);
            update.putExtra(NEW_USER, aUser);
            startActivity(update);
            finish();
        }
    }
}
