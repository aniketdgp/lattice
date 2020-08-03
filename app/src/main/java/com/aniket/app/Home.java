package com.aniket.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Home extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;

    TextView ScreenName , AvailableDevices;
    private ListView paired_device;
    private Button Pair;
    ListView available_device;
    private ArrayAdapter aAdapter;
    private BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ScreenName = findViewById(R.id.screen_name);
        AvailableDevices = findViewById(R.id.available_devices);
        available_device = findViewById(R.id.ad_list2);
        Pair = (Button) findViewById(R.id.pairnow);
        ScreenName.setText("Paired Devices");


        CheckBlueToothState();




        /*Check Button */
        final Button check = (Button)findViewById(R.id.btnGet);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPairDevice();
                ScreenName.setVisibility(View.VISIBLE);
                ScreenName.setText("Paired Devices");
                AvailableDevices.setVisibility(View.VISIBLE);
                paired_device.setVisibility(View.VISIBLE);
                Pair.setVisibility(View.VISIBLE);
                searchDevices();




            }
        });




    }

    /*Check Bluetooth State*/
    private void CheckBlueToothState(){
        if (bAdapter == null){
            ScreenName.setText("Bluetooth NOT support");
        }else{
            if (bAdapter.isEnabled()){
                if(bAdapter.isDiscovering()){
                    ScreenName.setText("Bluetooth is currently in device discovery process.");
                }else{
                    ScreenName.setText("Bluetooth is Enabled.");
                    // check.setEnabled(true);
                }
            }else{
                ScreenName.setText("Bluetooth is NOT Enabled!");
                ScreenName.setVisibility(View.GONE);
                AvailableDevices.setVisibility(View.GONE);
                Pair.setVisibility(View.GONE);

                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }



    /*Check Paired Devices*/
    void checkPairDevice(){
        if(bAdapter==null){
            Toast.makeText(getApplicationContext(),"Bluetooth Not Supported", Toast.LENGTH_SHORT).show();
        }
        else{
            Set<BluetoothDevice> pairedDevices = bAdapter.getBondedDevices();
            ArrayList list = new ArrayList();

            if(pairedDevices.size()>0){
                for(BluetoothDevice device: pairedDevices){
                    String devicename = device.getName();
                    list.add(devicename);
                }
                paired_device = (ListView) findViewById(R.id.ad_list);
                aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                paired_device.setAdapter(aAdapter);

            }
        }
    }





    /*Check Available Devices*/

    void searchDevices() {


    }





}