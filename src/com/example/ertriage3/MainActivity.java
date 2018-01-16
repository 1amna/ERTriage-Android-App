package com.example.ertriage3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	//public PatientManager patientManager;
	public User currentUser;
	public Triage triageList;
	private final String DEFAULT = "patient_records.txt";
	public void loadTriageList() throws FileNotFoundException {
		try {
			triageList = new Triage(this.getApplicationContext(),DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//triageList.loadPatientData("/files/patient_records.txt");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent intent = getIntent();
		currentUser = (User)intent.getSerializableExtra("CURRENT_USER");
		
		try {
			loadTriageList();
			//patientManager = new PatientManager();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException f) {
			f.printStackTrace();
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onActivityResult(int requestCode,int resultCode, Intent data){
		if (requestCode==1)
			triageList  = (Triage) data.getSerializableExtra("List");
	}

	public void lookupPatient(View view) {
		Intent intent = new Intent(this, LookupPatientActivity.class);
		
		intent.putExtra("PatientList", triageList);
		
		startActivity(intent);
	}

	public void newUpdateVisit(View view) {
		Intent intent = new Intent(this, NewUpdateVisitActivity.class);
		
		intent.putExtra("PatientList", triageList);
		startActivityForResult(intent, 1);
	}

	public void saveVisit(View view) {
        FileOutputStream outputStream;
        try {
                outputStream = openFileOutput(triageList.getRECORD(), MODE_PRIVATE);
                triageList.savePatientData(outputStream);
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
		Toast.makeText(this, R.string.saved_records, Toast.LENGTH_SHORT).show();
	}

	public void viewVisit(View view) {
		Intent intent = new Intent(this, ViewVisitActivity.class);
		//triageList.view();
		startActivity(intent);
	}
	
	
	public void createPatient(View view) {
		Intent intent = new Intent(this, CreatePatientActivity.class);
		intent.putExtra("PatientList", triageList);
		startActivityForResult(intent, 1);
		
	}
	
	public void doctorVisit(View view) {
		Intent intent = new Intent(this, DoctorVisitActivity.class);
		startActivity(intent);
	}
	
	public void listPatients(View view) {
		Intent intent = new Intent(this, ListPatientsActivity.class);
		startActivity(intent);
	}
	
	public void recordPrescription(View view) {
		Intent intent = new Intent(this, RecordPrescriptionActivity.class);
		startActivity(intent);
	}
}
