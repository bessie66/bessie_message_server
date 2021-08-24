/*
 * Copyright 2016 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.pawin.linebotspringboot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.LineBotMessages;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class IntegrationTest {
    @RestController
    @Slf4j
    public static class MyController {
        @Autowired
        private LineMessagingClient lineMessagingClient;
        
        
//        @Autowired
//        private RestTemplate restTemplate;
        private RestTemplate restTemplate = new RestTemplate();

        
//        @Value("classpath:images/20210809_001.jpg")
//        private Resource resource;

        
        @Autowired
        ResourceLoader resourceLoader;
        
        
        public void writeImage() throws Exception {
        	Resource resource = resourceLoader.getResource("classpath:/images/6.jpg");
            
        	
        	System.out.println(resource.getFile());
        	System.out.println(resource.getFile().exists());
        	
        	InputStream inputStream = resource.getInputStream();
        	byte[] buffer = IOUtils.toByteArray(inputStream);
        	
        	
        	final String picName = "src/main/resources/images/bessie20210821.jpg";
        	
        	File targetFile = new File(picName);
//        	
        	OutputStream outStream = new FileOutputStream(targetFile);
        	outStream.write(buffer);
        	IOUtils.closeQuietly(outStream);
        	
//        	ImageIO.write((RenderedImage)inputStream, "png", f);
        	
        	
        	
//            FileOutputStream os = new FileOutputStream("classpath:/images/6NEW.png");
//            ImageIO.write((RenderedImage) inputStream, "png", os);
            
//            ImageIO.write(inputStream, "png", "/images/6.png");
//            FileUtils.copyFile(screenshot, new File(os));
         
        	log.info("writeImage END ");
        }
        
        
        
        
//        @PostMapping("/alex")
//        public ResponseEntity<?> alex() throws Exception {
//			
//        	log.info("ALEX====================$$$$$$$$$=======");
//            
//        	try {
//        		
//        		
//        		URL url =new URL("http://localhost:8080/images/6.jpg");
//        		BufferedImage image = ImageIO.read(url);
//        		ImageIO.write(image, "jpg",new File("images2/888.jpg"));
//
//        		
//        		
//        		
////        		ClassPathResource resource = new ClassPathResource("classpath:/images/6.jpg");
////	            File sourceFile = resource.getFile();
////	             
////	            log.info(String.valueOf(sourceFile.exists()));
//////	            log.info(sourceFile.getPath());
//        		
////        		Resource resource = new Re       //resourceLoader.getResource("classpath:/images_test/6.jpg");
////        		ClassPathResource resource = new ClassPathResource("classpath:/images/6.jpg");
////                File sourceFile = resource.getFile();
////                log.info(String.valueOf(sourceFile.exists()));
//        	}catch(Exception e) {
//        		e.printStackTrace();
//        	}
//        	
//        	try {
////        		ClassPathResource resource = new ClassPathResource("6.jpg");
////                File sourceFile = resource.getFile();
////                log.info(String.valueOf(sourceFile.exists()));
//        	}catch(Exception e) {
//        		e.printStackTrace();
//        	}
//            
//            
////        	Resource resourceTarget = new ClassPathResource("classpath:images/6.jpg");
////        	log.info(String.valueOf(resourceTarget.getFile().exists()));
//            
//            
////            File sourceFile = resource.getFile();
////           
////            log.info(String.valueOf(sourceFile.exists()));
////            log.info(sourceFile.getPath());
//        	
////        	Resource resource = new ClassPathResource("classpath:images/6.jpg");
////        	log.info(resource.getFile().getPath());
//        	
////        	Resource resourceTarget = new ClassPathResource("file:images2/999.jpg");
////        	log.info(resourceTarget.getFile().getPath());
////        	log.info(String.valueOf(resourceTarget.getFile().exists()));
////        	Resource resource = new ClassPathResource("classpath:images/999.jpg");
////        	BufferedImage bi = ImageIO.read(new ClassPathResource("classpath:images/6.jpg").getFile());
//        	
//        	
//        	
////        	OutputStream out = response.getOutputStream();
////        	ImageIO.write(bi, "png", out);
////        	out.close();
//        	
//        	
////        	ClassPathResource classPathResource = new ClassPathResource("images");
////        	InputStream inputStream = classPathResource.getInputStream();
////        	File somethingFile = File.createTempFile("AAAA", ".pdf");
////        	
////        	FileUtils.copyInputStreamToFile(inputStream, somethingFile);
//        	
////            log.info("@@@@@@@@@@@@@@@@@@@@@@@@");
////            ClassPathResource resource = new ClassPathResource("images");    
////            File sourceFile = resource.getFile();
////            
////            
////            final String picName = sourceFile.getPath()+"/"+content.getId()+".jpg";
////            log.info("ALEX=========picName:"+picName);
////            File targetFile = new File(picName);
////            OutputStream outStream = new FileOutputStream(targetFile);
////            outStream.write(imageBytes);
////            IOUtils.closeQuietly(outStream);
//        	
//        	return null;
//        	
//        }
//        
        
        

        @PostMapping("/callbackArray")
        public void callbackArray(@NonNull @LineBotMessages List<Event> events) throws Exception {
//        public void callbackArray(@NonNull List<Event> events) throws Exception {
        	
        	
        	try {
	        	log.info("Bessie callbackArray request: "+ events);
	        	
	        	log.info("Bessie callbackArray events.size(): "+ events.size());
	        	
	        	
	            log.info("Got request LIST >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:  ", events);
	
	           
	//            writeImage();
	            
	            for (Event event : events) {
	                this.handleEvent(event);
	            }
	            
	//            System.out.println("PUSH API START ");
	//            this.post("push api success 00001 20210728");
	            log.info("callbackArray END ");
            }catch (Exception e) {
            	
            	log.error("callbackArray END >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ e);
            	
            }
            
        }
        
        
        

        private void handleEvent(Event event) throws Exception {
        	log.info("==============================OKOKOK==========================");
        	
            if (event instanceof MessageEvent) {
            	log.info("==============================文字OK==========================");
            	
            	
                MessageContent content = ((MessageEvent) event).getMessage();
                
             	//TextMessageContent
                if (content instanceof TextMessageContent) {
                    String text = ((TextMessageContent) content).getText();
                    BotApiResponse response = lineMessagingClient.replyMessage(
                            new ReplyMessage(((MessageEvent) event).getReplyToken(),
                                             new TextMessage(text)))
                                       .get();
                }
                
                
                //ImageMessageContent
                if (content instanceof ImageMessageContent) {
                	log.info(" OK OK OK ImageMessageContent >>>>>>>>>>>>>>>>Start>>>>>>>"+content.getId());
                	log.info(event.toString());
//                       ImageMessageContent content = ((MessageEvent) event).getMessage();
                	
//                       String replyToken = ((MessageEvent) event).getReplyToken();
                      
                       
                       
//                       try {
                    	 //Using RestTemplate
                    	   HttpHeaders headers = new HttpHeaders();
                    	   headers.set("Content-Type", "application/json");
                    	   headers.set("Authorization", "Bearer KDPIrnSbnjuvze5jDb0RQVaPbkmDiV29TFLeCBb1TbFUYTVDE63JpzXG6ZT50kcEn1CiQDZUgCYhQFfFx7XPM4CQ74H7k6XExrqfnsaCkRffcTq++U8w2rA6lligH3lXkkL2xefjobvEIwwKyiN8IQdB04t89/1O/w1cDnyilFU=");
                    	   HttpEntity<String> entity = new HttpEntity<String>(headers);
//                    	   ResponseEntity<String> result = restTemplate.exchange("https://api-data.line.me/v2/bot/message/14482888801702/content", HttpMethod.GET, entity, String.class);
                    	   log.info(">>>>>>>>>>>>>>>>>getHeaders>>>>>>>>>>>>>>>>>>>> : "+entity.getHeaders());
                    	   log.info(">>>>>>>>>>>>>>>>>getBody>>>>>>>>>>>>>>>>>>>> : "+entity.getBody());
                    	   
                    	   
                    	   ResponseEntity<byte[]> response = restTemplate.exchange("https://api-data.line.me/v2/bot/message/"+content.getId()+"/content", HttpMethod.GET, entity, byte[].class);
                    	   byte[] imageBytes = response.getBody();
                    	   InputStream is = new ByteArrayInputStream(imageBytes);
                    	   BufferedImage newBi = ImageIO.read(is);
                    	   ImageIO.write(newBi, "jpg",new File("images2/"+content.getId()+".jpg"));
                    	   
                    	   
                    	   
                    	   
                    	   
                    	   
//                    	 //save image to project
//                    	   ResponseEntity<byte[]> response = restTemplate.exchange("https://api-data.line.me/v2/bot/message/"+content.getId()+"/content", HttpMethod.GET, entity, byte[].class);
//                    	   byte[] imageBytes = response.getBody();
//                    	// convert byte[] back to a BufferedImage
//                           InputStream is = new ByteArrayInputStream(imageBytes);
//                           BufferedImage newBi = ImageIO.read(is);
////                           newBi.getType();
                           
                           
//                           // add a text on top on the image, optional, just for fun
//                           Graphics2D g = newBi.createGraphics();
//                           g.setFont(new Font("TimesRoman", Font.BOLD, 30));
//                           g.setColor(Color.BLUE);
//                           g.drawString("BESSIE LINE BOT IMAGE SUCCESS =======> "+content.getId(), 100, 100);
//                           Path target = Paths.get("D:\\BESSIEtest\\"+content.getId()+".jpg");
//                           ImageIO.write(newBi, "png", target.toFile());
//                        log.info("ALEX====================$$$$$$$$$=======");
//                        ClassPathResource resource = new ClassPathResource("6.jpg");
//                        File sourceFile = resource.getFile();
//                       
//                        log.info(String.valueOf(sourceFile.exists()));
//                        log.info(sourceFile.getPath());
                        
//                        log.info("@@@@@@@@@@@@@@@@@@@@@@@@");
//                        ClassPathResource resource = new ClassPathResource("images");    
//                        File sourceFile = resource.getFile();
//                        
//                        
//                        final String picName = sourceFile.getPath()+"/"+content.getId()+".jpg";
//                        log.info("ALEX=========picName:"+picName);
//                        File targetFile = new File(picName);
//                        OutputStream outStream = new FileOutputStream(targetFile);
//                        outStream.write(imageBytes);
//                        IOUtils.closeQuietly(outStream);
                        
                        
//                    	final String picName = "images/"+content.getId()+".jpg";
//                    	
//                    	File targetFile = new File(picName);
////                    	
//                    	OutputStream outStream = new FileOutputStream(targetFile);
//                    	outStream.write(imageBytes);
//                    	IOUtils.closeQuietly(outStream);
                           
                	   //send image to channel
                    	BotApiResponse responseToCannel = lineMessagingClient.replyMessage(
                                new ReplyMessage(((MessageEvent) event).getReplyToken(),new ImageMessage("https://38eb-122-147-2-198.ngrok.io/images/"+content.getId()+".jpg", "https://38eb-122-147-2-198.ngrok.io/images/"+content.getId()+".jpg"))).get();
//                	   BotApiResponse responseToCannel = lineMessagingClient.replyMessage(
//                             new ReplyMessage(((MessageEvent) event).getReplyToken(),new ImageMessage("https://4b17-2001-b011-3803-14f4-5a-a33a-af73-febb.ngrok.io/images/6.jpg", "https://4b17-2001-b011-3803-14f4-5a-a33a-af73-febb.ngrok.io/images/6.jpg"))).get();
                    	   
                    	   
                    	   
                    	 
                    	  
//                    	   
//                    	   
//                    	 
//                    	   
////                    	   //=============改以下這段
////                    	    resource = new ClassPathResource("/images/14482888801702.jpg"); 
//////                    	    File file = new File("/images/14482888801702.jpg");
////                    	    FileOutputStream fos = new FileOutputStream(file);
////                    	    byte[] strToByte = result.getBody().getBytes();
////                    	    fos.write(strToByte);
////                    	    fos.close();
////                    	    
////                    	    Resource resource = new ClassPathResource("classpath:data.txt");
////                            InputStream inputStream = resource.getInputStream();
////                            try {
////                                byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
////                                String data = new String(bdata, StandardCharsets.UTF_8);
////                                LOGGER.info(data);
////                            } catch (IOException e) {
////                                LOGGER.error("IOException", e);
////                            }
////                    	    
////                    	    //=============
//                    	    
//                    	    
//                    	    
//                    	    
//                    	    
//                    	    
//                    	    
//                    	    
//                    	    
//                    	    
//                    	    
//                    	    
//                    	   
//                 
//                    	   
//                    	   
//                    	   
//                    	   
//                    	   
//                    	   
////                    	   RestTemplate restTemplate = new RestTemplate();
////                    	   final String baseUrl = "https://api-data.line.me/v2/bot/message/14482888801702/content";
////                    	   URI uri = new URI(baseUrl);
////                    	   HttpHeaders headers = new HttpHeaders();
////                    	   headers.set("X-COM-PERSIST", "true");  
////                    	   headers.set("X-COM-LOCATION", "USA");
////                    	   HttpEntity<Employee> requestEntity = new HttpEntity<>(null, headers);
////                    	   ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
////                    	   //Verify request succeed
////                    	   Assert.assertEquals(200, result.getStatusCodeValue());
////                    	   Assert.assertEquals(true, result.getBody().contains("employeeList"));
//                           
//                    	   
//                    	   
//                    	   
//                    	   
//                    	   
//                    	   
//                    	      	   

//                	   
//                    	   
//                    	   
////                           MessageContentResponse messageContentResponse = lineMessagingClient.getMessageContent(
////                               content.getId()).get();
//                           
//                           
////                           DownloadedContent jpg = saveContent("jpg", response);
////                           DownloadedContent previewImage = createTempFile("jpg");
////                           system("convert", "-resize", "242x", jpg.path.toString(), previewImage.path.toString());
////                           reply(replyToken, new ImageMessage(jpg.getUri(), previewImage.getUri()));
//                           
////                           BotApiResponse response = lineMessagingClient.replyMessage(
////                                   new ReplyMessage(((MessageEvent) event).getReplyToken(),
////                                                    new TextMessage(text)))
////                                              .get();
//
////                       } catch (InterruptedException | ExecutionException e) {
//                       } catch (Exception e) {
////                           reply(replyToken, new TextMessage("Cannot get image: " + content));
//                           throw new RuntimeException(e);
//                       }
                	
                    	log.info(" ImageMessageContent >>>>>>>>>>>>>>>>END>>>>>>>");
                	
                }
                
            } 
            
            
            else if (event instanceof FollowEvent) {
            	BotApiResponse response = lineMessagingClient.replyMessage(
                        new ReplyMessage(((FollowEvent) event).getReplyToken(),
                                         new TextMessage("follow")))
                                   .get();
            } 
            
            
            
            log.info("handleEvent 結束");
            
            
        }
        
        

        
        
        
        public void post(String text) throws IOException {
        	
        	 System.out.println("11111>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>postpostpostpostpostpostpost ");
        	
//          if (lineProperties.isEnabled() == false) {
//              return;
//          }
          OkHttpClient client = new OkHttpClient.Builder()
                  .connectTimeout(10, TimeUnit.SECONDS)
                  .writeTimeout(10, TimeUnit.SECONDS)
                  .readTimeout(60, TimeUnit.SECONDS)
                  .build();
          HashMap object = new HashMap<>();
//          object.put("to", lineProperties.getTo());
          object.put("to", "U5f115621f3ef3f5a258243489499e9f6");
          List messages = new ArrayList();
          HashMap message = new HashMap<>();
          message.put("type", "text");
          message.put("text", text );
          messages.add(message);
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
                  log.warn("發送失敗" + response.body().toString());
                  //throw new IOException("Unexpected code " + response);

              }
              response.close();
          }
      }
        
        
        
        
        
        
        
    }
    
    
    
    

//    @BeforeClass
//    public static void beforeClass() {
//        server = new MockWebServer();
//        System.setProperty("line.bot.apiEndPoint", server.url("/").toString());
//    }
//
//    @Before
//    public void before() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
//                                      .build();
//    }
//
//    @Test
//    public void missingSignatureTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/callback")
//                                              .content("{}"))
//               .andDo(print())
//               .andExpect(status().isBadRequest())
//               .andExpect(content().string(containsString("Missing 'X-Line-Signature' header")));
//    }
//
//    @Test
//    public void validCallbackTest() throws Exception {
//        server.enqueue(new MockResponse().setBody("{}"));
//        server.enqueue(new MockResponse().setBody("{}"));
//
//        String signature = "ECezgIpQNUEp4OSHYd7xGSuFG7e66MLPkCkK1Y28XTU=";
//
//        InputStream resource = getClass().getClassLoader().getResourceAsStream("callback-request.json");
//        byte[] json = ByteStreams.toByteArray(resource);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/callback")
//                                              .header("X-Line-Signature", signature)
//                                              .content(json))
//               .andDo(print())
//               .andExpect(status().isOk());
//
//        // Test request 1
//        RecordedRequest request1 = server.takeRequest(3, TimeUnit.SECONDS);
//        assertThat(request1.getPath()).isEqualTo("/v2/bot/message/reply");
//        assertThat(request1.getHeader("Authorization")).isEqualTo("Bearer TOKEN");
//        assertThat(request1.getBody().readUtf8())
//                .isEqualTo("{\"replyToken\":\"nHuyWiB7yP5Zw52FIkcQobQuGDXCTA\","
//                           + "\"messages\":[{\"type\":\"text\",\"text\":\"Hello, world\"}],"
//                           + "\"notificationDisabled\":false}");
//
//        // Test request 2
//        RecordedRequest request2 = server.takeRequest(3, TimeUnit.SECONDS);
//        assertThat(request2.getPath()).isEqualTo("/v2/bot/message/reply");
//        assertThat(request2.getHeader("Authorization")).isEqualTo("Bearer TOKEN");
//        assertThat(request2.getBody().readUtf8())
//                .isEqualTo("{\"replyToken\":\"nHuyWiB7yP5Zw52FIkcQobQuGDXCTA\","
//                           + "\"messages\":[{\"type\":\"text\",\"text\":\"follow\"}],"
//                           + "\"notificationDisabled\":false}");
//    }
}
