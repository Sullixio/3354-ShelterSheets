/** Public class DonorProfile.java contains the data
 *  needed to manage the profile of a generic user (Donor)
 *  Utilizes custom DialogFragment ChangeNameDialogFragment.java
 *  to do so.
 *
 * @author Madison Pickering
 * 4/13/19
 */

package com.example.sheltersheets;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

public class DonorProfile extends AppCompatActivity {

    private Intent intent;                                  //used to hold the User instance

    /*Create the UI
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile);

        //add actionbar functionality
        Toolbar tool = (Toolbar) findViewById(R.id.toolbar);
        tool.setTitle("User Settings");
        setSupportActionBar(tool);

        //add up button to toolbar
        ActionBar myToolBar = getSupportActionBar();
        myToolBar.setDisplayHomeAsUpEnabled(true);


        //set class data
        intent = getIntent();
        //set the user's name
        Bundle aBundle = intent.getBundleExtra(MainActivity.NEW_USER);
        User user = aBundle.getParcelable(MainActivity.NEW_USER);/*intent.getParcelableExtra(MainActivity.NEW_USER);*/

        /*****for debugging***********/
        Log.d("my", "DP. user name: " + user.getUserName());
        Log.d("my", "DP. user num donations: " + user.getNumDonations());
        Log.d("my", "DP. user fave shelters " + user.getFavShelters());
        Log.d("my", "DP. user wanted items " + user.getWantedItems());
        Log.d("my", "DP. user is admin " + user.getAdmin());

        Button userName = findViewById(R.id.userName);
        userName.setText(user.getUserName());

        //set if first time user
        user.setIsFirstTimeUser(0);


        //set location

        //set num donations
        TextView numDonations = findViewById(R.id.donationsMade);
        numDonations.setText("You have made " + user.getNumDonations() + " donations! Thank you for your hard work!");

    }

    /** On button press, initiates a transaction between
     * a custom dialog fragment (specified in ChangeNameDialogFragment.java) and
     * dialog_username.xml. The results of that fragment are used
     * @param v the current view
     */
    public void changeName(View v)
    {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        //check to make sure previous fragment is gone
        Fragment previousFragment = manager.findFragmentByTag("dialog");
        if (previousFragment != null)
            transaction.remove(previousFragment);

        //testing...

        transaction.addToBackStack(null);

        ChangeNameDialogFragment newChangeName = new ChangeNameDialogFragment();
        //add the user as a bundle to retrieve on the other side
        Parcelable aParcel = intent.getParcelableExtra(MainActivity.NEW_USER);
        Bundle aBundle = new Bundle();
        aBundle.putParcelable(MainActivity.NEW_USER, aParcel);
        newChangeName.setArguments(aBundle);

        newChangeName.show(transaction, ChangeNameDialogFragment.NAMETAG);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent newIntent = new Intent(this, DonorHome.class);
                Bundle wrapper = new Bundle();
                Bundle userBund = intent.getParcelableExtra(MainActivity.NEW_USER);
                User user = userBund.getParcelable(MainActivity.NEW_USER);
                wrapper.putParcelable(MainActivity.NEW_USER, user);
                newIntent.putExtra(MainActivity.NEW_USER, wrapper);

                startActivity(newIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

}

