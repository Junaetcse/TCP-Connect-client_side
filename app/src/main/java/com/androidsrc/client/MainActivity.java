package com.androidsrc.client;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends Activity {

	TextView response;
	EditText editTextAddress, editTextPort;
	Button buttonConnect, btn_tea,btn_coffee,btn_water,btn_breakfast,btn_juice;
	String mainMessage;

	//For time handler

	private Calendar calendar;
	private String format = "";
	Handler handler = new Handler();
	private Runnable timerRunnable = new Runnable() {
		@Override
		public void run() {
			method();
			handler.postDelayed(timerRunnable, 10000);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		handler.postDelayed(timerRunnable, 10000);

		//editTextAddress = (EditText) findViewById(R.id.addressEditText);
		//editTextPort = (EditText) findViewById(R.id.portEditText);
		buttonConnect = (Button) findViewById(R.id.btn_general);
		btn_tea = (Button) findViewById(R.id.btn_tea);
		btn_coffee = (Button) findViewById(R.id.btn_coffee);
		btn_water = (Button) findViewById(R.id.btn_water);
		btn_breakfast = (Button) findViewById(R.id.btn_breakfast);
		btn_juice = (Button) findViewById(R.id.btn_juice);
		response = (TextView) findViewById(R.id.responseTextView);

		final String IP  = Common.getPreference(MainActivity.this).getString(Common.ip,"");
		final String name  = Common.getPreference(MainActivity.this).getString(Common.name,"");

		Log.i("IP:","="+IP);
		buttonConnect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ExecuteTask(IP,Common.general+":"+name);
			}
		});
		btn_tea.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ExecuteTask(IP,Common.tea+":"+name);
			}
		});
		btn_coffee.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ExecuteTask(IP,Common.coffe+":"+name);
			}
		});
		btn_water.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ExecuteTask(IP,Common.water+":"+name);
			}
		});
		btn_breakfast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ExecuteTask(IP,Common.breakfast+":"+name);
			}
		});
		btn_juice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ExecuteTask(IP,Common.juice+":"+name);
			}
		});
		//just a comment for testing

		/*buttonClear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				response.setText("");
			}
		});*/
	}



	private void ExecuteTask(String IP,String msg){
		Client myClient = new Client(IP, Common.port, response,msg);
		myClient.execute();
	}




	private void method(){
		calendar = Calendar.getInstance();
		System.currentTimeMillis();

		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		if (hour == 0) {
			hour += 12;
			format = "AM";
		} else if (hour == 12) {
			format = "PM";
		} else if (hour > 12) {
			hour -= 12;
			format = "PM";
		} else {
			format = "AM";
		}

		/*time.setText(new StringBuilder().append(hour).append(" : ").append(min)
				.append(" ").append(format));*/

		if (hour==11&&min==33){
			// Toast.makeText(getApplicationContext(),"yes",Toast.LENGTH_LONG).show();
			btn_breakfast.setVisibility(View.INVISIBLE);
		}
		if (hour==11&&min==34){
			// Toast.makeText(getApplicationContext(),"yes",Toast.LENGTH_LONG).show();
			btn_breakfast.setVisibility(View.VISIBLE);
		}
	}
}
