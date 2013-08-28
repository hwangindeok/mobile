package org.YD.AD.ydadview;

import java.io.IOException;
import java.io.InputStream;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
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
public class YDAD extends LinearLayout{

	boolean isPageOpen = true;
	private String gameID;
	private String marketID;
	private String androidVersion = null;
	private String local = null;
	private String displayInfo = null;
	private String packageName = null;
	private String packageVersion = null;
	private double width = 0;
	private double height = 0;
	private double webViewHeight = 0;
	private double webViewWidth = 0;
	private double imgButtonHeight = 0;
	private double imgButtonWidth = 0;
	private double webImageHeight = 0;
	private double webImageWidth = 0;
	private Context e;

    TranslateAnimation tAnimationDown;
    TranslateAnimation tAnimationUp;

	RelativeLayout slidingPage01;
	RelativeLayout slidingPage02;
	FrameLayout    framelayout;
	ImageView imageview01;
	ImageView imageview02;
	WebView yDADView = null;
	Boolean TabletYN = false;
	float DPSCALE =  getResources().getDisplayMetrics().density;
	//float DPSCALE =  1;

		
	public YDAD(Context context){
		
		super(context);
		e = null;
		e = context;
		TabletYN = isTablet(context);
		//Log.d("test",TabletYN.toString());
		
	}
	
	
	public void setGameID(String pGameID)
    {
    	gameID = pGameID;
    }
	
	
	public void setMarketID(String pMarketID)
    {
		marketID = pMarketID;
    }
	
	
	 public void start()
	 {
		 //Log.d("test","start");    
		 adroidInfo();
		 uIConfiguration();
	     bannerAnimate();
	
	 }
	 
	 static boolean isTablet (Context context) { 
	        int xlargeBit = 4; // Configuration.SCREENLAYOUT_SIZE_XLARGE;  // upgrade to HC SDK to get this 
	        Configuration config = context.getResources().getConfiguration(); 
	        return (config.screenLayout & xlargeBit) == xlargeBit; 
     } 

	
	 
	private void uIConfiguration(){
    	
    	 
    	 this.setGravity(Gravity.CENTER_HORIZONTAL);
    	 framelayout = new FrameLayout(this.getContext());
    	 //Log.d("test",TabletYN.toString());
    	 //핸드폰이 세로모드 일때
    	 if(width < height){
    		 webViewWidth    = width;
    		 webViewHeight   = (double)(width/3.55);
    	  	 //imgButtonWidth  = (double)(width/4.15);
    		 //imgButtonWidth  = (double)(width/4.15);
    		 imgButtonWidth  = (double)(width/2.60);
        	 imgButtonHeight = (double)(imgButtonWidth/4.92); 
        	// Log.d("test","1");
        	 
        	 //타블릿일 경우
        	 //if(androidVersion.startsWith("3.")){
        	 if(TabletYN){
        		 webViewWidth    =  width - (width/2.8);
        		 webViewHeight   = (double)(webViewWidth/3.6);
        	  	 //imgButtonWidth  = (double)(webViewWidth/3.8);
        		 imgButtonWidth  = (double)(webViewWidth/2.60);
            	 imgButtonHeight = (double)(imgButtonWidth/5.92); 
            	// Log.d("test","2");
        		 
            	 
        	 }
   	 
        	 
    	 //핸드폰이 가로모드 일때
    	 }else{
    		 webViewWidth    = width - (width/2.3);  
    		 webViewHeight   = (double)(webViewWidth/3.55) - 1 ;
    		 //imgButtonWidth  = (double)(webViewWidth/4.15);
    		 imgButtonWidth  = (double)(webViewWidth/2.60);
        	 imgButtonHeight = (double)(imgButtonWidth/4.92); 
        	 //Log.d("test","3");
        	 //Log.d("test",androidVersion);
        	 
        	 //타블릿일 경우
        	 //if(androidVersion.startsWith("3.")){
        	 if(TabletYN){
         		 webViewWidth    = width - (width/1.7);  
        		 webViewHeight   = (double)(webViewWidth/3.6) - 1 ;
        		 //imgButtonWidth  = (double)(webViewWidth/3.8);
        		 imgButtonWidth  = (double)(webViewWidth/2.60);
            	 imgButtonHeight = (double)(imgButtonWidth/5.92); 
            	 //Log.d("test","4");
        	 }
        	 
    	 }
    	 
    	 
    	 Log.d("test","webViewWidth: "+webViewWidth);
    	 Log.d("test","webViewHeight: "+webViewHeight);
    	 Log.d("test","imgButtonWidth: "+imgButtonWidth);
    	 Log.d("test","imgButtonHeight: "+imgButtonHeight);
    	   
         slidingPage01 = new RelativeLayout(this.getContext());
         RelativeLayout.LayoutParams relative1Layout = new RelativeLayout.LayoutParams(
         		RelativeLayout.LayoutParams.WRAP_CONTENT,
         		(int)(webViewHeight+imgButtonHeight));
          
         RelativeLayout.LayoutParams relative2Layout = new RelativeLayout.LayoutParams(
        		  RelativeLayout.LayoutParams.WRAP_CONTENT,
           		  (int)imgButtonHeight);
         
         slidingPage01.setLayoutParams(relative1Layout);
         slidingPage01.setBackgroundColor(Color.TRANSPARENT);
               
         //slidingPage01.setBackgroundColor(Color.RED);
                 
         RelativeLayout.LayoutParams webViewLayout = new RelativeLayout.LayoutParams(
        		 (int)webViewWidth,
        		 (int)webViewHeight);
         
         RelativeLayout.LayoutParams Img01Layout = new RelativeLayout.LayoutParams(
        		 (int)imgButtonWidth,
        		 (int)imgButtonHeight);
         
         RelativeLayout.LayoutParams Img02Layout = new RelativeLayout.LayoutParams(
        		 (int)imgButtonWidth,
        		 (int)imgButtonHeight);
      
         yDADView = new WebView(this.getContext());
         yDADView.setId(1);
         yDADView.setHorizontalScrollBarEnabled(false);
         yDADView.setVerticalScrollBarEnabled(false);
         WebSettings webSettings = yDADView.getSettings();
         webSettings.setSavePassword(false);
         webSettings.setSaveFormData(false);
         webSettings.setJavaScriptEnabled(true);
         webSettings.setRenderPriority(RenderPriority.HIGH);
         //webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
         webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
         webSettings.setSupportZoom(false);
                
         //Log.d("",androidVersion);
         webImageWidth = (int)(webViewWidth / DPSCALE);
         
         //if(androidVersion.equals("2.3.3")){
       //if(androidVersion.indexOf("2.3") > -1){
         if(androidVersion.startsWith("2.")){
        	 webImageHeight = (int)((webViewHeight+2)/DPSCALE);
         }else{
        	 webImageHeight = (int)(webViewHeight /DPSCALE);
         }
         
         //yDADView.loadUrl("http://pcbang.ncucu.com/test_widthheight.asp?gameid="+gameID+"&width="+webImageWidth+"&height="+webImageHeight);
         
         yDADView.loadUrl("http://webview.ncucu.com/banner/Index/gameid/"+gameID+"/marketid/"+marketID+"/widht/"+webImageWidth+"/height/"+webImageHeight+"/v/"+androidVersion);
              
         Log.d("test","gameID: "+gameID);
         Log.d("test","marketid: "+marketID);

         yDADView.setWebViewClient(new WebViewClient() {
              public void onReceivedError(WebView view, int errorCode, String description, String fallingUrl) {
                  //Toast.makeText(this, "로딩오류"+description, Toast.LENGTH_SHORT).show();
              }
       
              public boolean shouldOverrideUrlLoading(WebView view, String url) {
           
            	  //Log.d("test",url);
          	  
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
            	  //Log.d("test","2");
               	  slidingPage01.setVisibility(View.GONE);
              } 
              
              public void onPageFinished(WebView view, String url) {
            	  //Log.d("test","3");
            	  slidingPage01.setVisibility(View.VISIBLE);
            	   
              }
              
          });
         
           
         webViewLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);   
         yDADView.setLayoutParams(webViewLayout);
         slidingPage01.addView(yDADView);
          
        
         imageview01 = new ImageView(this.getContext()); 
         Img01Layout.addRule(RelativeLayout.CENTER_HORIZONTAL);
         Img01Layout.addRule(RelativeLayout.BELOW, 1);
         
         imageview01.setLayoutParams(Img01Layout);
         slidingPage01.addView(imageview01);
         slidingPage01.setVisibility(View.GONE);
         
         
         framelayout.addView(slidingPage01);

        
         slidingPage02 = new RelativeLayout(this.getContext());
         slidingPage02.setBackgroundColor(Color.TRANSPARENT);
         //slidingPage02.setBackgroundColor(Color.CYAN);
         slidingPage02.setLayoutParams(relative2Layout);
            
         imageview02 = new ImageView(this.getContext()); 
         Img02Layout.addRule(RelativeLayout.CENTER_HORIZONTAL);
         imageview02.setLayoutParams(Img02Layout);
           
         imageViewLoad();
         slidingPage02.setVisibility(View.GONE);
         framelayout.addView(slidingPage02);
         this.addView(framelayout);
       
    }
    
 
    private void bannerAnimate(){
    	tAnimationDown = new TranslateAnimation(0,0,(int)(-webViewHeight-imgButtonHeight),0);
    	tAnimationDown.setFillAfter(true);
		//Log.d("ta", webViewHeight+"  "+imgButtonHeight);
		tAnimationDown.setDuration(200);
		         
		tAnimationUp = new TranslateAnimation(0,0,0,(int)(-webViewHeight));
		tAnimationUp.setFillAfter(true);
		
		tAnimationUp.setDuration(200);
		  
		SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
		tAnimationDown.setAnimationListener(animListener);
		tAnimationUp.setAnimationListener(animListener);
		  
		imageview02.setOnClickListener(new OnClickListener() {
			 public void onClick(View v) {
				 
				 slidingPage02.removeAllViews();
		     	 slidingPage01.addView(yDADView);
				 slidingPage01.addView(imageview01);
				 slidingPage01.startAnimation(tAnimationDown);
		

		  	}
		});
		        
		imageview01.setOnClickListener(new OnClickListener() {
		  	public void onClick(View v) {
		  		slidingPage01.startAnimation(tAnimationUp);
		  	}
		});
    
    }
        
    
    private void imageViewLoad(){
  
 		  InputStream in = null;
 		  InputStream in2 = null;
    	  try{ 
	     		in = getResources().getAssets().open("bt_close.png");
	     	    Bitmap bit=BitmapFactory.decodeStream(in);
	     	    imageview01.setImageBitmap(bit);
	     	    
	     		in = getResources().getAssets().open("bt_open.png");
	     	    Bitmap bit2=BitmapFactory.decodeStream(in);
	     	    imageview02.setImageBitmap(bit2);
	     	   
    	    } catch (IOException e) {
    		    e.printStackTrace();
    		    //Log.d("test","imageViewLoad_fail");
    		} finally {
    		    if(in != null)
    		    	try{
    		    	 in.close();
    		    	}catch(IOException e) {
    	    		    e.printStackTrace();
    	    		}
    		}
    	  
    	  //Log.d("test","imageViewLoad");
	  
    }
     
    private class SlidingPageAnimationListener implements AnimationListener {

		public void onAnimationEnd(Animation animation) {
			if (isPageOpen) {
				slidingPage01.removeAllViews();
				slidingPage02.addView(imageview02);
				slidingPage02.setVisibility(View.VISIBLE);
				isPageOpen = false;
				
			} else {
				 
				slidingPage01.setVisibility(View.VISIBLE);
				isPageOpen = true;
				
			}
		}

		public void onAnimationRepeat(Animation animation) {

		}

		public void onAnimationStart(Animation animation) {
			
		}

    }
    
    
    private void adroidInfo()
    {
    	//Log.d("",android.os.Build.VERSION.RELEASE);
        TelephonyManager telephonymanager = (TelephonyManager)this.getContext().getSystemService("phone");
        Display display = ((WindowManager)this.getContext().getSystemService("window")).getDefaultDisplay();
        
        //androidVersion = telephonymanager.getNetworkOperatorName()+android.os.Build.VERSION.RELEASE;
        androidVersion = android.os.Build.VERSION.RELEASE;
        local  =  this.getContext().getResources().getConfiguration().locale.toString();
        height =  display.getHeight();
        width  =  display.getWidth();
        
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
        
        //Log.d("test","adroidInfo");   
        
        //Log.d("test",local+" "+androidVersion+" "+displayInfo+" "+packageName+" "+packageVersion);
    }
    
    
    public void destroy()
    {
    	yDADView = null;
    	//imageview01 = null;
    	//imageview02 = null;
    	//slidingPage01 = null;
    	//slidingPage02 = null;
    }
      
}