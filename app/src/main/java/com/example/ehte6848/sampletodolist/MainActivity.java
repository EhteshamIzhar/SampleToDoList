package com.example.ehte6848.sampletodolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Button;

import java.util.ArrayList;

import static android.R.attr.start;
import static android.R.attr.value;
import static android.os.Build.VERSION_CODES.N;


public class MainActivity extends AppCompatActivity {
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvitems;
    public final static String ID_EXTRA = "com.example.ehte6848.sampletodolist._ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create a list
        lvitems = (ListView) findViewById(R.id.lvItems);
        items  = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        lvitems.setAdapter(itemsAdapter);
        items.add("First Item of the list");
        items.add("Second Item of the list");
        setupListViewListener();
    }
    //defining ListViewListener
    private void setupListViewListener(){
        lvitems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,NewActivity.class);
                /*Object obj = this.getListAdapter().getItem(position);
                String value= obj.toString();*/
                i.putExtra(ID_EXTRA,items.get(position));
                startActivity(i);
            }
        });
    }

    public void onAddItem(View view)
    {
        EditText etext;
        etext = (EditText)findViewById(R.id.editText);
        String text = etext.getText().toString();
        if(text!="") {
            itemsAdapter.add(text);
            etext.setText("");
        }
    }
    public void onDeleteItem(View view){
        EditText etext;
        int pos;
        etext = (EditText)findViewById(R.id.editText);
        String text = etext.getText().toString();
        if(isInteger(text)) {
            pos = Integer.parseInt(text);
            int lastPosition = lvitems.getChildCount()-1;
            if(pos<=lastPosition){
            items.remove(pos);
            etext.setText("");
            itemsAdapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(this, "Wrong position for deleting", Toast.LENGTH_LONG).show();
                etext.setText("");
                itemsAdapter.notifyDataSetChanged();
            }
        }
        else {
            Toast.makeText(this, "Wrong position for deleting", Toast.LENGTH_LONG).show();
            etext.setText("");
            itemsAdapter.notifyDataSetChanged();
        }
    }
    //check if it is an integer
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
