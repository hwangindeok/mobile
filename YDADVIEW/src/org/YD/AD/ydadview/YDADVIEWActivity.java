package org.YD.AD.ydadview;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Page Sliding
 *
 * @author Mike
 */
public class YDADVIEWActivity extends Activity {

	YDAD yDAD = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setBackgroundColor(Color.GREEN);
         
        yDAD = new YDAD(this);
        yDAD.setGameID("tiw");
        yDAD.start();
        
        linearLayout.addView(yDAD, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        
        
        /**
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.WRAP_CONTENT,
        		LinearLayout.LayoutParams.WRAP_CONTENT);
        
        Button button1 = new Button(this);
        button1.setText("Button 01");
        button1.setLayoutParams(params);
        linearLayout.addView(button1);
        */

        setContentView(linearLayout);
        
    }

   

}