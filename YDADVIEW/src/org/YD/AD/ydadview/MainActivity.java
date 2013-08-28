package org.YD.AD.ydadview;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final int REQUEST_LOGIN  = 1001;
	public static final int REQUEST_LOGOUT = 2001;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		   LinearLayout linearLayout = new LinearLayout(this);
		   linearLayout.setOrientation(LinearLayout.VERTICAL);
		   LinearLayout.LayoutParams  _linearLayout  = new LinearLayout.LayoutParams(
		   LinearLayout.LayoutParams.MATCH_PARENT,
		   LinearLayout.LayoutParams.WRAP_CONTENT);
		   Button startBtn = new Button(this);
		   startBtn.setText("Login");
		   startBtn.setLayoutParams(_linearLayout);
		   Button startBtn2 = new Button(this);
		   startBtn2.setText("Logout");
		   startBtn2.setLayoutParams(_linearLayout);
		   linearLayout.addView(startBtn);
		   linearLayout.addView(startBtn2);
		   setContentView(linearLayout);
	        startBtn.setOnClickListener(new OnClickListener() {
	        	public void onClick(View v) {
	        		Toast.makeText(getApplicationContext(), "start button click.", 100).show();
	
	        		Intent myIntent = new Intent(getApplicationContext(), AuthActivity.class);
	        		myIntent.putExtra("userno", "50121");
	        		myIntent.putExtra("gameid", "tiw");
	        		startActivityForResult(myIntent, REQUEST_LOGIN);
	
	        	}
	        });	        
	        startBtn2.setOnClickListener(new OnClickListener() {
	        	public void onClick(View v) {
	        		Toast.makeText(getApplicationContext(), "start button click.", 500).show();
	
	        		Intent myIntent = new Intent(getApplicationContext(), LogOutActivity.class);
	        		myIntent.putExtra("userno", "50121");
	        		myIntent.putExtra("charactername", "ÁÖÇÏÂ¯");
	        		startActivityForResult(myIntent, REQUEST_LOGOUT);
	
	        	}
	        });
		
		}
		
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_LOGIN) {
			Toast toast = Toast.makeText(getBaseContext(), "onActivityResult called with code : " + resultCode, Toast.LENGTH_LONG);
			toast.show();
			

			if (resultCode == 1002) {
				String userid = data.getExtras().getString("userid");
				String userno = data.getExtras().getString("userno");
				toast = Toast.makeText(getBaseContext(), " Login userid : " + userid + " , Login userno : " + userno, Toast.LENGTH_LONG);
				toast.show();
		
			}

		}
		
		if (requestCode == REQUEST_LOGOUT) {
			Toast toast = Toast.makeText(getBaseContext(), "onActivityResult called with code : " + resultCode, Toast.LENGTH_LONG);
			toast.show();
			
			if (resultCode == 2002) {
				String userid = data.getExtras().getString("userid");
				String userno = data.getExtras().getString("userno");
				toast = Toast.makeText(getBaseContext(), " Logout " + userid , Toast.LENGTH_LONG );
				toast.show();
		
			}

		}

	}



}
