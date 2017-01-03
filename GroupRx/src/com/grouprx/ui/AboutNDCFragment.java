package com.grouprx.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;

import com.nationaldrugcard.qlinkwireless.R;
import com.grouprx.util.MyFragment;

public class AboutNDCFragment extends MyFragment {
	
	private Button visitButton;

	@SuppressLint({ "InflateParams", "SetJavaScriptEnabled" })
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		isMainLevel = true;
		View view = inflater.inflate(R.layout.fragment_about_ndc, null);
		visitButton = (Button) view.findViewById(R.id.visit_website);
		visitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String url = "https://ndcsavingsclub.com/";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
				
			}
		});
		
		View imageview_ndc = (View) view.findViewById(R.id.imageview_ndc);
		imageview_ndc.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(),PlayVideoActivity.class);
				String fileName = "android.resource://"+  getActivity().getPackageName() + "/raw/ndc_savings_club";
				i.putExtra("url", fileName);
				startActivity(i);
			}
		});
		 
		return view;
	}

	@Override
	public int getTitle(){
		return R.string.About_Healthcare_Saving_Card;
	}
	

}
