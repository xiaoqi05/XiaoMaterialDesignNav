package com.demo.materialdesign.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.demo.materialdesign.R;
import com.demo.materialdesign.bean.FoodProBean;

public class DetailActivity extends AppCompatActivity
{
    private TextView tv_food_name;
    private TextView tv_food_address;
    private TextView tv_food_type;
    private TextView tv_food_pro_name;
    private TextView tv_food_state;
    private TextView tv_food_code;
    private TextView tv_food_product_state;
    private TextView tv_food_total_num;
    private TextView tv_food_person_num;
    private FoodProBean foodProBean;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        initialise();
        findView();
        getData();
        setData();
    }

    private void initialise()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void findView(){
        tv_food_name = (TextView) findViewById(R.id.tv_food_name);
        tv_food_address = (TextView) findViewById(R.id.tv_food_address);
        tv_food_type = (TextView) findViewById(R.id.tv_food_type);
        tv_food_pro_name = (TextView) findViewById(R.id.tv_food_pro_name);
        tv_food_person_num = (TextView) findViewById(R.id.tv_food_person_num);
        tv_food_state = (TextView) findViewById(R.id.tv_food_state);
        tv_food_code = (TextView) findViewById(R.id.tv_food_code);
        tv_food_product_state = (TextView) findViewById(R.id.tv_food_product_state);
        tv_food_total_num = (TextView) findViewById(R.id.tv_food_total_num);
    }

    private void getData(){
        foodProBean = (FoodProBean) this.getIntent().getBundleExtra("bundle").getSerializable("foodBean");
    }
    
    private void setData(){
        tv_food_name.setText("企业名称 ："+foodProBean.getQyName());
        tv_food_address.setText("生产地址 ："+foodProBean.getProductionAddr());
        tv_food_type.setText("产品种类 ："+foodProBean.getCpType());
        tv_food_pro_name.setText("法定代表人 : "+foodProBean.getLegalName());
        tv_food_person_num.setText("身份证号码 ："+foodProBean.getIdNumber());
        tv_food_state.setText("委托加工情况 ："+foodProBean.getIsCommissionedProcessing());
        tv_food_code.setText("组织机构代码 ："+foodProBean.getOrganizationCode());
        tv_food_product_state.setText("生产状况 ："+foodProBean.getProductionStatus());
        tv_food_total_num.setText("企企业员工总数 ："+foodProBean.getEmployeesNumber());
    }
    
    

}
