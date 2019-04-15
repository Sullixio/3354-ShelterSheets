package com.example.sheltersheets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.util.ArrayList;

public class DonorHome extends AppCompatActivity {

    //class data
    private Intent intent;

    //constants
    public static final String ASSOCIATED_BUTTON_KEY = "ASSOCIATED_BUTTON_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_home);

        intent = getIntent();
        Bundle aBundle = intent.getBundleExtra(MainActivity.NEW_USER);
        User user = aBundle.getParcelable(MainActivity.NEW_USER);
        ArrayList<String> wantedItems =  user.getWantedItems();

        //for testing...
        wantedItems.add("a banana");
        wantedItems.add("avocadi");
        wantedItems.add("string beanss");
        wantedItems.add("a can");
        wantedItems.add("mac n cheese");
        wantedItems.add("dad back");


        user.setWantedItems(wantedItems);

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



        //it is assumed that numWantedItems is sorted by priority
        Button button = null;
        for (int i = 0; i < numWantedItems; i++)
        {
            if (i == 0)
            {
                button = findViewById(R.id.wanted1);
                button.setText(wantedItems.get(i));
            }
            if (i == 1)
            {
                button = findViewById(R.id.wanted2);
                button.setText(wantedItems.get(i));
            }
            if (i == 2)
            {
                button = findViewById(R.id.wanted3);
                button.setText(wantedItems.get(i));
            }
            if (i == 3)
            {
                button = findViewById(R.id.wanted4);
                button.setText(wantedItems.get(i));
            }
            if (i == 4)
            {
                button = findViewById(R.id.wanted5);
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
}
