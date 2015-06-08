package com.demo.materialdesign.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.demo.materialdesign.R;
import com.demo.materialdesign.fragments.FoodProductFragment;

public class EditInfoDetailActivity extends BaseActivity {
    private String name;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info_detail);
        name = getIntent().getStringExtra("name");
        id = getIntent().getIntExtra("id", 0);
        initialise();
        initFragmentView();
    }


    /**
     * Create, bind and set up the resources
     */
    public void initialise() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //当我们使用最新的AppCompatActivity继承而来的toolbar时，就不需要在去设置setSupportActionBar项了
        /*setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        toolbar.canShowOverflowMenu();
        toolbar.setSubtitle(name);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initFragmentView() {
        Bundle bundle = new Bundle();
        bundle.putInt(FoodProductFragment.sARGUMENT_COLOR, R.color.background_material_light);
        switch (id) {
          
            case 0:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_edit_info, FoodProductFragment.newInstance(bundle))
                        .commit();
                break;

            case 1:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_edit_info, FoodProductFragment.newInstance(bundle))
                        .commit();
                break;
            case 2:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_edit_info, FoodProductFragment.newInstance(bundle))
                        .commit();
                break;
            case 3:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_edit_info, FoodProductFragment.newInstance(bundle))
                        .commit();
                break;
            case 4:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_edit_info, FoodProductFragment.newInstance(bundle))
                        .commit();
                break;
            case 5:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_edit_info, FoodProductFragment.newInstance(bundle))
                        .commit();
                break;

            default:
                break;

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_info_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
