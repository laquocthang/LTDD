package vn.edu.itdlu.a1610207.calculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class activity_data extends AppCompatActivity implements TextWatcher,AdapterView.OnItemSelectedListener {

    Spinner spinner1, spinner2;
    EditText editText1, editText2;
    ArrayList<String> listdata;
    CoreFunctions functions = new CoreFunctions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        map();
        loadSpinner();
        addTextChangeListener();
        addItemSelectedListener();
    }

    void map() {
        spinner1 = findViewById(R.id.spinner_data_1);
        spinner2 = findViewById(R.id.spinner_data_2);
        editText1 = findViewById(R.id.et_data_1);
        editText2 = findViewById(R.id.et_data_2);
    }

    void array2List() {
        String[] temp = functions.Data;
        listdata = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            if (i % 2 == 0)
                listdata.add(temp[i]);
        }
    }

    void loadSpinner() {
        array2List();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_row, listdata);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
    }

    void addTextChangeListener() {
        editText1.addTextChangedListener(this);
        editText2.addTextChangedListener(this);
    }

    void addItemSelectedListener() {
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
