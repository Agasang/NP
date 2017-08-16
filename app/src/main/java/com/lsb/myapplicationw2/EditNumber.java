package com.lsb.myapplicationw2;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import java.text.DecimalFormat;

import static android.R.attr.inputType;

/**
 * Created by L on 2017-08-15.
 */

public class EditNumber extends android.support.v7.widget.AppCompatEditText {

    public EditNumber(Context context) {
        this(context, null, 0);

    }

    public EditNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    setText(numToStr(getText().toString()));
                } else {
                    setText(strToNum(getText().toString()));
                }
            }
        });
    }

    public EditNumber(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    private String strToNum(String str) {
        String num = "";
        DecimalFormat format = new DecimalFormat("###,###");
        try {
            num = format.format(Long.parseLong(str));
        } catch (Exception e) {

        }
        return num;
    }

    private String numToStr(String num) {
        String str = "";
        try {
            str = num.replaceAll(",", "");
        } catch (Exception e) {

        }
        return str;
    }

    @Override
    public Editable getText() {
        if (hasFocus()) {
            return super.getText();
        } else {
            if (super.getText().toString().length() > 0) {
                return new SpannableStringBuilder(numToStr(super.getText().toString()));
            } else {
                return super.getText();
            }
        }
    }
}

