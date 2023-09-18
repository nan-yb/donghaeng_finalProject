package kr.or.ddit.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.json.XML;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class HttpUtils {
	public static String jsonPostData(String tempUrl, MultiValueMap<String, Object> params) throws IOException{
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters()
        .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		ResponseEntity<String> response = restTemplate.postForEntity( tempUrl, params, String.class );
		return response.getBody();
	}
	
	public static String jsonGetData(String tempUrl, HttpHeaders headers) throws IOException{
		RestTemplate restTemplate = new RestTemplate(); 
		restTemplate.getMessageConverters()
        .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		
		HttpEntity entity = new HttpEntity("parameters", headers); 
		URI url=URI.create(tempUrl); 
		ResponseEntity response= restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		
		return (String) response.getBody();
	}

	public static String getData(String tempUrl) throws IOException {
		StringBuffer buffer = new StringBuffer();

		URL url = new URL(tempUrl);

		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("CONTENT-TYPE", "text/xml");

		try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));) {

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				buffer.append(inputLine.trim());
			}

			org.json.JSONObject xmlJSONObj = XML.toJSONObject(buffer.toString());
			String jsonPrettyPrintString = xmlJSONObj.toString(4);
			return jsonPrettyPrintString;
		}
	}
}
