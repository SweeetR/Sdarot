package com.example.sdarot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


/**
 * @author Roy Tendler
 * @since 16/11/2019
 */
public class MainActivity extends AppCompatActivity {

    RadioButton rb1;
    EditText ed1,ed2;
    boolean tf=true;
    double member,d ;
    String memberS,dS,title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
    }

    /**
     * @since 16/11/2019
     * Moving to the next activity with  the information that was entered
     */

    public void Next(View view) {
        if (rb1.isChecked()){
            tf = true;
        }
        else{
            tf= false;
        }
        memberS= ed1.getText().toString();
        dS = ed2.getText().toString();
        if (memberS.equals("")||dS.equals("")||memberS.equals("+")||dS.equals("+")||memberS.equals("-")||dS.equals("-")||dS.equals(".")||memberS.equals(".")){
            Toast.makeText(this, "Wrong input", Toast.LENGTH_SHORT).show();

        }
        else{
            Intent si = new Intent(this,Series.class);
            member=Double.parseDouble(memberS);
            d=Double.parseDouble(dS);
            si.putExtra("tf",tf);
            si.putExtra("member",member);
            si.putExtra("d",d);
            startActivity(si);
        }




    }

    /**
     * Creates a new option menu with credits
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,"Credits");
        return true;
    }

    /**
     * moves to the credits activity when pressed in the option menu
     */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        title = item.getTitle().toString();
        if(title.equals("Credits")){
            Intent si = new Intent(this,Credits.class);
            startActivity(si);
        }
        return true;
    }
}
