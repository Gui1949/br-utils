package br.utils;

public class strings {
	 
	  public Boolean isStringUsable(String s) {
	    return s != null && !s.isEmpty();
	  }
	  
	  public static String validateString(Object data) {
		  String response = "";
		  
		  try {
			  response = data.toString();
			  
		  }
		  catch (Exception e) {
			  response = "";
		  }
		  
		  return response;
	  }
}
