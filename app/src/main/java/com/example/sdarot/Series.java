package com.example.sdarot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Series extends AppCompatActivity implements AdapterView.OnItemLongClickListener,View.OnCreateContextMenuListener{

    Intent gi;
    boolean tf;
    int pos;
    double a1, d;
    Double[] series = new Double[20];
    ListView lv1;
    TextView tv1;
    String op,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        lv1 = findViewById(R.id.lv1);
        tv1 = findViewById(R.id.tv1);
        lv1.setOnItemLongClickListener(this);
        gi = getIntent();
        a1 = gi.getDoubleExtra("member", 859324384.0);
        d = gi.getDoubleExtra("d", 343256.0);
        tf = gi.getBooleanExtra("tf",true);
        if (tf) {
            for (int i = 0; i < series.length; i++) {
                series[i] = a1 + ((i+1) - 1) * d;
            }
        } else {

            for (int i = 0; i < series.length; i++) {
                series[i] = a1 * Math.pow(d, (i+1) - 1);
            }
        }
        ArrayAdapter<Double> adp = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,series);
        lv1.setOnCreateContextMenuListener(this);
        lv1.setAdapter(adp);
    }

    /**
     * Creates a context menu
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Options");
        menu.add("Position");
        menu.add("Sum");
    }

    /**
     * Puts the sum or the position depends on what the user chose
     */

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        double sum=0.0;
        op = item.getTitle().toString();
        if (op.equals("Position")){
            tv1.setText("The position is:" + " " +( pos+1));
        }
        else{
            for (int k=0;k<=pos;k++){
                sum=series[k]+sum;
            }
            tv1.setText("The sum is:"+" "+sum);
        }

        return true;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        pos=position;
        return false;
    }

    public void back(View view) {
        finish();
    }


    /**
     *Creates an option menu with credits
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
        title=item.getTitle().toString();
        if (title.equals("Credits")){
            Intent si = new Intent(this,Credits.class);
            startActivity(si);

        }
        return true;
    }
}
