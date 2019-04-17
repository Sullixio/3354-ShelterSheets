package com.example.sheltersheets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.util.ArrayList;

public class DonorHome extends AppCompatActivity {

    //class data
    private Intent intent;
    private  GestureDetectorCompat watcher;

    //constants
    public static final String ASSOCIATED_BUTTON_KEY = "ASSOCIATED_BUTTON_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_home);

        //set up a listener for transitions
        watcher = new GestureDetectorCompat(this, new HomeGestureListener());

        intent = getIntent();
        Bundle aBundle = intent.getBundleExtra(MainActivity.NEW_USER);
        User user = aBundle.getParcelable(MainActivity.NEW_USER);
        ArrayList<String> wantedItems =  user.getWantedItems();
        ArrayList<String> numToDonate = user.getNumToDonate();

        //for testing...

        wantedItems.add("a banana");
        numToDonate.add("0");
        wantedItems.add("avocadi");
        numToDonate.add("0");
        wantedItems.add("string beanss");
        numToDonate.add("0");
        wantedItems.add("a can");
        numToDonate.add("0");
        wantedItems.add("mac n cheese");
        numToDonate.add("0");
        wantedItems.add("dad back");
        numToDonate.add("0");


        int numWantedItems = wantedItems.size();

        //hide all buttons
        findViewById(R.id.wanted1).setVisibility(View.GONE);
        findViewById(R.id.wanted2).setVisibility(View.GONE);
        findViewById(R.id.wanted3).setVisibility(View.GONE);
        findViewById(R.id.wanted4).setVisibility(View.GONE);
        findViewById(R.id.wanted5).setVisibility(View.GONE);

        // if user has not looked at shelters first, display the appropriate text.
        if (numWantedItems != 0)
            findViewById(R.id.findSheltersFirst).setVisibility(View.GONE);
        else
            findViewById(R.id.wantedItems).setVisibility(View.GONE);


        /*****for debugging***********/
        Log.d("my", "DH. user name: " + user.getUserName());
        Log.d("my", "DH. user num donations: " + user.getNumDonations());
        Log.d("my", "DH. user fave shelters " + user.getFavShelters());
        Log.d("my", "DH. user wanted items " + user.getWantedItems());
        Log.d("my", "DH user amt to donate" + user.getNumToDonate());
        Log.d("my", "DH. user is admin " + user.getAdmin());

        //it is assumed that numWantedItems is sorted by priority
        Button button = null;
        for (int i = 0; i < numWantedItems; i++)
        {
            if (i == 0)
            {
                button = findViewById(R.id.wanted1);
                if (!numToDonate.get(i).equals("0"))
                {
                    button.setText("You are donating " + numToDonate.get(i) + " " + wantedItems.get(i) + "'s.");
                    button.setBackgroundColor(getResources().getColor(R.color.colorConfirm));
                }
                else
                    button.setText(wantedItems.get(i));
            }
            if (i == 1)
            {
                button = findViewById(R.id.wanted2);
                if (!numToDonate.get(i).equals("0"))
                {
                    button.setText("You are donating " + numToDonate.get(i) + " " + wantedItems.get(i) + "'s.");
                    button.setBackgroundColor(getResources().getColor(R.color.colorConfirm));
                }
                else
                    button.setText(wantedItems.get(i));
            }
            if (i == 2)
            {
                button = findViewById(R.id.wanted3);
                if (!numToDonate.get(i).equals("0"))
                {
                    button.setText("You are donating " + numToDonate.get(i) + " " + wantedItems.get(i) + "'s.");
                    button.setBackgroundColor(getResources().getColor(R.color.colorConfirm));
                }
                else
                    button.setText(wantedItems.get(i));
            }
            if (i == 3)
            {
                button = findViewById(R.id.wanted4);
                if (!numToDonate.get(i).equals("0"))
                {
                    button.setText("You are donating " + numToDonate.get(i) + " " + wantedItems.get(i) + "'s.");
                    button.setBackgroundColor(getResources().getColor(R.color.colorConfirm));
                }
                else
                    button.setText(wantedItems.get(i));
            }
            if (i == 4)
            {
                button = findViewById(R.id.wanted5);
                if (!numToDonate.get(i).equals("0"))
                {
                    button.setText("You are donating " + numToDonate.get(i) + " " + wantedItems.get(i) + "'s.");
                    button.setBackgroundColor(getResources().getColor(R.color.colorConfirm));
                }
                else
                    button.setText(wantedItems.get(i));
            }

            button.setVisibility(View.VISIBLE);

            if (i > 4)
                break;
        }

    }


    public void wantedBehavior(View v)
    {
        int buttonId = v.getId();
        Button button = findViewById(buttonId);

        //only do stuff if the button is visible
        if (button.getVisibility() == View.VISIBLE) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            //check to make sure previous fragment is gone
            Fragment previousFragment = manager.findFragmentByTag("dialog");
            if (previousFragment != null)
                transaction.remove(previousFragment);
            transaction.addToBackStack(null);

            //create the NumberPickerFragment
            NumberPickerFragment quantity = new NumberPickerFragment();

            //add needed bundles
            Parcelable aParcel = intent.getParcelableExtra(MainActivity.NEW_USER);
            Bundle aBundle = new Bundle();
            aBundle.putParcelable(MainActivity.NEW_USER, aParcel);

            aBundle.putInt(DonorHome.ASSOCIATED_BUTTON_KEY, buttonId);
            quantity.setArguments(aBundle);

            //display the fragment
            quantity.show(transaction, NumberPickerFragment.NUMBER_PICKER_FRAGMENT_KEY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        this.watcher.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class HomeGestureListener extends GestureDetector.SimpleOnGestureListener
    {

     @Override
     public boolean onDown(MotionEvent event)
     {
         return true;
     }

     @Override
     public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float veloctiyY)
     {
         Log.d("my", "Fling detected!: Velocityx: " + velocityX + " , velocityY: " + veloctiyY);
         if (velocityX < 0) //if rightward swipe
         {
             Intent goToSettings = new Intent(DonorHome.this, DonorProfile.class);
             Bundle aBundle = intent.getBundleExtra(MainActivity.NEW_USER);
             goToSettings.putExtra(MainActivity.NEW_USER, aBundle);
             startActivity(goToSettings);
             finish();
         }
         else
         {
             Intent goToMaps = new Intent(DonorHome.this, MapsActivity.class);
             Bundle aBundle = intent.getBundleExtra(MainActivity.NEW_USER);
             goToMaps.putExtra(MainActivity.NEW_USER, aBundle);
             startActivity(goToMaps);
             finish();
         }

         return true;
     }
    }
}
