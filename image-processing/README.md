REST API for Image Processing module

ImageProcessing:

resource path: “/imageupload”

  method: POST - Adds a new Image in a repository by applicant ID 
    @RequestParan: String applicantID, MultipartFile file
    returns: (aplication/json) String with operation status
  
  method: PUT - Modifies applicants image
    @RequestParan: String applicantID, MultipartFile file
    returns: (aplication/json) String with operation status
  
  method: GET - Provides posibility to get image with required size 
    @RequestParan: String applicantID, int heigth, int width
    returns: (image/*) image as byte array (ResponseEntity<byte[]>)
  
  method: DELETE - Delete image from repository by applicantID
    @RequestParan: String applicantID
    returns: (aplication/json) String with operation status
    
    
  In case of exception:
  returns: (aplication/json) String with exception info
