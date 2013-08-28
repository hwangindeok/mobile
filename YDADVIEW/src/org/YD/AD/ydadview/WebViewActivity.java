package org.YD.AD.ydadview;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

public class WebViewActivity extends Activity {

	//private static final String TAG = "SampleWebViewActivity";
	YDAD yDAD = null;

	//YDADView yDADView = null;
	public void onCreate(Bundle icicle) {
         super.onCreate(icicle);
         //setContentView(R.layout.main);
         LinearLayout linearLayout = new LinearLayout(this);
         
         linearLayout.setBackgroundColor(Color.GREEN);
         yDAD = new YDAD(this);
         yDAD.setGameID("tiw");
         yDAD.setMarketID("TS");
         yDAD.start();
         linearLayout.addView(yDAD, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
         setContentView(linearLayout);
         
         
         
     }
	
	
	protected void onDestroy() {
		super.onDestroy();
		if (yDAD != null) {
			yDAD.destroy(); // 7
			yDAD = null;
		}
	}
	


}
