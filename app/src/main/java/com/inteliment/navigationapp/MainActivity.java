package com.inteliment.navigationapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.inteliment.navigationapp.adapter.SpinnerAdapter;
import com.inteliment.navigationapp.asynctask.CallWebService;
import com.inteliment.navigationapp.dialog.ConnectionErrorDialog;
import com.inteliment.navigationapp.genric.ConnectionLookUP;
import com.inteliment.navigationapp.model.Fromcentral;
import com.inteliment.navigationapp.model.SampleJsonModel;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<SampleJsonModel> sampleList=null;
    Spinner spinner;
    SampleJsonModel simpModel=null;
    int Selectedposition=0;
    Toolbar toolbar;

    @Override
    public void onSaveInstanceState(Bundle outState) {

        if(null!=simpModel) {
            outState.putInt("position",Selectedposition);
            outState.putParcelable("obj", simpModel);
            outState.putParcelableArrayList("objlist", sampleList);
        }
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(null!=savedInstanceState){
            simpModel=savedInstanceState.getParcelable("obj");
            sampleList=savedInstanceState.getParcelableArrayList("objlist");
            Selectedposition=savedInstanceState.getInt("position");
        }
        setContentView(R.layout.main_activity_layout);
        toolbar=((Toolbar)findViewById(R.id.toolbar));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        toolbar.setTitle(getResources().getString(R.string.navigation_app));
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            initView();
        } catch (IOException e) {
            Toast.makeText(MainActivity.this,"Error in loading",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void initView() throws IOException {
        spinner= (Spinner) findViewById(R.id.spinner);
        ((Button)findViewById(R.id.button)).setEnabled(false);
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class).putExtra("OBJ", simpModel));
            }
        });

        if(null==sampleList){

            if (ConnectionLookUP.isInternetConnectionActive(MainActivity.this)) {
                new CallWebService(MainActivity.this).execute();
            } else {
                ConnectionErrorDialog connectionErrorDialog = new ConnectionErrorDialog();
                connectionErrorDialog.show(getFragmentManager(), connectionErrorDialog.getClass().getName());
            }
        }else{

            loadAdapter();
            if(null!=simpModel){
                updateViewonSelection(Selectedposition);
            }
        }


    }


    public void loadSpinner(final String s) {

        String result=s;
        System.out.println(result);

        if(null!=result) {
            Type listType = new TypeToken<ArrayList<SampleJsonModel>>() {
            }.getType();
            sampleList = new Gson().fromJson(result.toString(), listType);
            sampleList.add(0,new SampleJsonModel(0,"Select Location"));
            loadAdapter();

        }
    }

    private void updateViewonSelection(int position){
        spinner.setSelection(position);

        simpModel=position>0?sampleList.get(position):null;
        if(null!=simpModel){

            Fromcentral fromcentral=simpModel.getFromcentral();
            Selectedposition=position;
            String str=null!=fromcentral.getCar()?"Car :"+fromcentral.getCar()+"\n\n":"";
            str+=null!=fromcentral.getTrain()?"Train :"+fromcentral.getTrain():"";

            ((TextView)findViewById(R.id.textView2)).setText(str);
            ((Button)findViewById(R.id.button)).setEnabled(true);

        }else {
            ((TextView) findViewById(R.id.textView2)).setText("");
            ((Button)findViewById(R.id.button)).setEnabled(false);
        }

    }

    public void loadAdapter(){
        SpinnerAdapter spinnerAdapter=new SpinnerAdapter(sampleList);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                updateViewonSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
