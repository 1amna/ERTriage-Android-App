//package com.example.backend;
//
//import java.io.FileNotFoundException;
//import java.io.Serializable;
//
//
//public class PatientManager implements Serializable  {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 7185674839074378630L;
//
//	/**
//	 * @param args
//	 */
//	final static String temp_path ="/files/patient_records.txt";
//	
//	
//	
//	private Triage triage;
//	
//
//	
//
//	public PatientManager() throws FileNotFoundException {
//		this.triage = new Triage();
//		this.triage.loadPatientData(temp_path);
//
//		
//		
//	}
//	public Triage getTriage() {
//		return triage;
//	}
//	public void setTriage(Triage triage) {
//		this.triage = triage;
//	}
//	public Patient findbyHealthCard(String healthcardnumber){
//		return triage.getPatient(healthcardnumber);
//		
//	}
//
///*   public static void main(String[] args) throws FileNotFoundException {
//		// TODO Auto-generated method stub
//		PatientManager test = new PatientManager();
//	
//		for (Entry<String, Patient> entry : test.triage.getPatients().entrySet())
//		{
//		    System.out.println(entry.getKey() + "/" + entry.getValue());
//		}
//	
//	}*/
//
//}
