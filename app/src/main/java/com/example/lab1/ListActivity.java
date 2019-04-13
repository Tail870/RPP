package com.example.lab1;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class ListActivity extends AppCompatActivity {
    private String numlist[];
    private MyAdapter adapter;
    private ListView lvMain;
    private Parcelable statelvMain = null;

    protected void listFill(int arg) {
        numlist = new String[arg];
        for (int i = 0; i < arg; i++) {
            numlist[i] = intToWord(arg - i);//i + 1);
        }
    }

    private String intToWord(int arg) {
        String word = "";
        if (arg > 1000000)
            return "больше одного миллиона";
        else if (arg == 1000000)
            return "один миллион";
        else if (arg == 0)
            return "нуль";
        else if (arg < 0)
            return "меньше нуля";
        else {
            int thousands = arg % 1000000 / 1000, hundreds = arg % 1000 / 100, tens = arg % 100 / 10;
            {
                String[] st = {"", "сто ", "двести ", "триста ", "четыреста ", "пятьсот ", "шестьсот ", "семьсот ", "восемьсот ", "девятьсот "};
                word += st[thousands / 10 % 10];
            }
            if (thousands % 100 / 10 == 1) {
                String[] st = {"", "десять тысяч ", "одиннадцать тысяч ", "двенадцать тысяч ", "тринадцать тысяч ", "четырнадцать тысяч ", "пятнадцать тысяч ", "шестнадцать тысяч ", "семнадцать тысяч ", "восемнадцать тысяч ", "девятнадцать тысяч "};
                word += st[thousands % 10];
            }
            if (thousands / 10 % 10 < 10) {
                String[] st = {"", "", "двадцать ", "тридцать ", "сорок ", "пятьдесят ", "шестьдесят ", "семьдесят ", "восемьдесят ", "девяносто "};
                word += st[thousands / 10 % 10];
            }
            if (thousands / 10 > 1) {
                String[] st = {"тысяч ", "одна тысяча ", "две тысячи ", "три тысячи ", "четыре тысячи ", "пять тысяч ", "шесть тысяч ", "семь тысяч ", "восемь тысяч ", "девять тысяч "};
                word += st[thousands % 10];
            }
            {
                String[] st = {"", "сто ", "двести ", "триста ", "четыреста ", "пятьсот ", "шестьсот ", "семьсот ", "восемьсот ", "девятьсот "};
                word += st[hundreds];
            }
            if (tens == 1) {
                String[] st = {"десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
                word += st[(arg % 10)];
            } else {
                String[] st = {"", "", "двадцать ", "тридцать ", "сорок ", "пятьдесят ", "шестьдесят ", "семьдесят ", "восемьдесят ", "девяносто "};
                word += st[tens];
            }
            if (tens != 1) {
                String[] st = {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
                word += st[arg % 10];
            }
        }
        return word;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Runnable runnable = new Runnable() {
            public void run() {
                listFill(1000000);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        adapter = new MyAdapter(this, Arrays.asList(numlist));
        lvMain = findViewById(R.id.ListView);
        lvMain.setAdapter(adapter);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        statelvMain = lvMain.onSaveInstanceState();
        setContentView(R.layout.activity_list);
        adapter = new MyAdapter(this, Arrays.asList(numlist));
        lvMain = findViewById(R.id.ListView);
        lvMain.setAdapter(adapter);
        super.onConfigurationChanged(newConfig);
        if (statelvMain != null)
            lvMain.onRestoreInstanceState(statelvMain);
    }
}


