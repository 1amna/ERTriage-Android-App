package com.example.ertriage3;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Patient implements Serializable {
	/**
	 * A Patient at the ER.
	 */
	private static final long serialVersionUID = -4281718426706162879L;
	// Instance variables.
	
	private String name;
	private String dateOfBirth;
	private int age;
	private int urgencyScore = 0;
	private String healthCardNumber;
	private Calendar arrivalTime;
	

	
//	private TreeMap<Calendar, VitalSignsAndSymptoms> currentVisit ;
	private TreeMap<Calendar, Object> currentVisit ;
	private TreeMap<Calendar, String> doctorVisits;
	private VitalSignsAndSymptoms mostRecent ;
	
//	private TreeMap<Calendar, Map<Calendar, VitalSignsAndSymptoms>> visits = new TreeMap<Calendar, Map<Calendar, VitalSignsAndSymptoms>>();
	private TreeMap<Calendar, Map<Calendar, Object>> visits = new TreeMap<Calendar, Map<Calendar, Object>>();
	
	/**
	 * Constructs a new Patient object.
	 * @param name a String which represents the patient's name.
	 * @param dateOfBirth a String which represents the patient's birth date.
	 * @param healthCardNumber a String which represents the patient's unique health card number.
	 * @param arrivalTime a Calendar which represent the time of arrival of Patient to the ER.
	 */
	
	public Patient (String healthCardNumber, String name, String dateOfBirth) {
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.healthCardNumber = healthCardNumber;
		
		this.arrivalTime = Calendar.getInstance();  // An instance of current time is produced.
		currentVisit = new TreeMap<Calendar, Object>();
		visits.put(arrivalTime, currentVisit);
		doctorVisits = new TreeMap<Calendar, String>();
		mostRecent = new VitalSignsAndSymptoms();
		setAge();
	}
	
	/**
	 * Creates a new visit for Patient if they return to the ER.
	 */
	public void newVisit() {
		this.arrivalTime = Calendar.getInstance(); 
		currentVisit = new TreeMap<Calendar, Object>();
		visits.put(arrivalTime, currentVisit);
		doctorVisits = new TreeMap<Calendar, String>();
		mostRecent = new VitalSignsAndSymptoms();
		urgencyScore = 0;
		setAge();
	}

	
	/**
	 * Get the name of the Patient.
	 * @return the Patient's name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Set the name of the Patient.
	 * @param name set the Patient's name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Get the Patient's date of birth.
	 * @return the Patient's dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}


	/**
	 * Set the Patient's date of birth.
	 * @param dateOfBirth set the Patient's dateOfBirth
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	/**
	 * 
	 *  Sets the current age of Patient based on date of birth and arrival time.
	 */
	
	private void setAge() {
		String[] dobArray = dateOfBirth.split("-");
		int birthYear = Integer.valueOf(dobArray[0]);
		
		int birthMonth = Integer.valueOf(dobArray[1]);
		int birthDay = Integer.valueOf(dobArray[2]);
		int arrivalYear = arrivalTime.get(Calendar.YEAR);   // Get the year of arrival, using Calendar.
		int arrivalMonth = arrivalTime.get(Calendar.MONTH) + 1;   // Get the month of arrival, using Calendar.
		int arrivalDay = arrivalTime.get(Calendar.DAY_OF_MONTH);
		this.age = arrivalYear - birthYear;
		if(arrivalMonth < birthMonth) {
			this.age -= 1;
		}
		else if (arrivalMonth == birthMonth) {
			if (arrivalDay < birthDay) {
				this.age -= 1;   
			}

		}
	}
	
	/**
	 * Get the current age of the patient.
	 * @return Patient's current age.
	 */
	public int getAge() {
		return this.age;
	}
	
	
	/**
	 * Get the healthCardNumber of the Patient.  
	 * @return the Patient's unique healthCardNumber
	 */
	public String getHealthCardNumber() {
		return healthCardNumber;
	}


	/**
	 * Set the Patient's healthCardNumber
	 * @param healthCardNumber sets the Patient's healthCardNumber
	 */
	public void setHealthCardNumber(String healthCardNumber) {
		this.healthCardNumber = healthCardNumber;
	}


	/**
	 * Get the time at which the Patient arrived at the ER.
	 * @return the Patient's arrivalTime
	 */
	public Calendar getArrivalTime() {
		return arrivalTime;
	}


	/**
	 * Set the time at which the patient arrived at the ER.
	 * @param arrivalTime set the Patient's arrivalTime
	 */
	public void setArrivalTime(Calendar arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * Get the the Patient's urgency score
	 * @return the Patient's urgency score
	 */
	public int getUrgencyScore() {
		  return urgencyScore;
		}
		public void setUrgencyScore(int calculatedUrgencyScore) {
		 urgencyScore =  calculatedUrgencyScore;
		}

	/**
	 * Get the patient's a map of the data of Patient's current ER visit including symptoms,
	 * vital signs and medication prescriptions.
	 * @return the Patient's visit data
	 */
	public TreeMap<Calendar, Object> getCurrentVisitData() {
		return currentVisit;         
	}


	/**
	 * Set the Patient's condition, including vital signs and symptoms.
	 * @param condition set the Patient's condition
	 */

	public void setCondition(TreeMap<Calendar, Object> condition) {
		this.currentVisit = condition;
	}


	/**
	 * Return a list of the Patient's history of doctor visits.
	 * @return the Patient's history of doctorVisits
	 */
	public Map<Calendar, String> getDoctorVisits() {
		return doctorVisits;     // A list of time-stamps of when Patient was by a doctor
	}


	/**
	 * Set the Patient's doctor visits. 
	 * @param doctorVisits set the Patient's doctorVisits
	 */
	public void setDoctorVisits(TreeMap<Calendar, String> doctorVisits) {
		this.doctorVisits = doctorVisits;
	}


	/**
	 * Get the Patient's most recent instance of VitalSignsAndSymptoms
	 * @return the Patient's mostRecent vital signs and symptoms
	 */
	public VitalSignsAndSymptoms getMostRecent() {
		return mostRecent;     
	}

	/**
	 * 
	 * Updates the mostRecent vital signs and symptoms taken by the nurse.  If no new values
	 * were taken, values from the previous instance are taken, that is, the most recent values.
	 * @param newVitalAndSymptom
	 */

	private void updateMostRecent(VitalSignsAndSymptoms newVitalAndSymptom) {   // VitalSignsAndSymptoms updated.
		if (newVitalAndSymptom.getTemperature() != 0.0) {
			mostRecent.setTemperature(newVitalAndSymptom.getTemperature());
		}
		if (newVitalAndSymptom.getBloodPressureDiastolic() != 0.0) {
			mostRecent.setBloodPressureDiastolic(newVitalAndSymptom.getBloodPressureDiastolic());
		}
		if (newVitalAndSymptom.getBloodPressureSystolic() != 0.0) {
			mostRecent.setBloodPressureSystolic(newVitalAndSymptom.getBloodPressureSystolic());
		}
		if (newVitalAndSymptom.getHeartRate() != 0.0) {
			mostRecent.setHeartRate(newVitalAndSymptom.getHeartRate());
		}
		if (newVitalAndSymptom.getSymptoms() != null ) {
			mostRecent.setSymtoms(newVitalAndSymptom.getSymptoms());
		}
	}
	/**
	 * Return the Patient's last recorded temperature
	 * @return double value which represents the last recorded temperature of Patient.
	 */
	public double getTemperature() {
		return mostRecent.getTemperature();
	}
	/**
	 * Return the Patient's last recorded diastolic blood pressure.
	 * @return double value which represents the last recorded diastolic blood pressure of Patient.
	 */
	public double getBloodPressureDiastolic() {
		return mostRecent.getBloodPressureDiastolic();
	}
	/**
	 * Return the Patient's last recorded systolic blood pressure.
	 * @return double value which represents the last recorded systolic blood pressure of Patient.
	 */
	public double getBloodPressureSystolic() {
		return mostRecent.getBloodPressureSystolic();
	}	
	/**
	 * Return the Patient's last recorded heartrate.
	 * @return double value which represents the last recorded heart rate of Patient.
	 */
	public double getHeartRate() {
		return mostRecent.getHeartRate();
	}
	/**
	 * Return the Patient's last recorded symptoms.
	 * @return String which represents the last recorded symptoms of Patient.
	 */
	public String getSymptoms() {
		return mostRecent.getSymptoms();
	}
	
	/**
	 * 
	 * Records VitalSignsAndSymptoms of a nurse's visit with a time stamp.
	 * Also updates the most recent values for all available vital signs and symptoms.
	 * @param newVitalAndSymptom Patient's vital signs recorded by the nurse.
	 * 
	 */
	public void updateCondition(VitalSignsAndSymptoms newVitalAndSymptom) {   
		updateMostRecent(newVitalAndSymptom);
		Calendar now = Calendar.getInstance();
		if (currentVisit.containsKey(now)) {
			try {
				Thread.sleep(1000);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			now = Calendar.getInstance();
		}
		currentVisit.put(now, newVitalAndSymptom);   // New symptoms and/or vital signs added		
	}
	
	/**
	 * 
	 * Records a doctor's visit and prescription.
	 */
	public void updateDoctorVisit(String prescription) {
		Calendar now = Calendar.getInstance();
		if (currentVisit.containsKey(now)) {
			try {
				Thread.sleep(1000);      
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			now = Calendar.getInstance();
		}
		doctorVisits.put(now, prescription);
	}
	
	/**
	 * Returns whether or not Patient has been seen by a doctor.
	 * @return whether the patient has been seen by any doctor
	 */
	public boolean seenByDoctor() {
		boolean result = false;
		if (!doctorVisits.isEmpty()) {     // Patient has been seen by doctor
			result = true;
		}
		return result;
	}
	/**
	 * Resets if Patient has been seen by a doctor. Use when Patient returns to the ER.
	 */
	public void resetSeenByDoctor() {
		this.doctorVisits = new TreeMap<Calendar, String>();     
	}
	
	/**
	 * Prints a string representation of Patient's data.
	 * @return String representation of Patient, including his/her entire Patient history
	 */
	public String toString(){
		String result = "";
		String divider = "----------------------------------------\n";

		StringBuffer patientHistory = new StringBuffer();
		
		String patientIdentifier = "Name               : " + this.name + "\n" +"Health Card # : " + this.healthCardNumber + "\n" +"Date of Birth   : " + this.dateOfBirth + "\n";
		StringBuffer currentCondition = new StringBuffer("Current Condition: ");
		if (mostRecent.getTemperature() != 0.0) {
			currentCondition.append("Temperature: " + mostRecent.getTemperature() + ", ");
		}
		if (mostRecent.getBloodPressureDiastolic() != 0.0 && mostRecent.getBloodPressureSystolic() != 0.0) {
			currentCondition.append("Blood Pressure: " + mostRecent.getBloodPressureSystolic() + "/" + mostRecent.getBloodPressureDiastolic() + ", ");
		}
		if (mostRecent.getHeartRate() != 0.0) {
			currentCondition.append("Heart rate: " + mostRecent.getHeartRate() + ", ");
		}
		currentCondition.append(mostRecent.getSymptoms() + "\n");
		currentCondition.append(divider);
		patientIdentifier += currentCondition;
		
		// append prescription info
		StringBuffer prescriptions = new StringBuffer("");
		if (!doctorVisits.isEmpty()) {
			prescriptions = new StringBuffer("Prescriptions:\n");
			Iterator<Calendar> pIt = doctorVisits.descendingKeySet().iterator();
			while (pIt.hasNext()){
				Calendar timeStamp = pIt.next();
				String text = doctorVisits.get(timeStamp);
				if (text != null && text.trim().length()>0) prescriptions.append(text+"\n");
			}
			prescriptions.append(divider);
		}
		
		if (prescriptions.length()>0) {
			patientIdentifier += prescriptions.toString();
		}

		TreeMap<Calendar, Object> oneVisit;
		
		Iterator<Calendar> timeStamps = visits.descendingKeySet().iterator();
		while (timeStamps.hasNext()) {
			Calendar timeStamp = timeStamps.next();
			oneVisit = (TreeMap<Calendar, Object>)visits.get(timeStamp);
			patientHistory.append(getPatientVisitDataString(oneVisit));
		}

		result = patientIdentifier + divider + patientHistory.toString() +
				"\nHas been seen by doctor: " + this.seenByDoctor();
		System.out.println(result);
		return result;
	}
	

	/**
	 * @param oneVisitToER data
	 * @return the String representation of a Patient's latest condition to his/her current trip to the ER
	 */
	
	private static String getPatientVisitDataString(TreeMap<Calendar, Object> oneVisitToER){
		String result = "";
		StringBuffer patientHistory = new StringBuffer();		
		StringBuffer currentCondition = new StringBuffer("");
		Iterator<Calendar> timeStamps = oneVisitToER.descendingKeySet().iterator();
		String divider = "----------------------------------------\n";
		while (timeStamps.hasNext()) {
			Calendar timeStamp = timeStamps.next();
			Object dataObject = oneVisitToER.get(timeStamp);
			if (dataObject != null && dataObject instanceof VitalSignsAndSymptoms) {
				VitalSignsAndSymptoms currentVS = (VitalSignsAndSymptoms)dataObject;     // A current version of VitalSignsAndSymptoms
				String timeStr = timeStamp.get(Calendar.YEAR) + "-" + 
				(new Integer (timeStamp.get(Calendar.MONTH)+1)).toString() + "-" +
				timeStamp.get(Calendar.DAY_OF_MONTH) +" " + timeStamp.get(Calendar.HOUR_OF_DAY) + ":" + 
						timeStamp.get(Calendar.MINUTE) + ":" + timeStamp.get(Calendar.SECOND);

				currentCondition = new StringBuffer(timeStr + ": ");
				if (currentVS.getTemperature() != 0.0) {
					currentCondition.append("Temperature: " + currentVS.getTemperature() + ", ");
				}
				if (currentVS.getBloodPressureDiastolic() != 0.0 && currentVS.getBloodPressureSystolic() != 0.0) {
					currentCondition.append("Blood Pressure: " + currentVS.getBloodPressureSystolic() +
							"/" + currentVS.getBloodPressureDiastolic() + ", ");
				}
				if (currentVS.getHeartRate() != 0.0) {
					currentCondition.append("Heart rate: " + currentVS.getHeartRate() + ", ");
				}
				currentCondition.append(currentVS.getSymptoms() + "\n");
				patientHistory.append(currentCondition + divider);
			} else if (dataObject != null && dataObject instanceof String) { // prescription
				String prescription = (String)dataObject;
				if (prescription != null && prescription.trim().length()>0 ){
					String timeStr = timeStamp.get(Calendar.YEAR) + "-" + (new Integer (timeStamp.get(Calendar.MONTH)+1)).toString() +
							"-" + timeStamp.get(Calendar.DAY_OF_MONTH) + " " + timeStamp.get(Calendar.HOUR_OF_DAY) +
							":" + timeStamp.get(Calendar.MINUTE) + ":" + timeStamp.get(Calendar.SECOND);

					currentCondition = new StringBuffer(timeStr + ": Prescription: "+ prescription + "\n");
					patientHistory.append(currentCondition + divider);
				}
			}
		}
		result = patientHistory.toString();
		return result;
	}
	
	public String getCurrentVisitInfo() {
		String result = "";
		String divider = "----------------------------------------\n";
		
		String patientIdentifier = "Name               : " + this.name + "\n" +"Health Card # : " + this.healthCardNumber + "\n" +"Date of Birth   : " + this.dateOfBirth + "\n";
		StringBuffer currentCondition = new StringBuffer("Current Condition: ");
		if (mostRecent.getTemperature() != 0.0) {
			currentCondition.append("Temperature: " + mostRecent.getTemperature() + ", ");
		}
		if (mostRecent.getBloodPressureDiastolic() != 0.0 && mostRecent.getBloodPressureSystolic() != 0.0) {
			currentCondition.append("Blood Pressure: " + mostRecent.getBloodPressureSystolic() + "/" + mostRecent.getBloodPressureDiastolic() + ", ");
		}
		if (mostRecent.getHeartRate() != 0.0) {
			currentCondition.append("Heart rate: " + mostRecent.getHeartRate() + ", ");
		}
		currentCondition.append(mostRecent.getSymptoms() + "\n");
		currentCondition.append(divider);
		
		if (urgencyScore != 0) {
			currentCondition.append("Urgency score: " + urgencyScore + "\n");
			currentCondition.append(divider);
		}

		patientIdentifier += currentCondition;

		// append prescription info
		StringBuffer prescriptions = new StringBuffer("");
		if (!doctorVisits.isEmpty()) {
			prescriptions = new StringBuffer("Prescriptions:\n");
			Iterator<Calendar> pIt = doctorVisits.descendingKeySet().iterator();
			while (pIt.hasNext()){
				Calendar timeStamp = pIt.next();
				String text = doctorVisits.get(timeStamp);
				if (text != null && text.trim().length()>0) prescriptions.append(text+"\n");
			}
			prescriptions.append(divider);
		}
		
		if (prescriptions.length()>0) {
			patientIdentifier += prescriptions.toString();
		}
		result = patientIdentifier + divider + getPatientVisitDataString(currentVisit);
		return result;
	}

}
