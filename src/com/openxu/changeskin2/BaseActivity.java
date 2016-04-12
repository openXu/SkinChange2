package com.openxu.changeskin2;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.text.TextUtils;

public class BaseActivity extends Activity{
	
	protected AssetManager mAssetManager;   //资源管理器  
	protected Resources mResources;         //资源  
	protected String TAG;
	protected Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		TAG = getClass().getSimpleName();
	}
	
	protected void loadResources(String dexPath) {  
		if(TextUtils.isEmpty(dexPath)){
			mAssetManager = super.getAssets();
			mResources = super.getResources();
		}else{
			try {  
	            AssetManager assetManager = AssetManager.class.newInstance();  
	            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);  
	            addAssetPath.invoke(assetManager, dexPath);  
	            mAssetManager = assetManager;  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        Resources superRes = super.getResources();  
	        superRes.getDisplayMetrics();  
	        superRes.getConfiguration();  
	        mResources = new Resources(mAssetManager, superRes.getDisplayMetrics(),superRes.getConfiguration());  
		}
    }  
	
	@Override  
	public AssetManager getAssets() {  
	    return mAssetManager == null ? super.getAssets() : mAssetManager;  
	}  
	
	@Override  
	public Resources getResources() {  
	    return mResources == null ? super.getResources() : mResources;  
	}
	

}
