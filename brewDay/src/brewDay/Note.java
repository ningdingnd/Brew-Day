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
	
	//initialize class
	public Note(String content, String date) {
		this.content = content;
		this.createDate = date;
	}
	
	//edit the content of note
	public Boolean edit(String content) {
		this.content = content;	
		return true;
	}
	
	public String getContent() {
		return this.content;
	}
	

	public String getDate() {
		return this.createDate;
	}
}
