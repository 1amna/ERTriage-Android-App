
/**
 * Class for storing vital signs and symptoms of of a particular patient.
 * Specifically, temperature, systolic blood pressure, diastolic blood
 * pressure, and symptoms.
 * 
 * @author Steven
 * @version 2014-11-04
 */

package com.example.ertriage3;

import java.io.Serializable;

public class VitalSignsAndSymptoms implements Serializable {
	
	private static final long serialVersionUID = 4226295918010114056L;
	// Instance variables.
	private double temperature = 0.0;
	private double bloodPressureSystolic = 0.0;
	private double bloodPressureDiastolic = 0.0;
	private double heartRate = 0.0;
	private String symptoms = "N/A";
	
	/**
	 * Constructor for a <code>VitalSignsAndSymptoms</code> object with default values.
	 */
	public VitalSignsAndSymptoms() {
		
	}
	
	/**
	 * Constructor for a <code>VitalSignsAndSymptoms</code> object.
	 * 
	 * @param temperature a double referring to patient temperature.
	 * @param bloodPressureSystolic a double referring to patient systolic 
	 * blood pressure.
	 * @param bloodPressureDiastolic a double referring to patient diastolic
	 * blood pressure.
	 * @param heartRate a double referring to patient heart rate.
	 * @param symptoms a String referring to patient described symptoms.
	 */
	public VitalSignsAndSymptoms(double temperature, double 
			bloodPressureSystolic, double bloodPressureDiastolic, 
			double heartRate, String symptoms) {
		if (temperature > 0.0 && temperature < 200.0) {
			this.temperature = temperature;
		}
		if (bloodPressureSystolic > 0.0 && bloodPressureSystolic < 500.0){
				this.bloodPressureSystolic = bloodPressureSystolic;	
		}
		if (bloodPressureDiastolic > 0.0 && bloodPressureDiastolic < 500.0){
			this.bloodPressureDiastolic = bloodPressureDiastolic;
		}
		if (heartRate > 0.0 && heartRate < 300.0) {   
			this.heartRate = heartRate;
		}
		if (symptoms != null && symptoms.length() > 0) {
			this.symptoms = symptoms;
		}
	}

	
	/**
	 * Gets a double temperature
	 * 
	 * @return 	a double temperature.
	 */
	public double getTemperature() {
		return temperature;
	}
	/**
	 * Sets the temperature
	 * @param temperature patient's temperature must be within logical range
	 */

	public void setTemperature(double temperature) {
		if (temperature > 0.0 && temperature < 200.0) {
			this.temperature = temperature;
		}
	}
	/**
	 *  Gets systolic blood pressure.
	 *  
	 * @return	a double bloodPressureSystolic.
	 */
	public double getBloodPressureSystolic() {
		return bloodPressureSystolic;
	}
	/**
	 * Sets the patient's systolic blood pressure.
	 * @param bloodPressureSystolic patient's systolic blood pressure.
	 */
	
	public void setBloodPressureSystolic(double bloodPressureSystolic) {
		if (bloodPressureSystolic > 0.0 && bloodPressureSystolic < 500.0){   // Criteria for systolic pressure
			this.bloodPressureSystolic = bloodPressureSystolic;	    
		}
	}
	
	/**
	 * Gets a double diastolic blood pressure.
	 * 
	 * @return a double bloodPressureDiastolic
	 */
	public double getBloodPressureDiastolic() {
		return bloodPressureDiastolic;
	}
	/**
	 * Sets the patient's diastolic blood pressure.
	 * @param bloodPressureDiastolic patient's diastolic blood pressure
	 */
	
	public void setBloodPressureDiastolic(double bloodPressureDiastolic) {
		if (bloodPressureDiastolic > 0.0 && bloodPressureDiastolic < 500.0){
			this.bloodPressureDiastolic = bloodPressureDiastolic;        // Assign variable to value
		}
	}
	
	
	/**
	 * Gets a double heart rate.
	 * 
	 * @return a double heartRate
	 */
	public double getHeartRate() {
		return heartRate;
	}
	/**
	 * Gets heartrate.
	 * @param heartRate patient's heartrate
	 */

	public void setHeartRate(double heartRate) {
		if (heartRate > 0.0 && heartRate < 300.0) {
			this.heartRate = heartRate;
		}
	}
	
	
	/**
	 * Gets the symptoms of a <code>VitalSignsAndSymptoms</code> object.
	 * 
	 * @return a string of the particular instance's symptoms.
	 */
	public String getSymptoms() {
		return symptoms;
	}
	
	/**
	 * Sets the patient's symptoms in the form of a String.
	 * @param symptoms patient's symptoms
	 */
	
	public void setSymtoms(String symptoms) {
		if (symptoms != null && symptoms.length() > 0) {     // There is in fact a description for the symptom; non-empty
			this.symptoms = symptoms;
		}
	}
	
}
