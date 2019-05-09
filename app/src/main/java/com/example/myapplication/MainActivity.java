package com.example.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity<adapter> extends AppCompatActivity {

    private ArrayAdapter<Planets> adapter;
    ListView my_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adapter = new ArrayAdapter<Planets>(this, R.layout.list_item_textview, R.id.list_item_textview);
        my_listview = (ListView) findViewById(R.id.my_listview);

        my_listview.setAdapter(adapter);
        my_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), adapter.getItem(position).info(), Toast.LENGTH_SHORT).show();
            }
        });

        new FetchData().execute();


    }

    private class FetchData extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;


            String jsonStr = null;

            try {
                Log.d("a18waqje", "TRY");

                URL url = new URL("http://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=a18waqje");


                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();


                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {

                    Log.d("a18waqje", "inputStream == null");
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {

                    Log.d("a18waqje", "buffer.length() == 0");
                    return null;
                }
                jsonStr = buffer.toString();
                return jsonStr;
            } catch (Exception e) {
                Log.e("a18waqje", "IOException:" + e.getMessage());

                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.d("a18waqje", "Network error. Closing streamd" + e.getMessage());
                    }
                }
                Log.d("a18waqje", "Finally");

            }
        }
        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            if (o != null) {
                Log.d("a18waqje", o);
            } else {
                Log.d("a18waqje", "Null was received");
            }




            try
            {
                // For loop
                JSONArray planet = new JSONArray(o);
                Log.d("a18waqje", planet.get(0).toString());


                adapter.clear();

                //JSONArray aProperty = obj.getJSONArray("properties");
                for (int i = 0; i < planet.length(); i++) {
                    Log.d("a18waqje", "" + planet.length());

                    JSONObject ajProperty = planet.getJSONObject(i);

                    String name = (String) ajProperty.get("name");
                    Log.d("a18waqje", name);
                    String company = ajProperty.getString("company");
                    String location = ajProperty.getString("location");
                    int size = ajProperty.getInt("size");
                    String auxdata = ajProperty.getString("auxdata");
                    Log.d("a18waqje", name +"," + company + "," + location + "," + size + "," + auxdata);
                    adapter.add(new Planets(name, company, location, size, auxdata));
                    Log.d("a18waqje", ajProperty.getString("name"));

/*

                    my_listview.setAdapter(adapter);
                    my_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override

                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                            Toast.makeText(getApplicationContext(),adapter.get(position).info(), Toast.LENGTH_SHORT).show();
                        }
                    });



*/

/*
                    ArrayAdapter<Planets> adapter=new ArrayAdapter<Planets>(getApplicationContext(),R.layout.list_item_textview,R.id.list_item_textview);

                    ListView my_listview=(ListView) findViewById(R.id.my_listview);

                    my_listview.setAdapter(adapter);
*/
                }
            }



            catch (Exception e) {
                Log.e("a18waqje", "E:" + e.getMessage());
            }

        }
    }
}

