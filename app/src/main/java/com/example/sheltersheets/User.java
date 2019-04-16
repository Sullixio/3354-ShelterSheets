/** Creates the user class as defined in the
 * UML class diagram on the Google Drive
 *
 * There are some changes which have been commented accordingly.
 * Not all methods or class data has been implemented at this time
 *
 * @author Madison Pickering
 * 4/4/19
 */
package com.example.sheltersheets;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

public class User implements Parcelable //implements parcelable so instances can be passed as a param
{
    //class data
    private String name;
    private int firstTimeUser; //ideally would be boolean but can only write boolean arrays w/ parcelable
    //a value of 0 denotes false, a value of one denotes true
    private int admin; // a "boolean"; a value of one denotes the user has food bank admin privileges
    private int numDonations; //this is just for fun
    private ArrayList<String> favShelters; //this is an arraylist because I couldnt get it to work as an array.
    private ArrayList<String> wantedItems; //most wanted items. These should be in order of priority where 0 = greatest priority
    private ArrayList<String> numToDonate; //index corresponds to wanted items; how many of x item to donate



    /**
     * Default constructor
     * sets name to "default name" and firstTimeUser to true
     * Both of those values should be changed shortly after
     * startup
     */
    public User()
    {
        name = "Default Name";
        firstTimeUser = 1; //(true)
        admin = 0; //by default, not a food bank admin
        numDonations = 0;
        favShelters = new ArrayList<>();
       // Log.d("my", "In user.java: favShelters:" + favShelters.toString());
        wantedItems = new ArrayList<>();
        numToDonate = new ArrayList<>();
    }

    /**
     * Parcel constructor
     */
    public User(Parcel in)
    {
        this.name = in.readString();
        this.firstTimeUser = in.readInt();
        this.admin = in.readInt(); //by default, not a food bank admin
        this.numDonations = in.readInt();
        this.favShelters = in.createStringArrayList();
        this.wantedItems = in.createStringArrayList();
        this.numToDonate = in.createStringArrayList();
    }

    //gets & sets
    public void setUserName(String newUserName)
    {
        name = newUserName;
    }
    public String getUserName()
    {
        return name;
    }
    public int isFirstTimeUser()
    {
        return firstTimeUser;
    }
    /** @precondition newVal must be one or 0 */
    public void setIsFirstTimeUser(int newVal)
    {
        firstTimeUser = newVal;
    }
    public int getAdmin() { return admin;}
    public void setAdmin(int newAdmin) { admin = newAdmin; } // toggle if an admin exists or not
    public void incrementDonations(){ numDonations++; }
    public int getNumDonations(){return numDonations;}
    public ArrayList<String> getFavShelters(){return favShelters;}
    public ArrayList<String> getWantedItems(){return wantedItems;}
    public ArrayList<String> getNumToDonate(){return numToDonate;}


    //these must be implemented b/c we are implementing the Parcelable interface

    /** part of the Parcelable interface, either returns a description
     * of the parcels contents or 0.
     * @return 0; no description about the contents is provided
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**Updates the values of the destination parcel, dest, to the values
     * of this current class instance
     *
     * @param dest the parcel that will be updated
     * @param flags additional flags about how the parcel should be written
     */
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
        dest.writeInt(firstTimeUser);
        dest.writeInt(admin);
        dest.writeInt(numDonations);
        dest.writeStringList(favShelters);
        dest.writeStringList(wantedItems);
        dest.writeStringList(numToDonate);
    }


    /** generates instances of the class from a parcel
     * Syntax follows pretty verbatim from the developer guide
     */
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>()
    {
        public User createFromParcel(Parcel in)
        {
            return new User(in);
        }

        public User[] newArray(int arraySize)
        {
            return new User[arraySize];
        }
    };


}
