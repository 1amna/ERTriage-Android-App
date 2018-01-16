## Phase III of ERTriage Project

 The application, ERTriage, allows nurses to prioritize patients coming 
 into an ER in terms of urgency or arrival time.  Nurses can also keep 
 track of and update patients' vitals and symptoms until seen by a doctor.
 The condition and thus the urgency are monitored and updated 
 when any changes are made.

 For optimum functioning of the application, data about patients is saved
 to and retrieved from text files.


# Class Patient has the following variables:
  - Name: String
  - DateofBirth: String
  - HealthCardNumber: String
  - ArrivalTime: Calendar -  Will mark the patient's time of arrival.
  - UrgencyScore: int
  - Condition: NavigableMap<Calendar, VitalSignsAndSymptoms> - Type TreeMap
    where keys are the times that a vital sign is recorded and values are
    the vitals that were recorded.
  - SeenByDoctor: List<Calendar> when the patient was seen by doctors.
  - MostRecent: VitalSignsAndSymptoms - an instance of the patients most recent
    vital signs and symptoms.

# Class Patient has the following methods, aside from setters and getters:
  - UpdateMostRecent - has one parameter, an object of type VitalSignsAndSymptoms.  
    It updates the mostRecent vital signs and symptoms taken by the nurse.  If no 
    new values are taken, values from the previous instance are taken instead.
  - UpdateCondition - takes on parameterer, an object of type VitalSignsAndSymptoms.  
    Records VitalSignsAndSymptoms of a nurse's visit with a time stamp.	Also updates 
    the most recent values for all available vital signs and symptoms.
  - UpdateDoctorVisit - adds a time stamp to List doctorVisits when the patient
    has been seen by a doctor.
  - seenByDoctor - returns boolean; whether or not patient was seen by a doctor. 
  - resetSeenByDoctor() - resets doctorVisits list when Patient has been seen by 
    a doctor.  Used when Patient returns to ER.
  - PrintPatientInfo - prints the patient's info on screen.

# Class Triage has the following variables:
  - Patients: Map<String, Patient>: Where the keys are each Patient's
    HealthCardNumber and the values for each key are the numbers' respective
    Patient.  (Note Patients has no specific order since the methods
    GetPatientListByUrgency and GetPatientListByArrivalTime generate ordered
    lists).
  - path: String: a path to the location of the source text file storing patients' 
    information.
  - temp_path: String: a temporary path to the location of source the text file 
    storing patients' information (this is for testing purposes). 
  - patientList: List<Patient>: a list of all patients using source text file.

# Class Triage has the following methods, aside from setters and getters:
  - AddPatient - returns Patient and has one parameter, a Map<String, String>
    (where the first string is the name of the patient info's field, and the
    second is the value of which that field will take, ex: the first element is
    "name" and the second is "John Smith").  If the add is successful, return
    the instance of Patient that was added, else return null.
  - RemovePatient - returns Patient if the patient is removed from Patients
    list, returns null if an error occurs.  RemovePatient takes one parameter,
    the patient's HealthCardNumber.
  - UpdatePatient - returns boolean and has six parameters: patient's
    healthCardNumber, a set of new info in String form: temp, bpDiastolic, 
    bpSystolic, heartRate, symptoms.  UpdatePatient returns false if any error 
    occurs, and will return true if the update is successful. UpdatePatient calls 
    Patient.UpdateCondition as appropriate to update the patient's condition.
  - GetPatientListByUrgency - creates and returns a list of patients ordered by 
    the urgency score of each patient where the highest urgency patient is at the
    top of the list.
  - GetPatientListByArrivalTime - creates and returns a list of patients ordered
    by the their arrival times where the most recently arrived patient is at the
    bottom of the list (non increasing order).
  - calculateUrgencyScore - returns an int which denotes the urgency level and has
    one parameter, an object of type Patient.  
  - SavePatientData - Save all serialized patient records into a .ser file. 
  - LoadPatientData - Builds triage patient list from the serialized patient
    records file.

# Class VitalSignsAndSymptoms has the following variables:

  - temperature: double
  - bloodPressureSystolic: double
  - bloodPressureDiastolic: double
  - heartRate: double
  - symptoms: String

## Class VitalSignsAndSymptoms has setter and getter methods, which make use of the variables.

# Class User has the following variables:

  - userName: String
  - passwords: String
  - permissions: String

# Class User has setter and getter methods, which make use of the variables.

# Class Nurse, which extends user, does not have any new variables and one constructor 
   making use of username and password vairbales.

# Class CreatePatientActivity is an activity with no new variables and has @Override methods.

# Class DoctorVisitActivity is an activity with the following variables:
  - triageList: Triage
  - healthCard: String

# Aside from @Override methods, class DoctorVisitActivity has the method:
  - RecordDoctorVisit  - records visit into triageList.


# Class ListPatientsActivity is an activity with the following variables:
  - ListOfPatients : TextView
  - triageList: Triage

# Aside from @Override methods, class ListPatientsActivity has the following methods:
  - listByUrgency - views all patients in terms of urgency by hospital standards.
  - listByArrival - views all patients in terms of arrival time.

# Class LoginActivity is an activity with the following variables:
  - userList : Map<String, User>

# Aside from @Override methods, class LoginActivity has the following methods:
  - login - allows users to login.
  - loudUserData - loads login information. 

# Class RecordPrescriptionActivity is an activity with the following methods, aside
from @Override methods:
  - recordPrescription - records prescription of medication.



