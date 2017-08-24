package com.lsb.myapplicationw2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SendActivity extends AppCompatActivity {

    private TextView mTitleText;
    private TextView mPayPeopleText;
    private TextView mMaxMoneyText;
    private TextView mNameMoneyText;
    private LinearLayout mPayLayout;
    private LinearLayout mPayLayout2;
    private LinearLayout mPayBankLayout;
    private String mPayCheck;
    private int mMaximumNum;
    private String mSendDisplay;
    private TextView mBankText;
    private TextView mBankNumberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);


        mTitleText = (TextView) findViewById(R.id.send_title);
        mPayPeopleText = (TextView) findViewById(R.id.send_paypeople);
        mMaxMoneyText = (TextView) findViewById(R.id.send_maximum_money);
        mNameMoneyText = (TextView) findViewById(R.id.name_and_money_text);

        mPayLayout = (LinearLayout) findViewById(R.id.send_paypeople_layout);
        mPayLayout2 = (LinearLayout) findViewById(R.id.send_paypeople_layout2);
        mPayBankLayout = (LinearLayout) findViewById(R.id.send_paypeople_bank_layout);

        mBankText = (TextView) findViewById(R.id.send_bank_name);
        mBankNumberText = (TextView) findViewById(R.id.send_bank_number);


        if (getIntent() != null) {
            String name_money = "";

            mPayCheck = getIntent().getStringExtra("paypcheck");


            if (mPayCheck.equals("OK")) {
                mPayLayout.setVisibility(View.VISIBLE);
                mPayLayout2.setVisibility(View.VISIBLE);
                mPayBankLayout.setVisibility(View.VISIBLE);
                mPayPeopleText.setText(getIntent().getStringExtra("paypeople") + " 님");
            } else {

            }

            mMaxMoneyText.setText(getIntent().getStringExtra("MaxMoney") + " 원");

            mMaximumNum = getIntent().getIntExtra("num", 0);
            int checkPeople = getIntent().getIntExtra("checkPeople", 32);
            for (int i = 0; i < mMaximumNum; i++) {
                if (i == checkPeople) {

                } else {
                    name_money = name_money + getIntent().getStringExtra("name" + i) + "\t" + "\t"
                            + getIntent().getStringExtra("Money" + i) + " 원"
                            + getIntent().getStringExtra("division" + i) ;

                    name_money = name_money + "\n";
                }


            }
            mNameMoneyText.setText("\n"+name_money);
            mTitleText.setText(getIntent().getStringExtra("title"));
        }


    }

    private void display() {
        if (mPayCheck.equals("OK")) {
            mSendDisplay = "제  목  :  " + mTitleText.getText().toString() + "\n" + "\n"
                    + "결  제  :  " + mPayPeopleText.getText().toString() +  "\n" + "\n"
                    + "금  액  :  " + mMaxMoneyText.getText().toString() + "원" + "\n" + "\n"
                    + "총  원  :  " + mMaximumNum + "명" +  "\t"+ "( )절사" +  "\n" + "\n"
                    + "==================" + "\n"
                    + mBankText.getText().toString() + "\n"
                    + "계좌번호 : " + mBankNumberText.getText().toString()+ "\n"
                    + "==================" + "\n"
                    + mNameMoneyText.getText().toString();

        } else {
            mSendDisplay = "제  목  :  " + mTitleText.getText().toString() + "\n" + "\n"
                    + "금  액  :  " + mMaxMoneyText.getText().toString() +  "원" + "\n" + "\n"
                    + "총  원  :  " + mMaximumNum + "명" +  "\t"+ "( )절사" + "\n" + "\n"
                    + "==================" + "\n"
                    + mNameMoneyText.getText().toString();
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

    public void onClickSendClicked(View view) {
        display();
        MmsMessage(mSendDisplay);
    }
}

