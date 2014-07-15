package com.gdmc.trivia.android.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.gdmc.trivia.android.BaseApplication;
import com.gdmc.trivia.android.other.SSLSocketFactoryEx;

public class HttpUtils {
	private static final String TAG = "HttpUtil";
	private static CookieStore cookies;
	private final static int retryTime = 3;  

	public static String post(String basciURL, Map<String, String> params){		
		String result = null;
		try {
			HttpPost httpPost = new HttpPost(basciURL);    
			if (params!=null) {  //添加参数
				List<NameValuePair> param=new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> entry : params.entrySet()) {
					//构建表单字段内容
					param.add(new BasicNameValuePair(entry.getKey(),entry.getValue())); 
				} 
				HttpEntity entity = new UrlEncodedFormEntity(param, HTTP.UTF_8);
				httpPost.setEntity(entity);
			}
			DefaultHttpClient httpClient = new DefaultHttpClient();
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,10000 );//设置超市连接
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
			httpClient.getParams().setParameter(CoreConnectionPNames.TCP_NODELAY, false);  
			httpClient.getParams().setParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, 1024 * 1024); 

			if (BaseApplication.getCookie()!=null) {
				cookies=BaseApplication.getCookie();
				((AbstractHttpClient) httpClient).setCookieStore(cookies);
			}

			//HttpRequestRetryHandler是负责处理请求重试的接口。
			HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler()  
	        {  
	            @Override  
	            public boolean retryRequest(IOException exception, int executionCount, HttpContext context)  
	            {  
	                if (executionCount >= retryTime)  
	                {  
	                    // Do not retry if over max retry count  
	                    return false;  
	                }  
	                if (exception instanceof InterruptedIOException)  
	                {  
	                    // Timeout  
	                    return false;  
	                }  
	                if (exception instanceof UnknownHostException)  
	                {  
	                    // Unknown host  
	                    return false;  
	                }  
	                if (exception instanceof ConnectException)  
	                {  
	                    // Connection refused  
	                    return false;  
	                }  
	                if (exception instanceof SSLException)  
	                {  
	                    // SSL handshake exception  
	                    return false;  
	                }  
	                HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);  
	                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);  
	                if (idempotent)  
	                {  
	                    // Retry if the request is considered idempotent  
	                    return true;  
	                }  
	                return false;  
	            }  
	  
	        };
	        
	        httpClient.setHttpRequestRetryHandler(myRetryHandler); 
//			HttpResponse response = httpClient.execute(httpPost);
			HttpResponse response =  (HttpResponse) getNewHttpClient().execute(httpPost);
			if(response.getStatusLine().getStatusCode() == 200 ||response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				cookies=((AbstractHttpClient)httpClient).getCookieStore();
				BaseApplication.setCookie(cookies);
				HttpEntity responseEntity=response.getEntity(); 
				result=EntityUtils.toString(responseEntity, HTTP.UTF_8);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); 
		}
		return result;
	}

	public static String get(String uri,Map<String, String> params){
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet;
		BufferedReader br = null;
		try{
			if (params!=null) {  //添加参数
				uri=uri+"?";
				for (Map.Entry<String, String> entry : params.entrySet()) {
					//构建表单字段内容
					uri=uri+"&"+entry.getKey()+"="+entry.getValue();
				} 
			}
			if (BaseApplication.getCookie()!=null) {
				cookies=BaseApplication.getCookie();
				((AbstractHttpClient) httpclient).setCookieStore(cookies);
			}
			httpGet = new HttpGet(uri);
//			HttpResponse response = httpclient.execute(httpGet); 
			HttpResponse response =  (HttpResponse) getNewHttpClient().execute(httpGet);
			if(response.getStatusLine().getStatusCode() == 200 ||response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				cookies=((AbstractHttpClient)httpclient).getCookieStore();
				BaseApplication.setCookie(cookies);
			}
			InputStream in=response.getEntity().getContent();
			InputStreamReader isr = new InputStreamReader(in,"utf-8");
			br = new BufferedReader(isr);
			String line;
			StringBuilder sBuilder = new StringBuilder() ;
			while ((line = br.readLine()) != null ) {
				sBuilder.append(line);			
			}
			return sBuilder.toString();
		} catch (Exception e) { 
			e.printStackTrace(); 
			return "";
		}finally{
			try {
				if(br != null){
					br.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}

	}
	
	public static HttpClient getNewHttpClient() {
		   try {
		       KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		       trustStore.load(null, null);
		 
		       SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);
		       sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		 
		       HttpParams params = new BasicHttpParams();
		       HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		       HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
		 
		       SchemeRegistry registry = new SchemeRegistry();
		       registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		       registry.register(new Scheme("https", sf, 443));
		 
		       ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
		 
		       return new DefaultHttpClient(ccm, params);
		   } catch (Exception e) {
		       return new DefaultHttpClient();
		   }
		}
}
