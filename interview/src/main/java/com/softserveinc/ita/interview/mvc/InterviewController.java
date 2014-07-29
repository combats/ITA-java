package com.softserveinc.ita.interview.mvc;


import com.softserveinc.ita.entity.interview.ApplicantInterview;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class InterviewController {



	@RequestMapping(
			method = RequestMethod.POST,
			consumes = "application/json",
			produces = "application/json"
	)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ApplicantInterview createNewIntrview(@RequestBody ApplicantInterview applicantInterview){
		return new ApplicantInterview();
	}

}
