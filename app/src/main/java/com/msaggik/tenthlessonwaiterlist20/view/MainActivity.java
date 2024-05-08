package com.msaggik.tenthlessonwaiterlist20.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.msaggik.tenthlessonwaiterlist20.R;

public class MainActivity extends AppCompatActivity {

    // поля
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // привязка разметки к полям
        imageButton = findViewById(R.id.imageButton);

        // обработка касания кнопки
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // создание намерения перехода во вторую активность
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}