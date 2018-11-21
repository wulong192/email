package com.senqi.util;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpDownUtil {

	
	public static void down(List<String> urls) throws Exception {
		
	CloseableHttpClient httpClient=HttpClients.createDefault();
	
	
	for (String url : urls) {
		
		HttpGet get=new HttpGet(url);
		
		HttpResponse res=httpClient.execute(get);
		
		HttpEntity hEntity=res.getEntity();
		
		InputStream is = hEntity.getContent();
		
		String fileName = UUID.randomUUID().toString() + ".jpg";
		
		File file=new File("d://pics",fileName);
		FileUtils.copyToFile(is,file);
		
		System.out.println(fileName + "œ¬‘ÿ≥…π¶");
		
	}
	httpClient.close();
	}
}
