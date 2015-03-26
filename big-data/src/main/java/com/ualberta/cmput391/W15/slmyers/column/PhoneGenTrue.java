package main.java.com.ualberta.cmput391.W15.slmyers.column;

public class PhoneGenTrue extends PhoneGenerator implements Generator {
	public PhoneGenTrue(){
		this.type = "STRING";
	}
	public String genTrue(){
		return this.genNumber();
	}
}
