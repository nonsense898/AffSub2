package com.example.affsub2.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.affsub2.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    TextView firstName;
    TextView lastName;
    TextView email;
    TextView phone;
    TextView age;
    CircleImageView avatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        initView();
        setData();

    }

    private void setData() {
        Bundle extras = getIntent().getExtras();
        assert extras != null;

        Picasso.get().load(extras.getString("avatar")).into(avatar);
        firstName.setText(extras.getString("first_name"));
        lastName.setText(extras.getString("last_name"));
        age.setText(extras.getString("age"));
        phone.setText(extras.getString("phone"));
        email.setText(extras.getString("email"));
    }

    private void initView() {
        avatar = findViewById(R.id.profile_image);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email_text);
        phone = findViewById(R.id.phone_text);
        age = findViewById(R.id.age);
    }


}
