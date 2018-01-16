package com.example.ertriage3;

import java.util.Calendar;
import java.util.TreeMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NewUpdateVisitActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_update_visit);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_update_visit, menu);
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
	
	public void newUpdatePatientVisit(View view) {
		Intent data = new Intent();
		
		EditText healthCardNumber = (EditText) findViewById(R.id.new_update_visit_hcn_field);
		EditText temperature = (EditText) findViewById(R.id.new_update_visit_temperature_field);
		EditText systolic = (EditText) findViewById(R.id.new_update_visit_systolic_field);
		EditText diastolic = (EditText) findViewById(R.id.new_update_visit_diastolic_field);
		EditText heartRate = (EditText) findViewById(R.id.new_update_visit_heartrate_field);
		EditText symptoms = (EditText) findViewById(R.id.new_update_visit_symptoms_field);
		
		Intent intent = getIntent();
		Triage patientList = (Triage) intent.getSerializableExtra("PatientList");
		data.putExtra("List", patientList);
		TextView updatePatient = (TextView) findViewById(R.id.updated_patient);
		
		Patient patient = patientList.getPatient(healthCardNumber.getText().toString());
		VitalSignsAndSymptoms newCondition = new VitalSignsAndSymptoms(Double.parseDouble(temperature.getText().toString()),
																	   Double.parseDouble(systolic.getText().toString()), 
																	   Double.parseDouble(diastolic.getText().toString()),
																	   Double.parseDouble(heartRate.getText().toString()),
																	   symptoms.getText().toString());
		
//		TreeMap<Calendar, VitalSignsAndSymptoms> condition = new TreeMap<Calendar, VitalSignsAndSymptoms>();
		TreeMap<Calendar, Object> condition = new TreeMap<Calendar, Object>();
		condition.put(Calendar.getInstance(), newCondition);
		patient.setCondition(condition);
		updatePatient.setText(patient.toString());
		patientList.getPatients().put(patient.getHealthCardNumber(),patient);
		setResult(RESULT_OK,data);
		//finish();
	}
}
