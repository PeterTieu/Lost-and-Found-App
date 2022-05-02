package com.tieutech.lostandfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tieutech.lostandfoundapp.Model.Advert;
import com.tieutech.lostandfoundapp.data.AdvertDatabaseHelper;
import com.tieutech.lostandfoundapp.util.Util;

//ABOUT: Activity to create a new Advert
public class CreateANewAdvertActivity extends AppCompatActivity {

    //View variables
    RadioGroup lostAndFoundRadioGroup;
    RadioButton radioButton;
    int radioButtonID;
    EditText nameEdiText;
    EditText phoneEditText;
    EditText descriptionEditText;
    EditText dateEditText;
    EditText locationEditText;

    //Data variables
    String itemState;
    String name;
    String phone;
    String description;
    String date;
    String location;

    //Database variables
    AdvertDatabaseHelper advertDatabaseHelper;
    long rowID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_a_new_advert);

        //Obtain the Adverts database
        advertDatabaseHelper = new AdvertDatabaseHelper(this);

        //Obtain the views
        lostAndFoundRadioGroup = (RadioGroup) findViewById(R.id.lostAndFoundRadioGroup);
        nameEdiText = (EditText) findViewById(R.id.nameEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
        dateEditText = (EditText) findViewById(R.id.dateEditText);
        locationEditText = (EditText) findViewById(R.id.locationEditText);
    }

    //Listener for the "Save" Button - to save the Advert to the Advert database
    public void saveButton(View view) {

        //Obtain value selected for the Radio Button
        radioButtonID = lostAndFoundRadioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioButtonID);

        if (radioButton.getText().equals("Lost")) {
            itemState = "Lost";
        }
        if (radioButton.getText().equals("Found")) {
            itemState = "Found";
        }

        //Obtain values entered in the EditText views
        name = nameEdiText.getText().toString();
        phone = phoneEditText.getText().toString();
        description = descriptionEditText.getText().toString();
        date = dateEditText.getText().toString();
        location = locationEditText.getText().toString();

        //Insert a new entry to the Order database using all the obtained data
        rowID = advertDatabaseHelper.insertAdvert(new Advert(
                itemState,
                name,
                phone,
                description,
                date,
                location));

        Util.makeToast(this, "Advert Saved!");

        //Pop the activity from the activity stack, revealing the MainActivity
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}