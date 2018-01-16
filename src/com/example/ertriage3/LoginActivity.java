package com.example.ertriage3;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	/**
	 * the map of users.
	 */
	private Map<String, User> userList = new TreeMap<String, User>();
	
	
	/**
	 * Loads the login information from the file /file/passwords.txt
	 * 
	 * @throws FileNotFoundException
	 */
	public void loadUserData() throws FileNotFoundException {
		Scanner scanner = new Scanner(getClass().getResourceAsStream("/files/passwords.txt"));
		String[] record;	
		while(scanner.hasNextLine()){
			record = scanner.nextLine().split(",");
			String userName = record[0];
			String password = record[1];
			String permissions = record[2];
			User currentUser = new User(userName, password, permissions);
			userList.put(userName, currentUser);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		try {
			loadUserData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();		
		}
	}
	
	/**
	 * Implementation of the submit button for entering login data.
	 * 
	 * @param view
	 */
	public void login(View view) {
		String enteredUsername = ((EditText)findViewById(R.id.userNameField))
								  .getText().toString();
		String enteredPassword = ((EditText)findViewById(R.id.passwordField))
								  .getText().toString();
		if (userList.containsKey(enteredUsername)) {	// check the user exists.
			if (userList.get(enteredUsername).getPassword().equals(enteredPassword)) {	// check the users password.
				Intent intent = new Intent(this, MainActivity.class);
				intent.putExtra("CURRENT_USER", userList.get(enteredUsername));
				startActivity(intent);
			} else {
				Toast.makeText(this, R.string.incorrect_password_toast, Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(this, R.string.incorrect_username_toast, Toast.LENGTH_SHORT).show();
		}		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
