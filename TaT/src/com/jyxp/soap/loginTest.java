package com.jyxp.soap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class loginTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			(new loginTest()).sendSms();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private void sendSms() throws Exception {
		String name = "TMHOTS207343";
		String pass="a93241" ;
		String urlString ="https://entservices.totalegame.net/EntServices.asmx";
		String xml = loginTest.class.getClassLoader().getResource("login.xml").getFile();
		String xmlFile = replace(xml, "name", name).getPath();
		xmlFile=replace (xml, "pass", pass).getPath();
        String soapActionString = urlString+"?op=IsAuthenticate";
        URL url = new URL(urlString);
        HttpsURLConnection httpConn = (HttpsURLConnection) url.openConnection();
        File fileToSend = new File(xmlFile);
        byte[] buf = new byte[(int) fileToSend.length()];
        new FileInputStream(xmlFile).read(buf);
        httpConn.setRequestProperty("Content-Length", String.valueOf(buf.length));
        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        httpConn.setRequestProperty("soapActionString", soapActionString);
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        OutputStream out = httpConn.getOutputStream();
        out.write(buf);
        out.close();

        byte[] datas = readInputStream(httpConn.getInputStream());
        String result=new String(datas);
        //��ӡ���ؽ��
        System.out.println("result:" + result);
	}
	
	/**
     * �ļ������滻
     * 
     * @param inFileName Դ�ļ�
     * @param from
     * @param to
     * @return �����滻���ļ�
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    public static File replace(String inFileName, String from, String to)
            throws IOException, UnsupportedEncodingException {
        File inFile = new File(inFileName);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(inFile), "utf-8"));
        File outFile = new File(inFile + ".tmp");
        PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outFile), "utf-8")));
        String reading;
        while ((reading = in.readLine()) != null) {
            out.println(reading.replaceAll(from, to));
        }
        out.close();
        in.close();
        //infile.delete(); //ɾ��Դ�ļ�
        //outfile.renameTo(infile); //����ʱ�ļ�������
        return outFile;
    }
    
    /**
     * ���������ж�ȡ����
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len = inStream.read(buffer)) !=-1 ){
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();//��ҳ�Ķ���������
        outStream.close();
        inStream.close();
        return data;
    }
}
