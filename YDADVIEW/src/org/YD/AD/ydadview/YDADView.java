package org.YD.AD.ydadview;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;



public class YDADView extends RelativeLayout {

	private WebView webView;
	private String channelID;
	private String androidVersion = null;
	private String local = null;
	private String displayInfo = null;
	private String packageName = null;
	private String packageVersion = null;
	private Handler mHandler = new Handler();
	
	public YDADView(Context context){
		super(context);
		create(context,null);
				
		//this.setOrientation(RelativeLayout.VERTICAL);
       		
	}
	
	
    public YDADView(Context context, AttributeSet attributeset)
    {
    	super(context,attributeset);
    	create(context,attributeset);
    	start();
    }
    
    

	private void create(Context context,AttributeSet attrs)
	{
	    /** 
		if(attrs != null) {
	         TypedArray typeArray = context.obtainStyledAttributes(attrs,  R.styleable.YDADView);
	         channelID = typeArray.getString(R.styleable.YDADView_channel_id);
	     }
	     */
	     
	     if(attrs != null)
	        {
	            for(int j = 0; j < attrs.getAttributeCount(); j++)
	            {
	                String s1 = attrs.getAttributeName(j);
	                if(s1.equals("channel_id"))
	                {
	                	channelID = attrs.getAttributeValue(j);
	                  
	                }
	               
	            }

	        }
	}


	public void setChannelID(String s)
    {
    	channelID = s;
    }
	
	
	private void copyImage() {
		 
		AssetManager am = getResources().getAssets();
		File f = new File("/data/data/org.YD.AD.ydadview/temp.jpg");
 
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
 
		try {
 
			 URL url = new URL("http://file.web.ydonline.gscdn.com/uploadimg/Ncucu/GM_CONTENT/217x254_ncucu_20130117.jpg");
	         URLConnection conn = url.openConnection();
	         conn.connect();
	   	
	         BufferedInputStream in = new BufferedInputStream(conn.getInputStream(), conn.getContentLength());
 
			// 만약에 파일이 있다면 지우고 다시 생성
			if (f.exists()) {
				f.createNewFile();
			}
			fos = new FileOutputStream(f);
			bos = new BufferedOutputStream(fos);
 
			int read = -1;
			byte[] buffer = new byte[1024];
			while ((read = in.read(buffer, 0, 1024)) != -1) {
				bos.write(buffer, 0, read);
			}
			bos.flush();
 
			fos.close();
			bos.close();
			in.close();
		
 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
    public void start()
    {
    	   /** 
    	   RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
         		LinearLayout.LayoutParams.FILL_PARENT,
         		LinearLayout.LayoutParams.FILL_PARENT);
         */
           /**
		    try{ 
		         URL url = new URL("http://file.web.ydonline.gscdn.com/uploadimg/Ncucu/GM_CONTENT/217x254_ncucu_20130117.jpg");
		         URLConnection conn = url.openConnection();
		         conn.connect();
		   	
		         BufferedInputStream in = new BufferedInputStream(conn.getInputStream(), conn.getContentLength());
		   	
		         //Bitmap bitmap = BitmapFactory.decodeStream(in);
		         //in.close();
		        // BitmapDrawable bitmapdrawable = new BitmapDrawable(null, bitmap);
		   	
			      	//FrameLayout c = new FrameLayout(this.getContext());
		     	    //this.addView(c);
		         //this.setBackgroundDrawable(bitmapdrawable);
		            String fileName =  "temp.jpg";

		            ByteArrayBuffer baf = new ByteArrayBuffer(50);
		         
					int current = 0;
		 
					while ((current = in.read()) != -1) {
						baf.append((byte) current);
					}
		 
					FileOutputStream fos = openFileOutput(fileName, 0);
					fos.write(baf.toByteArray());
					fos.close();

		 		       	   
		       	
		         }catch(Exception e){
		        	 
		        	 e.printStackTrace();
		         }
		       
                */
    	
    	         //copyImage();
    	         adroidInfo();
    	          	       
		         webView = new WebView(this.getContext());
		         WebSettings webSettings = webView.getSettings();
		         webSettings.setSavePassword(false);
		         webSettings.setSaveFormData(false);
		         webSettings.setJavaScriptEnabled(true);
		         webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		         webSettings.setSupportZoom(false);
		         webView.addJavascriptInterface(new JavaScriptMethods(), "sample");
		         webView.loadUrl("http://pcbang.ncucu.com/test.asp?channelid="+channelID+"|"+androidVersion+"|"+ local+"|"+displayInfo+"|"+packageName+"|"+packageVersion);
		         //webView.loadUrl("http://pcbang.ncucu.com/test.asp?channelid="+packageName+"|"+packageVersion);
		         this.addView(webView);
				         
    }
    
    final class JavaScriptMethods {

   	 JavaScriptMethods() {
        }

        public void clickOnFace() {
            mHandler.post(new Runnable() {
                public void run() {
               	   webView.loadUrl("javascript:goURL()");
                }
            });

        }
    }
    
    
    private void adroidInfo()
    {
    	   //Log.d("",android.os.Build.VERSION.RELEASE);
        TelephonyManager telephonymanager = (TelephonyManager)this.getContext().getSystemService("phone");
        Display display = ((WindowManager)this.getContext().getSystemService("window")).getDefaultDisplay();
        
        androidVersion = telephonymanager.getNetworkOperatorName()+android.os.Build.VERSION.RELEASE;
        local =        this.getContext().getResources().getConfiguration().locale.toString();
        displayInfo = display.getWidth()+"/"+display.getHeight();
        
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
    }
    
  
    public void destroy()
    {
       webView = null;
    }
	
		

}
