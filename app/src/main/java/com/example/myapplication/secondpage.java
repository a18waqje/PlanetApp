package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class secondpage extends AppCompatActivity {
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpage);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name" );
        int diameter = intent.getIntExtra("Diameter",-1 );
        String location = intent.getStringExtra("Locataion" );
        String type = intent.getStringExtra("Type" );
        String textInfo = intent.getStringExtra("Textinfo" );
        Log.d("WAQAR", "Fick följande från main activity: "+name);
        Log.d("WAQAR", "Fick följande från main activity: "+diameter);
        Log.d("WAQAR", "Fick följande från main activity: "+location);
        TextView textView = findViewById(R.id.textFromMain);
       TextView textView1 = findViewById(R.id.planetDiameter);
        TextView textView2 = findViewById(R.id.planetLocation);
        TextView textView3 = findViewById(R.id.planettype);
        TextView textView4 = findViewById(R.id.planetinfo);
       textView.setText(name);
       textView1.setText("Diameter :  \n"+diameter);
       textView2.setText(location);
        textView3.setText("Type :  \n"+type);
       textView4.setText("Information :  \n"+textInfo);
       // Toast.makeText(getApplicationContext(), name + location, Toast.LENGTH_SHORT).show();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


   /* @Override
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
            new MainActivity.FetchData().execute();
            return true;


        } if (id == R.id.settings) {
            Toast.makeText(this, "Settings not available at the moment", Toast.LENGTH_SHORT).show();
            Log.d("Waqar_debug", "Settings has been pressed");
            return true;
        } else {
            return false;

        }


    }*/

}
