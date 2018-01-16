package com.example.ertriage3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LookupPatientActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lookup_patient);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lookup_patient, menu);
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
	
	
	/**
	* Looks up Patient by healthCardNumber and views Patient's details,
	* else "Patient Not Found!" is shown.
	*/
	
	public void lookupPatient(View view) {
		String healthCardNumber = ((EditText)findViewById(R.id.lookup_hcn_field))
									.getText().toString();
		Intent intent = getIntent();
		Triage patientList = (Triage)intent.getSerializableExtra("PatientList");
		
		TextView foundPatient = (TextView) findViewById(R.id.found_patient);
		Patient temp = patientList.getPatient(healthCardNumber);
		if (temp!= null)
			foundPatient.setText(temp.toString());
		else
			foundPatient.setText("Patient Not Found!");


	}
}
