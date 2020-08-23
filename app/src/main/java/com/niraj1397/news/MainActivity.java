package com.niraj1397.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.niraj1397.news.parameter.Articles;
import com.niraj1397.news.parameter.Headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    final String API_KEY="d0e3ca861d6746b0a875dd0e72e623b9";
    Button refreshButton;
    List<Articles> articlesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articlesList = new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        refreshButton= findViewById(R.id.refreshbutton);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final String country = getCountry();
        fetchJSON(country,API_KEY);

    }

    private void fetchJSON(String country, String api_key) {
        Call<Headlines> call = Client.getInstance().getApi().getHaedline(country,api_key);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if(response.isSuccessful() && response.body().getArticles()!=null){
                    articlesList=response.body().getArticles();
                    myAdapter = new MyAdapter(MainActivity.this,articlesList);
                    recyclerView.setAdapter(myAdapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Check your Internet Connection",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country=locale.getCountry();
        return country.toLowerCase();
    }
}