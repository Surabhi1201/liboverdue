package com.example.liboverdue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    TextView result;
    Button calculate;
    EditText issue;
    EditText retbk;
    final Calendar mycalendar = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        issue=findViewById(R.id.issue);
        result=findViewById(R.id.result);
        calculate=findViewById(R.id.calculate);
        retbk= findViewById(R.id.returnbk);

        DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mycalendar.set(Calendar.YEAR, year);
                mycalendar.set(Calendar.MONTH, month);
                mycalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();



            }
        };
        DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mycalendar.set(Calendar.YEAR, year);
                mycalendar.set(Calendar.MONTH, month);
                mycalendar.set(Calendar.DAY_OF_MONTH, day);
                upLabel();



            }
        };
        issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,date,mycalendar.get(Calendar.YEAR),mycalendar.get(Calendar.MONTH),mycalendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });
        retbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,d,mycalendar.get(Calendar.YEAR),mycalendar.get(Calendar.MONTH),mycalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try {
                   Date date1, date2;
                   SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yy");
                   date1 = dates.parse(issue.getText().toString());
                   date2=dates.parse(retbk.getText().toString());
                   long diff=Math.abs(date1.getTime() - date2.getTime());
                   long diffdate=diff/(24 * 60*60*1000);
                   String daydiff=Long.toString(diffdate);

                   if(Integer.parseInt(daydiff) > 10)
                   {
                        Date newDate=new Date(date1.getTime() + (10*24*60*60*1000));

                        long newdiff= Math.abs(date2.getTime() - newDate.getTime());
                        long newdays=newdiff/(24*60*60*1000);
                       Long penalty=newdays * 15;
                       result.setText("Late Days=" +newdays +"\nPenalty ="+penalty);




                   }


               }
               catch(Exception e)

               {

               }
            }
        });
    }
  private void updateLabel()
  {
      String myFormat="MM/dd/yy";
      SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
      issue.setText(dateFormat.format(mycalendar.getTime()));

  }
    private void upLabel()
    {
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);

        retbk.setText(dateFormat.format(mycalendar.getTime()));
    }



}


