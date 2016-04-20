package xtc.model.gsdata;


/**  
 * @author teason
 * 公众号
 */
public class Nickname {

	private String	  	  wx_name ;          // 公众号英文
	private String	  	  wx_nickname ;      // 公众号
	
	private String		  url_num ;           // 文章数
	private String		  url_num_10w ;       // 文章达10w+的数量(负数为下降)
	private String		  url_num_10w_up ;    // 文章达10w+的数量的增量(负数为下降)
	private String		  readnum_all ;       // 总阅读
	private String		  readnum_all_up ;    // 总阅读的增量(+-)
	private String		  readnum_av ;        // 平均阅读
	private String		  readnum_av_up ;     // 平均阅读的增量(负数为下降)
	private String		  likenum_all ;       // 总点赞
	private String		  likenum_all_up ;    // 总点赞的增量(负数为下降)
	private String	  	  likenum_av ;        // 平均点赞
	private String		  likenum_av_up ;     // 平均点赞的增量 (负数为下降)
	private String		  readnum_max ;       // 最大阅读数
	private String		  likenum_max	;       // 最大点赞数
	private String    	  likenum_readnum_rate ; // 阅读数与点赞数的比率
	private String    	  wci ;                  // 微信传播指数
	private String    	  wci_up ;               // 微信传播指数 增量
	private String		  rowno ;             // 排名
	private String		  rowno_up ;          // 排名上升增量
	private String   	  result_day ;       // 排名日期
	private String		  url_times ;         // 该公众号在上述日期中发文次数
	private String		  url_times_up ;      // 发文次数相对上次的增量（负数即为下降）
	private String		  url_times_readnum ; // 该公众号在上述日期中发文的总阅读数
	private String		  url_times_readnum_up ; // 发文的总阅读数相对上次的增量（负数即为下降）
	private String		  url_num_up ;        // 文章总数相对上次的增量（负数即为下降）	
	
	
	public String getWx_name() {
		return wx_name;
	}
	public void setWx_name(String wx_name) {
		this.wx_name = wx_name;
	}
	public String getWx_nickname() {
		return wx_nickname;
	}
	public void setWx_nickname(String wx_nickname) {
		this.wx_nickname = wx_nickname;
	}	
	public String getUrl_num() {
		return url_num;
	}
	public void setUrl_num(String url_num) {
		this.url_num = url_num;
	}
	public String getUrl_num_10w() {
		return url_num_10w;
	}
	public void setUrl_num_10w(String url_num_10w) {
		this.url_num_10w = url_num_10w;
	}
	public String getUrl_num_10w_up() {
		return url_num_10w_up;
	}
	public void setUrl_num_10w_up(String url_num_10w_up) {
		this.url_num_10w_up = url_num_10w_up;
	}
	public String getReadnum_all() {
		return readnum_all;
	}
	public void setReadnum_all(String readnum_all) {
		this.readnum_all = readnum_all;
	}
	public String getReadnum_all_up() {
		return readnum_all_up;
	}
	public void setReadnum_all_up(String readnum_all_up) {
		this.readnum_all_up = readnum_all_up;
	}
	public String getReadnum_av() {
		return readnum_av;
	}
	public void setReadnum_av(String readnum_av) {
		this.readnum_av = readnum_av;
	}
	public String getReadnum_av_up() {
		return readnum_av_up;
	}
	public void setReadnum_av_up(String readnum_av_up) {
		this.readnum_av_up = readnum_av_up;
	}
	public String getLikenum_all() {
		return likenum_all;
	}
	public void setLikenum_all(String likenum_all) {
		this.likenum_all = likenum_all;
	}
	public String getLikenum_all_up() {
		return likenum_all_up;
	}
	public void setLikenum_all_up(String likenum_all_up) {
		this.likenum_all_up = likenum_all_up;
	}
	public String getLikenum_av() {
		return likenum_av;
	}
	public void setLikenum_av(String likenum_av) {
		this.likenum_av = likenum_av;
	}
	public String getLikenum_av_up() {
		return likenum_av_up;
	}
	public void setLikenum_av_up(String likenum_av_up) {
		this.likenum_av_up = likenum_av_up;
	}
	public String getReadnum_max() {
		return readnum_max;
	}
	public void setReadnum_max(String readnum_max) {
		this.readnum_max = readnum_max;
	}
	public String getLikenum_max() {
		return likenum_max;
	}
	public void setLikenum_max(String likenum_max) {
		this.likenum_max = likenum_max;
	}
	public String getLikenum_readnum_rate() {
		return likenum_readnum_rate;
	}
	public void setLikenum_readnum_rate(String likenum_readnum_rate) {
		this.likenum_readnum_rate = likenum_readnum_rate;
	}
	public String getWci() {
		return wci;
	}
	public void setWci(String wci) {
		this.wci = wci;
	}
	public String getWci_up() {
		return wci_up;
	}
	public void setWci_up(String wci_up) {
		this.wci_up = wci_up;
	}
	public String getRowno() {
		return rowno;
	}
	public void setRowno(String rowno) {
		this.rowno = rowno;
	}
	public String getRowno_up() {
		return rowno_up;
	}
	public void setRowno_up(String rowno_up) {
		this.rowno_up = rowno_up;
	}
	public String getResult_day() {
		return result_day;
	}
	public void setResult_day(String result_day) {
		this.result_day = result_day;
	}
	public String getUrl_times() {
		return url_times;
	}
	public void setUrl_times(String url_times) {
		this.url_times = url_times;
	}
	public String getUrl_times_up() {
		return url_times_up;
	}
	public void setUrl_times_up(String url_times_up) {
		this.url_times_up = url_times_up;
	}
	public String getUrl_times_readnum() {
		return url_times_readnum;
	}
	public void setUrl_times_readnum(String url_times_readnum) {
		this.url_times_readnum = url_times_readnum;
	}
	public String getUrl_times_readnum_up() {
		return url_times_readnum_up;
	}
	public void setUrl_times_readnum_up(String url_times_readnum_up) {
		this.url_times_readnum_up = url_times_readnum_up;
	}
	public String getUrl_num_up() {
		return url_num_up;
	}
	public void setUrl_num_up(String url_num_up) {
		this.url_num_up = url_num_up;
	}
	
}
