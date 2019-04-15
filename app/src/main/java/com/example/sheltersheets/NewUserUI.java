/** Provides functionality to a screen that asks
 * a new user if they are a system admin or not. If
 * they are, they must confirm so via a snackbar before
 * they can proceed. See associated xml file activity_new_user_ui.xml
 *
 * @author Madison Pickering
 * 4/4/19
 */

package com.example.sheltersheets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.android.material.snackbar.Snackbar;


public class NewUserUI extends AppCompatActivity {

    private Intent intent;
    public static boolean launchAdminActivity = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_ui);

        intent = getIntent(); //get the intent that started this activity (carrying user as an extra)
        /*****for debugging***********/
        User aUser = intent.getParcelableExtra(MainActivity.NEW_USER);
        Log.d("my", "NUUI. user name: " + aUser.getUserName());
        Log.d("my", "NUUI. user num donations: " + aUser.getNumDonations());
        Log.d("my", "NUUI. user fave shelters " + aUser.getFavShelters());
        Log.d("my", "NUUI. user wanted items " + aUser.getWantedItems());
        Log.d("my", "NUUI. user is admin " + aUser.getAdmin());
    }

    public void yesButtonClicked(View view)
    {
        User theUser = (User)(intent.getParcelableExtra(MainActivity.NEW_USER));
        //create a snackbar to ensure that the user is sure that they want to do this
        Snackbar confirm = Snackbar.make(view, "Are you sure? This is for food bank/shelter employees only.", Snackbar.LENGTH_INDEFINITE);
        ConfirmationListener confirmAdmin = new ConfirmationListener();
        confirm.setAction("Yes", confirmAdmin);
        confirm.setActionTextColor(Color.YELLOW);
        confirm.show();

        //if the user is sure, launch the activity
        if (launchAdminActivity)
            System.out.println("blah");
    }

    public void noButtonClicked(View view)
    {
        //create activity to ask if user wants to make a profile now
        Intent anIntent = new Intent(this, CreateProfileNow.class);
        anIntent.putExtra(MainActivity.NEW_USER, intent.getParcelableExtra(MainActivity.NEW_USER));
        startActivity(anIntent);
        finish();
    }
}

class ConfirmationListener implements OnClickListener
{
    @Override
    public void onClick(View view)
    {
        //launch activity to send a request to create an admin account
        NewUserUI.launchAdminActivity = true;
    }
}