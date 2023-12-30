package com.sonnyburnettfun.noordzee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

//Tides app

public class MainActivity extends AppCompatActivity {

    public Button jaarbutton, dagbutton, maandbutton, plaatsbutton, helpbutton, minbutton, plusbutton;
    public TextView prevtidename, nowtidename, nexttidename;
    public TextView prevtidetime, nowtidetime, nexttidetime;
    public TextView prevtidehight, nowtidehight, nexttidehight;
    public LinearLayout totaal;
    public List<String> PLACES = Arrays.asList("IJmuiden", "Bergen", "Den Helder", "Texel", "Terschelling", "Hoek van Holland", "Scheveningen", "Westkapelle", "Vlissingen");
    public int currentPlaceIndex = 0;
    public String currentPlace;
    public List<Waterstand> waterstanden, bergen, texel, ijmuiden, denhelder, terschelling, hoekvanholland, scheveningen, westkapelle, vlissingen;

    public static final String SELECTED_PLACE = "com.sonnyburnettfun.noordzee.SELECTED_PLACE";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String NUMBER = "0";
    private String saveNum;
    // Dark colors
    public String IndianRed = "#CD5C5C";
    // Light colors
    public String LightSalmon = "#FFA07A", LightPink = "#FFB6C1", Moccasin = "#FFE4B5", Khaki = "#F0E68C";
    public String Lavender = "#E6E6FA", Thistle = "#D8BFD8", LightGreen = "#90EE90", MediumAquamarine = "#66CDAA";
    public String PaleTurquoise = "#AFEEEE", LightBlue = "#ADD8E6", BlanchedAlmond = "#FFEBCD", Gainsboro = "#DCDCDC";
    public String LightCoral = "#F08080", Orange = "#FFA500", Gold = "#FFD700", PeachPuff = "#FFDAB9", Plum = "#DDA0DD", GreenYellow = "#ADFF2F";
    public String SkyBlue = "#87CEEB", LightSteelBlue = "#B0C4DE", PapayaWhip = "#FFEFD5";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        identifyMainActivityFields();

        if (savedInstanceState != null) {
            currentPlaceIndex = savedInstanceState.getInt("PlaceIndex");
        }

        minbutton.setText("<");
        plusbutton.setText(">");
        loadTideDatainLists();
        loadData();
        setTideDatainFields(1);

        plaatsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTideDatainFields(1);            }
        });

        minbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTideDatainFields(-1);            }
        });

        plusbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTideDatainFields(1);            }
        });

        jaarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberString = Integer.toString(currentPlaceIndex);
                String tidelistString = JSONfile.convertListOfObjectsToJSONstring(waterstanden);

                Intent intent = new Intent(MainActivity.this, TidesList.class);
                intent.putExtra(SELECTED_PLACE, currentPlace );
                startActivity(intent);

            }
        });

        helpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HelpTides.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.e("msg", "onSaveInstanceState");
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("PlaceIndex", currentPlaceIndex);
        savedInstanceState.putBoolean("MyBoolean", true);
        savedInstanceState.putDouble("myDouble", 1.9);
        savedInstanceState.putInt("MyInt", 1);
        savedInstanceState.putString("MyString", "Welcome back to Android");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.e("msg", "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        boolean myBoolean = savedInstanceState.getBoolean("MyBoolean");
        double myDouble = savedInstanceState.getDouble("myDouble");
        int myInt = savedInstanceState.getInt("MyInt");
        String myString = savedInstanceState.getString("MyString");
        int myInt2 = savedInstanceState.getInt("PlaceIndex");
        currentPlaceIndex = myInt2;
        Log.e("msg", "we are in onRestoreInstanceState");
    }

    public void saveData() {
        Log.e("msg", " saveData");
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NUMBER, Integer.toString(currentPlaceIndex));
        editor.apply();
    }
    public void loadData() {
        Log.e("msg", " loadData");
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        if (sharedPreferences.contains(NUMBER)) {
            Log.e("msg", "sharedPreferences.contains(NUMBER) is waar");
            if (!sharedPreferences.getString(NUMBER, "").equals(null)) {
                Log.e("msg", "sharedPreferences.getString NUMBER is ook waar");
                saveNum = sharedPreferences.getString(NUMBER, "");
                currentPlaceIndex = (Integer.parseInt(saveNum))-1;
                Log.e("msg", "Dus CurrentPlaceIndex is geladen " + currentPlaceIndex);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int result = data.getIntExtra("result", 0);
            }
        }
    }

    public void setTideDatainFields(int richting) {
        Log.e("msg", "setTideDatainFields");
        if (richting == 1) {
            if (currentPlaceIndex < PLACES.size() - 1) {
                currentPlaceIndex++;
            } else {
                currentPlaceIndex = 0;
            }
        }
        else {
            if (currentPlaceIndex == 0) {
                currentPlaceIndex = PLACES.size()-1;
            }
            else {
                currentPlaceIndex--;
            }
            }
        switch (currentPlaceIndex) {
            case(0) :
                currentPlace = "IJmuiden";
                waterstanden = ijmuiden;
                break;
            case (1) :
                currentPlace = "Bergen";
                waterstanden = bergen;
                break;
            case(2) :
                currentPlace = "Den Helder";
                waterstanden = denhelder;
                break;
            case (3) :
                currentPlace = "Texel";
                waterstanden = texel;
                break;
            case (4) :
                currentPlace = "Terschelling";
                waterstanden = terschelling;
                break;
            case (5) :
                currentPlace = "Hoek van Holland";
                waterstanden = hoekvanholland;
                break;
            case (6) :
                currentPlace = "Scheveningen";
                waterstanden = scheveningen;
                break;
            case (7) :
                currentPlace = "Westkapelle";
                waterstanden = westkapelle;
                break;
            case (8) :
                currentPlace = "Vlissingen";
                waterstanden = vlissingen;
                break;
        }
        String jaar = DatumTijd.getTodayYear();
        String dagString = DatumTijd.getDateString(DatumTijd.getToday());
        String tijdString = DatumTijd.getTimeString(DatumTijd.getTodayTime());
        int previousTideIndex = Tides.getPreviousTide(jaar, dagString, tijdString, waterstanden);
        int nextTideIndex = previousTideIndex+1;
        setActivityFields(jaar, dagString, tijdString, previousTideIndex, nextTideIndex);
        saveData();
    }

    public void loadTideDatainLists() {
        Log.e("msg", "oadTideDatainLists");
        ijmuiden = JSONfile.getWaterstanden(getApplicationContext(), "IJmuiden");
        denhelder = JSONfile.getWaterstanden(getApplicationContext(), "Den Helder");
        texel = JSONfile.getWaterstanden(getApplicationContext(), "Texel");
        scheveningen = JSONfile.getWaterstanden(getApplicationContext(), "Scheveningen");
        hoekvanholland = JSONfile.getWaterstanden(getApplicationContext(), "Hoek van Holland");
        terschelling = JSONfile.getWaterstanden(getApplicationContext(), "Terschelling");
        westkapelle = JSONfile.getWaterstanden(getApplicationContext(), "Westkapelle");
        vlissingen = JSONfile.getWaterstanden(getApplicationContext(), "Vlissingen");
        bergen = JSONfile.getWaterstanden(getApplicationContext(), "Bergen");
        //bergen = Tides.estimateBergenTides(ijmuiden, denhelder);
    }


    public void identifyMainActivityFields() {
        Log.e("msg", "identifyMainActivityFields");
        totaal = findViewById(R.id.totaal);
        jaarbutton = findViewById(R.id.jaarbutton);
        helpbutton = findViewById(R.id.helpbutton);
        dagbutton = findViewById(R.id.dagbutton);
        maandbutton = findViewById(R.id.maandbutton);
        plaatsbutton = findViewById(R.id.plaatsbutton);
        minbutton = findViewById(R.id.minbutton);
        plusbutton = findViewById(R.id.plusbutton);
        prevtidename = findViewById(R.id.tideprevname);
        nowtidename = findViewById(R.id.tidenowname);
        nexttidename = findViewById(R.id.tidenextname);
        prevtidetime = findViewById(R.id.tideprevtime);
        nowtidetime = findViewById(R.id.tidenowtime);
        nexttidetime = findViewById(R.id.tidenexttime);
        prevtidehight = findViewById(R.id.tideprevhight);
        nowtidehight = findViewById(R.id.tidenowhight);
        nexttidehight = findViewById(R.id.tidenexthight);
    }


    public void setActivityFields(String jaar, String dagString, String tijdString, int previousTideIndex, int nextTideIndex) {
        Log.e("msg", "setActivityFields");
        plaatsbutton.setText(currentPlace);
        jaarbutton.setText(jaar);
        String achtergrond = LightSalmon;
        switch (currentPlaceIndex) {
            case(0):
                achtergrond = Lavender;
                break;
            case (1) :
                achtergrond = LightBlue;
                break;
            case(2):
                achtergrond = LightGreen;
                break;
            case (3) :
                achtergrond = LightPink;
                break;
            case (4) :
                achtergrond = MediumAquamarine;
                break;
            case (5) :
                achtergrond = Moccasin;
                break;
            case (6) :
                achtergrond = Khaki;
                break;
            case (7) :
                achtergrond = Thistle;
                break;
            case (8) :
                achtergrond = PaleTurquoise;
                break;
        }

        plaatsbutton.setBackgroundColor(Color.parseColor(achtergrond));
        minbutton.setBackgroundColor(Color.parseColor(SkyBlue));
        plusbutton.setBackgroundColor(Color.parseColor(SkyBlue));
        jaarbutton.setBackgroundColor(Color.parseColor(LightSteelBlue));
        helpbutton.setBackgroundColor(Color.parseColor( BlanchedAlmond));

        totaal.setBackgroundColor(Color.parseColor(Gainsboro));

        dagbutton.setText(dagString.substring(2,4));
        maandbutton.setText(DatumTijd.getMonthName(dagString.substring(0,2)));

        dagbutton.setBackgroundColor(Color.parseColor(SkyBlue));
        maandbutton.setBackgroundColor(Color.parseColor(SkyBlue));

        if (waterstanden.get(previousTideIndex).tide.equals("HW")) {
            prevtidename.setBackgroundColor(Color.parseColor(PeachPuff));
            prevtidetime.setBackgroundColor(Color.parseColor(PeachPuff));
            prevtidehight.setBackgroundColor(Color.parseColor(PeachPuff));
            nexttidename.setBackgroundColor(Color.parseColor(Plum));
            nexttidename.setBackgroundResource(R.drawable.eb);
            nexttidetime.setBackgroundColor(Color.parseColor(Plum));
            nexttidehight.setBackgroundColor(Color.parseColor(Plum));
            prevtidename.setText("VLOED");
            prevtidename.setBackgroundResource(R.drawable.vloed);
            nexttidename.setText("EB");
        }
        else {
            nexttidename.setBackgroundColor(Color.parseColor(PeachPuff));
            nexttidename.setBackgroundResource(R.drawable.vloed);
            nexttidetime.setBackgroundColor(Color.parseColor(PeachPuff));
            nexttidehight.setBackgroundColor(Color.parseColor(PeachPuff));
            prevtidename.setBackgroundColor(Color.parseColor(Plum));
            prevtidetime.setBackgroundColor(Color.parseColor(Plum));
            prevtidehight.setBackgroundColor(Color.parseColor(Plum));
            prevtidename.setText("EB");
            prevtidename.setBackgroundResource(R.drawable.eb);
            nexttidename.setText("VLOED");
        }
        int verschilEbenVloed = Math.abs(DatumTijd.timeDiffinMinutes(waterstanden.get(previousTideIndex).time, waterstanden.get(nextTideIndex).time));
        Log.e("msg","eb en vloed verschil: " + verschilEbenVloed);
        int verschilNuenVorige = DatumTijd.timeDiffinMinutes(waterstanden.get(previousTideIndex).time, tijdString);
        Log.e("msg", "vorige tij en nu verschil: " + verschilNuenVorige);

        float percentageVerlopen = ((float) verschilNuenVorige / (float) verschilEbenVloed)*100;

        nowtidehight.setText(Math.round(percentageVerlopen) + " % ");
        nowtidename.setBackgroundColor(Color.parseColor(PaleTurquoise));
        nowtidename.setBackgroundResource(R.drawable.nu);
        nowtidetime.setBackgroundColor(Color.parseColor(PaleTurquoise));
        nowtidehight.setBackgroundColor(Color.parseColor(PaleTurquoise));

        prevtidetime.setText(DatumTijd.makeNiceTime(waterstanden.get(previousTideIndex).time));
        nowtidetime.setText(DatumTijd.makeNiceTime(tijdString));
        nexttidetime.setText(DatumTijd.makeNiceTime(waterstanden.get(nextTideIndex).time));

        prevtidehight.setText(waterstanden.get(previousTideIndex).val);
        nowtidename.setText("NU");
        nexttidehight.setText(waterstanden.get(nextTideIndex).val);

    }


}