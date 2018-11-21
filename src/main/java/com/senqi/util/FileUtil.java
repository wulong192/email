package com.senqi.util;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class FileUtil {

	public static void up(File file) throws Exception {
		
		CloseableHttpClient client = HttpClients.createDefault();

		HttpPost post = new HttpPost("http://localhost:8080/upfile");

		FileBody body = new FileBody(file);

		MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
		entityBuilder.addPart("file", body);

		HttpEntity entity = entityBuilder.build();
		post.setEntity(entity);
		
		HttpResponse resp = client.execute(post);

		System.out.println(file.getName() + "的上传的状态为：" + resp.getStatusLine().getStatusCode());
		
		client.close();
		
	}

}
