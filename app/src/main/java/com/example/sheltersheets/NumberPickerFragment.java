package com.example.sheltersheets;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.ArrayList;

public class NumberPickerFragment extends DialogFragment {

    public static final String NUMBER_PICKER_FRAGMENT_KEY = "NUMBER_PICKER_FRAGMENT_KEY";


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(getActivity());

        //inflate layout
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_number_picker_fragment, null);
        dialogBox.setView(view);

        //create a number picker
        final NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.dialog_numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(100);
        numberPicker.setWrapSelectorWheel(false);

        //add a listener
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });

        dialogBox.setMessage("Provide how many of this item?")
                .setTitle("Donate")
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        //update stockpiler's pending requests
                        /* ***************************************/
                        /*               TO DO                   */
                        /****************************************/


                        Bundle aBundle = getArguments();
                        User user = aBundle.getBundle(MainActivity.NEW_USER).getParcelable(MainActivity.NEW_USER);


                        //get the associated button
                        //Precondition: there are only five buttons
                        int thisButtonNumber = aBundle.getInt(DonorHome.ASSOCIATED_BUTTON_KEY);
                        String thisItem = "";
                        Button thisButton = null;
                        String itemGotIndex;
                        if (thisButtonNumber == R.id.wanted1)
                        {
                            itemGotIndex = "0";
                            thisItem = user.getWantedItems().get(0);
                            thisButton = getActivity().findViewById(R.id.wanted1);
                        }
                        else if (thisButtonNumber == R.id.wanted2)
                        {
                            itemGotIndex = "1";
                            thisItem = user.getWantedItems().get(1);
                            thisButton = getActivity().findViewById(R.id.wanted2);
                        }
                        else if (thisButtonNumber == R.id.wanted3)
                        {
                            itemGotIndex = "2";
                            thisItem = user.getWantedItems().get(2);
                            thisButton = getActivity().findViewById(R.id.wanted3);
                        }
                        else if (thisButtonNumber == R.id.wanted4)
                        {
                            itemGotIndex = "3";
                            thisItem = user.getWantedItems().get(3);
                            thisButton = getActivity().findViewById(R.id.wanted4);
                        }
                        else
                        {
                            itemGotIndex = "4";
                            thisItem = user.getWantedItems().get(4);
                            thisButton = getActivity().findViewById(R.id.wanted5);
                        }

                        //get number picked & update button's text appropriately
                        int donationQuantity = numberPicker.getValue();
                        if (donationQuantity != 0)
                        {

                            thisButton.setText("You are donating " + donationQuantity + " " + thisItem + "'s.");
                            thisButton.setBackgroundColor(getResources().getColor(R.color.colorConfirm));
                            //update numToDonateArray to save data
                            ArrayList<String> numToDonate = user.getNumToDonate();
                            numToDonate.set(Integer.parseInt(itemGotIndex), "" + donationQuantity);
                        }
                        else
                        {
                            thisButton.setText(thisItem);
                            thisButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        }

                        FragmentManager manage = getFragmentManager();
                        manage.popBackStack();

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //user canceled the dialog
            }
        });

        return dialogBox.create();
    }
}
