package com.demo.materialdesign.activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.materialdesign.R;
import com.demo.materialdesign.customViews.ScrimInsetsFrameLayout;
import com.demo.materialdesign.fragments.ColorFragment;
import com.demo.materialdesign.fragments.MainFragment;
import com.demo.materialdesign.managers.ManagerTypeface;
import com.demo.materialdesign.fragments.EditInfoFragment;
import com.demo.materialdesign.utils.UtilsDevice;
import com.demo.materialdesign.utils.UtilsMiscellaneous;

/**
 * Main class hosting the navigation drawer
 *
 * @author Sotti https://plus.google.com/+PabloCostaTirado/about
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener,EditInfoFragment.OnFragmentInteractionListener
{
    private final static double sNAVIGATION_DRAWER_ACCOUNT_SECTION_ASPECT_RATIO = 9d/16d;

    private DrawerLayout mDrawerLayout;
    private FrameLayout mFrameLayout_AccountView;
    private LinearLayout mNavDrawerEntriesRootView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private ScrimInsetsFrameLayout mScrimInsetsFrameLayout;
    private FrameLayout mFrameLayout_Home, mFrameLayout_Explore, mFrameLayout_HelpAndFeedback,
            mFrameLayout_About;
    private TextView mTextView_AccountDisplayName, mTextView_AccountEmail;
    private TextView mTextView_Home, mTextView_Explore, mTextView_HelpAndFeedback, mTextView_About;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置默认的配置文件
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        initialise();
    }

    /**
     * Bind, create and set up the resources
     */
    private void initialise()
    {
        // Toolbar
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // Layout resources
        mFrameLayout_AccountView = (FrameLayout) findViewById(R.id.navigation_drawer_account_view);
        mNavDrawerEntriesRootView = (LinearLayout)findViewById(R.id.navigation_drawer_linearLayout_entries_root_view);

        mFrameLayout_Home = (FrameLayout) findViewById(R.id.navigation_drawer_items_list_linearLayout_home);
        mFrameLayout_Explore = (FrameLayout) findViewById(R.id.navigation_drawer_items_list_linearLayout_explore);
        mFrameLayout_HelpAndFeedback = (FrameLayout) findViewById(R.id.navigation_drawer_items_list_linearLayout_help_and_feedback);
        mFrameLayout_About = (FrameLayout) findViewById(R.id.navigation_drawer_items_list_linearLayout_about);

        mTextView_AccountDisplayName = (TextView) findViewById(R.id.navigation_drawer_account_information_display_name);
        mTextView_AccountEmail = (TextView) findViewById(R.id.navigation_drawer_account_information_email);

        mTextView_Home = (TextView) findViewById(R.id.navigation_drawer_items_textView_home);
        mTextView_Explore = (TextView) findViewById(R.id.navigation_drawer_items_textView_explore);
        mTextView_HelpAndFeedback = (TextView) findViewById(R.id.navigation_drawer_items_textView_help_and_feedback);
        mTextView_About = (TextView) findViewById(R.id.navigation_drawer_items_textView_about);

        // Typefaces
        mTextView_AccountDisplayName.setTypeface(ManagerTypeface.getTypeface(this, R.string.typeface_roboto_medium));
        mTextView_AccountEmail.setTypeface(ManagerTypeface.getTypeface(this, R.string.typeface_roboto_regular));
        mTextView_Home.setTypeface(ManagerTypeface.getTypeface(this, R.string.typeface_roboto_medium));
        mTextView_Explore.setTypeface(ManagerTypeface.getTypeface(this, R.string.typeface_roboto_medium));
        mTextView_HelpAndFeedback.setTypeface(ManagerTypeface.getTypeface(this, R.string.typeface_roboto_medium));
        mTextView_About.setTypeface(ManagerTypeface.getTypeface(this, R.string.typeface_roboto_medium));

        // Navigation Drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_activity_DrawerLayout);
        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.primaryDark));
        mScrimInsetsFrameLayout = (ScrimInsetsFrameLayout) findViewById(R.id.main_activity_navigation_drawer_rootLayout);

        mActionBarDrawerToggle = new ActionBarDrawerToggle
                (
                        this,
                        mDrawerLayout,
                        mToolbar,
                        R.string.navigation_drawer_opened,
                        R.string.navigation_drawer_closed
                )
        {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {
                // Disables the burger/arrow animation by default
                super.onDrawerSlide(drawerView, 0);
            }
        };

        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mActionBarDrawerToggle.syncState();

        // Navigation Drawer layout width
        int possibleMinDrawerWidth = UtilsDevice.getScreenWidth(this) -
                UtilsMiscellaneous.getThemeAttributeDimensionSize(this, android.R.attr.actionBarSize);
        int maxDrawerWidth = getResources().getDimensionPixelSize(R.dimen.navigation_drawer_max_width);

        mScrimInsetsFrameLayout.getLayoutParams().width = Math.min(possibleMinDrawerWidth, maxDrawerWidth);

        // Account section height
        mFrameLayout_AccountView.getLayoutParams().height = (int) (mScrimInsetsFrameLayout.getLayoutParams().width
                * sNAVIGATION_DRAWER_ACCOUNT_SECTION_ASPECT_RATIO);

        // Nav Drawer item click listener
        mFrameLayout_AccountView.setOnClickListener(this);
        mFrameLayout_Home.setOnClickListener(this);
        mFrameLayout_Explore.setOnClickListener(this);
        mFrameLayout_HelpAndFeedback.setOnClickListener(this);
        mFrameLayout_About.setOnClickListener(this);

        // Set the first item as selected for the first time
        getSupportActionBar().setTitle(R.string.toolbar_title_home);
        mFrameLayout_Home.setSelected(true);

        // Create the first fragment to be shown
        Bundle bundle = new Bundle();
        bundle.putInt(ColorFragment.sARGUMENT_COLOR, R.color.background_material_light);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_activity_content_frame, MainFragment.newInstance(bundle))
                .commit();
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.navigation_drawer_account_view)
        {
            mDrawerLayout.closeDrawer(Gravity.START);

            // If the user is signed in, go to the profile, otherwise show sign up / sign in
        }
        else
        {
            if (!view.isSelected())
            {
                onRowPressed((FrameLayout) view);

                switch (view.getId())
                {
                    case R.id.navigation_drawer_items_list_linearLayout_home:
                    {
                        getSupportActionBar().setTitle(getString(R.string.toolbar_title_home));

                        view.setSelected(true);

                        Bundle bundle = new Bundle();
                        bundle.putInt(ColorFragment.sARGUMENT_COLOR, R.color.background_material_light);

                        // Insert the fragment by replacing any existing fragment
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_activity_content_frame, MainFragment.newInstance(bundle))
                                .commit();
                        break;
                    }

                    case R.id.navigation_drawer_items_list_linearLayout_explore:
                    {
                        getSupportActionBar().setTitle(getString(R.string.toolbar_title_explore));

                        view.setSelected(true);

                        Bundle bundle = new Bundle();
                        bundle.putInt(ColorFragment.sARGUMENT_COLOR, R.color.amber_500);

                        // Insert the fragment by replacing any existing fragment
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_activity_content_frame, EditInfoFragment.newInstance("1", "2"))
                                .commit();
                        break;
                    }

                    case R.id.navigation_drawer_items_list_linearLayout_help_and_feedback:
                        // Start intent to send an email
                        startActivity(new Intent(view.getContext(), OtherActivity.class));
                        break;

                    case R.id.navigation_drawer_items_list_linearLayout_about:
                        // Show about activity
                        startActivity(new Intent(view.getContext(), SettingsActivity.class));
                        break;

                    default:
                        break;
                }
            }
            else
            {
                mDrawerLayout.closeDrawer(Gravity.START);
            }
        }
    }

    /**
     * Set up the rows when any is pressed
     *
     * @param pressedRow is the pressed row in the drawer
     */
    private void onRowPressed(FrameLayout pressedRow)
    {
        if (pressedRow.getTag() != getResources().getString(R.string.tag_nav_drawer_special_entry))
        {
            for (int i = 0; i < mNavDrawerEntriesRootView.getChildCount(); i++)
            {
                View currentView = mNavDrawerEntriesRootView.getChildAt(i);

                boolean currentViewIsMainEntry = currentView.getTag() ==
                        getResources().getString(R.string.tag_nav_drawer_main_entry);

                if (currentViewIsMainEntry)
                {
                    if (currentView == pressedRow)
                    {
                        currentView.setSelected(true);
                    }
                    else
                    {
                        currentView.setSelected(false);
                    }
                }
            }
        }

        mDrawerLayout.closeDrawer(Gravity.START);
    }

    @Override
    public void onFragmentInteraction(String id) {
        
    }
}
