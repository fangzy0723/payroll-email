package cn.com.sinosoft.payrollemail.util;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述：字符串工具类 集成了Apache commons<br>
 */
public class StringUtils {
    static final Pattern PATTERN = Pattern.compile("\\s*|\t|\r|\n");

	public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Matcher m = PATTERN.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
	
	/**
	 * 方法名称: isBlank<br>
	 * 描述：判断数组对象是否为空值 , 如果是 ""," ","null",null 则返回true, 对于Collection.size<1也空 <br>
	 * @return
	 */
    public static boolean isBlank(Object...objects){
        Boolean result = false ;
        for (Object object : objects) {
            if(object == null || "".equals(object.toString().trim()) 
                    || "null".equals(object.toString().trim())
                    || "[null]".equals(object.toString().trim())
                    || "[]".equals(object.toString().trim())){
                result = true ; 
                break ; 
            }
        }
        return result ; 
    }
    
	/**
	 * 方法名称: isNotBlank<br>
	 * 描述：判断是否不为空, 如果是 ""," ","null",null 则返回false<br>
	 * @return
	 */
    public static boolean isNotBlank(Object...objects){
        return !isBlank(objects);
    }
	
    
    public static String getYYYYMM(){
    	Calendar c = Calendar.getInstance();
		StringBuffer s = new StringBuffer();
		s.append(c.get(Calendar.YEAR));
		int m = c.get(Calendar.MONTH)+1;
		if(m < 10){
			s.append("0").append(m);
		} else {
			s.append(m);
		}
		return s.toString();
    }
    
}
