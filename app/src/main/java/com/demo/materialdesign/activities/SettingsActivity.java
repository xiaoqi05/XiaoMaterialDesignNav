package com.demo.materialdesign.activities;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.demo.materialdesign.R;
import com.demo.materialdesign.fragments.SettingsFragment;


public class SettingsActivity extends AppCompatActivity implements SettingsFragment.OnFragmentInteractionListener {

    private SettingsFragment mSettingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if (savedInstanceState == null) {
            mSettingsFragment = SettingsFragment.newInstance("","");
            replaceFragment(R.id.settings_container, mSettingsFragment);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void replaceFragment(int viewId, android.app.Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(viewId, fragment).commit();
    }


    //todo 搞清楚这个是干啥用的
    @Override
    public void onFragmentInteraction(Uri uri) {
        
    }
}
