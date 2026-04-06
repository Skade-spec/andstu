package com.example.lab13database;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppDatabase db;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new UserFragment())
                .commit();

        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "database-name"
        ).allowMainThreadQueries().build();

        db.userDao().insert(new User("Рамиль", "ramil@mail.com"));
        db.userDao().insert(new User("Алекс", "alex@mail.com"));

        List<User> users = db.userDao().getAll();

        String data = "";

        for (User user : users) {
            data += user.id + " "
                    + user.name + " "
                    + user.email + "\n";
        }

        textView.setText(data);

    }
}