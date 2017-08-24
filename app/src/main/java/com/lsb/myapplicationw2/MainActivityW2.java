package com.lsb.myapplicationw2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static android.R.attr.data;
import static android.R.attr.name;
import static android.R.attr.value;
import static android.text.InputType.TYPE_CLASS_TEXT;

public class MainActivityW2 extends AppCompatActivity  {

    private EditText mTitleText;
    private EditText mMoneyText;
    private EditText mPeopleNumText;
    private CheckBox mCheckName;
    private LinearLayout mAddNameLay;
    private RadioGroup mRadioButton;
    private RadioButton mRadioButton10;
    private RadioButton mRadioButton100;
    private RadioButton mRadioButton1000;
    private RadioButton mRadioButton0;

    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_w2);
//f

        mTitleText = (EditText) findViewById(R.id.edit_title);
        mMoneyText = (EditText) findViewById(R.id.edit_money);
        mPeopleNumText = (EditText) findViewById(R.id.edit_peoplenum);

        mCheckName = (CheckBox) findViewById(R.id.checkbox_name_check);
        mAddNameLay = (LinearLayout) findViewById(R.id.add_view_lay);


        mRadioButton = (RadioGroup) findViewById(R.id.radio_group_button);

        mRadioButton0 = (RadioButton) findViewById(R.id.radio_button_0);
        mRadioButton10 = (RadioButton) findViewById(R.id.radio_button_10);
        mRadioButton100 = (RadioButton) findViewById(R.id.radio_button_100);
        mRadioButton1000 = (RadioButton) findViewById(R.id.radio_button_1000);


        mTitleText.setLines(1);
        mMoneyText.addTextChangedListener(new TextWatcher() {


            DecimalFormat df = new DecimalFormat("###,###.####");

            String strAmount = "";

            protected String makeStringComma(String str) {    // 천단위 콤마 처리
                if (str.length() == 0)
                    return "";
                long value = Long.parseLong(str);
                DecimalFormat format = new DecimalFormat("###,###");
                return format.format(value);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!s.toString().equals(result)) {     // StackOverflow를 막기위해,
//                    result = df.format(Long.parseLong(s.toString().replaceAll(",", "")));   // 에딧텍스트의 값을 변환하여, result에 저장.
//                    mMoneyText.setText(result);    // 결과 텍스트 셋팅.
//                    mMoneyText.setSelection(result.length());     // 커서를 제일 끝으로 보냄.

                if (s.toString().isEmpty()) {

                } else if (!s.toString().equals(strAmount)) { // StackOverflow 방지
                    strAmount = makeStringComma(s.toString().replace(",", ""));
                    mMoneyText.setText(strAmount);
                    Editable e = mMoneyText.getText();
                    Selection.setSelection(e, strAmount.length());
                    if (Integer.valueOf(s.toString().replace(",", "")) > 100000000) {
                        mMoneyText.setText("");
                        Toast.makeText(MainActivityW2.this, "100,000,000원 이하로 설정 해 주세요.", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });

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
                                    name_text.requestFocus();
                                    name_text.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                                    name_text.setInputType(InputType.TYPE_CLASS_TEXT);
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


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        mAddNameLay.removeAllViews();

        Boolean check = savedInstanceState.getBoolean("ischeck");
        String people_num = savedInstanceState.getString("peoplenum");

        String name;

        mCheckName.setChecked(check);
        mAddNameLay.setVisibility(View.VISIBLE);

        if (people_num.equals("")) {

        } else {
            for (int i = 0; i < Integer.valueOf(people_num); i++) {
                EditText name_text = new EditText(MainActivityW2.this);
                if (savedInstanceState.getString("nameText" + i) != null) {
                    name = savedInstanceState.getString("nameText" + i);
                    name_text.setText(name);
                }
                name_text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                name_text.setPadding(20, 10, 10, 10);
                name_text.setTextSize(24);
                name_text.requestFocus();
                name_text.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                name_text.setInputType(InputType.TYPE_CLASS_TEXT);
                name_text.setHint((i + 1) + ". " + "이름을 입력 해 주세요");
                name_text.setId(i);

                name_text.setTag("NameView" + i);

                mAddNameLay.addView(name_text);

            }
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
                    name_text.requestFocus();
                    name_text.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                    name_text.setInputType(InputType.TYPE_CLASS_TEXT);
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
        String money_check = mMoneyText.getText().toString().replace(",", "").trim();
        String people_check = mPeopleNumText.getText().toString().trim();

        if (Integer.valueOf(money_check) == 0 && Integer.valueOf(people_check) == 0) {
            Toast.makeText(this, "계산 할 수 없습니다.", Toast.LENGTH_SHORT).show();
        } else {


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

                    if (mRadioButton10.isChecked()) {
                        intent.putExtra("division", 10);
                    } else if (mRadioButton100.isChecked()) {
                        intent.putExtra("division", 100);
                    } else if (mRadioButton1000.isChecked()) {
                        intent.putExtra("division", 1000);
                    } else if (mRadioButton0.isChecked()) {
                        intent.putExtra("division", 1);
                    }
                    intent.putExtra("title", mTitleText.getText().toString());
                    intent.putExtra("money", mMoneyText.getText().toString().replace(",", ""));
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
    }

    private void simplecalculation() {
        Intent intent = new Intent(MainActivityW2.this, SimpleCalculation.class);
        intent.putExtra("name", mTitleText.getText().toString());
        intent.putExtra("money", mMoneyText.getText().toString().replace(",", ""));
        intent.putExtra("money2", mMoneyText.getText().toString());
        intent.putExtra("people", mPeopleNumText.getText().toString());

        if (mRadioButton10.isChecked()) {
            intent.putExtra("division", 10);
        } else if (mRadioButton100.isChecked()) {
            intent.putExtra("division", 100);
        } else if (mRadioButton1000.isChecked()) {
            intent.putExtra("division", 1000);
        } else if (mRadioButton0.isChecked()) {
            intent.putExtra("division", 1);
        }

        startActivity(intent);
    }

}


