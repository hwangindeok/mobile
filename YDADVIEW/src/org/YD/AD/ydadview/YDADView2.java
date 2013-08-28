package org.YD.AD.ydadview;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;



public class YDADView2 extends RelativeLayout {

	private WebView webView;
	public String channelID;

	
	public YDADView2(Context context){
		super(context);
		
		//this.setOrientation(RelativeLayout.VERTICAL);
       		
	}
	
	
    public YDADView2(Context context, AttributeSet attributeset)
    {
    	super(context);
    }
	
	
    public void setChannelID(String s)
    {
    	channelID = s;
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
		   	
		         Bitmap bitmap = BitmapFactory.decodeStream(in);
		         in.close();
		         BitmapDrawable bitmapdrawable = new BitmapDrawable(null, bitmap);
		   	
			      	//FrameLayout c = new FrameLayout(this.getContext());
		     	    //this.addView(c);
		       	this.setBackgroundDrawable(bitmapdrawable);
		       	   
		       	
		         }catch(Exception e){
		        	 
		        	 e.printStackTrace();
		         }
		         */
    	     	    
		       		    
		         webView = new WebView(this.getContext());
		         WebSettings webSettings = webView.getSettings();
		         webSettings.setSavePassword(false);
		         webSettings.setSaveFormData(false);
		         webSettings.setJavaScriptEnabled(true);
		         webSettings.setSupportZoom(false);
		
		         webView.setWebChromeClient(new WebChromeClient());
		         webView.loadUrl("http://pcbang.ncucu.com/test.asp?channelid="+channelID);
		         this.addView(webView);
				         
    }
    
  
    public void destroy()
    {
       webView = null;
    }
	
		

}
