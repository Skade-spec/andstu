package com.example.lab13database;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UserFragment extends Fragment {

    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_user,
                container,
                false);

        textView = view.findViewById(R.id.textViewFragment);

        AppDatabase db = Room.databaseBuilder(
                getContext(),
                AppDatabase.class,
                "database-name"
        ).allowMainThreadQueries().build();


        List<User> users = db.userDao().getAll();

        String data = "";

        for (User user : users) {
            data += user.name + "\n";
        }

        textView.setText(data);

        return view;
    }
}