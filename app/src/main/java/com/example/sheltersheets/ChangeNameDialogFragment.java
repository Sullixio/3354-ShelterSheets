/** ChangeNameDialogFragment.java provides code for a custom
 * DialogFragment. It has a textbox and title, and takes user input
 * in order to update a user's name in the instance of that User.
 * The associated XML file is called dialog_username.xml
 *
 * There are a lot of scattered log messages in here because of the many,
 * many errors it once had. Please keep them to ensure that the revamped fragment
 * is working as it should.
 *
 * @author Madison Pickering
 * 4/13/19
 */
package com.example.sheltersheets;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class ChangeNameDialogFragment extends DialogFragment
{
    public static final String NAMETAG = "Dialog Fragment";
    private EditText textbox;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(getActivity());

        //inflate layout
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_username, null);
        dialogBox.setView(view);

        textbox = (EditText)view.findViewById(R.id.usernameTextbox);
        dialogBox.setMessage("Shelters will be able to see this in order to verify any donations you make.")
                .setTitle("Change Name")
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //update name
                        String newname = textbox.getText().toString();
                        User user = getArguments().getBundle(MainActivity.NEW_USER).getParcelable(MainActivity.NEW_USER);

                        Button containsName = getActivity().findViewById(R.id.userName);

                        //only update button if a valid name is given
                        if (newname.length() > 0)
                        {
                            user.setUserName(newname);
                            Log.d("my", "The user's updated userName is: " + user.getUserName());

                            containsName.setText(newname);
                        }

                        FragmentManager manage = getFragmentManager();
                        Log.d("my", "Back stack count: " + manage.getBackStackEntryCount());
                        manage.popBackStack();
                        Log.d("my", "backstack popped");
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //user canceled the dialog
            }
        });

        Log.d("my", "about to return");
        return dialogBox.create();
    }

}
