# WhatIsNewDialog
 [![Build Status](https://travis-ci.org/berkayturanci/WhatIsNewDialog.svg?branch=master)](https://travis-ci.org/berkayturanci/WhatIsNewDialog)
 
What is new dialog for Android is used for presenting new features in the the app. It can be used in the activity starts, from menu or from a button. It is highly customizable and flexible. It has two options (customizable) where user can either select remind me later or close. Close selection will record that dialog for given version name is seen. So next time it won't be shown to the user. It uses Glide for showing gif and images. 

![](preview/usage.gif)

## Features
- Add unlimited pages for dialog
- Callbacks for buttons.
- It records the dialog seen condition for future openings.
- Gif support.
- It can show network images, gifs or local resources (image or gif) from the project.
- Extracts the accent color from your app's theme
- Customizable title, positive button and negative button texts
- Customizable button and title colors (It uses the activity style)
- Override dialog redirection to Google Play or Feedback form according to your needs
- Low memory usage.

If you want the dialog to appear on the start of the app, just add the `showDialogIfConditionsSuitable(activity)` to the `onCreate()` method of your Activity class. The dialog will appear when the app is opened and the condition is satisfied.

## How to use

Use the dialog as it is

```java

NewItemDialog newItemDialog = NewItemDialog
        .init(this)
        .setVersionName("1.2.0")
        .setDialogTitle("New Features of 1.2.0 Version!")
        .setItems(arrayList);


newItemDialog.showDialog(this);

```
