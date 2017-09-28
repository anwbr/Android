package com.example.honor.lab3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    final String filename = "ListRecords";
    private Map<String,String> ListNote;
    private CalendarView simpleCalendarView;
    private Button btnDel;
    private Button btnSave;
    private EditText Note;
    private SimpleDateFormat DateFormat;
    private String selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_calendar);
        getInit();
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = DateFormat.format(new Date(simpleCalendarView.getDate()));
                Note.setText("");
                if(!ListNote.isEmpty()){
                    //Note.setText(ListNote.get(String.valueOf(simpleCalendarView.getDate())));
                    Note.setText(ListNote.get(selectedDate));
                }
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Note.getText().toString().isEmpty() & ListNote.get(selectedDate) == null) {

                    ListNote.put(selectedDate, Note.getText().toString());
                    Toast.makeText(getApplicationContext(), "Created the record for " +  selectedDate , Toast.LENGTH_LONG).show();
                }
                else if (!Note.getText().toString().isEmpty() & ListNote.get(selectedDate) != null){
                    ListNote.put(selectedDate, Note.getText().toString());
                    Toast.makeText(getApplicationContext(), "Changed the record for " +  selectedDate , Toast.LENGTH_LONG).show();
                }

            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Note.getText().toString().isEmpty()) {
                    ListNote.remove(selectedDate);
                    Note.setText("");
                    Toast.makeText(getApplicationContext(), "Deleted the record for " +  selectedDate , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        String json = new Gson().toJson(ListNote);
        writeFile(json);
    }

    void writeFile(String json) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(filename, MODE_PRIVATE)));
            bw.write(json);
            bw.close();
            Toast.makeText(getApplicationContext(),"File written" , Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String readFile() {
        String temp = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(filename)));
            String str = "";
            while ((str = br.readLine()) != null) {
                temp += str;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
    void getInit(){
        ListNote = new HashMap<String,String>();
        String json = readFile();
        if(json != ""){
            Type itemsMapType = new TypeToken<Map<String, String>>() {}.getType();
            ListNote = new Gson().fromJson(json, itemsMapType);
        }
        btnDel = (Button) findViewById(R.id.btnDelete);
        btnSave = (Button) findViewById(R.id.btnSave);
        Note = (EditText) findViewById(R.id.Note);
        simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView);
        DateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }
}
