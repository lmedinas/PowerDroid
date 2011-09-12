package org.lmedinas.powerdroid;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PDInfo extends Activity {
	private TextView batterylevel;
	private TextView batteryhealth;
	private TextView batteryvoltage;
	private TextView batterystatus;
	private TextView batterytemperature;
	private TextView batterytype;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        
        batterylevel = (TextView)findViewById(R.id.textView_level);
        batteryhealth = (TextView)findViewById(R.id.textView_health);
        batteryvoltage = (TextView)findViewById(R.id.textView_voltage);
        batterystatus = (TextView)findViewById(R.id.textView_status);
        batterytemperature = (TextView)findViewById(R.id.textView_temperature);
        batterytype = (TextView)findViewById(R.id.textView_type);
        
        this.registerReceiver(this.myBatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        
    }
    
    private BroadcastReceiver myBatteryReceiver = new BroadcastReceiver()
    {

    	@Override
    	public void onReceive(Context arg0, Intent arg1) {
    		// TODO Auto-generated method stub

    		batterylevel.setText("Battery Level: " + String.valueOf(arg1.getIntExtra("level", 0)) + " %");
    		
    		batteryvoltage.setText("Voltage: " + String.valueOf((float)arg1.getIntExtra("voltage", 0)/1000) + "V");

    		batterytemperature.setText("Temperature: " + String.valueOf((float)arg1.getIntExtra("temperature", 0)/10) + " C");
    		
    		batterytype.setText("Type: " + String.valueOf(arg1.getStringExtra("technology")));
    		
    		int health = arg1.getIntExtra("health", BatteryManager.BATTERY_HEALTH_UNKNOWN);

    		String strHealth;
    		if (health == BatteryManager.BATTERY_HEALTH_GOOD){
    			strHealth = "Good";
    		} else if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT){
    			strHealth = "Over Heat";
    		} else if (health == BatteryManager.BATTERY_HEALTH_DEAD){
    			strHealth = "Dead";
    		} else if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE){
    			strHealth = "Over Voltage";
    		} else if (health == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE){
    			strHealth = "Unspecified Failure";
    		} else {
    			strHealth = "Unknown";
    		}
    		
    		batteryhealth.setText("Health: " + strHealth);
    		
    		int status = arg1.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN);
    		String strStatus;
    		if (status == BatteryManager.BATTERY_STATUS_CHARGING){
    			strStatus = "Charging";
    		} else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING){
    			strStatus = "Dis-charging";
    		} else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING){
    			strStatus = "Not charging";
    		} else if (status == BatteryManager.BATTERY_STATUS_FULL){
    			strStatus = "Full";
    		} else {
    			strStatus = "Unknown";
    		}
    		batterystatus.setText("Status: " + strStatus);
    	}
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu , menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.about:
            AboutDialog();
            return true;
        case R.id.exit:
            finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    //@Override
    public void AboutDialog() {
        //set up dialog
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.about);
        dialog.setTitle("PowerDroid v0.1");
        dialog.setCancelable(true);
        //there are a lot of settings, for dialog, check them all out!

        //set up text
        TextView text = (TextView) dialog.findViewById(R.id.AboutTextView1);
        text.setText("This is free software!!\nDeveloped by Luis Medinas <lmedinas@gmail.com>\n");

        //set up image view
        ImageView img = (ImageView) dialog.findViewById(R.id.AboutIcon);
        img.setImageResource(R.drawable.icon);

        //set up button
        Button button = (Button) dialog.findViewById(R.id.AboutCloseButton);
        button.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
               dismissDialog(R.layout.about);
        }
        });
        //now that the dialog is set up, it's time to show it    
        dialog.show();
    }
}