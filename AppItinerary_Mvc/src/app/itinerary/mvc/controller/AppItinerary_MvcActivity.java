package app.itinerary.mvc.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import app.itinerary.mvc.R;
import app.itinerary.mvc.extras.TimeEnum;
import app.itinerary.mvc.model.Itinerary;


/**
 * @author Devang Shah
 * The home activity as well as the Controller class.
 * The application can be implemented with single activity. No need to take another activity.
 */
public class AppItinerary_MvcActivity extends Activity implements OnClickListener{
	
	private static final String CLASS_NAME = AppItinerary_MvcActivity.class.getSimpleName();
	
	private Itinerary itinerary;
	
	private Button submit;
	private Button clear;
	private Toast toast;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        submit = (Button)findViewById(R.id.submit);
        clear = (Button)findViewById(R.id.clear);
        
        submit.setOnClickListener(this);
        clear.setOnClickListener(this);
        
        initSpinners();
    }
    
	@Override
	public void onClick(View v)
	{
		Log.d(CLASS_NAME, ":onClick() >>");
		//Capture all the View components:
		
		EditText source = (EditText)findViewById(R.id.source);
		EditText destination = (EditText)findViewById(R.id.destination);
		
		DatePicker depDate = (DatePicker) findViewById(R.id.depDate);
		DatePicker returnDate = (DatePicker) findViewById(R.id.returnDate);
		
		Spinner depTime = (Spinner) findViewById(R.id.depTime);
		Spinner returnTime = (Spinner) findViewById(R.id.returnTime);
		
		Spinner noOfPassengers = (Spinner) findViewById(R.id.numberOfPassengers);
		
		/*
		Log.d(CLASS_NAME, "source: "+source.getText()+" destination: "+destination.getText()+" departureDate: "+depDate.getCalendarView().getDate()+ 
				" departureTime: "+ (String)depTime.getSelectedItem() + " returnDate: "+returnDate.getCalendarView().getDate() + 
				" returnTime: "+ (String)returnTime.getSelectedItem() + " noOfPassengers: "+ (String)noOfPassengers.getSelectedItem());
		*/
		switch(v.getId())
		{
		case R.id.submit:
			Log.d(CLASS_NAME, ":onClick() >> You clicked submit button.");
			boolean isValidated = performValidations(source, destination, depDate, returnDate, depTime, returnTime);
			if(isValidated)
			{
				Log.d(CLASS_NAME, "Validated successfully.");
				//Store object.
				itinerary = createItinerary(source, destination, depDate, returnDate, depTime, returnTime, noOfPassengers);
				Calendar cal = Calendar.getInstance();
				
				cal.setTime(new Date(depDate.getCalendarView().getDate()));
				String depMonth = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
				
				cal.setTime(new Date(returnDate.getCalendarView().getDate()));
				String returnMonth = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
				
				new AlertDialog.Builder(this).setTitle("Itinerary")
											 .setMessage(new StringBuilder()
											 					.append("Number of Passengers: ").append(itinerary.getNoOfPassengers())
											 					.append("\nFrom: ").append(itinerary.getSource())
											 					.append("\nTo: ").append(itinerary.getDestination())
											 					.append("\nDeparting: ").append(depMonth+" ").append(itinerary.getDepDayOfMonth()+" ").append(itinerary.getDepYear()).append(" "+itinerary.getDepTime())
											 					.append("\nReturning: ").append(returnMonth+" ").append(itinerary.getReturnDayOfMonth()+" ").append(itinerary.getReturnYear()+" ").append(" "+itinerary.getReturnTime()))
											 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
												@Override
												public void onClick(DialogInterface dialog, int which) 
												{
													Log.d(CLASS_NAME, "Positive alert.");
												}
											 })
											 .show();
			}
		break;
		case R.id.clear:
			Log.d(CLASS_NAME, ":onClick() >> You clicked clear button.");
			source.setText("");
			destination.setText("");
			toast = Toast.makeText(getApplicationContext(), "'From' and 'to' fields are cleared.", Toast.LENGTH_SHORT);
			toast.show();
		break;
		}
	}
	
	private Itinerary createItinerary(EditText source, EditText destination,
			DatePicker depDate, DatePicker returnDate, Spinner depTime,
			Spinner returnTime, Spinner noOfPassengers) 
	{
		Itinerary itinerary = new Itinerary();
		
		itinerary.setSource(source.getText().toString());
		itinerary.setDestination(destination.getText().toString());

		itinerary.setDepYear(depDate.getYear());
		itinerary.setDepMonth(depDate.getMonth());
		itinerary.setDepDayOfMonth(depDate.getDayOfMonth());
		itinerary.setDepTime((String)depTime.getSelectedItem());
		
		itinerary.setReturnYear(returnDate.getYear());
		itinerary.setReturnMonth(returnDate.getMonth());
		itinerary.setReturnDayOfMonth(returnDate.getDayOfMonth());
		itinerary.setReturnTime((String)returnTime.getSelectedItem());
		
		itinerary.setNoOfPassengers((Integer.parseInt((String)noOfPassengers.getSelectedItem())));
		
		return itinerary;
	}

	private boolean performValidations(EditText source, EditText destination, 
			DatePicker depDate, DatePicker returnDate, Spinner depTime,
			Spinner returnTime) 
	{
		/* Case: If from or to fields are blank.*/
		if(source.getText().toString().equals("") || destination.getText().toString().equals(""))
		{
			Log.d(CLASS_NAME, "From and to blank.");
			toast = Toast.makeText(getApplicationContext(), "From or to fields should not be blank.", Toast.LENGTH_SHORT);
			toast.show();
			return false;
		}
		
		/* Case: If from or to fields are null.
		 * This should never happen.
		 * */
		else if(source == null || destination == null)
		{
			Log.d(CLASS_NAME, "Source and destination null.");
			toast = Toast.makeText(getApplicationContext(), "From or to fields should not be blank.", Toast.LENGTH_SHORT);
			toast.show();
			return false;
		}
		
		/* Case: If departure date is later than return date.*/
		else if(depDate.getCalendarView().getDate() > returnDate.getCalendarView().getDate())
		{
			Log.d(CLASS_NAME, "Departure date cannot be later than return date.");
			toast = Toast.makeText(getApplicationContext(), "Your return date cannot be earlier than departure date. Please input valid values.", 
					Toast.LENGTH_LONG);
			toast.show();
			return false;
		}
		
		/* Case: If departure date and return date falls on the same day.*/
		else if(depDate.getYear() == returnDate.getYear() && depDate.getMonth() == returnDate.getMonth() 
				&& depDate.getDayOfMonth() == returnDate.getDayOfMonth())
		{
			Map m = TimeEnum.getMap();
			
			/*
			Log.d(CLASS_NAME, "index of depTime: "+ (Short)m.get(depTime.getSelectedItem().toString()) + 
					" index of returnTime: "+(Short)m.get(returnTime.getSelectedItem().toString()));
			*/
			/* Case: Departure time and return time specified as Any time. In this case validate.*/
			if((Short)m.get(depTime.getSelectedItem().toString()) == 0 && (Short)m.get(returnTime.getSelectedItem().toString()) == 0)
			{
				toast = Toast.makeText(getApplicationContext(), "Your departure date and return date fall on the same day. Your flight will be booked" +
						"as per the availability on the day.", Toast.LENGTH_LONG);
				toast.show();
				return true;
			}
			/* Case: Departure time is Any time, and return time is specified.
			 * In this case notify the user that his/her ticket will be booked and departure time will be earlier than the return time specified.
			 * */
			else if((Short)m.get(depTime.getSelectedItem().toString()) == 0 && (Short)m.get(returnTime.getSelectedItem().toString()) != 0)
			{
				toast = Toast.makeText(getApplicationContext(), "Your departure time of flight will be earlier than " +returnTime.getSelectedItem().toString()+
						" time on the same day, as per the availability on that day.", Toast.LENGTH_LONG);
				toast.show();
				return true;
			}
			/* Case: Return time is any time and departure time is specified.
			 * In this case notify the user that his/her ticket will be booked and return time will be later than the departure time specified.
			 * */
			else if((Short)m.get(depTime.getSelectedItem().toString()) != 0 && (Short)m.get(returnTime.getSelectedItem().toString()) == 0)
			{
				toast = Toast.makeText(getApplicationContext(), "Your return time of flight will be later than " +depTime.getSelectedItem().toString()+
						" time on the same day, as per the availability on that day.", Toast.LENGTH_LONG);
				toast.show();
				return true;
			}
			else if((Short)m.get(depTime.getSelectedItem().toString()) == (Short)m.get(returnTime.getSelectedItem().toString()))
			{
				toast = Toast.makeText(getApplicationContext(), "Your departure time and return time cannot be same.", Toast.LENGTH_LONG);
				toast.show();
				return false;
			}
			else /*if((Short)m.get(depTime.getSelectedItem().toString()) > (Short)m.get(returnTime.getSelectedItem().toString()))*/
			{
				/* Case: Departure time is still later than return time.*/
				Log.d(CLASS_NAME, "Departure time cannot be later than return time on the same day.");
				toast = Toast.makeText(getApplicationContext(), "Your return time cannot be earlier than departure time, on the same day. " +
						"Please input valid values.", Toast.LENGTH_LONG);
				toast.show();
				return false;
			}
		}
		return true;
	}

	private void initSpinners()
    {
    	ArrayAdapter<CharSequence> adapter = null;
    	
    	Spinner spinnerNoOfPass = (Spinner)findViewById(R.id.numberOfPassengers);
        adapter = ArrayAdapter.createFromResource(this, R.array.number_array, 
        		android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNoOfPass.setAdapter(adapter);
        
        Spinner spinnerDepTime = (Spinner)findViewById(R.id.depTime);
        adapter = ArrayAdapter.createFromResource(this, R.array.time_array, 
        		android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepTime.setAdapter(adapter);
        
        Spinner spinnerRetTime = (Spinner)findViewById(R.id.returnTime);
        adapter = ArrayAdapter.createFromResource(this, R.array.time_array, 
        		android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRetTime.setAdapter(adapter);
    }

}