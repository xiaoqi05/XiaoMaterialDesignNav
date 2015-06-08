package com.demo.materialdesign.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.demo.materialdesign.R;

/**
 * Represents other activity different from the main activity
 *
 * @author Sotti https://plus.google.com/+PabloCostaTirado/about
 */
public class OtherActivity extends AppCompatActivity
{
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_activity);
        initialise();
       
    }

    private void initialise()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
   
    

}
