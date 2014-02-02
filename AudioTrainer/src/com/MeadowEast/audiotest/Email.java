package com.MeadowEast.audiotest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Email extends Activity implements View.OnClickListener{

	EditText personEmail, emailSubject, emailComment;
	String emailAddress, subject, comment;
	Button emailButton;
	public static String hanziMessage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		initializeVars();
		
		emailButton.setOnClickListener(this);
	}

	public void getHanziTextForEmail(String hanziText)
	{
		hanziMessage = hanziText;
		

		
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		convertTheText();
		String emailAddressArray[] = {emailAddress};
		Toast.makeText(this, "the text: " + hanziMessage, Toast.LENGTH_LONG).show();
		//Will have to call a method from the MainActivity to get Hanzi text and MP3 file
		//String hanziMessage = "This will be the clip information Hanzi text"; //get this from MainActivity
		//String hanziMessage = getHanziTextForEmail();
		
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		//Extra email will take a string array as the vaule to send to address
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailAddressArray);
		//Add the subject message into the email
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject + " this is subject");
		
		emailIntent.setType("plain/text");
		//Add the comment
		//emailIntent.putExtra(android.content.Intent.E, comment + " this is comment");
		//add in the Hanzi text now
		
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, hanziMessage);
		startActivity(emailIntent);

		
	}
	
	private void initializeVars(){
		emailButton = (Button)findViewById(R.id.sendMail);
		personEmail = (EditText)findViewById(R.id.emailText);
		emailSubject = (EditText)findViewById(R.id.subjectText);
		emailComment = (EditText)findViewById(R.id.commentText);
	}
	
	private void convertTheText(){
	
		emailAddress = personEmail.getText().toString();
		subject = emailSubject.getText().toString();
		comment = emailComment.getText().toString();
	}
	
	protected void onPause(){
		super.onPause();
		finish();
	}


	
}
