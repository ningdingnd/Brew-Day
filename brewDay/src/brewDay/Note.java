package brewDay;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
	private String content;
	private String createDate;
	
	//initialize class
	public Note(String content) {
		this.content = content;
        Date date = new Date();// get system time
		SimpleDateFormat sdf = new SimpleDateFormat();// format time 
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		this.createDate = sdf.format(date);
	}
	
	//edit the content of note
	public Boolean edit(String content) {
		this.content = content;	
		return true;
	}
}
