/** Contains code that provides functionality to
 *  activity_create_profile_now.xml (Launching an activity to
 *  create a profile now or going straight to the home screen)
 *
 *  It is not fully implemented as the home screen is not yet fully implemented
 * @author Madison Pickering
 * 4/4/19
 */
package com.example.sheltersheets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class CreateProfileNow extends AppCompatActivity {

    private Intent user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile_now);
        user = getIntent();
        User aUser = user.getParcelableExtra(MainActivity.NEW_USER);
        /*****for debugging***********/
        Log.d("my", "CPN. user name: " + aUser.getUserName());
        Log.d("my", "CPN. user num donations: " + aUser.getNumDonations());
        Log.d("my", "CPN. user fave shelters " + aUser.getFavShelters());
        Log.d("my", "CPN. user wanted items " + aUser.getWantedItems());
        Log.d("my", "CPN. user is admin " + aUser.getAdmin());

        //user is no longer a first time user!
        aUser.setIsFirstTimeUser(0);
    }

    public void createProfileNow(View v)
    {
        //launch activity to create a profile now
        Intent createProfile = new Intent(this, DonorProfile.class);
        Bundle wrapper = new Bundle();
        wrapper.putParcelable(MainActivity.NEW_USER, user.getParcelableExtra(MainActivity.NEW_USER));
        //createProfile.putExtra(MainActivity.NEW_USER, user.getParcelableExtra(MainActivity.NEW_USER));
        createProfile.putExtra(MainActivity.NEW_USER, wrapper);
        startActivity(createProfile);
        finish();
    }

    public void dontCreateProfileNow(View v)
    {
        //go straight to home screen
        Intent goHome = new Intent(this, DonorHome.class);
        Bundle wrapper = new Bundle();
        wrapper.putParcelable(MainActivity.NEW_USER, user.getParcelableExtra(MainActivity.NEW_USER));
        goHome.putExtra(MainActivity.NEW_USER, wrapper);
        startActivity(goHome);
        finish();
    }



}
