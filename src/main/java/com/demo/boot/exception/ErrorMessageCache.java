package com.demo.boot.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
@Configuration
public class ErrorMessageCache {
	private static final Map<String,String> errorCodeCache = new HashMap<String, String>();
	private static final Map<String,String> errorMsgCache = new HashMap<String, String>();
	private static Logger logger = LoggerFactory.getLogger(ErrorMessageCache.class);
	private static final String regex = "^[1-9][0-9]*";
	@PostConstruct
	public static void init(){
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = null;
		try {
			resources = resolver.getResources("classpath:/errorMsg/*.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(null != resources && resources.length>0){
			for(Resource s : resources){
				try {
					Properties p = new Properties();     
					p.load(s.getInputStream());
					for(Entry<Object, Object> entry : p.entrySet()){
						String key = (String)entry.getKey();
						String value = new String(
								String.valueOf(entry.getValue()).getBytes("ISO-8859-1"),"utf-8");
						if(!Pattern.matches(regex, key)){
							continue;
						}
						if(key.endsWith("CODE")){
							errorCodeCache.put(key.toUpperCase(), value);
						}
						if(key.endsWith("MSG")){
							errorMsgCache.put(key.toUpperCase(), value);
						}
					}
				} catch (IOException e) {
					logger.error(e.toString(),e);
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 获取缓存的错误信息
	 * @param codeKey
	 * @return
	 */
	public static String getMsgCache(String codeKey){
		codeKey = codeKey.toLowerCase();
		String msgKey = codeKey.substring(0,codeKey.lastIndexOf("CODE"))+"MSG";
		String value = null;
		if(errorMsgCache.containsKey(msgKey)){
			value = errorMsgCache.get(msgKey);
		}
		return value;
	}
	/**
	 * 获取缓存错误码
	 * @param codeKey
	 * @return
	 */
	public static Long getCodeCashe(String codeKey){
		Long code = null;
		if(errorCodeCache.containsKey(codeKey)){
			code = Long.valueOf(errorCodeCache.get(codeKey));
		}
		return code;
	}
	
}
