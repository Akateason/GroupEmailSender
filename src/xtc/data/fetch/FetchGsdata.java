package xtc.data.fetch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import xtc.http.HttpRequest;
import xtc.json.JsonToMap;
import xtc.model.gsdata.Nickname;

public class FetchGsdata {

	//"http://114.55.74.220:8080/gsdata/api" ;
	//"http://localhost:8080/gsdata/api" ;
	//"http://wei.subaojiang.com:8080/gsdata/api" ;
	private final static String kUrlGsdataApi	= "http://114.55.74.220:8080/gsdata/api" ; 
	private final static String kSName7Days	  	= "wx/opensearchapi/nickname_order_list" ;
	private final static String kParamJsonStr 	= "{\"num\":7,\"sort\":\"asc\",\"wx_nickname\":\"日本流行每日速报\"}" ;
	
	/**
	 * 前天 速报 详细
	 * @return
	 */
	public Nickname fetchSubaoNickname() {
		// REQUEST .
		String resultStr = HttpRequest.sendGet(kUrlGsdataApi, "spaceName=" + kSName7Days + "&jsonStr=" + kParamJsonStr) ; 
		// PARSE .
		JsonObject resultMap = JsonToMap.parseJson(resultStr) ;		
		JsonObject resultData = resultMap.get("returnData").getAsJsonObject() ;
		JsonArray itemsList = resultData.get("items").getAsJsonArray() ;
		JsonElement lastDayInfoElement = itemsList.get((itemsList.size() - 2)) ; // 前天的 .
		Gson gson = new Gson() ;
		// GET NICKNAME INFO .
		Nickname nickname = gson.fromJson(lastDayInfoElement, Nickname.class) ;
		return nickname ;
	}
		
	private final static String kResultDay 		= "wx/wxapi/result_day" ;
	/**
	 * 前日 排名
	 */
	public String fetchSortFromTwoDaysAgo() {
		
		String returnString = "【前日竞品排名】\n\n" ;
		
		Calendar calendar = Calendar.getInstance() ;
		calendar.add(Calendar.DATE, -2);
		String dayBeforeYesterday = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()) ;
//		System.out.println(dayBeforeYesterday);
				
		// SETUP .
		String spaceName = "spaceName=" + kResultDay ;
		String jsonStr = "&jsonStr=" 
						+ "{\"order\":\"desc\",\"day\":\"" 
						+ dayBeforeYesterday
						+ "\",\"groupid\":\"38504\",\"sort\":\"wci\"}" ;
		// REQUEST .
		String resultStr = HttpRequest.sendGet(kUrlGsdataApi, spaceName + jsonStr) ;
		// PARSE .
		JsonObject resultMap = JsonToMap.parseJson(resultStr) ;		
		JsonObject resultData = resultMap.get("returnData").getAsJsonObject() ;
		JsonArray rowsList = resultData.get("rows").getAsJsonArray() ;
		Gson gson = new Gson() ;
		ArrayList<Nickname> nicknames = new ArrayList<>() ; 
		for (JsonElement jsonElement : rowsList) {
			Nickname nick = gson.fromJson(jsonElement, Nickname.class) ;
			nicknames.add(nick) ;
		}
//		System.out.println(nicknames);

		// GET TOP3 .
		for (int i = 0; i < 3; i++) {
			Nickname nick = nicknames.get(i) ;
			String topStr = "TOP" + (i+1) + " : " + nick.getWx_nickname() + " , 最高阅读数 : " + nick.getReadnum_max() + "\n";  
			returnString += topStr ;
		}
		
		// SORT OF SUBAO
		String subaoSortInfo = "\n日本流行每日速报 : 排名" ;
		int sortNumber = 1 ;
		for (Nickname nickname : nicknames) {
			if (nickname.getWx_name().equals("zhepen") ) {
				subaoSortInfo += sortNumber + " 最高阅读数 : " + nickname.getReadnum_max() ;
				break ;
			}
			sortNumber ++ ;
		}
		
		returnString += subaoSortInfo + "\n\n" ;
		
		return returnString ;
	}
	
}
