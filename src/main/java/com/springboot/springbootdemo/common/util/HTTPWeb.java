package com.springboot.springbootdemo.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HTTPWeb
 * @author admin
 * @date 2014年5月6日 上午10:41:13
 * @Description http服务请求交互
 */
public class HTTPWeb {
	
	private static Logger logger = LoggerFactory.getLogger(HTTPWeb.class);
	
	/**
	 * @author admin
	 * @date 2014年5月6日
	 * @param url 请求服务路径
	 * @param data 请求参数对象
	 * @return 请求结果
	 * @Description 执行一个http的web请求
	 */
	public static String post(String url , Map<String, ? extends Object > data) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost();
		try {
			httpPost.setURI(new URI(url));
			if(data != null){
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for (String key : data.keySet()) {
					if(key==null|| StringUtils.isBlank(key)||data.get(key)==null|| StringUtils.isBlank(data.get(key).toString()))
						continue;
					params.add(new BasicNameValuePair(key, data.get(key).toString()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
			}
			RequestConfig config = RequestConfig.custom().setConnectTimeout(1000).setConnectionRequestTimeout(2000).build();
			httpPost.setConfig(config);
			CloseableHttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if(entity == null) return null;
			BufferedReader reader = new BufferedReader((new InputStreamReader(entity.getContent(),"utf-8")));
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ( (str = reader.readLine()) != null ) {
				buffer.append(str);
			}
			response.close();
			return buffer.toString();
		} catch (URISyntaxException | IOException e) {
			logger.warn("服务器请求失败..URL:"+url, e);
			return null;
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				logger.warn("服务器关闭连接失败", e);
			}
		}
	}
	
	/**
	 * @author admin
	 * @date 2014年5月6日
	 * @param url 请求服务路径
	 * @param data 请求参数对象
	 * @return 请求结果
	 * @Description 执行一个http的web请求
	 */
	public static String post(String url , Map<String, ? extends Object > data , String userAgent) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost();
		try {
			httpPost.setURI(new URI(url));
			if(data != null){
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for (String key : data.keySet()) {
					params.add(new BasicNameValuePair(key, data.get(key).toString()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
				httpPost.setHeader("User-Agent",  userAgent + "_Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.76 Safari/537.36");
			}
			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(1000)
					.setConnectionRequestTimeout(2000)
					.build();
			httpPost.setConfig(config);
			CloseableHttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if(entity == null) return null;
			BufferedReader reader = new BufferedReader((new InputStreamReader(entity.getContent(),"utf-8")));
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ( (str = reader.readLine()) != null ) {
				buffer.append(str);
			}
			response.close();
			return buffer.toString();
		} catch (URISyntaxException | IOException e) {
			logger.warn("服务器请求失败..URL:"+url, e);
			return null;
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				logger.warn("服务器关闭连接失败", e);
			}
		}
	}
	
	/**
	 * @author admin
	 * @date 2014年5月6日
	 * @param url 请求服务路径
	 * @param json 请求参数对象
	 * @return 请求结果
	 * @Description 执行一个http的web请求
	 */
	public static String post(String url , JSONObject json ) {
		Map<String, String> data = new HashMap<String, String>();
		for (Object key : json.keySet()) {
			data.put(key.toString(), json.get(key.toString()).toString());
		}
		return post(url, data);
	}
	
	/**
	 * @author admin
	 * @date 2014年5月6日
	 * @param url  请求服务路径
	 * @param json  请求参数对象
	 * @return 请求结果
	 * @Description 执行一个http的web请求
	 */
	public static String post(String url , String json){
		return post(url, new JSONObject(json));
	}
	
	/**
	 * @author admin
	 * @date 2014年5月7日
	 * @param url 请求服务路径
	 * @param data 请求参数
	 * @return 请求结果
	 * @Description 通过一个get请求获得数据
	 */
	public static String get(String url , Map<String, ? extends Object> data){
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet();
		try {
			URIBuilder uriBuilder = new URIBuilder().setPath(url);
			if(data != null){
				for (String key : data.keySet()) {
					uriBuilder.setParameter(key, data.get(key).toString());
				}
			}
			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(1000)
					.setConnectionRequestTimeout(2000)
					.build();
			httpGet.setConfig(config);
			httpGet.setURI(uriBuilder.build());
			CloseableHttpResponse response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if(entity == null) return null;
			BufferedReader reader = new BufferedReader((new InputStreamReader(entity.getContent(),"utf-8")));
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ( (str = reader.readLine()) != null ) {
				buffer.append(str);
			}
			response.close();
			return buffer.toString();
		} catch (URISyntaxException e) {
			logger.warn("服务器请求失败..URL:"+url, e);
		} catch (ClientProtocolException e) {
			logger.warn("服务器请求失败..URL:"+url, e);
		} catch (IOException e) {
			logger.warn("服务器请求失败..URL:"+url, e);
		} finally {
			try {
//				System.out.println("关闭连接");
				client.close();
			} catch (IOException e) {
				logger.warn("关闭连接失败",e);
			}
		}
		return null;
	}
	
	/**
	 * @author admin
	 * @date 2014年5月6日
	 * @param url 请求服务路径
	 * @param json 请求参数对象
	 * @return 请求结果
	 * @Description 执行一个http的web请求
	 */
	public static String get(String url , JSONObject json ) {
		Map<String, String> data = new HashMap<String, String>();
		for (Object key : json.keySet()) {
			data.put(key.toString(), json.get(key.toString()).toString());
		}
		return get(url, data);
	}
	
	/**
	 * @author admin
	 * @date 2014年5月6日
	 * @param url  请求服务路径
	 * @param json  请求参数对象
	 * @return 请求结果
	 * @Description 执行一个http的web请求
	 */
	public static String get(String url , String json){
		return get(url, new JSONObject(json));
	}
	
	/**
	 * @author admin
	 * @date 2014年5月28日
	 * @param url  请求服务路径
	 * @param data 请求参数对象
	 * @return 请求结果
	 * @Description 直接post一个body字符串
	 */
	public static String postBody(String url , String data){
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost();
		try {
			httpPost.setURI(new URI(url));
			httpPost.setEntity(new StringEntity(data));
			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(1000)
					.setConnectionRequestTimeout(2000)
					.build();
			httpPost.setConfig(config);
			CloseableHttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if(entity == null) return null;
			BufferedReader reader = new BufferedReader((new InputStreamReader(entity.getContent(),"utf-8")));
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ( (str = reader.readLine()) != null ) {
				buffer.append(str);
			}
			response.close();
			return buffer.toString();
		} catch (URISyntaxException | IOException e) {
			logger.warn("服务器请求失败..URL:"+url, e);
			return null;
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				logger.warn("关闭连接失败"+e);
			}
		}
	}
	
	
	
	/**
	 * @author zhangkun
	 * @date 2014年7月18日
	 * @param url   请求服务路径
	 * @param file  上传的文件
	 * @return
	 * @Description 
	 */
	public static String postFile(String url , File file){
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost();
		try {
			httpPost.setURI(new URI(url));
			FileBody fileBody = new FileBody(file);
			MultipartEntity reqEntity = new MultipartEntity(); 
			reqEntity.addPart("file", fileBody);
            httpPost.setEntity(reqEntity);
			CloseableHttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if(entity == null) return null;
			BufferedReader reader = new BufferedReader((new InputStreamReader(entity.getContent(),"utf-8")));
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ( (str = reader.readLine()) != null ) {
				buffer.append(str);
			}
			response.close();
			return buffer.toString();
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				logger.warn("关闭连接失败",e);
			}
		}
	}
	
	/**
	 * @author admin
	 * @date 2014年5月7日
	 * @param url 请求服务路径
	 * @param data 请求参数
	 * @return 请求结果
	 * @Description 通过一个get请求获得数据
	 */
	public static String getSSL(String url , Map<String, ? extends Object> data){
		CloseableHttpClient client = createSSLClientDefault();
		HttpGet httpGet = new HttpGet();
		try {
			URIBuilder uriBuilder = new URIBuilder().setPath(url);
			if(data != null){
				for (String key : data.keySet()) {
					uriBuilder.setParameter(key, data.get(key).toString());
				}
			}
			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(1000)
					.setConnectionRequestTimeout(2000)
					.build();
			httpGet.setConfig(config);
			httpGet.setURI(uriBuilder.build());
			CloseableHttpResponse response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if(entity == null) return null;
			BufferedReader reader = new BufferedReader((new InputStreamReader(entity.getContent(),"utf-8")));
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ( (str = reader.readLine()) != null ) {
				buffer.append(str);
			}
			response.close();
			return buffer.toString();
		} catch (URISyntaxException e) {
			logger.warn("服务器请求失败..URL:"+url, e);
		} catch (ClientProtocolException e) {
			logger.warn("服务器请求失败..URL:"+url, e);
		} catch (IOException e) {
			logger.warn("服务器请求失败..URL:"+url, e);
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				logger.warn("关闭连接失败",e);
			}
		}
		return null;
	}
	
	/**
	 * @author admin
	 * @date 2014年5月6日
	 * @param url 请求服务路径
	 * @param json 请求参数对象
	 * @return 请求结果
	 * @Description 执行一个http的web请求
	 */
	public static String getSSL(String url , JSONObject json ) {
		Map<String, String> data = new HashMap<String, String>();
		for (Object key : json.keySet()) {
			data.put(key.toString(), json.get(key.toString()).toString());
		}
		return getSSL(url, data);
	}
	
	/**
	 * @author admin
	 * @date 2014年5月6日
	 * @param url  请求服务路径
	 * @param json  请求参数对象
	 * @return 请求结果
	 * @Description 执行一个http的web请求
	 */
	public static String getSSL(String url , String json){
		return getSSL(url, new JSONObject(json));
	}
	
	/**
	 * @author admin
	 * @date 2014年5月6日
	 * @param url 请求服务路径
	 * @param data 请求参数对象
	 * @return 请求结果
	 * @Description 执行一个http的web请求
	 */
	public static String postSSL(String url , Map<String, ? extends Object > data) {
		CloseableHttpClient client = createSSLClientDefault();
		HttpPost httpPost = new HttpPost();
		try {
			httpPost.setURI(new URI(url));
			if(data != null){
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for (String key : data.keySet()) {
					if(key==null|| StringUtils.isBlank(key)||data.get(key)==null|| StringUtils.isBlank(data.get(key).toString()))
						continue;
					params.add(new BasicNameValuePair(key, data.get(key).toString()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
			}
			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(1000)
					.setConnectionRequestTimeout(2000)
					.build();
			httpPost.setConfig(config);
			CloseableHttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if(entity == null) return null;
			BufferedReader reader = new BufferedReader((new InputStreamReader(entity.getContent(),"utf-8")));
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ( (str = reader.readLine()) != null ) {
				buffer.append(str);
			}
			response.close();
			return buffer.toString();
		} catch (URISyntaxException | IOException e) {
			logger.warn("服务器请求失败..URL:"+url, e);
			return null;
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				logger.warn("服务器关闭连接失败", e);
			}
		}
	}
	
	/**
	 * @author admin
	 * @date 2014年5月6日
	 * @param url 请求服务路径
	 * @param data 请求参数对象
	 * @return 请求结果
	 * @Description 执行一个http的web请求
	 */
	public static String postSSL(String url , Map<String, ? extends Object > data , String userAgent) {
		CloseableHttpClient client = createSSLClientDefault();
		HttpPost httpPost = new HttpPost();
		try {
			httpPost.setURI(new URI(url));
			if(data != null){
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for (String key : data.keySet()) {
					params.add(new BasicNameValuePair(key, data.get(key).toString()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
				httpPost.setHeader("User-Agent",  userAgent + "_Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.76 Safari/537.36");
			}
			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(1000)
					.setConnectionRequestTimeout(2000)
					.build();
			httpPost.setConfig(config);
			CloseableHttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if(entity == null) return null;
			BufferedReader reader = new BufferedReader((new InputStreamReader(entity.getContent(),"utf-8")));
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ( (str = reader.readLine()) != null ) {
				buffer.append(str);
			}
			response.close();
			return buffer.toString();
		} catch (URISyntaxException | IOException e) {
			logger.warn("服务器请求失败..URL:"+url, e);
			return null;
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				logger.warn("服务器关闭连接失败", e);
			}
		}
	}
	
	/**
	 * @author admin
	 * @date 2014年5月6日
	 * @param url 请求服务路径
	 * @param json 请求参数对象
	 * @return 请求结果
	 * @Description 执行一个http的web请求
	 */
	public static String postSSL(String url , JSONObject json ) {
		Map<String, String> data = new HashMap<String, String>();
		for (Object key : json.keySet()) {
			data.put(key.toString(), json.get(key.toString()).toString());
		}
		return postSSL(url, data);
	}
	
	/**
	 * @author admin
	 * @date 2014年5月6日
	 * @param url  请求服务路径
	 * @param json  请求参数对象
	 * @return 请求结果
	 * @Description 执行一个http的web请求
	 */
	public static String postSSL(String url , String json){
		return postSSL(url, new JSONObject(json));
	}
	
	/**
	 * @author Administrator
	 * @date 2016年4月12日
	 * @return
	 * @Description 创建SSL客户端
	 */
	public static CloseableHttpClient createSSLClientDefault(){
		try {
             SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                 //信任所有
                 public boolean isTrusted(X509Certificate[] chain,
                                 String authType) throws CertificateException {
                     return true;
                 }
             }).build();
             SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
             return HttpClients.custom().setSSLSocketFactory(sslsf).build();
         } catch (KeyManagementException e) {
             e.printStackTrace();
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
         } catch (KeyStoreException e) {
             e.printStackTrace();
         }
         return HttpClients.createDefault();
	}
	
	/**
     * 忽视证书HostName
     */
    private static HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
		@Override
		public boolean verify(String arg0, SSLSession arg1) {
			return true;
		}
    };

     /**
     * Ignore Certification
     */
    private static TrustManager ignoreCertificationTrustManger = new X509TrustManager() {

        private X509Certificate[] certificates;

        @Override
        public void checkClientTrusted(X509Certificate certificates[],
                String authType) throws CertificateException {
            if (this.certificates == null) {
                this.certificates = certificates;
            }
        }
        @Override
        public void checkServerTrusted(X509Certificate[] ax509certificate,
                String s) throws CertificateException {
            if (this.certificates == null) {
                this.certificates = ax509certificate;
            }
        }
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

    public static String getLocalSSL(String urlString) {
        PrintWriter out = null;
        BufferedReader reader = null;
    	String result = "";
        try {
            URL url = new URL(urlString);
            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            TrustManager[] tm = { ignoreCertificationTrustManger };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            connection.setSSLSocketFactory(ssf);
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            while( (line = reader.readLine()) != null ){
            	result += line;
            }
            connection.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                if(out!=null){
                    out.close();
                }
                if(reader!=null){
                	reader.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    public static String postLocalSSL(String urlString ,String body) {
        PrintWriter out = null;
        BufferedReader reader = null;
    	String result = "";
        try {
            URL url = new URL(urlString);
            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            TrustManager[] tm = { ignoreCertificationTrustManger };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            connection.setSSLSocketFactory(ssf);
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();
            out = new PrintWriter(connection.getOutputStream());
            out.print(body);
            out.flush();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            while( (line = reader.readLine()) != null ){
            	result += line;
            }
            connection.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{
                if(out!=null){
                    out.close();
                }
                if(reader!=null){
                	reader.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
	
}
