package com.example.myfirstapplication.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.example.myfirstapplication.R;

public class CheckboxWithEditText extends LinearLayout {

    private CheckBox sizeElement;
    private EditText valueSizeElement;
    private ImageView subtract;
    private ImageView add;
    private final float scale = this.getResources().getDisplayMetrics().density;

    public CheckboxWithEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        obtainStyledAttributes(context,attrs);
        addView(sizeElement);
        addView(subtract);
        addView(valueSizeElement);
        addView(add);

    }

    private void init(Context context){
        sizeElement = new CheckBox(context);
        valueSizeElement = new EditText(context);
        subtract = new ImageView(context);
        add = new ImageView(context);

        sizeElement.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        valueSizeElement.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        subtract.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        add.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

        //attrs edittext
        valueSizeElement.setInputType(InputType.TYPE_CLASS_NUMBER);
        valueSizeElement.setWidth((int)(80.0f * scale + 0.5f));
        valueSizeElement.setText("0");
        valueSizeElement.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        valueSizeElement.setEnabled(false);
        //attrs checkbox
        sizeElement.setPadding(0,0,(int)(60.0f * scale + 0.5f),0);

        //attrs bottoms
        add.setImageResource(R.drawable.ic_baseline_add_circle_24);
        add.setColorFilter(ContextCompat.getColor(context,R.color.green_normal));
        subtract.setImageResource(R.drawable.ic_outline_remove_circle_24);
        subtract.setColorFilter(ContextCompat.getColor(context,R.color.green_normal));
        add.setEnabled(false);
        subtract.setEnabled(false);

        subtract.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                float valueEditText = Float.valueOf(valueSizeElement.getText().toString());
                if(valueEditText!=0){
                    valueSizeElement.setText(String.valueOf(valueEditText-1));
                }
            }
        });
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                float valueEditText = Float.valueOf(valueSizeElement.getText().toString());
                valueSizeElement.setText(String.valueOf(valueEditText+1));
            }
        });

        sizeElement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    valueSizeElement.setEnabled(true);
                    add.setEnabled(true);
                    subtract.setEnabled(true);
                    valueSizeElement.setText("1.0");
                }
                else{
                    valueSizeElement.setEnabled(false);
                    add.setEnabled(false);
                    subtract.setEnabled(false);
                    valueSizeElement.setText("0");
                }
            }
        });

    }

    private void obtainStyledAttributes(Context context, AttributeSet attrs){
        if(attrs != null){
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CheckboxWithEditText,0,0);
            String textSize = typedArray.getString(R.styleable.CheckboxWithEditText_textSize);
            sizeElement.setText(textSize);
        }
    }

    public boolean itemSelected(){
        return sizeElement.isChecked();
    }

    public EditText getValueSizeElement(){
        return valueSizeElement;
    }
}
