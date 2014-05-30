package UtilityPkg;

import java.util.Calendar;



public class GetDate {

  public static String getDate()
  {
		       Calendar cal=Calendar.getInstance();
		       int year=cal.get(Calendar.YEAR);
		       int date = cal.get(Calendar.DATE);
		       int Month = cal.get(Calendar.MONTH)+1;
		       if(date < 10)
		       { 
		    	   if(Month > 10)
		    	   {
		    		   String currentDate = (year+"-"+Month+"-"+"0"+date);
		    		   return currentDate;
		    	   }
		    	   else
		    	   {
		    		   String currentDate = (year+"-"+"0"+Month+"-"+"0"+date);
		    		   return currentDate;
		    	   }
		       }
		       else
		       {
		    	   if(Month < 10)
		    	   {
		    		   String currentDate = (year+"-"+"0"+Month+"-"+date);
		    		   return currentDate;
		    	   }
		    	   else
		    	   {
		    		   String currentDate = (year+"-"+Month+"-"+date);
		    		   return currentDate;
		    	   }
		       }
		 
		      
	 }  
  
  public static String getyear()
  {
	  Calendar cal=Calendar.getInstance();
	  int y = cal.get(Calendar.YEAR);
	  String year = (""+y);
	  return year;
  }
  
  public static String gettime()
  {
	  Calendar cal=Calendar.getInstance();
	  String time = ""+cal.get(Calendar.MINUTE)+cal.get(Calendar.HOUR)+cal.get(Calendar.SECOND);
	  return time;
  }
  public static String getday()
   {
      Calendar cal=Calendar.getInstance();
     
      int date = cal.get(Calendar.DATE);
     
	
      if(date < 10)
      {
    	  String day = ("0"+date);
    	  return day;
      }
      else
      {
    	  String day = (""+date);
    	  return day;
      }
    }
  
  public static String getMonth()
  {
     Calendar cal=Calendar.getInstance();
    
     int Month = cal.get(Calendar.MONTH)+1;
    
	
     if(Month < 10)
     {
   	  String month = ("0"+Month);
   	  return month;
     }
     else
     {
   	  String month = (""+Month);
   	  return month;
     }
   }
  
  }

