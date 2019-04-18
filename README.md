# 3354-ShelterSheets
3354-ShelterSheets Project Repository


you should be able to download the contents of this into your android studio and run the current version of sheltersheets

Whats new:

  4/16/19   added data that allows for users to go between activities and have their home screen remain unchanged.
        Details:
            Added an ArrayList<String> in User.java to hold the number of <dontation item name> to donate. This must be kept at the same               array index as the ArrayList<String> wantedItems for proper behavior. Therefore, when adding to wantedItems, also add a "0" to 
            numToDonate.
  
            Fixed issues with ArrayList<String> instance data being mutated across activities
            Added swiping function in DonorHome so that users can swipe to go to their settings, and to the map
            Added map and associated toolbar to navigate to and from
  
as of 4/17 the code will be hosted on my (Madison Pickering)'s repository as no more collaborators can be added to this one and Justin and mitch both need to be able to see the code
