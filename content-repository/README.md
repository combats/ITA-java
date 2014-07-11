REST API for Repository module

ImageProcessing:


resource path: “repo/image/user-applicant/{ID}”

    method: POST - Adds a new Image in a repository by applicant ID
    
        returns: (json) String with operation status (ResponseEntity<String>)
        
    method: GET - Provides possibility to get image with required size
    
        returns: (base64) image as byte array (ResponseEntity<String>)

additional path: “/{ID}?height=value1&width=value2”

        method: GET - Provides possibility to get image with required size
        
            returns: (base64) image as byte array (ResponseEntity<String>)

IN CASE OF EXCEPTION:
returns: (json) String with exception info


resource path: “repo/imagefile/{ID}”

    method: POST - Adds a new Image in a repository by applicant ID
    
        @RequestParam: MultipartFile file
        
        returns: (json) String with operation status
        
    method: GET - Provides possibility to get image with required size
    
        returns: (image/*) image as byte array (ResponseEntity<byte[]>)
        
    method: DELETE - Delete image from repository by applicantID
    
        returns: (json) String with operation status
        
additional path: “repo/imagefile/{ID}?height=value1&width=value2” - not yet

        method: GET - Provides possibility to get image with required size
        
            returns: (image/*) image as byte array (ResponseEntity<byte[]>)