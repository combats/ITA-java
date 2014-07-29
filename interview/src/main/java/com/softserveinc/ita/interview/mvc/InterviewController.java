package com.softserveinc.ita.interview.mvc;


import com.softserveinc.ita.dao.ApplicantInterviewDAO;
import com.softserveinc.ita.entity.ApplicantInterview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class InterviewController {


	@Autowired
	private ApplicantInterviewDAO applicantInterviewDAO;

	@RequestMapping(
			method = RequestMethod.POST,
			consumes = "application/json",
			produces = "application/json"
	)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ApplicantInterview createNewIntrview(@RequestBody ApplicantInterview applicantInterview){
		return applicantInterviewDAO.addInterview(applicantInterview);
	}


	@RequestMapping(
			method = RequestMethod.GET,
			produces = "application/json"
	)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ApplicantInterview> getAllInterviews(){
		return applicantInterviewDAO.getAllInterview();
	}

	@RequestMapping(
			value = "{id}",
			method = RequestMethod.GET,
			produces = "application/json"
	)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ApplicantInterview getInterviewById(@PathVariable String id){
		return applicantInterviewDAO.getInterviewById(id);
	}

	@RequestMapping(
			method = RequestMethod.PUT,
			consumes = "application/json",
			produces = "application/json"
	)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ApplicantInterview updateIntrview(@RequestBody ApplicantInterview applicantInterview){
		return applicantInterviewDAO.updateInterview(applicantInterview);
	}

	@RequestMapping(
			value = "{id}",
			method = RequestMethod.DELETE,
			produces = "application/json"
	)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ApplicantInterview deleteIntrview(@PathVariable String id){
		return applicantInterviewDAO.deleteInterviewById(id);
	}

}
