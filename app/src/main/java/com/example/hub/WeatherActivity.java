package com.example.hub;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WeatherActivity extends AppCompatActivity {

    TextView txtCity, txtLastUpdate, txtDescription, txtHumidity, txtTime, txtCelsius;
    EditText editCity;
    FloatingActionButton btnSearch;

    ImageView imageView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        txtCity = (TextView) findViewById(R.id.txtCity);
        //txtLastUpdate = (TextView) findViewById(R.id.txtLastUpdate);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        //txtHumidity = (TextView) findViewById(R.id.txtHumidity);
        //txtTime = (TextView) findViewById(R.id.txtTime);
        txtCelsius = (TextView) findViewById(R.id.txtCelsius);
        imageView = (ImageView) findViewById(R.id.imageView);
        editCity = (EditText) findViewById(R.id.editCity);
       btnSearch = (FloatingActionButton) findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getRootView().getWindowToken(), 0);
                api_key(String.valueOf(editCity.getText()));

            }
        });
    }
    private void api_key(final String city){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url("http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=1e2a16b7da365166e337ee3a3d511cff&units=metric").get().build();
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try{
            Response response=client.newCall(request).execute();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseDate=response.body().string();
                    try {
                        JSONObject json=new JSONObject(responseDate);
                        JSONArray array=json.getJSONArray("weather");
                        JSONObject object=array.getJSONObject(0);
                        String description=object.getString("description");
                        String icon=object.getString("icon");
                        JSONObject temp=json.getJSONObject("main");
                        Double temperature=temp.getDouble("temp");

                        setText(txtCity,city);

                        String temps=Math.round(temperature)+" °C";
                        setText(txtCelsius,temps);
                        setText(txtDescription,description);
                        setImage(imageView,icon);




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }


    }
    private void setText(final TextView text, final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }
    private void setImage(final ImageView imageView, final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (value){
                    case "01d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d01d));
                        break;
                    case "01n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d01d));
                        break;
                    case "02d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d02d));
                        break;
                    case "02n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d02d));
                        break;
                    case "03d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d03d));
                        break;
                    case "03n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d03d));
                        break;
                    case "04d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d04d));
                        break;
                    case "04n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d04d));
                        break;
                    case "09d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d09d));
                        break;
                    case "09n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d09d));
                        break;
                    case "10d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d10d));
                        break;
                    case "10n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d10d));
                        break;
                    case "11d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d11d));
                        break;
                    case "11n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d11d));
                        break;
                    case "13d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d13d));
                        break;
                    case "13n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d13d));
                        break;
                    default:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.wheather));

                }
            }
        });
    }
}





//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        provider = locationManager.getBestProvider(new Criteria(), false);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(WeatherActivity.this,new String[]{
//                        Manifest.permission.INTERNET,
//                        Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.ACCESS_NETWORK_STATE,
//                        Manifest.permission.SYSTEM_ALERT_WINDOW,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE
//
//                },MY_PERMISSION);
//
//            }
//        }
//        Location location = locationManager.getLastKnownLocation(provider);
//        if (location==null){
//            Log.e("TAG","No Location ");
//
//        }


//    @Override
//    protected void onPause() {
//        super.onPause();
//        locationManager.removeUpdates(this);
//    }

//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(WeatherActivity.this,new String[]{
//                    Manifest.permission.INTERNET,
//                    Manifest.permission.ACCESS_COARSE_LOCATION,
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_NETWORK_STATE,
//                    Manifest.permission.SYSTEM_ALERT_WINDOW,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE
//
//            },MY_PERMISSION);
//
//        }
//        locationManager.requestLocationUpdates(provider,400,1,this);
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        lat=location.getLatitude();
//        lng=location.getLongitude();
//
//        new GetWeather().execute(Common.apiRequest(String.valueOf(lat),String.valueOf(lng)));
//
//
//
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//
//    }
//    private class GetWeather extends AsyncTask<String,Void,String> {
//
//        ProgressDialog pd= new ProgressDialog(WeatherActivity.this);
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            pd.setTitle("Please Wait...");
//            pd.show();
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            Log.e("TAG", "in post");
//            super.onPostExecute(s);
//            if(s.contains("Error: Not found city")){
//                pd.dismiss();
//                return;
//            }
//            Gson gson= new Gson();
//            Type mType = new TypeToken<OpenWeatherMap>(){}.getType();
//            openWeatherMap = gson.fromJson(s,mType);
//            pd.dismiss();
//
//            txtCity.setText(String.format("%s,%s",openWeatherMap.getName(),openWeatherMap.getSys().getCountry()));
//            txtLastUpdate.setText(String.format("Last Updated: %s", Common.getDateNow()));
//            txtDescription.setText(String.format("%s",openWeatherMap.getWeather().get(0).getDescription()));
//            txtHumidity.setText(String.format("%d%%",openWeatherMap.getMain().getHumidity()));
//            txtTime.setText(String.format("%s/%s",Common.unixTimeStampToDateTime(openWeatherMap.getSys().getSunrise()),Common.unixTimeStampToDateTime(openWeatherMap.getSys().getSunset())));
//            txtCelsius.setText(String.format("%.2f °C",openWeatherMap.getMain().getTemp()));
//            Picasso.get().load(Common.getImage(openWeatherMap.getWeather().get(0).getIcon())).into(imageView);
//
//
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            Log.e("TAG","In background");
//            String stream=null;
//            String urlString= params[0];
//            Helper hhttp=new Helper();
//            stream= hhttp.getHTTPData(urlString);
//
//
//            return stream;
//        }
//    }




