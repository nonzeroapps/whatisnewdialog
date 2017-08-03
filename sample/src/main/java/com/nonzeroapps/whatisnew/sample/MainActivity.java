package com.nonzeroapps.whatisnew.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nonzeroapps.android.whatisnew.sample.R;
import com.nonzeroapps.whatisnew.NewItemDialog;
import com.nonzeroapps.whatisnew.object.NewFeatureItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create and show the dialog.

        ArrayList<NewFeatureItem> arrayList = new ArrayList<>();

        NewFeatureItem newFeatureItem = new NewFeatureItem();
        newFeatureItem.setFeatureDesc("From now on, you can search all things with keys. For searching please go to ");
        newFeatureItem.setFeatureTitle("Searching");
        newFeatureItem.setImageUrl("https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bzhp5Z4wHba3aXcyajZDN29jS1k/components_dialogs_confirmation2.png");
        arrayList.add(newFeatureItem);

        NewFeatureItem newFeatureItem2 = new NewFeatureItem();
        newFeatureItem2.setFeatureTitle("Feature 2");
        newFeatureItem2.setFeatureDesc("You waited long for this feature, we know that!!!\n\n From now on, you can follow your friend with our application. This makes our application super and cool. Don't believe my words, try and see it. If you want another features like this please contact with us via e-mail or feedback button.");
        newFeatureItem2.setImageUrl("https://media.giphy.com/media/JltOMwYmi0VrO/giphy.gif");
        arrayList.add(newFeatureItem2);

        NewFeatureItem newFeatureItem3 = new NewFeatureItem();
        newFeatureItem3.setFeatureTitle("Feature of the Feature");
        newFeatureItem3.setFeatureDesc("Lets use lorem ipsum feature for this; Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum");
        newFeatureItem3.setImageUrl("https://media.giphy.com/media/3o7btU9FSeydVQHJy8/giphy.gif");
        arrayList.add(newFeatureItem3);

        NewItemDialog
                .init(this)
                .setDialogTitle("New Features of 1.2.0 Version!")
                .setItems(arrayList)
                .showDialog(this);

    }
}
