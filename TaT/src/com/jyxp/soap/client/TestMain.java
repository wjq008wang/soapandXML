package com.jyxp.soap.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
//对接口进行测试
public class TestMain {
	private String url = "https://entservices.totalegame.net/";
	private String charset = "utf-8";
	private HttpClientUtil httpClientUtil = null;
	
	public TestMain(){
		httpClientUtil = new HttpClientUtil();
	}
	
	public void test(){
		String httpOrgCreateTest = "https://entservices.totalegame.net/EntServices.asmx?op=IsAuthenticate";
		Map<String,String> createMap = new HashMap<String,String>();
//	   createMap.put("authuser","TMHOTS207343");
//		createMap.put("authpass","a93241");
//		createMap.put("orgkey","wjq123456");
//	    createMap.put("orgname","hot100");
//		createMap.put("op","IsAuthenticate");
		createMap.put("loginName","TMHOTS207343");
		createMap.put("pinCode","a93241");
		String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,createMap,charset);
		System.out.println("result:"+httpOrgCreateTestRtn);
	}
	
	
	
	public static void main(String[] args){
		TestMain main = new TestMain();
		main.test();
	}
}