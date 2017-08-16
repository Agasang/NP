package com.lsb.myapplicationw2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class Calculation extends AppCompatActivity {

    private TextView mTitleText;
    private TextView mPeopleNum;
    private TextView mMaxmoneyText;
    private TextView mMoneyText;

    private LinearLayout m0CalculAddNameLay;
    private String[] m1CalculAddName;
    private String[] m2CalculAddMoney;
    private Boolean[] m3CalculAddCheck;
    private Boolean[] m4CalculAddPay;

    private int checkedMoney;  // 고정 금액이 총 금액을 넘는지 체크
    private int checkedCount;



    // 새로 만들어 추가된 에디트박스들의 평균 돈

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        mTitleText = (TextView) findViewById(R.id.calcul_title_text);
        mPeopleNum = (TextView) findViewById(R.id.calcul_peoplenum_text);
        mMaxmoneyText = (TextView) findViewById(R.id.calcul_maxmoney_text);
        mMoneyText = (TextView) findViewById(R.id.calcul_money_text);





        checkedCount = 0;


        m0CalculAddNameLay = (LinearLayout) findViewById(R.id.add_all_lay);


//        m1CalculAddNameLay = (LinearLayout) findViewById(R.id.add_lay_calcul_name);
//        m2CalculAddMoneyLay = (LinearLayout) findViewById(R.id.add_lay_calcul_money);
//        m3CalculAddCheckLay = (LinearLayout) findViewById(R.id.add_lay_calcul_check);
//        m4CalculAddPayLay = (LinearLayout) findViewById(R.id.add_lay_calcul_pay);


        if (getIntent() != null) {
            String title = getIntent().getStringExtra("title");
            String people_num = getIntent().getStringExtra("people");
            String money = getIntent().getStringExtra("money");


            int people_money = (Integer.valueOf(money) / Integer.valueOf(people_num));

            mTitleText.setText(title);
            mPeopleNum.setText(people_num);
            mMaxmoneyText.setText(money);


            // 이름 받는 배열 생성
            m1CalculAddName = new String[Integer.valueOf(mPeopleNum.getText().toString())];

            m2CalculAddMoney = new String[Integer.valueOf(mPeopleNum.getText().toString())];
            m3CalculAddCheck = new Boolean[Integer.valueOf(mPeopleNum.getText().toString())];
            m4CalculAddPay = new Boolean[Integer.valueOf(mPeopleNum.getText().toString())];


            for (int i = 0; i < Integer.valueOf(mPeopleNum.getText().toString()); i++) {
                String n = getIntent().getStringExtra("name2" + i);
                m1CalculAddName[i] = n;
            }
            // 이름 받는 배열 생성


            mMoneyText.setText(String.valueOf(people_money));


            // 레이아웃 생성
            for (int i = 0; i < Integer.valueOf(mPeopleNum.getText().toString()); i++) {
                LinearLayout parentLL = new LinearLayout(Calculation.this);
                parentLL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                parentLL.setTag("AllLay" + i);
                parentLL.setId(i);
                parentLL.setOrientation(LinearLayout.HORIZONTAL);
                m0CalculAddNameLay.addView(parentLL);


            }


//                 이름 받아서 에디트 박스 수 생성
            for (int i = 0; i < Integer.valueOf(mPeopleNum.getText().toString()); i++) {
                LinearLayout Alllay = (LinearLayout) m0CalculAddNameLay.findViewWithTag("AllLay" + i);


                final EditText nameEdit = new EditText(Calculation.this);

//                nameEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                LinearLayout.LayoutParams Lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
                Lp.weight = 1;
                nameEdit.setPadding(20, 10, 10, 10);
                nameEdit.setTextSize(24);
                nameEdit.setHint((i + 1) + ". " + "이름");
                nameEdit.setId(i);
                nameEdit.setTag("NameView" + i);
                nameEdit.setText(m1CalculAddName[i]);

                nameEdit.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.toString().isEmpty()) {
                            m1CalculAddName[nameEdit.getId()] = s.toString();
                        } else {
                            m1CalculAddName[nameEdit.getId()] = s.toString();
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                Alllay.addView(nameEdit, Lp);
            }
            // 이름 받아서 에디트 박스 수 생성

            // 평균 금액
            for (int i = 0; i < Integer.valueOf(mPeopleNum.getText().toString()); i++) {
                LinearLayout Alllay = (LinearLayout) m0CalculAddNameLay.findViewWithTag("AllLay" + i);


//                final EditText mPeopleMoneyText = new EditText(Calculation.this);
                final EditNumber mPeopleMoneyText = new EditNumber(Calculation.this);


                LinearLayout.LayoutParams Lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
                Lp.weight = 1;
                mPeopleMoneyText.setPadding(20, 10, 10, 10);
                mPeopleMoneyText.setTextSize(24);
                mPeopleMoneyText.setHint((i + 1) + ". " + "금액");
                mPeopleMoneyText.setId(i);
                mPeopleMoneyText.setTag("MoneyView" + i);
                mPeopleMoneyText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_TEXT_VARIATION_NORMAL);
                mPeopleMoneyText.setText(mMoneyText.getText().toString());
                m2CalculAddMoney[i] = mMoneyText.getText().toString();
                mPeopleMoneyText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.toString().isEmpty()) {


                        } else if (Integer.valueOf(s.toString()) > Integer.valueOf(mMaxmoneyText.getText().toString())) {
                            mPeopleMoneyText.setText(mMoneyText.getText().toString());
                            Toast.makeText(Calculation.this, "총액 보다 금액이 많습니다.", Toast.LENGTH_SHORT).show();
                        } else if (Integer.valueOf(s.toString()) < 0) {
                            mPeopleMoneyText.setText(mMoneyText.getText().toString());
                            Toast.makeText(Calculation.this, "금액이 적습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            m2CalculAddMoney[mPeopleMoneyText.getId()] = s.toString();
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                Alllay.addView(mPeopleMoneyText, Lp);
            }





            // 평균 금액


            // 고정금액 체크 박스

            for (int i = 0; i < Integer.valueOf(mPeopleNum.getText().toString()); i++) {
                LinearLayout Alllay = (LinearLayout) m0CalculAddNameLay.findViewWithTag("AllLay" + i);
                final CheckBox checkedMoney = new CheckBox(Calculation.this);
//                checkedMoney.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                LinearLayout.LayoutParams Lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
                Lp.weight = 1;
                checkedMoney.setTextSize(24);
                checkedMoney.setId(i);
                checkedMoney.setTag("CheckView" + i);
                checkedMoney.setText("고정");
                m3CalculAddCheck[i] = false;
                checkedMoney.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            m3CalculAddCheck[checkedMoney.getId()] = true;
                        } else {
                            m3CalculAddCheck[checkedMoney.getId()] = false;
                        }

                    }
                });
                Alllay.addView(checkedMoney, Lp);
            }

            // 고정금액 체크 박스


            for (int i = 0; i < Integer.valueOf(mPeopleNum.getText().toString()); i++) {
                LinearLayout Alllay = (LinearLayout) m0CalculAddNameLay.findViewWithTag("AllLay" + i);
                final CheckBox checkedPay = new CheckBox(Calculation.this);
//                checkedPay.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                LinearLayout.LayoutParams Lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
                Lp.weight = 1;
                checkedPay.setTextSize(24);
                checkedPay.setId(i);
                checkedPay.setTag("PayView" + i);
                checkedPay.setText("결제자");
                m4CalculAddPay[i] = false;
                checkedPay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked) {
                            if (checkedCount == 0) {
                                m4CalculAddPay[checkedPay.getId()] = true;
                                checkedCount = 1;
                            } else {
                                checkedPay.setChecked(false);
                                Toast.makeText(Calculation.this, "결제자는 한명 이어야 합니다.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (m4CalculAddPay[checkedPay.getId()] == true) {
                                checkedCount = 0;
                                m4CalculAddPay[checkedPay.getId()] = false;
                            }
                        }
                    }
                });
                Alllay.addView(checkedPay, Lp);
            }


        }


    }



    public void onClickButton(View view) {


        int people_num = Integer.valueOf(mPeopleNum.getText().toString()); // 사람 수 만큼 for문을 위해


        switch (view.getId()) {

            case R.id.button_calc:

                //고정금액 - 총금액을 위해
                int minusMoney = Integer.valueOf(mMaxmoneyText.getText().toString());
                // 한 사람당 돌아갈 동
                int newPeopleMoney = 0;
                //체크한 돈 총액 비교를 위해
                checkedMoney = 0;

                // 체크한 사람 숫자
                int checkedMoneyPeopleCount = 0;

                for (int i = 0; i < people_num; i++) {
                    if (m3CalculAddCheck[i]) {
                        checkedMoney = checkedMoney + Integer.valueOf(m2CalculAddMoney[i]);
                        checkedMoneyPeopleCount++;
                    }
                }

                if (checkedMoney > Integer.valueOf(mMaxmoneyText.getText().toString())) {
                    Toast.makeText(this, "고정 금액의 총합이 " + "\n" + "총 금액을 초과 하였습니다." +
                            "\n" + "다시 설정 해 주세요", Toast.LENGTH_SHORT).show();

                } else {
                    for (int i = 0; i < Integer.valueOf(mPeopleNum.getText().toString()); i++) {
                        if (m3CalculAddCheck[i]) {
                            minusMoney = minusMoney - Integer.valueOf(m2CalculAddMoney[i]);
                        }
                    }
                    newPeopleMoney = minusMoney / (Integer.valueOf(mPeopleNum.getText().toString()) - checkedMoneyPeopleCount);

                    for (int i = 0; i < Integer.valueOf(mPeopleNum.getText().toString()); i++) {
                        if (!m3CalculAddCheck[i]) {
                            LinearLayout vvv = (LinearLayout) findViewById(i);
                            EditText PeopleMoney = (EditText) vvv.findViewWithTag("MoneyView" + i);
                            PeopleMoney.setText("" + newPeopleMoney);
                            m2CalculAddMoney[i] = PeopleMoney.getText().toString();
//                            EditText PeopleMoney = (EditText) PeopleMoneyText.findViewWithTag("MoneyView" + i);
//                            PeopleMoney.setText(""+newPeopleMoney);
                        }
                    }

                }
                break;

            case R.id.button_ok:

                //확인만 누를 때를 위해 한번더 입력 나중에 해결하자.

                //고정금액 - 총금액을 위해
                minusMoney = Integer.valueOf(mMaxmoneyText.getText().toString());
                // 한 사람당 돌아갈 동
                newPeopleMoney = 0;
                //체크한 돈 총액 비교를 위해
                checkedMoney = 0;

                // 체크한 사람 숫자
                checkedMoneyPeopleCount = 0;

                for (int i = 0; i < people_num; i++) {
                    if (m3CalculAddCheck[i]) {
                        checkedMoney = checkedMoney + Integer.valueOf(m2CalculAddMoney[i]);
                        checkedMoneyPeopleCount++;
                    }
                }

                if (checkedMoney > Integer.valueOf(mMaxmoneyText.getText().toString())) {
                    Toast.makeText(this, "고정 금액의 총합이 " + "\n" + "총 금액을 초과 하였습니다." +
                            "\n" + "다시 설정 해 주세요", Toast.LENGTH_SHORT).show();

                } else {
                    for (int i = 0; i < Integer.valueOf(mPeopleNum.getText().toString()); i++) {
                        if (m3CalculAddCheck[i]) {
                            minusMoney = minusMoney - Integer.valueOf(m2CalculAddMoney[i]);
                        }
                    }
                    newPeopleMoney = minusMoney / (Integer.valueOf(mPeopleNum.getText().toString()) - checkedMoneyPeopleCount);

                    for (int i = 0; i < Integer.valueOf(mPeopleNum.getText().toString()); i++) {
                        if (!m3CalculAddCheck[i]) {
                            LinearLayout vvv = (LinearLayout) findViewById(i);
                            EditText PeopleMoney = (EditText) vvv.findViewWithTag("MoneyView" + i);
                            PeopleMoney.setText("" + newPeopleMoney);
                            m2CalculAddMoney[i] = PeopleMoney.getText().toString();

                        }
                    }

                }
                //확인만 누를 때를 위해 한번더 입력 나중에 해결하자.


                int calcPeopleCheck = 32;
                String calcPeopleName;
                String calcPeopleMoney;
                Intent intent = new Intent(Calculation.this, SendActivity.class);
                intent.putExtra("paypcheck", "NO");
                for (int i = 0; i < people_num; i++) {

                    if (m4CalculAddPay[i] == true) {

                        calcPeopleCheck = i;

                        LinearLayout vvv = (LinearLayout) findViewById(i);
                        EditText PeopleName = (EditText) vvv.findViewWithTag("NameView" + i);
                        calcPeopleName = PeopleName.getText().toString();
                        intent.putExtra("paypeople", calcPeopleName);
                        intent.putExtra("paypcheck", "OK");

                    } else {

                    }
                }


                for (int i = 0; i < people_num; i++) {

                    if (i != calcPeopleCheck) {
                        LinearLayout vvv = (LinearLayout) findViewById(i);
                        EditText PeopleName = (EditText) vvv.findViewWithTag("NameView" + i);
                        String N = PeopleName.getText().toString();

                        EditText PeopleMoney = (EditText) vvv.findViewWithTag("MoneyView" + i);
                        String M = PeopleMoney.getText().toString();

                        intent.putExtra("name" + i, N);
                        intent.putExtra("Money" + i, M);
                    } else {

                    }

                }
                intent.putExtra("MaxMoney", mMaxmoneyText.getText().toString());
                intent.putExtra("num", people_num);
                intent.putExtra("title",mTitleText.getText().toString());
                intent.putExtra("checkPeople", calcPeopleCheck);
                startActivity(intent);

        }


    }
}


// 이름 받아서 에디트 박스 수 생성
//            for (int i = 0; i < Integer.valueOf(mPeopleNum.getText().toString()); i++) {
//                EditText topTV1 = new EditText(Calculation.this);
//                topTV1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//                topTV1.setPadding(20, 10, 10, 10);
//                topTV1.setTextSize(24);
//                topTV1.setHint((i + 1) + ". " + "이름");
//                topTV1.setId(i);
//                topTV1.setTag("NameView" + i);
//                topTV1.setText(m1CalculAddName[i]);
//                m1CalculAddNameLay.addView(topTV1);
//            }
//            // 이름 받아서 에디트 박스 수 생성
//
//            // 평균 금액
//            for (int i = 0; i < Integer.valueOf(mPeopleNum.getText().toString()); i++) {
//                EditText topTV1 = new EditText(Calculation.this);
//                topTV1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//                topTV1.setPadding(20, 10, 10, 10);
//                topTV1.setTextSize(24);
//                topTV1.setHint((i + 1) + ". " + "금액");
//                topTV1.setId(i);
//                topTV1.setTag("MoneyView" + i);
//                topTV1.setText(mMoneyText.getText().toString());
//                m2CalculAddMoneyLay.addView(topTV1);
//            }
//            // 평균 금액
//
//            for (int i = 0; i < Integer.valueOf(mPeopleNum.getText().toString()); i++) {
//                CheckBox topTV1 = new CheckBox(Calculation.this);
//                topTV1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//
//                topTV1.setTextSize(24);
//                topTV1.setId(i);
//                topTV1.setTag("CheckView" + i);
//                topTV1.setText("고정");
//                m3CalculAddCheckLay.addView(topTV1);
//            }