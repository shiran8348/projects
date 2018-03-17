package com.example.ed_it_art.clientapplication.controller;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ed_it_art.clientapplication.R;

import java.net.URI;

import static java.security.AccessController.getContext;

public class ContactActivity extends AppCompatActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        setViews();
    }


    private FloatingActionButton phoneButton;
    private FloatingActionButton mailButton;
    private FloatingActionButton webButton;
    private FloatingActionButton addressButton;

    public void setViews() {
        phoneButton = (FloatingActionButton) findViewById(R.id.callButton);
        mailButton = (FloatingActionButton) findViewById(R.id.emailButton);
        webButton = (FloatingActionButton) findViewById(R.id.siteButton);
        addressButton = (FloatingActionButton) findViewById(R.id.addressButton);

        mailButton.setOnClickListener(this);
        phoneButton.setOnClickListener(this);
        webButton.setOnClickListener(this);
        addressButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mailButton) {
            Intent i = new Intent(Intent.ACTION_SENDTO);
            i.setData(Uri.parse(("mailto:")));
            i.putExtra(Intent.EXTRA_EMAIL, "shiran8348@gmail.com");
            i.putExtra(Intent.EXTRA_SUBJECT, "RentCar App sender");
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }
/*            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(ContactActivity.this, "Email was not sent", Toast.LENGTH_SHORT).show();
            }*/
            //   URI mailto = new URI("mailto:john@example.com?subject=Hello%20World");
        }
        if (v == phoneButton) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+972532304558"));
            Intent chooser = Intent.createChooser(intent, "Call");
            startActivity(chooser);
        }
        if (v == webButton) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://www.take$go.com"));
            startActivity(intent);
            //   startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse((String) webButton())));
        }
        if (v == addressButton) {
            // Creates an Intent that will load a map of the studing
            Uri gmmIntentUri = Uri.parse("geo:31.785597, 35.189633");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
    }
}