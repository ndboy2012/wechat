package com.wmsoft.web.util;



import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.wmsoft.web.constant.MessageConstant;


/**
 * 基于common-httpclient java发送Http请求
* @Title: HttpUtils.java 
* @Package com.wmsoft.mc.utils 
* @Description: 暂时还不够完善~ 
* @author 蒋信
* @date 2014年6月25日 上午10:08:37 
* @version V1.0
 */
public class HttpClientUtil {
	static HttpClient httpClient = null;
    //构造HttpClient的实例
	private static HttpClientUtil instance;
	
    private HttpClientUtil() {
        httpClient = new HttpClient();
    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
    public static HttpClientUtil getInstance() {
        if(instance == null) {
            return new HttpClientUtil();
        }
        return instance;
    }
    
    //Https
    
    /**
     * 设置代理
     * 代理服务器是位于客户端和Web服务器之间一个中间服务器。我们通过使用浏览器中配置的相应代理，
     * 将请求发送到代理服务器，代理服务器代理我们客户端去请求Web服务器，将请求到的响应资源再次
     * 响应我们客户端，同时缓存到代理服务器上，以备下次客户端请求代理服务器的时候，直接从代理服
     * 务器上取得要请求的资源，而无需再次请求网站的Web服务器，提高了响应速度。
     */
    
    /**
     * GET请求
     * 使用 HttpClient 需要以下 6 个步骤：
     *    1. 创建 HttpClient 的实例
     *    2. 创建某种连接方法的实例，在这里是 GetMethod。在 GetMethod 的构造函数中传入待连接的地址
     *    3. 调用第一步中创建好的实例的 execute 方法来执行第二步中创建好的 method 实例
     *    4. 读 response
     *    5. 释放连接。无论执行方法是否成功，都必须释放连接
     *    6. 对得到后的内容进行处理
     * @param url
     * @return 
     */
    public String getMethod(String url) {
        GetMethod getMethod = null;
        try {
            //URI uri = new URI(url, true);
            getMethod = new GetMethod(url);// 创建GET方法的实例,可同时对请求的头,等等，做相应的设置
//            /** 设置代理*/
//            HostConfiguration hostConfiguration = new HostConfiguration();
//            hostConfiguration.setHost(uri);
//            hostConfiguration.setProxy("127.0.0.1", 8080);// 配置代理IP和端口
//            httpClient.setHostConfiguration(hostConfiguration);
            
            // 使用系统提供的默认的恢复策略
            getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
            getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
            managerParams.setConnectionTimeout(30000);     // 设置连接超时时间(单位毫秒) 
            managerParams.setSoTimeout(120000);        //设置读数据超时时间(单位毫秒) 
            int statusCode = httpClient.executeMethod(getMethod);// 执行getMethod,返回响应码
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + getMethod.getStatusLine());    //Method failed: HTTP/1.1 200 OK
            }
            String charSet = getMethod.getResponseCharSet();    //响应的字符编码
            byte[] responseBody = getMethod.getResponseBody();    // 读取内容
            
            System.out.println("responseCode:\r\n" + statusCode);
            System.out.println("charSet:\r\n" + charSet);
            System.out.println("response:\r\n" + new String(responseBody, charSet));
            
            Header headers[] = getMethod.getResponseHeaders();
            for(int i = 0; i < headers.length; i ++) {
                System.out.println(headers[i]);
            }
            /*Date: Mon, 21 Dec 2009 16:34:06 GMT
            Content-Type: text/vnd.wap.wml; charset=utf-8
            Transfer-Encoding: chunked
            Connection: keep-alive
            Server: Resin/3.1.0
            Cache-Control: no-cache
            Expires: Thu, 01 Dec 1994 16:00:00 GMT
            Set-Cookie: JSESSIONID=abc8cwOo6fWhcf2uQfYws; path=/
            P3P: policyref="http://www.500wan.com/w3c/P3P.xml" CP="NOI ADM DEV PSAi COM NAV OUR OTR STP IND DEM"
             */
            if(statusCode==HttpStatus.SC_OK){
            	return getMethod.getResponseBodyAsString();
            }
        } catch (HttpException e) {
            e.printStackTrace();// 发生致命的异常，可能是协议不对或者返回的内容有问题
        } catch (IOException e) {
            e.printStackTrace();// 发生网络异常
        } finally {
            getMethod.releaseConnection();// 释放连接
        }
		return null;
    }

    public String postMethod(String url, Map<String, String> datas) {
       
    	PostMethod postMethod = new PostMethod(url);
    	String xml = MessageConstant.header + MessageConstant.body
				+ MessageConstant.tailer;
    	String targetXml = xml.replace(MessageConstant.serialNumber, "123456")          //流水号
				.replace(MessageConstant.mobile,datas.get(MessageConstant.mobile) )           //电话号码
				.replace(MessageConstant.smsContent, datas.get(MessageConstant.smsContent)); 
    	
        try {
        	postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            postMethod.setRequestBody(targetXml);
            HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
            managerParams.setConnectionTimeout(30000);     // 设置连接超时时间(单位毫秒)
            managerParams.setSoTimeout(120000);    // 设置读数据超时时间(单位毫秒)
            int statusCode = httpClient.executeMethod(postMethod);// 执行postMethod
            //HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
            if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {//301或者302
                Header locationHeader = postMethod.getResponseHeader("location");// 从头中取出转向的地址
                String location = null;
                if (locationHeader != null) {
                    location = locationHeader.getValue();
                    System.out.println("The page was redirected to:" + location);
                } else {
                    System.err.println("Location field value is null.");
                }
            }
            if(statusCode == HttpStatus.SC_OK){
            	return postMethod.getResponseBodyAsString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }
    
    public void cookieSet(String domain,String path,String name,String value) {
        httpClient.getParams().setCookiePolicy(CookiePolicy.RFC_2109);// RFC_2109是支持较普遍的一个，还有其他cookie协议
        HttpState initialState = new HttpState();
        Cookie cookie = new Cookie();
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setName(name);
        cookie.setValue(value);
        initialState.addCookie(cookie);
        httpClient.setState(initialState);
    }
    
    public String cookieGet(String name) {
        Cookie[] cookies = httpClient.getState().getCookies();   
        System.out.println("Present cookies: ");   
        for (int i = 0; i < cookies.length; i++) {
            System.out.println(" - " + cookies[i].toExternalForm());   
            System.out.println(" - domain=" + cookies[i].getDomain());   
            System.out.println(" - path=" + cookies[i].getPath());  
            if(cookies[i].getName().equals(name)){
            	return cookies[i].getValue();
            }
        }
		return null; 
    }
    
}
