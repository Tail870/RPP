package com.example.lab2.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.lab2.NetworkService;
import com.example.lab2.TechDownload;
import com.example.lab2.Technology;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    ArrayList<Technology> technologies;
int counter;
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        technologies = new ArrayList();

        NetworkService.getRetrofit().create(TechDownload.class)
                .getTechs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::fillTech);
    }

    public void fillTech(ArrayList<Technology> teches){
        for (Technology t : teches) {
            if (t.getName() != null && !t.getName().isEmpty() ){
                technologies.add(t);
            }
            counter++;
        }
        Toast.makeText(this, "Loaded in general: "+String.valueOf(counter), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("techArray", technologies);
        startActivity(intent);
        finish();
    }
}





