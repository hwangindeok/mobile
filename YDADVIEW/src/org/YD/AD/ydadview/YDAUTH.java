package org.YD.AD.ydadview;

import java.io.IOException;
import java.io.InputStream;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * Page Sliding
 *
 * @author Mike
 */
//public class YDAD2 extends FrameLayout{
public class YDAUTH extends LinearLayout{

	boolean isPageOpen = true;
	private String gameID;
	private String androidVersion = null;
	private String local = null;
	private String displayInfo = null;
	private String packageName = null;
	private String packageVersion = null;
	private int width = 0;
	private int height = 0;
	private int webViewHeight = 0;
	private int webViewWidth = 0;

	private Context e;

   
	WebView yDAuthView = null;
	float DPSCALE =  getResources().getDisplayMetrics().density;
		
	public YDAUTH(Context context){
		
		super(context);
		e = null;
		e = context;
	}
	
	
	public void setGameID(String pGameID)
    {
    	gameID = pGameID;
    }
	
	 public void start()
	 {
		 adroidInfo();
		 uIConfiguration();
	
	
	 }
	 
	 
	private void uIConfiguration(){
    	
    	 
    	 this.setGravity(Gravity.CENTER_HORIZONTAL);
    	    	 
    	 
    	 if(width < height){
    		 webViewWidth    = width;
    		 webViewHeight   = height;

    	 }else{
    		 webViewWidth    = width;
    		 webViewHeight   = height;

    	 } 	 
    
    	 
       	 LinearLayout.LayoutParams  _linearLayout  = new LinearLayout.LayoutParams(
    			 LinearLayout.LayoutParams.MATCH_PARENT,
    			 LinearLayout.LayoutParams.MATCH_PARENT);
          
         
      
       	yDAuthView = new WebView(this.getContext());
       	yDAuthView.setId(1);
       	yDAuthView.setHorizontalScrollBarEnabled(false);
       	yDAuthView.setVerticalScrollBarEnabled(false);
         WebSettings webSettings = yDAuthView.getSettings();
         webSettings.setSavePassword(false);
         webSettings.setSaveFormData(false);
         webSettings.setJavaScriptEnabled(true);
         webSettings.setRenderPriority(RenderPriority.HIGH);
         webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
         webSettings.setSupportZoom(false);
                
         //Log.d("",androidVersion);
              
         
         //yDADView.loadUrl("http://pcbang.ncucu.com/test_widthheight.asp?gameid="+gameID+"&width="+webImageWidth+"&height="+webImageHeight);
         
         //yDAuthView.loadUrl("http://pcbang.ncucu.com/test.asp?gameid="+gameID+"&v="+androidVersion);
         yDAuthView.loadUrl("http://m.naver.com");
       
         yDAuthView.setWebViewClient(new WebViewClient() {
              public void onReceivedError(WebView view, int errorCode, String description, String fallingUrl) {
                  //Toast.makeText(this, "로딩오류"+description, Toast.LENGTH_SHORT).show();
              }
       
              public boolean shouldOverrideUrlLoading(WebView view, String url) {
           
            	  Log.d("test",url);
          	  
            	  if(url.startsWith("http://")){
            		
            		  Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                	                  	                	  
                	  try
                      {
                          if(myIntent != null)
                          {
                        	  e.startActivity(myIntent);
                          }
                      }
                      catch(ActivityNotFoundException activitynotfoundexception){}
            
            		  
               	  }else{
            	    view.loadUrl(url);
            	  }
            	 
            	  return true;
           	  }
         

              
              public void onPageStarted(WebView view, String url, Bitmap favicon) {
             
              } 
              
              public void onPageFinished(WebView view, String url) {
            
            	   
              }
              
          });
           

         yDAuthView.setLayoutParams(_linearLayout);
         this.addView(yDAuthView);
           
       
    }
    
 
        
    
    private void adroidInfo()
    {
    	//Log.d("",android.os.Build.VERSION.RELEASE);
        TelephonyManager telephonymanager = (TelephonyManager)this.getContext().getSystemService("phone");
        Display display = ((WindowManager)this.getContext().getSystemService("window")).getDefaultDisplay();
        
        //androidVersion = telephonymanager.getNetworkOperatorName()+android.os.Build.VERSION.RELEASE;
        androidVersion = android.os.Build.VERSION.RELEASE;
        local =        this.getContext().getResources().getConfiguration().locale.toString();
        height = display.getHeight();
        width =  display.getWidth();
        
        displayInfo = width+"/"+height;
      
        
        try
        {
            PackageInfo packageinfo = this.getContext().getPackageManager().getPackageInfo(this.getContext().getPackageName(), 0);
            packageName = packageinfo.packageName;
            packageVersion = packageinfo.versionName;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
        }
        
        Log.d("",local+" "+androidVersion+" "+displayInfo+" "+packageName+" "+packageVersion);
    }
    
    
    public void destroy()
    {
    	yDAuthView = null;

    }
    
  
}