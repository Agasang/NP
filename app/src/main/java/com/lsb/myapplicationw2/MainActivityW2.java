package com.lsb.myapplicationw2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.data;
import static android.R.attr.name;
import static android.R.attr.value;

public class MainActivityW2 extends AppCompatActivity {

    private EditText mTitleText;
    private EditText mMoneyText;
    private EditText mPeopleNumText;
    private CheckBox mCheckName;
    private LinearLayout mAddNameLay;


    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_w2);



        mTitleText = (EditText) findViewById(R.id.edit_title);
        mMoneyText = (EditText) findViewById(R.id.edit_money);
        mPeopleNumText = (EditText) findViewById(R.id.edit_peoplenum);

        mCheckName = (CheckBox) findViewById(R.id.checkbox_name_check);
        mAddNameLay = (LinearLayout) findViewById(R.id.add_view_lay);






        mPeopleNumText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mAddNameLay.removeAllViews();
                if (mCheckName.isChecked()) {
                    String people_check = mPeopleNumText.getText().toString();

                    if ((people_check.trim().isEmpty())) {
                        mAddNameLay.removeAllViews();


                    } else if (Integer.valueOf(s.toString()) > 30) {
                        mPeopleNumText.setText("");
                        Toast.makeText(MainActivityW2.this, "인원은 30명 까지 입력 가능합니다.", Toast.LENGTH_SHORT).show();


                    } else if (!(people_check.trim().isEmpty())) {

                        for (int i = 0; i < Integer.valueOf(s.toString()); i++) {
                            EditText name_text = new EditText(MainActivityW2.this);
                            name_text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            name_text.setPadding(20, 10, 10, 10);
                            name_text.setTextSize(24);
                            name_text.setHint((i + 1) + ". " + "이름을 입력 해 주세요");
                            name_text.setTag("NameView" + i);
                            name_text.setId(i);
                            mAddNameLay.addView(name_text);
                        }
                    }
                } else {
                    String people_check = mPeopleNumText.getText().toString();
                    if ((people_check.trim().isEmpty())) {
                        mAddNameLay.removeAllViews();


                    } else if (Integer.valueOf(s.toString()) > 30) {
                        mPeopleNumText.setText("");
                        Toast.makeText(MainActivityW2.this, "인원은 30명 까지 입력 가능합니다.", Toast.LENGTH_SHORT).show();


                    }

                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
//        mCheckName.setChecked(settings.getBoolean("ischeck" , true));
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = settings.edit();
//        editor.
//        editor.apply();
//
//    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        mAddNameLay.removeAllViews();

        Boolean check = savedInstanceState.getBoolean("ischeck");
        String people_num = savedInstanceState.getString("peoplenum");

        String name;

        mCheckName.setChecked(check);
        mAddNameLay.setVisibility(View.VISIBLE);

        for (int i = 0; i < Integer.valueOf(people_num); i++) {
            EditText name_text = new EditText(MainActivityW2.this);
            if (savedInstanceState.getString("nameText" + i) != null) {
                name = savedInstanceState.getString("nameText" + i);
                name_text.setText(name);
            }
            name_text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            name_text.setPadding(20, 10, 10, 10);
            name_text.setTextSize(24);
            name_text.setHint((i + 1) + ". " + "이름을 입력 해 주세요");
            name_text.setId(i);
            name_text.setTag("NameView" + i);

            mAddNameLay.addView(name_text);


        }
        mPeopleNumText.setText(people_num);
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putBoolean("ischeck", mCheckName.isChecked()); // 체크박스 저장
        outState.putString("peoplenum", mPeopleNumText.getText().toString());
        if (mCheckName.isChecked()) {
            for (int i = 0; i < Integer.valueOf(mPeopleNumText.getText().toString()); i++) {
                EditText PeopleName = (EditText) findViewById(i);
                if (!PeopleName.getText().toString().trim().equals("")) {
                    outState.putString("nameText" + i, PeopleName.getText().toString());
                }

            }
        }


        super.onSaveInstanceState(outState);
    }


    public void onCheckBoxClicked(View view) {
        if (mCheckName.isChecked()) {
            mAddNameLay.removeAllViews();
            mAddNameLay.setVisibility(View.VISIBLE);
            if (mPeopleNumText.getText().toString().trim().equals("")) {
                Toast.makeText(this, "인원을 입력 해 주세요", Toast.LENGTH_SHORT).show();
                mCheckName.setChecked(false);
            } else {
                for (int i = 0; i < Integer.valueOf(mPeopleNumText.getText().toString()); i++) {
                    EditText name_text = new EditText(MainActivityW2.this);
                    name_text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    name_text.setPadding(20, 10, 10, 10);
                    name_text.setTextSize(24);
                    name_text.setHint((i + 1) + ". " + "이름을 입력 해 주세요");

                    name_text.setId(i);
                    name_text.setTag("NameView" + i);
                    mAddNameLay.addView(name_text);
                }
            }
        } else {

            mAddNameLay.setVisibility(View.GONE);
            mAddNameLay.removeAllViews();
        }


    }

    public void onClickButton(View view) {
        String money_check = mMoneyText.getText().toString().trim();
        String people_check = mPeopleNumText.getText().toString().trim();


        if (!(money_check.equals("")) && !(people_check.equals(""))) {
            String name[] = new String[Integer.valueOf(mPeopleNumText.getText().toString())];
            if (mCheckName.isChecked()) {
                Intent intent = new Intent(MainActivityW2.this, Calculation.class);
                for (int i = 0; i < Integer.valueOf(mPeopleNumText.getText().toString()); i++) {

                    TextView NameTexts = (TextView) mAddNameLay.findViewWithTag("NameView" + i);
                    if (NameTexts.getText().toString().trim().equals("")) {
                        intent.putExtra("name2" + i, "" + (i + 1));
                    } else {
                        name[i] = NameTexts.getText().toString();
                        intent.putExtra("name2" + i, name[i]);
                    }


                }

                intent.putExtra("title", mTitleText.getText().toString());
                intent.putExtra("money", mMoneyText.getText().toString());
                intent.putExtra("people", mPeopleNumText.getText().toString());


                startActivity(intent);


            } else {

                simplecalculation();

            }
        } else if (!(money_check.equals("")) && (people_check.equals(""))) {
            Toast.makeText(this, "사람 수를 입력 해 주세요", Toast.LENGTH_SHORT).show();
        } else if ((money_check.equals("")) && !(people_check.equals(""))) {
            Toast.makeText(this, "금액 입력 해 주세요", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "금액과 사람 수를 입력 해 주세요", Toast.LENGTH_SHORT).show();
        }
    }

    private void simplecalculation() {
        Intent intent = new Intent(MainActivityW2.this, SimpleCalculation.class);
        intent.putExtra("name", mTitleText.getText().toString());
        intent.putExtra("money", mMoneyText.getText().toString());
        intent.putExtra("people", mPeopleNumText.getText().toString());
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected String makeStringComma(String str) {
        if (str.length() == 0)
            return "";
        long value = Long.parseLong(str);
        DecimalFormat format = new DecimalFormat("###,###");
        return format.format(value);
    }


}


