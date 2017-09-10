package com.demo.boot.exception;
/**
 * 自定义业务异常
 * @author lism
 *
 */
public class CustomException extends RuntimeException{
	private static final long defaultCode = 10000L;
	private static final String defaultMsg = "未知异常";

	/**
	 * 
	 */
	private static final long serialVersionUID = 4311453872960987357L;
	
	private Long code;
	
	private String msg;
	
	protected CustomException(){
		super();
	}
	
	protected CustomException(Long code,String msg){
		this.code = code;
		this.msg = msg;
	}
	/**
	 * 
	 * @param msg 错误信息
	 * @param e
	 */
	public CustomException(String msg,Throwable e){
		this.code = defaultCode;
		this.msg = msg;
		super.initCause(e);
	}
	/**
	 * 错误codeKey
	 * @param codeKey
	 */
	public CustomException(String codeKey){
		
		Long code = ErrorMessageCache.getCodeCashe(codeKey);
		String msg = ErrorMessageCache.getMsgCache(codeKey);
		if(null == code || code.equals(0)){
			code = defaultCode;
		}
		if(null == msg ||"".equals(msg)){
			msg = defaultMsg;
		}
		this.code = code;
		this.msg = msg;
		
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
