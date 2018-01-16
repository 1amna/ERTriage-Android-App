package com.example.ertriage3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import android.content.Context;

/**
 * The Class Triage; an assessment of urgency in ER.
 */
public class Triage implements Serializable {
	
	private static final long serialVersionUID = 3130275708414163175L;
	private Map<String, Patient> patients ;
	private final String RECORD = "patient_info.ser";




	public Triage(Context dir, String fileName) throws IOException {
		patients = new HashMap<String, Patient>();     // A map of all patients.
		File file = new File(dir.getFilesDir(), RECORD);
		if (file.exists()) {
			this.loadPatientData(new FileInputStream(file.getPath()));
		} else {
			file.createNewFile();
			this.loadPatientDataDefault(getClass().getResourceAsStream("/files/"+fileName));
		}
	}
	
	public Patient getPatient(String healthCardNumber) {
		return patients.get(healthCardNumber);
	}
	
	
	/**
	 * Returns true if updating the Patient's condition using the unique 
	 * healthCardNumber is successful, false otherwise.   
	 * @param healthCardNumber uniquely identifies each patient
	 * @param temp,the temperature of the patient the nurse may update
	 * @param bpDiastolic, the diastolic blood pressure the nurse may update
	 * @param bpSystolic, the systolic blood pressure the nurse may update
	 * @param heartRate, the patient's heart rate which the nurse may update
	 * @param symptoms, the patient's symptoms which the nurse may update
	 * @return true updating is successful, false otherwise.
	 */
	public boolean updatePatientCondition(String healthCardNumber, 
			String temp, 
			String bpDiastolic,
			String bpSystolic,
			String heartRate,
			String symptoms) {
		boolean result = false;
		Patient patient = patients.get(healthCardNumber);	// Each healthCardNumber is unique and relates to a Patient, if it exists
		if (patient != null) {
			double tempVal = 0.0;
			if (temp != null && !temp.isEmpty()) {
				tempVal = Double.valueOf(temp);
			}
			double bpDiastolicVal = 0.0;
			if (bpDiastolic != null && !bpDiastolic.isEmpty()) {
				bpDiastolicVal = Double.valueOf(bpDiastolic);
			}
			double bpSystolicVal = 0.0;
			if (bpSystolic != null && !bpSystolic.isEmpty()) {
				bpSystolicVal = Double.valueOf(bpSystolic);
			}
			double heartRateVal = 0.0;
			if (heartRate != null && !heartRate.isEmpty()) {
				heartRateVal = Double.valueOf(heartRate);
			}
			
			VitalSignsAndSymptoms newVAndS = new VitalSignsAndSymptoms(tempVal, 
					bpSystolicVal, bpDiastolicVal, heartRateVal, symptoms);
			patient.updateCondition(newVAndS);

			result = true;
		} else {
			System.out.println("Patient "+healthCardNumber+" not checked in.");
		}
		return result;
    }
	
	public boolean updateDoctorVisitPatient(String healthCardNumber, String newInstruction) {
		boolean result = false;
		Patient patient = patients.get(healthCardNumber);	
		if (patient != null) {
			patient.updateDoctorVisit(newInstruction);
			result = true;
		} else {
			System.out.println("Patient "+healthCardNumber+" not checked in.");
		}
		return result;
	}
	
	/**
	 * Adds a patient to the Triage 
	 * If the Patient does not already exist in the AllPatients records, add the patients history records to the
	 * Triage. Adds the patient to AllPatients for brand new patient.
	 */
/*	public Patient patientCheckIn(String name, String dob, String healthCardNumber) {
		Patient result = null;
		if (validateDOB(dob) && validateHealthCardNum(healthCardNumber)) {
			result = getPatient(healthCardNumber);
			if (result != null) {
				result = allPatients.get(healthCardNumber);
				if (result == null) {
					result = addPatient(name, dob, healthCardNumber);					
				} else {
					patients.put(healthCardNumber, result);
					result.newVisit();
					saveTriagePatientsInfo(patients);
				}
			}

		} else {
			System.out.println("Patient info invalid");
		}
		return result;
	}*/
	public void patientDischarge(String healthCardNumber) {
		patients.remove(healthCardNumber);
	}

	private boolean validateHealthCardNum(String healthCardNum) {
		boolean result = true;
		if (healthCardNum.length() != 6) {return false;}
		return result;
	}
	
	
	/**
	 * @param dob the Patient's birth date
	 * @return true if the birth date is valid
	 */
	private boolean validateDOB(String dob) {
		boolean result = true;
		dob = dob.trim();
//		dob = dob.replaceAll("/", "-");
		dob = dob.replaceAll("\\.", "-");
		String[] dobInfo = dob.split("-");
			if (dobInfo[0].length() != 4) {
				return false;
			}
			int month = 0;
			int date = 0;
		try {
			month = Integer.parseInt(dobInfo[1]);
			date = Integer.parseInt(dobInfo[2]);
		} catch (NumberFormatException e) {
			return false;		
		}
		if (month < 1 || month > 12) {result = false;}
		if (date < 1 || date > 31) {result = false;}
		

		return result;	
	}
	
	/**
	 * @param dob the Patient's birth date
	 * @return the correct String format of the Patient's birth date
	 */
	private String formatDOB(String dob) {
		String result = dob;
		dob = dob.trim();
//		dob = dob.replaceAll("/", "-");
		dob = dob.replaceAll("\\.", "-");
		String[] dobInfo = dob.split("-");
		result = dobInfo[0] + "-" + dobInfo[1] + "-" + dobInfo[2];
		return result;
	}
	
	/**
	 * Adds a new Patient to the Triage as well as AllPatients
	 * @param name is the name of the patient
	 * @param dob is the birth date of the patient
	 * @param healthCardNum is the patient's unique health card number
	 */
/*	public Patient addPatient(String name, String dob, String healthCardNum) {
		Patient result = null;
			if (validateDOB(dob)) dob = formatDOB(dob);
			Patient newPatient = new Patient(healthCardNum, name, dob);
			allPatients.put(healthCardNum, newPatient);
			saveAllPatientsInfo();
			patients.put(healthCardNum, newPatient);
			saveTriagePatientsInfo(patients);
			result = newPatient;
			return result;
	}*/
	
	
	/**
	 * Return the integer representing urgency score of Patient based on 
	 * the hospital policy and Patient's current condition.
	 * @param patient of which the urgency score is calculated
	 * @return int patient's urgency score.
	 */
	private int calculateUrgencyScore(Patient patient) {
		int score = 0;
		if (patient.getAge() < 2) {
			score ++;
		}
		if (patient.getMostRecent().getTemperature() >= 39.0) {
			score ++;
		}
		if (patient.getMostRecent().getBloodPressureSystolic() >= 140.0) {   // Systolic pressure too high!
			score ++;
		}
		if (patient.getMostRecent().getBloodPressureDiastolic() >= 90.0) {
			score ++;
		}
		if (patient.getMostRecent().getHeartRate() >= 100.0 ||
				patient.getMostRecent().getHeartRate() <= 50.0) {
			score ++;
		}	
		return score;
	}
	
	/**
	 * Creates a list of patients ordered by the urgency score of each patient
	 * where the highest urgency patient is at the top of the list.
	 * @return List of Patients ordered by urgency.
	 */
	public List<Patient> getPatientListByUrgency(){
		List<Patient> result = new ArrayList<Patient>();
		Iterator<Patient> allTriagePatients = patients.values().iterator();
		TreeMap<Integer, Set<Patient>> tempResult = new TreeMap<Integer, Set<Patient>>();
		Set<Patient> patientsWithSameScore;
		while (allTriagePatients.hasNext()) {
			  Patient currentPatient = allTriagePatients.next();
			  int score = calculateUrgencyScore(currentPatient);
			  currentPatient.setUrgencyScore(score);
			  Integer currentPatientScore = new Integer(score);  // Urgency score calculated.
			  patientsWithSameScore = tempResult.get(currentPatientScore);
			  if (patientsWithSameScore == null) {
			    patientsWithSameScore = new TreeSet<Patient>();
			    tempResult.put(currentPatientScore, patientsWithSameScore);
			  }
			  patientsWithSameScore.add(currentPatient);
			}
		Iterator<Integer> urgencyScores = tempResult.descendingKeySet().iterator();
		while (urgencyScores.hasNext()) {
			Integer currentScore = urgencyScores.next();
			result.addAll(tempResult.get(currentScore));
		}
		
		return result;
	}
	
	/**
	 * Prints a list of patients and their patient data 
	 * ordered by the urgency score of each patient
	 * where the highest urgency patient is at the top of the list.
	 */
	public String printPatientsByUrgency() {
		String result = "";
		StringBuffer tempString = new StringBuffer("");
		List<Patient> patients = getPatientListByUrgency();
		if (patients != null && !patients.isEmpty()) {
			Iterator<Patient> pIt = patients.listIterator();
			while (pIt.hasNext()) {
				Patient currentPatient = pIt.next();
				tempString.append(currentPatient.getCurrentVisitInfo());
			}
			result = tempString.toString();
		}
		System.out.println(result);
		return result;
	}
	
	/**
	 * Creates a list of patients ordered by the their arrival times
	 * where the most recently arrived patient is at the bottom of the list.
	 * @return List of Patients ordered by arrival time (non increasing order).
	 */
	public List<Patient> getPatientListByArrivalTime(){
		List<Patient> result = new ArrayList<Patient>();
		Iterator<Patient> allTriagePatients = patients.values().iterator();
		TreeMap<Calendar, Set<Patient>> tempResult = new TreeMap<Calendar, Set<Patient>>();
		Set<Patient> patientsArrivedAtTheSameTime ;
		while (allTriagePatients.hasNext()) {
			Patient currentPatient = allTriagePatients.next();
			// check if the patient has been seen by a doctor
			if (!currentPatient.seenByDoctor()) {
				Calendar currentPatientArrival = currentPatient.getArrivalTime();  // Arrival time of Patient.
				patientsArrivedAtTheSameTime = tempResult.get(currentPatientArrival);
				if (patientsArrivedAtTheSameTime == null) {
					patientsArrivedAtTheSameTime = new TreeSet<Patient>();
					tempResult.put(currentPatientArrival, patientsArrivedAtTheSameTime);
				}
				patientsArrivedAtTheSameTime.add(currentPatient);
			}
		}
		Iterator<Calendar> arrivalTimes = tempResult.keySet().iterator();
		while (arrivalTimes.hasNext()) {
			Calendar currentTime = arrivalTimes.next();
			result.addAll(tempResult.get(currentTime));
		}
		return result;
	}
	
	/**
	 * Prints a list of patients and their patient data
	 * ordered by their arrival times
	 * where the most recently arrived patient is at the bottom of the list.
	 */
	public String printPatientsByArrivalTime() {
		String result = "";
		StringBuffer tempString = new StringBuffer("");
		List<Patient> patients = getPatientListByArrivalTime();
		if (patients != null && !patients.isEmpty()) {
			Iterator<Patient> pIt = patients.listIterator();
			while (pIt.hasNext()) {
				Patient currentPatient = pIt.next();
				tempString.append(currentPatient.getCurrentVisitInfo());
			}
			result = tempString.toString();
		}
		System.out.println(result);
		return result;
	}
	
	public Map<String, Patient> getPatients() {
		return patients;
	}

	public void setPatients(Map<String, Patient> patients) {
		this.patients = patients;
	}

	/**
	 * Load existing patient data.
	 *
	 * @param filepath the filepath of the patient date
	 * @return 
	 * @return 
	 * @throws FileNotFoundException the file not found exception
	 */
	public void loadPatientDataDefault(InputStream file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		String[] record;	
		while(scanner.hasNextLine()){
			record = scanner.nextLine().split(",");
			String healthCardNum = record[0];
			String name = record[1];
			String dob = record[2];
			patients.put(healthCardNum, new Patient(healthCardNum, name, dob));
		}
		scanner.close();

	}
	public void loadPatientData(InputStream file) throws FileNotFoundException {
		try
	      {
	         ObjectInputStream ois = new ObjectInputStream(file);
	         patients = (HashMap<String, Patient>) ois.readObject();
	         ois.close();

	      }catch(IOException ioe)
	      {
	         ioe.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Class not found");
	         c.printStackTrace();
	         return;
	      }

	}

	public void savePatientData(FileOutputStream outputStream){
        try
        {
               ObjectOutputStream oos = new ObjectOutputStream(outputStream);
               oos.writeObject(patients);
               oos.close();
        }catch(IOException ioe)
         {
               ioe.printStackTrace();
         }
	}


	public String getRECORD() {
		return RECORD;
	}



/**
 * @param args
 */
public static void main(String[] args) {
	
}

}
