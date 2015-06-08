package com.demo.materialdesign.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import com.demo.materialdesign.R;
import com.demo.materialdesign.http.GetJsonResultNetWorkHelper;
import com.demo.materialdesign.http.NetworkHelper;
import com.demo.materialdesign.http.UIDataListener;
import com.demo.materialdesign.http.Url;
import com.demo.materialdesign.utils.ToastUtils;
import com.rey.material.app.DatePickerDialog;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.widget.Button;
import com.rey.material.widget.EditText;
import com.rey.material.widget.RadioButton;
import com.rey.material.widget.Spinner;
import com.rey.material.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FoodProductFragment extends Fragment implements UIDataListener<String>, View.OnClickListener {
    public static final String sARGUMENT_COLOR = "backgroundColor";
    private NetworkHelper<String> networkHelper;
    private RadioButton rb1;
    private RadioButton rb2;
    private EditText et_food_name;
    private EditText et_food_address;
    private EditText et_food_number;
    private EditText et_food_phone;
    private EditText et_food_pro_name;
    private EditText et_food_person;
    private EditText et_food_person_num;
    private TextView tv_get_date;
    private TextView tv_show_date;
    private Spinner spn_label;
    private Button bt_commit;


    public static FoodProductFragment newInstance(Bundle bundle) {
        FoodProductFragment foodFragment = new FoodProductFragment();
        if (bundle != null) {
            foodFragment.setArguments(bundle);
        }
        return foodFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_product_fragment, container, false);
        initialise(view);
        et_food_name = (EditText) view.findViewById(R.id.et_food_name);
        et_food_address = (EditText) view.findViewById(R.id.et_food_address);
        et_food_number = (EditText) view.findViewById(R.id.et_food_number);
        et_food_phone = (EditText) view.findViewById(R.id.et_food_phone);
        et_food_pro_name = (EditText) view.findViewById(R.id.et_food_pro_name);
        et_food_person = (EditText) view.findViewById(R.id.et_food_person);
        et_food_person_num = (EditText) view.findViewById(R.id.et_food_person_num);
        tv_get_date = (TextView) view.findViewById(R.id.tv_get_date);
        tv_get_date.setOnClickListener(this);
        tv_show_date = (TextView) view.findViewById(R.id.tv_show_date);
        bt_commit = (Button) view.findViewById(R.id.bt_commit);
        bt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getEditText(et_food_name);
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.show(getActivity(), "企业名称不能为空");
                } else {
                    /*
                    * productionaddr 生产地址
                    productionlicensenumber 生产许可证编号
                   validitydate 生产许可证有效期
                       hzcpname 获证产品名称
                   cptype 产品种类
                   qyeconomicnature 
                   iscommissionedprocessing 委托加工情况
                   legalname 企业法人
                     idnumber 身份证号码

                    * */
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("foodType", "T_SCQY"));
                    params.add(new BasicNameValuePair("qyname", getEditText(et_food_name)));
                    params.add(new BasicNameValuePair("productionaddr", getEditText(et_food_address)));
                    params.add(new BasicNameValuePair("productionlicensenumber", getEditText(et_food_number)));
                    //params.add(new BasicNameValuePair("validitydate", getEditText(et_food_name)));//生产许可证有效期
                    //params.add(new BasicNameValuePair("cptype", getEditText(et_food_person)));//产品种类
                    params.add(new BasicNameValuePair("legalname", getEditText(et_food_person)));
                    params.add(new BasicNameValuePair("idnumber", getEditText(et_food_person_num)));
                    networkHelper.sendGETRequest(Url.EDIT_FOOD_INFO, params);
                }

            }
        });
        networkHelper = new GetJsonResultNetWorkHelper(getActivity());
        networkHelper.setUiDataListener(this);

        /*=================spinner相关========================*/
        spn_label = (Spinner) view.findViewById(R.id.spinner_label);
        String[] items = new String[12];
        for (int i = 0; i < items.length; i++) {
            items[i] = getResources().getStringArray(R.array.food_type_info)[i];
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.row_spn, items);
        adapter.setDropDownViewResource(R.layout.row_spn_dropdown);
        spn_label.setAdapter(adapter);
        
        
        /*=================RadioButton相关========================*/
        rb1 = (RadioButton) view.findViewById(R.id.switches_rb1);
        rb2 = (RadioButton) view.findViewById(R.id.switches_rb2);
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rb1.setChecked(rb1 == buttonView);
                    rb2.setChecked(rb2 == buttonView);
                }

            }

        };
        rb1.setOnCheckedChangeListener(listener);
        rb2.setOnCheckedChangeListener(listener);
        return view;
    }

    private void initialise(View view) {
        view.getRootView().setBackgroundColor(getResources().getColor(getArguments().getInt(sARGUMENT_COLOR)));
    }


    @Override
    public void onDataChanged(String data) {

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(data);
            
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assert jsonObject != null;
        boolean state = jsonObject.optBoolean("result");
        if (state){
            ToastUtils.show(getActivity(),"提交成功");
            bt_commit.setText("提交成功");
            bt_commit.setBackgroundColor(getResources().getColor(R.color.grey_400));
            bt_commit.setEnabled(false);
        }
        Log.i("data", data.toString());
        
    }


    @Override
    public void onErrorHappened(String errorCode, String errorMessage) {
        ToastUtils.show(getActivity(),"提交失败，请重试");
    }

    private String getEditText(EditText text) {
        String texts = text.getText().toString().trim();
        return texts;
    }


    @Override
    public void onClick(View v) {
        Dialog.Builder builder = null;
        switch (v.getId()) {

            case R.id.tv_get_date:
                builder = new DatePickerDialog.Builder() {
                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        DatePickerDialog dialog = (DatePickerDialog) fragment.getDialog();
                        String date = dialog.getFormattedDate(SimpleDateFormat.getDateInstance());
                        tv_show_date.setText(date + "");
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        super.onNegativeActionClicked(fragment);
                    }
                };

                builder.positiveAction("确定")
                        .negativeAction("取消");
                DialogFragment fragment = DialogFragment.newInstance(builder);
                fragment.show(getFragmentManager(), null);
                break;

            default:

                break;
        }
    }
}
