package xtc.mail;

import xtc.field.value.UtilSplit;
import xtc.model.gsdata.Nickname;
import xtc.model.gsdata.NicknameTranslate;


public class EmailContentDisplay 
{

	/**
	 * getEmailContentWillDisplay
	 * @param nickname
	 * @return string .
	 */
	public String getEmailContentWillDisplay(Nickname nickname) 
	{
		String resultString = "" ;
		UtilSplit utilSplit = new UtilSplit() ;
		String[] namelist = utilSplit.getFiledName(nickname) ;

		for (String name : namelist) {
			String val = "" ;
			if (utilSplit.getFieldValueByName(name,nickname) != null) {
				val = utilSplit.getFieldValueByName(name,nickname).toString() ;
			}
			String zhongwen = NicknameTranslate.getZhongwen(name) ;
			if (zhongwen == null) {
				continue ;
			}
			resultString += zhongwen + " : " + val + "\n" ;
		}		
		
		return resultString ;
	}
	
}
