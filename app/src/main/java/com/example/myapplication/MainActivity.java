package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

    public static ArrayAdapter<Planets> adapter;
    ListView my_listview;

    // private String[] Extra_message;
    //public static final String EXTRA_MESSAGE ="Test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /* WebView webView = new WebView (this);
        webView =(WebView)findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/about.html");

        public void onBackPressed(){
            if (webView.canGoBack()){
                webView.goBack();
            }
            else {
                super.onBackPressed();
            }
        }
        */

        adapter = new ArrayAdapter<Planets>(this, R.layout.list_item_textview, R.id.list_item_textview);
        my_listview = (ListView) findViewById(R.id.my_listview);

        my_listview.setAdapter(adapter);
        my_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                /* Toast.makeText(getApplicationContext(), waqarsBerg.get(position).info(), Toast.LENGTH_SHORT).show();*/
                Intent intent = new Intent(getApplicationContext(), secondpage.class);

                intent.putExtra("Name" , adapter.getItem(position).toString());

                intent.putExtra( "Location", adapter.getItem(position).getLocation());
                intent.putExtra( "Type", adapter.getItem(position).getType());
                intent.putExtra( "Diameter", adapter.getItem(position).getDiameter());
                intent.putExtra( "Textinfo", adapter.getItem(position).getTextInfo());


                startActivity(intent);

            }
        });

        new FetchData().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.refresh) {
            Toast.makeText(this, "The Page is Refreshed", Toast.LENGTH_SHORT).show();
            Log.d("Waqar_debug", "Refresh pressed!");
            new FetchData().execute();
            return true;


        } if (id == R.id.settings) {
            Toast.makeText(this, "Settings not available", Toast.LENGTH_SHORT).show();
            Log.d("Waqar_debug", "Settings has been pressed");
            return true;
        }

        if (id == R.id.about) {
            Toast.makeText(this, "About \n This App contains information about our Solar system. The purpose of this app is to provide some information about planets to people who are eager to know (students or anyone). All the Information cited from https://en.wikipedia.org/wiki/Solar_System", Toast.LENGTH_LONG).show();
            Log.d("Waqar_debug", "About has been pressed");
            return true;
        }


        else {
            return false;

        }



    }


    public static class FetchData extends AsyncTask<Void, Void, String> {
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
                        Log.d("a18waqje", "Network error. Closing stream" + e.getMessage());
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
                    String type = ajProperty.getString("company");
                    String location = ajProperty.getString("location");
                    int diameter = ajProperty.getInt("size");
                    String infoText = ajProperty.getString("auxdata");
                    Log.d("a18waqje", name +"," + type + "," + location + "," + diameter + "," + infoText);
                    adapter.add(new Planets(name, type, location, diameter, infoText));
                    Log.d("a18waqje", ajProperty.getString("name"));


                }
            }



            catch (Exception e) {
                Log.e("a18waqje", "E:" + e.getMessage());
            }

        }
    }
}

