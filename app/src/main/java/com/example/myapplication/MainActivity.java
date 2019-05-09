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

                // Create the request to the PHP-service, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    Log.d("a18waqje", "inputStream == null");
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    Log.d("a18waqje", "buffer.length() == 0");
                    return null;
                }
                jsonStr = buffer.toString();
                return jsonStr;
            } catch (Exception e) {
                Log.e("a18waqje", "IOException:" + e.getMessage());
                // If the code didn't successfully get the weather data, there's no point in
                // attempting to parse it.
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

            // This code executes after we have received our data. The String object o holds
            // the un-parsed JSON string or is null if we had an IOException during the fetch.

            // Implement a parsing code that loops through the entire JSON and creates objects
            // of our newly created Mountain class.

/*
            try
            {
                // For loop
                JSONArray mountains = new JSONArray(o);
                Log.d("a18waqje", mountains.get(0).toString());
                //JSONObject obj =  mountains.getJSONObject(0);
                //Log.d( "Mountain",obj.getString("ID"));
                //Log.d( "Mountain",""+mountains.length());
                //String id = obj.getString("ID");

                adapter.clear();

                //JSONArray aProperty = obj.getJSONArray("properties");
                for (int i = 0; i < mountains.length(); i++) {
                    Log.d("a18waqje", "" + mountains.length());

                    JSONObject ajProperty = mountains.getJSONObject(i);

                    String name = (String) ajProperty.get("name");
                    Log.d("a18waqje", name);
                    String location = ajProperty.getString("location");
                    int height = ajProperty.getInt("size");
                    Log.d("a18waqje", name + "," + location + "," + height);
                    adapter.add(new Mountain(name, location, height));
                    Log.d("a18waqje", ajProperty.getString("name"));



                    my_listview.setAdapter(adapter);
                    my_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override

                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                            Toast.makeText(getApplicationContext(),waqarsBerg.get(position).info(), Toast.LENGTH_SHORT).show();
                        }
                    });






                    ArrayAdapter<Mountain> adapter=new ArrayAdapter<Mountain>(getApplicationContext(),R.layout.list_item_textview,R.id.list_item_textview,waqarsBerg);

                    ListView my_listview=(ListView) findViewById(R.id.my_listview);

                    my_listview.setAdapter(adapter);

                }
            }

            // Skapa Mountain obj för varje varv
            // Lägg till nya Mountain oibj i ArrayAdapter


            catch (Exception e) {
                Log.e("a18waqje", "E:" + e.getMessage());
            }
*/
        }
    }
}

