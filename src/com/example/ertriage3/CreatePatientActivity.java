package com.example.ertriage3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePatientActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_patient);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_patient, menu);
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

	public void createNewPatient(View view) {
		Intent intent = getIntent();
		Intent data = new Intent();
		Triage patientList = (Triage) intent.getSerializableExtra("PatientList");
		data.putExtra("List", patientList);
		try{
			String healthcard = ((EditText) findViewById(R.id.new_patient_hcn_field)).getText().toString();
			String name = ((EditText) findViewById(R.id.new_patient_name_field)).getText().toString();
			String dob = ((EditText) findViewById(R.id.new_patient_dob_field)).getText().toString();
			Patient newPatient = new Patient(healthcard, name, dob);
			double temp = Double.parseDouble(((EditText) findViewById(R.id.new_patient_temp_field)).getText().toString());
			double systolic = Double.parseDouble(((EditText) findViewById(R.id.new_patient_systolic_field)).getText().toString());
			double diastolic = Double.parseDouble(((EditText) findViewById(R.id.new_patient_diastolic_field)).getText().toString());
			double heartrate = Double.parseDouble(((EditText) findViewById(R.id.new_patient_heartrate_field)).getText().toString());
			String symptoms = ((EditText) findViewById(R.id.new_patient_symptoms_field)).getText().toString();
			VitalSignsAndSymptoms vs = new VitalSignsAndSymptoms(temp,systolic,diastolic,heartrate,symptoms);
			newPatient.updateCondition(vs);
			patientList.getPatients().put(healthcard, newPatient);
			setResult(RESULT_OK,data);
		}catch(Exception e){
			Toast.makeText(this, R.string.error_input, Toast.LENGTH_SHORT).show();
		}

	}
}
