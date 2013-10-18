package app.itinerary.mvc.model;


/**
 * @author Devang Shah
 * The Model.
 */
public class Itinerary /*extends Observable */{
	
	private String source = "";
	private String destination = "";
	private int depYear = 0;
	private int returnYear = 0;
	private int depMonth = 0;
	private int returnMonth = 0;
	private int depDayOfMonth = 0;
	private int returnDayOfMonth = 0;
	private String depTime = null;
	private String returnTime = null;
	private int noOfPassengers = 0;
	
	/** Default constructor with default values.*/
	public Itinerary ()
	{
		new Itinerary("", "", 0, 0, 0, 0, 0, 0, "","", 0);
	}
	
	/** Fully qualified constructor.*/
	public Itinerary (String source, String destination, int depYear, int returnYear, int depMonth, int returnMonth, 
			int depDayOfMonth, int returnDayOfMonth, String depTime, String returnTime, int noOfPassengers)
	{
		this.source = source;
		this.destination = destination;
		this.depYear = depYear;
		this.returnYear = returnYear;
		this.depMonth = depMonth;
		this.returnMonth = returnMonth;
		this.depDayOfMonth = depDayOfMonth;
		this.returnDayOfMonth = returnDayOfMonth;
		this.depTime = depTime;
		this.returnTime = returnTime;
		this.noOfPassengers = noOfPassengers;
	}
	
	/** Public getters and setters*/
	public String getSource() 
	{
		return source;
	}
	
	public void setSource(String source) 
	{
		this.source = source;
	}
	
	public String getDestination() 
	{
		return destination;
	}
	
	public void setDestination(String destination) 
	{
		this.destination = destination;
	}
	
	public int getDepYear() 
	{
		return depYear;
	}

	public void setDepYear(int depYear) 
	{
		this.depYear = depYear;
	}

	public int getReturnYear() 
	{
		return returnYear;
	}

	public void setReturnYear(int returnYear) 
	{
		this.returnYear = returnYear;
	}

	public int getDepMonth() 
	{
		return depMonth;
	}

	public void setDepMonth(int depMonth) 
	{
		this.depMonth = depMonth;
	}

	public int getReturnMonth() 
	{
		return returnMonth;
	}

	public void setReturnMonth(int returnMonth) 
	{
		this.returnMonth = returnMonth;
	}

	public int getDepDayOfMonth() 
	{
		return depDayOfMonth;
	}

	public void setDepDayOfMonth(int depDayOfMonth) 
	{
		this.depDayOfMonth = depDayOfMonth;
	}

	public int getReturnDayOfMonth() 
	{
		return returnDayOfMonth;
	}

	public void setReturnDayOfMonth(int returnDayOfMonth) 
	{
		this.returnDayOfMonth = returnDayOfMonth;
	}
	
	public String getDepTime() 
	{
		return depTime;
	}
	
	public void setDepTime(String depTime) 
	{
		this.depTime = depTime;
	}
	
	public String getReturnTime() 
	{
		return returnTime;
	}
	
	public void setReturnTime(String returnTime) 
	{
		this.returnTime = returnTime;
	}
	
	public int getNoOfPassengers() 
	{
		return noOfPassengers;
	}

	public void setNoOfPassengers(int noOfPassengers) 
	{
		this.noOfPassengers = noOfPassengers;
	}
}
