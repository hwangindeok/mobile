package org.YD.AD.ydadview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.RenderPriority;
import android.widget.LinearLayout;
import android.webkit.SslErrorHandler;
import android.net.http.SslError;

public class LogOutActivity extends Activity {

	//private static final String TAG = "SampleWebViewActivity";
	boolean isPageOpen = true;
	private String androidVersion = null;
	private String local = null;
	private String displayInfo = null;
	private String packageName = null;
	private String packageVersion = null;
	private int width = 0;
	private int height = 0;
	private int webViewHeight = 0;
	private int webViewWidth = 0;
	private Handler mHandler = new Handler();
	String userid;
	String userno;
	String charactername;
    String gameid;
    private int STATE = 1;
	private final int GAMEVIEW = 1;
	private final int WEBVIEW = 2;

	   
	WebView yDAuthView = null;
	LinearLayout linearLayout  = null;
    private ValueCallback<Uri> mUploadMessage;
    private final static int FILECHOOSER_RESULTCODE = 1;

	public void onCreate(Bundle icicle) {
         super.onCreate(icicle);
         //setContentView(R.layout.main);
         STATE = WEBVIEW;		//Set STATE to webview state
     	 CookieSyncManager.createInstance(this);      
         getParam();
         //adroidInfo();
         uIConfiguration();
    }
	
	/**
	public void setCookieDel(){
		CookieManager.getInstance().removeAllCookie();
		CookieSyncManager.getInstance().startSync();
	}
	*/
	
	public void getParam(){
	
	    //Intent resultIntent = new Intent();
		Intent resultIntent = getIntent();
	    gameid = resultIntent.getStringExtra("gameid");
	    charactername = resultIntent.getStringExtra("charactername");
	    //Log.d("test", "beforeuserno"+beforeuserno);
	    //Log.d("test", "gameid"+gameid);
	}
	
	
	public void setParam(String userid, String userno){
		
		//CookieSyncManager.getInstance().startSync();
		Intent resultIntent = getIntent();
	    resultIntent.putExtra("userid", userid);
	    resultIntent.putExtra("userno", userno);
	    // tell caller that we have succeeded and dispose this activity
	    //setCookieDel();
	    setResult(2002, resultIntent);
	    finish();
		//Log.d("test", userid+" "+userno);
	}
	
	
	private void uIConfiguration(){
   
     linearLayout = new LinearLayout(this);
     linearLayout.setOrientation(LinearLayout.HORIZONTAL);
     linearLayout.setBackgroundColor(Color.TRANSPARENT);
   	    	 
   	 
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
           
      	yDAuthView = new WebView(this);
      	//yDAuthView.setId(1);
      	yDAuthView.setHorizontalScrollBarEnabled(false);
      	yDAuthView.setVerticalScrollBarEnabled(false);
      	
        //Log.d("",androidVersion);
        
        yDAuthView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String fallingUrl) {
                //Toast.makeText(this, "로딩오류"+description, Toast.LENGTH_SHORT).show();
            }
            
            
            public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
            	 handler.proceed() ;
            }

     
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
         
          	   	    view.loadUrl(url);
           	  	    return true;
         	}
       
            
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
          	  //Log.d("test","2");
            	//yDAuthView.setVisibility(View.GONE);
             	
            } 
            
            public void onPageFinished(WebView view, String url) {
          	  //Log.d("test","3");
            	//yDAuthView.setVisibility(View.VISIBLE);
            	CookieSyncManager.getInstance().sync();
          	           	   
            }
            
        });
                
     
        yDAuthView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result)
            {
            	 new AlertDialog.Builder(view.getContext())  
                 .setTitle("알림")  
                 .setMessage(message)  
                 .setPositiveButton("확인",  
                       new AlertDialog.OnClickListener(){  
                          public void onClick(DialogInterface dialog, int which) {  
                           result.confirm();  
                          }  
                       })  
                 .setCancelable(false)  
                 .create()  
                 .show();  
            return true;  
            };
            
            
            public boolean onJsConfirm(WebView view, String url, String message, final android.webkit.JsResult result)
            {
         
             new AlertDialog.Builder(view.getContext())
                       .setTitle("확인")
                       .setMessage(message)
                       .setPositiveButton("확인",
                               new DialogInterface.OnClickListener() {
                                   public void onClick(
                                           DialogInterface dialog,
                                           int which) {
                                    result.confirm();
                                   }})
                       .setNegativeButton("취소", 
                               new DialogInterface.OnClickListener() {
                                   public void onClick(
                                           DialogInterface dialog,
                                           int which) {
                                    result.cancel();
                                   }})
                       .show();
           
                return true;
            };
            
                 
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
           		mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                LogOutActivity.this.startActivityForResult(Intent.createChooser(i, "File Chooser"),FILECHOOSER_RESULTCODE);
            }
     
            
            
            public void openFileChooser( ValueCallback<Uri> uploadMsg, String acceptType ){  
                mUploadMessage = uploadMsg;  
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);  
                i.addCategory(Intent.CATEGORY_OPENABLE);  
                i.setType("*/*"); 
                startActivityForResult( Intent.createChooser( i, "File Chooser" ),    FILECHOOSER_RESULTCODE );  
            }
            
            public void openFileChooser( ValueCallback<Uri> uploadMsg ){
                openFileChooser( uploadMsg, "" );
            }
            
                
        });
        
                
       	yDAuthView.addJavascriptInterface(this , "auth");
        WebSettings webSettings = yDAuthView.getSettings();
        webSettings.setPluginsEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setRenderPriority(RenderPriority.HIGH);
        //webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        //webSettings.setSupportZoom(false);
               
               
        yDAuthView.loadUrl("https://m.ncucu.com/auth/userinfo/?gameid="+gameid+"&charactername="+charactername);

        yDAuthView.setLayoutParams(_linearLayout);
        linearLayout.addView(yDAuthView);
        setContentView(linearLayout);
        
        //Log.d("test", userid+" "+userno);
   }
	
	@JavascriptInterface 
	 public void setLogOut(String userid , String userno) {
     	//AuthActivity au = new AuthActivity();
     	userid = userid;
     	userno = userno;
     	setParam(userid, userno);
     	//yDAuthView.loadUrl("javascript:changeFace()");
        	//Log.d("test", userid+" "+userno);
     }
    
		
	 protected  void onActivityResult(int requestCode, int resultCode,Intent intent) {

	     if (requestCode == FILECHOOSER_RESULTCODE) {

	         if (null == mUploadMessage)

	             return;

	         Uri result = intent == null || resultCode != RESULT_OK ? null

	                 : intent.getData();

	         mUploadMessage.onReceiveValue(result);

	         mUploadMessage = null;

	     }

	 }
	
 	  
   private void adroidInfo()
   {
   	//Log.d("",android.os.Build.VERSION.RELEASE);
       TelephonyManager telephonymanager = (TelephonyManager)this.getBaseContext().getSystemService("phone");
       Display display = ((WindowManager)this.getBaseContext().getSystemService("window")).getDefaultDisplay();
       
       //androidVersion = telephonymanager.getNetworkOperatorName()+android.os.Build.VERSION.RELEASE;
       androidVersion = android.os.Build.VERSION.RELEASE;
       local =        this.getBaseContext().getResources().getConfiguration().locale.toString();
       height = display.getHeight();
       width =  display.getWidth();
       
       displayInfo = width+"/"+height;
     
       
       try
       {
           PackageInfo packageinfo = this.getBaseContext().getPackageManager().getPackageInfo(this.getBaseContext().getPackageName(), 0);
           packageName = packageinfo.packageName;
           packageVersion = packageinfo.versionName;
       }
       catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
       {
           namenotfoundexception.printStackTrace();
       }
       
       //Log.d("",local+" "+androidVersion+" "+displayInfo+" "+packageName+" "+packageVersion);
   }
   
   
	public boolean onKeyDown (int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN && STATE==WEBVIEW) {
			if(yDAuthView.canGoBack())		//Check the record
				yDAuthView.goBack();		//If the web has history then go back page
			else
				closeWebView();			//If not, close the webview and return to game's view
		}
		return true;
	}
	
	private void closeWebView() {
		STATE = GAMEVIEW;		//Set STATE to game's view state
		//Log.e("CloseWebView", "CloseWebView");
	    
		finish();

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);	//Unlock the orientation
	
	}
   
  /**
   
   protected void onDestroy() {

	   yDAuthView.clearHistory();
	   yDAuthView.clearCache(true);
	   yDAuthView.clearView();
	}
	
  */
	
	@Override
	protected void onResume()
	{
	    super.onResume();
	    CookieSyncManager.getInstance().startSync();
	}
	
	@Override
	protected void onPause()
	{
	    super.onPause();
	    CookieSyncManager.getInstance().stopSync();
	}
	
}
