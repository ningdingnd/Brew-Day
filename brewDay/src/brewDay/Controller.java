package brewDay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
	protected Workbench w;
	
	public Controller(Workbench w) {
		this.w = w;
	}
}
