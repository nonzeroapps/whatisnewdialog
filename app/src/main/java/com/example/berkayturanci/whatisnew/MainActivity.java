package com.example.berkayturanci.whatisnew;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.whatisnew.NewItemDialog;
import com.example.whatisnew.object.NewFeatureItem;

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
        newFeatureItem.setImageUrl("https://media.giphy.com/media/uysHTqjZ67REQ/giphy.gif");
        arrayList.add(newFeatureItem);

        NewFeatureItem newFeatureItem2 = new NewFeatureItem();
        newFeatureItem2.setFeatureTitle("Yeni Özellik İki - Süper bir şey bu");
        newFeatureItem2.setFeatureDesc("Bu özellikte özellikle şunlar bunlar yapıldı ve çok da gzüel oldu.\n\n Bence herkes artık bunu kullanmalı\n" +
                "ki böyle yapmaya devam edebilelim bizde yoksa yok yani olmaz böyle şeyler hiç bir zaman hiç bir yerde");
        newFeatureItem2.setImageUrl("https://media.giphy.com/media/tyMOI37SDfx04/giphy.gif");
        arrayList.add(newFeatureItem2);

        NewFeatureItem newFeatureItem3 = new NewFeatureItem();
        newFeatureItem3.setFeatureTitle("Yeni Özellik İki - Süper bir şey bu");
        newFeatureItem3.setFeatureDesc("Bu özellikte özellikle şunlar bunlar yapıldı ve çok da gzüel oldu.\n\n Bence herkes artık bunu kullanmalı\n" +
                "ki böyle yapmaya devam edebilelim bizde yoksa yok yani olmaz böyle şeyler hiç bir zaman hiç bir yerde");
        newFeatureItem3.setImageUrl("https://media.giphy.com/media/3o7btU9FSeydVQHJy8/giphy.gif");
        arrayList.add(newFeatureItem3);


        NewFeatureItem newFeatureItem4 = new NewFeatureItem();
        newFeatureItem4.setFeatureTitle("Yeni Özellik İki - Süper bir şey bu");
        newFeatureItem4.setFeatureDesc("Bu özellikte özellikle şunlar bunlar yapıldı ve çok da gzüel oldu.\n\n Bence herkes artık bunu kullanmalı\n" +
                "ki böyle yapmaya devam edebilelim bizde yoksa yok yani olmaz böyle şeyler hiç bir zaman hiç bir yerde");
        newFeatureItem4.setImageUrl("https://media.giphy.com/media/JltOMwYmi0VrO/giphy.gif");
        arrayList.add(newFeatureItem4);

        NewFeatureItem newFeatureItem5 = new NewFeatureItem();
        newFeatureItem5.setFeatureTitle("Yeni Özellik İki - Süper bir şey bu");
        newFeatureItem5.setFeatureDesc("Bu özellikte özellikle şunlar bunlar yapıldı ve çok da gzüel oldu.\n\n Bence herkes artık bunu kullanmalı\n" +
                "ki böyle yapmaya devam edebilelim bizde yoksa yok yani olmaz böyle şeyler hiç bir zaman hiç bir yerde");
        newFeatureItem5.setImageUrl("https://storage.googleapis.com/material-design/publish/material_v_11/assets/0Bzhp5Z4wHba3aXcyajZDN29jS1k/components_dialogs_confirmation2.png");
        arrayList.add(newFeatureItem5);

        NewItemDialog.init(this).setDialogTitle("New Features of 1.2.0 Version!").setItems(arrayList).showDialog();

    }
}
