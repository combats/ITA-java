#ApplicantsNotification:

resource path: ```/appointments/applicant/{applicantId}```

	method:  GET -

	public List<Appointment> getAppointmentByApplicantId(@PathVariable String applicantId)
    - returns all appointments of applicant by his id
