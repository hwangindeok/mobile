package org.YD.AD.ydadview;

import java.io.IOException;
import java.io.InputStream;

import android.R.color;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
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
import android.widget.RelativeLayout;


/**
 * Page Sliding
 *
 * @author Mike
 */
public class YDAD_1 extends FrameLayout{
//public class YDAD extends LinearLayout{

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
	private int imgButtonHeight = 0;
	private int imgButtonWidth = 0;
	private int webImageHeight = 0;
	private int webImageWidth = 0;
	private Context e;

    TranslateAnimation tAnimationDown;
    TranslateAnimation tAnimationUp;

	RelativeLayout slidingPage01;
	RelativeLayout slidingPage02;
	FrameLayout    framelayout;
	ImageView imageview01;
	ImageView imageview02;
	WebView yDADView = null;
	float DPSCALE =  getResources().getDisplayMetrics().density;
		
	public YDAD_1(Context context){
		
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
	     bannerAnimate();
	
	 }
	 
    
	private void uIConfiguration(){
    	
    	 
    	 //this.setGravity(Gravity.CENTER_HORIZONTAL);
    	 webViewHeight   = (int)(width/3.55);
    	 webViewWidth    = width;
    	 imgButtonWidth  = (int)(width/4.15);
    	 imgButtonHeight = (int)(imgButtonWidth/3.08); 
         slidingPage01 = new RelativeLayout(this.getContext());
          RelativeLayout.LayoutParams relative1Layout = new RelativeLayout.LayoutParams(
         		RelativeLayout.LayoutParams.MATCH_PARENT,
         		(int)(webViewHeight+imgButtonHeight));
          
          RelativeLayout.LayoutParams relative2Layout = new RelativeLayout.LayoutParams(
        		  RelativeLayout.LayoutParams.MATCH_PARENT,
           		  imgButtonHeight);
         
         slidingPage01.setLayoutParams(relative1Layout);
         slidingPage01.setBackgroundColor(color.transparent);
         //slidingPage01.setBackgroundColor(color.holo_orange_dark);
                 
         RelativeLayout.LayoutParams webViewLayout = new RelativeLayout.LayoutParams(
        		 RelativeLayout.LayoutParams.MATCH_PARENT,
         		 webViewHeight);
         
         RelativeLayout.LayoutParams Img01Layout = new RelativeLayout.LayoutParams(
         		imgButtonWidth,
         		imgButtonHeight);
         
         RelativeLayout.LayoutParams Img02Layout = new RelativeLayout.LayoutParams(
         		imgButtonWidth,
         		imgButtonHeight);
      
         yDADView = new WebView(this.getContext());
         yDADView.setId(1);
         yDADView.setHorizontalScrollBarEnabled(false);
         yDADView.setVerticalScrollBarEnabled(false);
         WebSettings webSettings = yDADView.getSettings();
         webSettings.setSavePassword(false);
         webSettings.setSaveFormData(false);
         webSettings.setJavaScriptEnabled(true);
         webSettings.setRenderPriority(RenderPriority.HIGH);
         webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
         webSettings.setSupportZoom(false);
                
         //Log.d("",androidVersion);
         webImageWidth = (int)(webViewWidth / DPSCALE);
         
         if(androidVersion.equals("2.3.6")){
        	 webImageHeight = (int)((webViewHeight+2)/DPSCALE);
         }else if(androidVersion.equals("2.3.3")){
        	 webImageHeight = (int)(webViewHeight /DPSCALE);
        	 //webImageWidth = (int)((webViewWidth+10) / DPSCALE);
         }else{
        	 webImageHeight = (int)(webViewHeight /DPSCALE);
         }
         
         //yDADView.loadUrl("http://pcbang.ncucu.com/test_widthheight.asp?gameid="+gameID+"&width="+webImageWidth+"&height="+webImageHeight);
         
         yDADView.loadUrl("http://pcbang.ncucu.com/test.asp?gameid="+gameID+"&width="+webImageWidth+"&height="+webImageHeight+"&v="+androidVersion);
       
         yDADView.setWebViewClient(new WebViewClient() {
              public void onReceivedError(WebView view, int errorCode, String description, String fallingUrl) {
                  //Toast.makeText(this, "로딩오류"+description, Toast.LENGTH_SHORT).show();
              }
              
              
              public boolean shouldOverrideUrlLoading(WebView view, String url) {
            	   
            	  
            	 // Log.d("test",url);
            	             	  
            	  
            	  if(url.startsWith("http://")){
            		
            		  Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                	                  	                	  
                	  try
                      {
                          if(myIntent != null)
                          {
                        	  e.startActivity(myIntent);
                          }
                      }
                      catch(ActivityNotFoundException activitynotfoundexception)
                      {
                         
                      }
            
            		  
               	  }else{
            	    view.loadUrl(url);
            	  }
            	 
            	  return true;
           	  }
         

              
              public void onPageStarted(WebView view, String url, Bitmap favicon) {
               	  slidingPage01.setVisibility(View.INVISIBLE);
              } 
              
              public void onPageFinished(WebView view, String url) {
            	  slidingPage01.setVisibility(View.VISIBLE);
            	   
              }
              
          });
         
                
            
         webViewLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);   
         yDADView.setLayoutParams(webViewLayout);
         slidingPage01.addView(yDADView);
          
        
         imageview01 = new ImageView(this.getContext()); 
         Img01Layout.addRule(RelativeLayout.CENTER_HORIZONTAL);
         Img01Layout.addRule(RelativeLayout.BELOW, 1);
         
         //imageview01.setAlpha(950);
         imageview01.setLayoutParams(Img01Layout);
         slidingPage01.addView(imageview01);
         slidingPage01.setVisibility(View.INVISIBLE);
         
         this.addView(slidingPage01);
        
         slidingPage02 = new RelativeLayout(this.getContext());
         slidingPage02.setBackgroundColor(color.transparent);
         //slidingPage02.setBackgroundColor(color.black);
         //slidingPage02.setHorizontalGravity(Gravity.CLIP_HORIZONTAL);
         slidingPage02.setLayoutParams(relative2Layout);
            
         imageview02 = new ImageView(this.getContext()); 
         Img02Layout.addRule(RelativeLayout.CENTER_HORIZONTAL);
         imageview02.setLayoutParams(Img02Layout);
         //imageview02.setAlpha(950);
         
         imageViewLoad();
       
         slidingPage02.setVisibility(View.INVISIBLE);
         this.addView(slidingPage02);
       
    }
    
 
    private void bannerAnimate(){
    	tAnimationDown = new TranslateAnimation(0,0,(int)(-webViewHeight-imgButtonHeight),0);
		tAnimationDown.setFillAfter(true);
		//tAnimationDown.setFillEnabled(true);
		tAnimationDown.setDuration(200);
		         
		tAnimationUp = new TranslateAnimation(0,0,0,(int)(-webViewHeight));
		tAnimationUp.setFillAfter(true);
		//tAnimationUp.setFillEnabled(true);
		
		tAnimationUp.setDuration(200);
		  
		SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
		tAnimationDown.setAnimationListener(animListener);
		tAnimationUp.setAnimationListener(animListener);
		  
		imageview02.setOnClickListener(new OnClickListener() {
			 public void onClick(View v) {
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
    	  try{ 

	     		in = getResources().getAssets().open("bt_154x50.png");
	     	    Bitmap bit=BitmapFactory.decodeStream(in);
	     	    imageview01.setImageBitmap(bit);
	     	    imageview02.setImageBitmap(bit);
	     	   
    	    } catch (IOException e) {
    		    e.printStackTrace();
    		} finally {
    		    if(in != null)
    		    	try{
    		    	 in.close();
    		    	}catch(IOException e) {
    	    		    e.printStackTrace();
    	    		}
    		}
	  
    }
     
    private class SlidingPageAnimationListener implements AnimationListener {

		public void onAnimationEnd(Animation animation) {
			if (isPageOpen) {
				slidingPage01.removeAllViews();
				slidingPage02.addView(imageview02);
				slidingPage02.setVisibility(View.VISIBLE);
				isPageOpen = false;
			} else {
				slidingPage02.removeAllViews();
				slidingPage01.addView(yDADView);
				slidingPage01.addView(imageview01);
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
    	yDADView = null;
    	imageview01 = null;
    	imageview02 = null;
    	slidingPage01 = null;
    	slidingPage02 = null;
    }
    
  
}