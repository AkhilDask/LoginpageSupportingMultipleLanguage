package com.example.multiplelanguage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));


        Button changeLanguage =findViewById(R.id.changelang);
        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();
            }
        });
    }
    private void showChangeLanguageDialog(){
        final String[] listitems= {"française","हिंदी","Deutsche","English"};
        AlertDialog.Builder mBuilder= new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("choose Language..");
        mBuilder.setSingleChoiceItems(listitems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0){
                    setLocale("fr");
                    recreate();
                }
                else if(i==1){
                    setLocale("hi");
                    recreate();
                }
                else if(i==2){
                    setLocale("de");
                    recreate();
                }
               else if(i==3){
                    setLocale("en");
                    recreate();
                }
               dialogInterface.dismiss();
            }
        });
        mBuilder.create().show();

    }
    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }
    public void loadLocale() {
        SharedPreferences pref= getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = pref.getString("My_Lang","");
        setLocale(language);
    }
}