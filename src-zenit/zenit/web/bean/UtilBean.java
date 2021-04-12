package zenit.web.bean;

import java.io.Serializable;
import java.net.URLEncoder;

/** 
 * Зрна за ставке.
 * @author MV
 * @version 1.0
 */
public class UtilBean implements Serializable{
	private static final long serialVersionUID = -1790045708016532896L;
	
	public String url(String id) {
		try {
			return URLEncoder.encode(id, "UTF-8");
		}catch(Exception ex) {
			return ""; 
		}
	}
}
