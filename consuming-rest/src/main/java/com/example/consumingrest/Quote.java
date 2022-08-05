package com.example.consumingrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



//this annotation means that any properties not bound in this type should be ignored
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
	
	//to do what we are doing, the variable names in the class should be exactly the same as the JSON file.
	//In case your variable name and key in JSON doc do not match, you can use @JsonProperty annotation to 
	//specify the exact key of the JSON document.
	
	  private String type;
	  private Value value;		//value itself has an id(long), quote(string) attribute which we define in it's own class
	
	  public Quote() {
	  }
	
	  public String getType() {
	    return type;
	  }
	
	  public void setType(String type) {
	    this.type = type;
	  }
	
	  public Value getValue() {
	    return value;
	  }
	
	  public void setValue(Value value) {
	    this.value = value;
	  }
	
	  @Override
	  public String toString() {
	    return "Quote{" +
	    		"type='" + type + '\'' +
	    		", value=" + value +
	    		'}';
	  }
}