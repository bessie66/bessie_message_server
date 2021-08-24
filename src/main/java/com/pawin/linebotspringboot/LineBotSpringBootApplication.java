package com.pawin.linebotspringboot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


//@LineMessageHandler
@SpringBootApplication
@ComponentScan(basePackages = "com.pawin")
public class LineBotSpringBootApplication {

        static Path downloadedContentDir;
        
	public static void main(String[] args) throws Exception {
		
//		boot("boot boot boot_002");
		
		
		signature();
		
		
                downloadedContentDir = Files.createTempDirectory("line-bot");
		SpringApplication.run(LineBotSpringBootApplication.class, args);
	}
	

	
	
	
	public static void signature() throws Exception{

		String channelSecret = "3124c77394b138c2c7e0496c952fd0e9"; // Channel secret string
		
		
		String httpRequestBody = "{\"destination\": \"bessie\",\"events\": [\"bessie is success\"]}";
//			String httpRequestBody = "{\"destination\": \"bessie\",\"events\": [{\"replyToken\": \"0f3779fba3b349968c5d07db31eab56f\",\"type\": \"message\",\"mode\": \"active\",\"timestamp\": 1462629479859,\"source\": {\"type\": \"user\",\"userId\": \"U5f115621f3ef3f5a258243489499e9f6\"},\"message\": {\"id\": \"325708\",\"type\": \"text\",\"text\":\"Hello, world\"}}]}"; 
		// Request body string
		SecretKeySpec key = new SecretKeySpec(channelSecret.getBytes(), "HmacSHA256");
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(key);
		byte[] source = httpRequestBody.getBytes("UTF-8");
		String signature = Base64.encodeBase64String(mac.doFinal(source));
		
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(signature);
		// Compare x-line-signature request header string and the signature
	}
	


	
	
	public static void boot(String text) throws IOException {
    	
   	 System.out.println("11111>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>boot boot boot boot boot ");
   	
//     if (lineProperties.isEnabled() == false) {
//         return;
//     }
     OkHttpClient client = new OkHttpClient.Builder()
             .connectTimeout(10, TimeUnit.SECONDS)
             .writeTimeout(10, TimeUnit.SECONDS)
             .readTimeout(60, TimeUnit.SECONDS)
             .build();
     HashMap object = new HashMap<>();
//     object.put("to", lineProperties.getTo());
     object.put("to", "U5f115621f3ef3f5a258243489499e9f6");
     List messages = new ArrayList();
     
     //第一
     HashMap message = new HashMap<>();
     message.put("type", "text");
     message.put("text", text );
     messages.add(message);
     //第二
     HashMap message2 = new HashMap<>();
     message2.put("type", "sticker");
     message2.put("packageId", "1" );
     message2.put("stickerId", "2" );
     messages.add(message2);
     
     object.put("messages", messages);
     MediaType JSON = MediaType.parse("application/json; charset=utf-8");
     ObjectMapper mapper = new ObjectMapper();
     RequestBody body = RequestBody.create(JSON, mapper.writeValueAsString(object));
     Request request = new Request.Builder()
             //.header("Authorization", String.format("Bearer %s", lineProperties.getChannelToken()))
     		.header("Authorization", "Bearer KDPIrnSbnjuvze5jDb0RQVaPbkmDiV29TFLeCBb1TbFUYTVDE63JpzXG6ZT50kcEn1CiQDZUgCYhQFfFx7XPM4CQ74H7k6XExrqfnsaCkRffcTq++U8w2rA6lligH3lXkkL2xefjobvEIwwKyiN8IQdB04t89/1O/w1cDnyilFU=")
             .url("https://api.line.me/v2/bot/message/push")
             .post(body)
             .build();
     try (Response response = client.newCall(request).execute()) {
         if (!response.isSuccessful()) {
//             log.warn("發送失敗" + response.body().toString());
             //throw new IOException("Unexpected code " + response);

         }
         response.close();
     }
 }

//        @EventMapping
//        public Message handleTexMessage(MessageEvent<TextMessageContent> e){
//            System.out.println("event : " + e);
//            TextMessageContent message =  e.getMessage();
//            return new TextMessage(message.getText());
//        }
}
