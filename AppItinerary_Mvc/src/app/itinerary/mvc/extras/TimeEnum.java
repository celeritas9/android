package app.itinerary.mvc.extras;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Devang Shah
 * The values in Time spinner in the View(main.xml) correspond to this TimeEnum value holder.
 * Just to ease the programming, it's used.
 * It uses a singleton map object that stores indexes of the time values.
 */
public abstract class TimeEnum {
	
	public static final short ANY_TIME = 0;
	public static final short MORNING = 1;
	public static final short NOON = 2;
	public static final short EVENING = 3;
	public static final short LATE_NIGHT = 4;
	
	private static Map<String, Short> map;
	
	/*Use single object, a singleton.*/
	private TimeEnum()
	{
		
	}
	
	public static Map<String, Short> getMap()
	{
		if(map == null)
		{
			map = createMap();
		}
		return map;
	}
	
	private static Map<String, Short> createMap()
	{
		map = new HashMap<String, Short>();
		map.put("any time", ANY_TIME);
		map.put("morning", MORNING);
		map.put("noon", NOON);
		map.put("evening", EVENING);
		map.put("late night", LATE_NIGHT);
		
		return map;
	}
}
