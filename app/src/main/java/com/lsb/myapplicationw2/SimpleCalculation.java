package com.lsb.myapplicationw2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SimpleCalculation extends AppCompatActivity {

    private TextView mTitleText;
    private TextView mPeopleNum;
    private TextView mMaxmoneyText;
    private TextView mMoneyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculation);

        mTitleText = (TextView) findViewById(R.id.simple_title_text);
        mPeopleNum = (TextView) findViewById(R.id.simple_peoplenum_text);
        mMaxmoneyText = (TextView) findViewById(R.id.simple_maxmoney_text);
        mMoneyText = (TextView) findViewById(R.id.simple_money_text);


        if (getIntent() != null) {
            String name = getIntent().getStringExtra("name");
            String people_num = getIntent().getStringExtra("people");
            String money = getIntent().getStringExtra("money");

            int people_money = (Integer.valueOf(money) / Integer.valueOf(people_num));

            mTitleText.setText(name);
            mPeopleNum.setText(people_num);
            mMaxmoneyText.setText(money);

            mMoneyText.setText(String.valueOf(people_money) + "원 입니다.");
        }
    }
}
