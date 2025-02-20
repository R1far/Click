package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Integer count = 0;
    private boolean Up = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count");
            Up = savedInstanceState.getBoolean("Up");
        }

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> {
            if(Up) {
                Up = false;
                button2.setBackgroundColor(Color.RED);
            } else {
                Up = true;
                button2.setBackgroundColor(Color.GREEN);
            }
        });

        // Обновить текст при восстановлении состояния
        TextView txt = findViewById(R.id.txtCounter);
        txt.setText(count.toString());
    }

    public void onClickBatton(View view) {
        count++;
        TextView txt = findViewById(R.id.txtCounter);
        txt.setText(count.toString());

        if (Up) {
            count++;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
        outState.putBoolean("Up", Up);
    }
}
