package com.sevenlearn.session15;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ResultActivity extends AppCompatActivity {
    public static final String EXTRA_KEY_FIRST_NAME = "first_name";
    public static final String EXTRA_KEY_LAST_NAME = "last_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final EditText firstNameEt = findViewById(R.id.et_result_firstName);
        final EditText lastNameEt = findViewById(R.id.et_result_lastName);
        Button saveBtn = findViewById(R.id.btn_result_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_KEY_FIRST_NAME, firstNameEt.getText().toString());
                intent.putExtra(EXTRA_KEY_LAST_NAME, lastNameEt.getText().toString());
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}
