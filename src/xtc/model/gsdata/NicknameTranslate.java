package xtc.model.gsdata;

import java.util.HashMap;
import java.util.Map;

public class NicknameTranslate {

	private static Map<String, String> getChineseNameDictionary() 
	{
    	Map<String, String> map = new HashMap<String,String>() ; 
    	map.put("wx_name", "公众号英文") ;
    	map.put("wx_nickname", "公众号") ;
    	map.put("wx_title", "最新文章地址") ;
    	map.put("wx_url_posttime", "最新文章发布时间") ;    	
    	map.put("url_num", "文章数") ;
    	map.put("url_num_10w", "文章达10w+的数量(负数为下降)") ;
    	map.put("url_num_10w_up", "文章达10w+的数量的增量(负数为下降)") ;
    	map.put("readnum_all", "总阅读") ;
    	map.put("readnum_all_up", "总阅读的增量(+-)") ;    	
    	map.put("readnum_av", "平均阅读") ;
    	map.put("readnum_av_up", "平均阅读的增量(负数为下降)") ;
    	map.put("likenum_all", "总点赞") ;
    	map.put("likenum_all_up", "总点赞的增量(负数为下降)") ;
    	map.put("likenum_av", "平均点赞") ;
    	map.put("likenum_av_up", "平均点赞的增量 (负数为下降)") ;
    	map.put("readnum_max", "最大阅读数") ;
    	map.put("likenum_max", "最大点赞数") ;
    	map.put("likenum_readnum_rate", "阅读数与点赞数的比率") ;
    	map.put("wci", "微信传播指数") ;
    	map.put("wci_up", "微信传播指数的增量") ;
    	map.put("rowno", "排名") ;
    	map.put("rowno_up", "排名上升增量") ;
    	map.put("result_day", "排名日期") ;
    	map.put("url_times", "该公众号在上述日期中发文次数") ;
    	map.put("url_times_up", "发文次数相对上次的增量（负数即为下降）") ;
    	map.put("url_times_readnum", "该公众号在上述日期中发文的总阅读数") ;
    	map.put("url_times_readnum_up", "发文的总阅读数相对上次的增量（负数即为下降）") ;
    	map.put("url_num_up", "文章总数相对上次的增量（负数即为下降）") ;
    	    	
		return map ;
	}
	
	/**
	 * 
	 * @param propertyName
	 * @return 
	 */
	public static String getZhongwen(String propertyName) {
		return NicknameTranslate.getChineseNameDictionary().get(propertyName) ;
	}
	
}
