package mew;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class dateCal {

	public static Long reda(String date,String keep) throws ParseException{
		
		Date t = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String now = df.format(t);
		
		Long cal = df.parse(date).getTime()-df.parse(now).getTime();
		Long re = -cal/1000/60/60/24;
		
		Long k = Long.parseLong(keep);
		Long res = k-re;
		if(res<0){
			res = (long) 0;
		}
		
		return res;
	}
}
