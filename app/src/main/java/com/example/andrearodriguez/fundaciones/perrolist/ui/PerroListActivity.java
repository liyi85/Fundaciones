package com.example.andrearodriguez.fundaciones.perrolist.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.example.andrearodriguez.fundaciones.R;

/**
 * Created by andrearodriguez on 9/25/16.
 */
public class PerroListActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perro);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fav, menu);
        return super.onCreateOptionsMenu(menu);
    }



}
