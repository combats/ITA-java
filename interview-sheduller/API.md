#ENTITY: 

###Applicant: 
```java
//Id, uid
private String appointmentId;
private List <String> userIdList;
private String applicantId;
private long startTime;
private long durationTime;


```

#SERVICE:

###AppointmentService:

resource path: ```/appointments/applicant/{applicantId}```
	
	method:  GET - 
	
	public List<Appointment> getAppointmentByApplicantId(@PathVariable String applicantId) 
    - returns all appointments of applicant by his id

resource path: ```/appointments/{appointmentId}```

    method:  DELETE - 

    public void removeAppointmentById(@PathVariable String appointmentId)
    - removes appointment by its id
    
resource path: ```/appointments/```

    method:  POST - 
    
    public @ResponseBody String addNewAppointment(@RequestBody @Valid Appointment appointment)
    - adds new appointment

resource path: ```/appointments/{appointmentId}```

    method:  GET - 
    
    public Appointment getAppointmentByAppointmentID(@PathVariable("appointmentId") String appointmentId)
    - gets appointment by appointment id

resource path: ```/appointments/date/{date}```

    method:  GET - 
    
    public List<Appointment> getAppointmentsByDate(@PathVariable long date)
    - gets appointment by date
    
#Jul.3.2014