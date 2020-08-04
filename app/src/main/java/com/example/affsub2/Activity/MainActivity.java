package com.example.affsub2.Activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.affsub2.Adapter.AffAdapter;
import com.example.affsub2.R;
import com.example.affsub2.REST.AffApi;
import com.example.affsub2.REST.Model.Main;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String AFF_URL = "https://randomuser.me/api/";
    RecyclerView recyclerView;
    AffAdapter affListAdapter = new AffAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetch();
    }

    void fetch() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(AFF_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        AffApi user = retrofit.create(AffApi.class);
        Call<Main> call = user.getResults("https://randomuser.me/api/?results=20");


        call.enqueue(new Callback<Main>() {
            @Override
            public void onResponse(@NotNull Call<Main> call, @NotNull Response<Main> response) {
                Main result = response.body();

                recyclerView = findViewById(R.id.recycleView);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                affListAdapter.setData(result.getResults(), MainActivity.this);
                recyclerView.setAdapter(affListAdapter);
                recyclerView.setHasFixedSize(true);
            }

            @Override
            public void onFailure(@NotNull Call<Main> call, @NotNull Throwable t) {
                Log.d("retro", "" + t.getMessage());
            }
        });
    }
}