package com.lsb.myapplicationw2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.DecimalFormat;


public class SimpleCalculation extends AppCompatActivity {

    private TextView mTitleText;
    private TextView mPeopleNum;
    private TextView mMaxmoneyText;
    private TextView mMoneyText;
    private EditText mBankNeme;
    private EditText mBankNum;
    private String mSendDisplay;
    private int division_throw_money;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculation);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mTitleText = (TextView) findViewById(R.id.simple_title_text);
        mPeopleNum = (TextView) findViewById(R.id.simple_peoplenum_text);
        mMaxmoneyText = (TextView) findViewById(R.id.simple_maxmoney_text);
        mMoneyText = (TextView) findViewById(R.id.simple_money_text);

        mBankNeme = (EditText) findViewById(R.id.simple_bank_name);
        mBankNum = (EditText) findViewById(R.id.simple_bank_num);


        DecimalFormat comma = new DecimalFormat("###,###.####");


        if (getIntent() != null) {
            String name = getIntent().getStringExtra("name");
            String people_num = getIntent().getStringExtra("people");
            String money = getIntent().getStringExtra("money");
            String money2 = getIntent().getStringExtra("money2");

            int division = getIntent().getIntExtra("division", 0);


            if (division == 0) {
                int people_money = (Integer.valueOf(money) / Integer.valueOf(people_num));

                mTitleText.setText(name);
                mPeopleNum.setText(people_num);
                mMaxmoneyText.setText(money2 + "원");

                mMoneyText.setText(comma.format(Long.parseLong(String.valueOf(people_money).replaceAll(",", "")))
                        + "원 입니다.");

            } else {
                int people_money = (Integer.valueOf(money) / Integer.valueOf(people_num));

                int division_people_money = (int) ((double) people_money - (people_money % division));

                division_throw_money = (int) ((double) (people_money % division));

                mTitleText.setText(name);
                mPeopleNum.setText(people_num);
                mMaxmoneyText.setText(money2 + "원");

                mMoneyText.setText(comma.format(Long.parseLong(String.valueOf(division_people_money).replaceAll(",", "")))
                        + "원 입니다." + "\n" + "\t" + "\t" + "\t" + "\t" + "(" + division_throw_money + ")" + "원 절사");


            }


        }


    }


    private void display() {
        if (mBankNeme.getText().toString().isEmpty() && mBankNum.getText().toString().isEmpty()) {
            mSendDisplay = "제  목  :  " + mTitleText.getText().toString() + "\n" + "\n"
                    + "금  액  :  " + mMaxmoneyText.getText().toString() + "\n" + "\n"
                    + "총  원  :  " + mPeopleNum.getText().toString() + "명" + "\n" + "\n"
                    + "==================" + "\n"
                    + "     한 사람 당" + "\n"
                    + mMoneyText.getText().toString() + "\n";

        } else {
            mSendDisplay = "제  목  :  " + mTitleText.getText().toString() + "\n" + "\n"
                    + "금  액  :  " + mMaxmoneyText.getText().toString() + "\n" + "\n"
                    + "총  원  :  " + mPeopleNum.getText().toString() + "명" + "\n" + "\n"
                    + "==================" + "\n"
                    + "     한 사람 당" + "\n"
                    + mMoneyText.getText().toString() + "\n"
                    + "\n"
                    + "==================" + "\n"
                    + mBankNeme.getText().toString() + "\n"
                    + "계좌번호 : " + mBankNum.getText().toString() + "\n"
                    + "==================" + "\n";


        }
    }

    public void MmsMessage(String message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public void onClickButton(View view) {
        display();
        MmsMessage(mSendDisplay);
    }
}

