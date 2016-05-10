/**
 * 
 */
package cn.myapp.model;

/**
 * @author teason
 *
 */
public class ResultObj {
	
	private String returnCode ;
	private String returnMsg ;
	private Object returnData ;	
	
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public Object getReturnData() {
		return returnData;
	}
	public void setReturnData(Object returnData) {
		this.returnData = returnData;
	}
	
	public ResultObj(Object returnData) {
		super();
		String returnCode = "1001" ;
		String returnMsg = "接口调用成功" ;
		
		if (returnData == null) 
		{
			returnCode = "0" ;
			returnMsg = "调用失败" ;
		}
		
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.returnData = returnData;
	}
	
	public ResultObj(String returnCode,String returnMsg,Object returnData) {
		super();
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.returnData = returnData;
	}
	
}
