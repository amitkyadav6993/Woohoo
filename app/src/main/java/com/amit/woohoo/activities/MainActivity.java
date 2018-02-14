package com.amit.woohoo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.amit.woohoo.R;
import com.amit.woohoo.adapters.ForcastAdapter;
import com.amit.woohoo.models.Astronomy;
import com.amit.woohoo.models.Channel;
import com.amit.woohoo.models.ForcastResult;
import com.amit.woohoo.models.Forecast;
import com.amit.woohoo.models.Item;
import com.amit.woohoo.models.Query;
import com.amit.woohoo.models.Results;
import com.amit.woohoo.rest.ApiClient;
import com.amit.woohoo.rest.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.forcast_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        String q = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"bangalore\")";
        String format = "json";
        String env = "store://datatables.org/alltableswithkeys";

        Call<ForcastResult> call = apiService.getForcastResult(q,format,env);
        call.enqueue(new Callback<ForcastResult>() {
            @Override
            public void onResponse(Call<ForcastResult> call, Response<ForcastResult> response) {
                int statusCode = response.code();
                if (statusCode == 200) {

                    Channel channel = response.body().getQuery().getResults().getChannel();
                    Astronomy astronomy = channel.getAstronomy();
                    String sunrise = astronomy.getSunrise();
                    String sunset = astronomy.getSunset();
                    List<Forecast> forecastList = channel.getItem().getForecast();
                    recyclerView.setAdapter(new ForcastAdapter(forecastList,sunrise,sunset, R.layout.list_item_forcast, getApplicationContext()));

                }else {
                    try {
                        String err = response.errorBody().string();
                        Toast.makeText(getApplicationContext(), err, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        //handle err here
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ForcastResult> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
