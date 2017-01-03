package com.grouprx.sync;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class CommonUtilsManagerImplHTTPS {

	public String getServerURLStarter() {
		return "https://www.nationaldrugcard.com";
	}
	
	private String convertStreamToString(String url, InputStream is)
			throws Exception {
		String line = "";
		StringBuilder total = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		while ((line = rd.readLine()) != null) {
			p("URl : " + url + " line = " + line);
			total.append(line + "\n");
		}
		p("url = "+url+" total = "+total);
		rd.close();
		return total.toString();
	}

	protected synchronized JSONObject doPost(String url,
			Map<String, String> params) throws Exception {
		String line = getHttpResponse(url, params);
		JSONObject jsonObject = new JSONObject(line);
		return jsonObject;
	}
	
	public String getFinalURL(String url) {
		if (!url.startsWith("/")) {
			url += "/" + url;
		}
		url = getServerURLStarter() + url;
		p("getHttpResponse url : " + url);
		return url;
	}

	private String getHttpResponse(String urlstr, Map<String, String> params) throws Exception {
		String ss = getFinalURL(urlstr);
		URL url = new URL(ss);
//		String urlParameters = "";
//		if (params != null && !params.isEmpty()) {
//			for (String key : params.keySet()) {
//				urlParameters += key+"="+params.get(key)+"&";
//			}
//		}
//		if (ss.startsWith("http:")) {
//			HttpURLConnection con = (HttpURLConnection) url.openConnection();
//			con.setRequestMethod("POST");
//			con.setConnectTimeout(5000);
//			con.setDoOutput(true);
//			p("getHttpResponse Sending "+con.getRequestMethod()+" request to URL : " + urlstr);
//			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//			wr.write(urlParameters.getBytes("UTF-8"));
//			wr.flush();
//			wr.close();
//			int responseCode = con.getResponseCode();
//			p("getHttpResponse Response Code : " + responseCode);
//			return convertStreamToString(urlstr,con.getInputStream());
//		} else {
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(5000);
			p("getHttpResponse Sending "+con.getRequestMethod()+" request to URL : " + urlstr);
//			con.setDoInput(true);
//			con.setDoOutput(true);
			int responseCode = con.getResponseCode();
			p("getHttpResponse Response Code : " + responseCode);
			String sss = convertStreamToString(urlstr,con.getInputStream());
			con.disconnect();
			return sss;
//		}
		
	}
	
	protected synchronized JSONArray doPostArray(String url,
			Map<String, String> params) throws Exception {
		String line = getHttpResponse(url, params);
		JSONArray Jsonarray = new JSONArray(line);
		return Jsonarray;
	}

	public void p(String str) {
		System.out.println("Group RX Print : "+str);
	}

	
}
