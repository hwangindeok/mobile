package org.YD.AD.ydadview;
import android.app.Activity;
import android.os.Bundle;

public class WebViewXmlActivity extends Activity {

	//private static final String TAG = "SampleWebViewActivity";


	private YDADView yDADView = null;
	public void onCreate(Bundle icicle) {
         super.onCreate(icicle);
         //setContentView(R.layout.main);
           
         setContentView(R.layout.main);
         yDADView = (YDADView) findViewById(R.id.adview1);
         //yDADView.setChannelID("idhwang");
         //yDADView.start();
         
         
     }
	
	

	protected void onDestroy() {
		super.onDestroy();
		if (yDADView != null) {
			yDADView.destroy(); // 
			yDADView = null;
		}
	}
	


}
