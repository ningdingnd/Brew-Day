package brewDay;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Brew {
	private int batchSize;
	private String date;
	private String time;
	
	//initialize class
	public Brew(int batchSize) {
		this.batchSize = batchSize;
        Date date = new Date();// get system time
		SimpleDateFormat sdf = new SimpleDateFormat();// format time 
        sdf.applyPattern("yyyy-MM-dd");
		this.date = sdf.format(date);
		sdf.applyPattern("HH:mm:ss");
		this.time = sdf.format(date);
	}
	
	//not finish yet
	//subtract the storage ingredient with recipe ingredient
	public Boolean implement(Recipe recipe) {
		//search for database and get the storage ingredient with name and subtract
		return false;
	}
	
	//not finish yet
	//compare the batch size with the total capacity of equipment
	public Boolean verifyBatchSize() {
		//search database to get the total capacity of ingredient
		return false;
	}
	
	//create a new note
	public Boolean writeNote(String content) {
		Note note = new Note(content);
		return true;
	}
}
