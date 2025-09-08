<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <!DOCTYPE html>
        <html>

        <head>
          <meta charset="ISO-8859-1" />
          <title>Change images</title>
          <style>
            .required {
              color: red;
            }

            .alert {
              transition: opacity 0.4s ease-out;
            }

            .alert.closed {
              opacity: 0;
            }

            #myBtn {
              display: none;
              position: fixed;
              bottom: 20px;
              right: 40px;
              z-index: 99;
              font-size: 18px;
              border: none;
              outline: none;
              background-color: #0081cf;
              color: white;
              cursor: pointer;
              padding: 10px;
              border-radius: 4px;
            }

            #myBtn:hover {
              background-color: #555;
            }
          </style>
        </head>

        <body>
          <div class="container-fluid py-5 mx-0">
            <button onclick="topFunction()" id="myBtn" title="Go to top" class="rounded-circle">
              <i class="fa fa-arrow-up"></i>
            </button>
            <div class="row">
              <div class="col-md-12">

                <c:if test="${not empty message}">
                  <div class="alert alert-success  alert-dismissible fade show" role="alert">
                    ${message}
                  </div>
                </c:if>
                <div class="row">
                  <div class="col-md-12  px-5">
                    <!-- form card upload image -->
                    <div class="card mt-5">
                      <div class="card-header mx-0">
                        <h4 class="mb-0 p-2 ">Raise Issue</h4>
                      </div>

                      <div class="card-body shadow-lg pb-3" style="width: 100%">
                        <form:form class="form" modelAttribute="RequestByOfficer"
                          action="${pageContext.request.contextPath}/raiseIssue/submitIssue" method="post" role="form"
                          autocomplete="off" enctype="multipart/form-data" id="raiseIssueForm" onsubmit="return checkFileSize()">
                          <table class="table mb-0">
                            <tbody>
                              <tr>
                                <td class="pt-3">Title <span class="required">*</span></td>
                                <td class="pt-3" id="titles" > 
                                  <form:input type="text" name="title" path="issueTitle" class="form-control" id="title" placeholder="Enter the title of the issue" required="required" />
                                </td>

                                 
                              </tr>
                              <tr>
                                <td class="pt-3">Type of Issue <span class="required">*</span></td>
                                <td>
                                  <form:select name="issues" path="raisedIssueId" class="form-control" id="issues"
                                    required="required" menuPlacement="bottom">
                                    <option value="-1" selected disabled>
                                      --Select--
                                    </option>
                                    

                                    <c:forEach var="eachIssue" items="${IssueList}">
                                      <form:option value="${eachIssue.requestId}">
                                        ${eachIssue.requestName}
                                      </form:option>
                                    </c:forEach>
                                  </form:select><br>
                                  <label id="otherField" style="display: none;">
                                    <i>Please specify your issue below  <span class="required">*</span></i>
                                    <input type="text" id="others" name="new_issue" class="form-control "  pattern="[A-Za-z][A-Za-z0-9 ]*" title="should only start with letters"  />
                                  </label>
                   <!-- dropdown for Application list if issue is selected as unlock application -->
                   <div >
                    <tr   id="applicationList" style="display: none;"  >
                      <td>
                        <label for="applist"> Select no of applications   <span class="required">*</span></label>  
                      </td>
                      <td id="applicationsection"> 
                        <input type="number" id="appNumber" max="10" min="1" onchange="createApplicationTypeAndNumberGroups()" onkeyup="createApplicationTypeAndNumberGroups()" name="appNumber" class="form-control mb-1 " placeholder="Enter number of applications" required >
                        <span id="maxApplications"class="required  " style=" font-size: 14px;"> maximum of 10 applications can be selected </span>

                        <div id="applicationTypeAndNumberContainer" class="mt-2"></div>
                      </td>
                    </tr>
                  </div>
                              <!-- / -->
                                </td>
                              </tr>
                              <tr>
                                <td class="pt-3">Description <span class="required">*</span><span><br >(Maximum 500 characters)</span></td>
                                <td>
                                  <form:textarea name="description" path="description"   class="form-control" maxlength="500" placeholder="Enter description"  onkeyup="checklength(this.value);"
                                    id="description" rows="3"    required="true"></form:textarea>
                                    <!-- <span style="color: red;font-size: 12px;">(Max: 500)</span> -->
												&nbsp;&nbsp;<span id="remain" style="color: red;font-size: 14px;">500 </span> 
                        <span id="remain" style=" font-size: 14px;"> characters remaining </span> 
                                </td>
                              </tr>
                              <tr>
                                <td class="pt-3">Upload Files  <br >(Maximum 2MB)</span></td>
                                <td>
                                  <input type="file" name="upload_file" class="form-control p-1" id="file"   />
                                </td>

                                </td>
                              </tr>
                              <tr>
                                <td></td>
                                <td></td>
                              </tr>

                            </tbody>
                          </table>
                          <div class="text-center "><button type="submit" class="btn btn-flat text-white"
                              style="background-color: #0081cf">
                              Submit
                            </button></div>
                        </form:form>



                      </div>
                    </div>
                    <!-- /form card upload image -->

                    <!-- display saved images -->
                  </div>
                </div>
                <!--/row-->
              </div>
              <!--/col-->
            </div>
            <!--/row-->
            <hr />
          </div>
          <!--/container-->

          <script>
            // Function to close the alert after a  second
            function closeAlert() {
              setTimeout(function () {
                var alertElement = document.querySelector(".alert");
                if (alertElement) {
                  alertElement.classList.add("closed");
                }
              }, 1000);
            }

            document.addEventListener("DOMContentLoaded", function () {
              //fuction for alert closedown effect
              closeAlert();
              //set the select option to defal
              document.getElementById("issues").value = -1;
              // Get the button
              let mybutton = document.getElementById("myBtn");

              // When the user scrolls down 20px from the top of the document, show the button
              window.onscroll = function () {
                scrollFunction();
              };

              function scrollFunction() {
                if (
                  document.body.scrollTop > 30 ||
                  document.documentElement.scrollTop > 30
                ) {
                  mybutton.style.display = "block";
                } else {
                  mybutton.style.display = "none";
                }
              }

              // When the user clicks on the button, scroll to the top of the document
            });

            // JavaScript to populate input field with selected value
          </script>

          <script>
         

            function topFunction() {
              window.scrollTo({
                top: 0,
                behavior: "smooth",
              });
            }



          </script>
          <script>
    	function checklength(value){
	var val=$('#description').val();
		var maxchars = 500;
		var tlength = $('#description').val().length;
		$('#description').val($('#description').val().substring(0, maxchars));
		var tlength = $('#description').val().length;
		remain = maxchars - parseInt(tlength);
		$('#remain').text(remain);
}
    function checkFileSize() {
        var file = document.getElementById('file');
        if(file.files[0].size > 2097200) {
            alert('File size exceeds 2MB limit!');
            return false;
        }
        return true;
    }

   
    
 
    document.getElementById('issues').addEventListener('change', function() {
        if (this.value === '-2') {
            document.getElementById('otherField').style.display = 'block';
            document.getElementById('others').required = true;
            document.getElementById('applicationList').style.display = 'none';
            document.getElementById('appNumber').value='';
            document.getElementById('applicationTypeAndNumberContainer').innerHTML=''; 
            document.getElementById('appNumber').required = false;
          } 
          else if(this.value === '1'||this.value ==='2'){
            document.getElementById('applicationList').style.display = '';     
            document.getElementById('appNumber').required = true;
            document.getElementById('otherField').style.display = 'none';
            document.getElementById('others').value = '';
            document.getElementById('others').required = false;
        }
        else {
          document.getElementById('applicationList').style.display = 'none'; 
          document.getElementById('appNumber').value=''; 
          document.getElementById('applicationTypeAndNumberContainer').innerHTML=''; 

            document.getElementById('otherField').style.display = 'none';
            document.getElementById('others').value = '';
            document.getElementById('others').required = false;
            document.getElementById('appNumber').required = false;

        }
    });
 
          </script>
<script>
    $(document).ready(function () {


      
      // Initialize form validation on the myForm form element
      $("#raiseIssueForm").validate({ 

        
            // Specify validation rules
            rules: {
              issueTitle: {
                required: true,

                pattern: "[A-Za-z][A-Za-z0-9 ]*",
              },
              description: {
                required: true,

                pattern: "[A-Za-z][A-Za-z0-9 ]*",
              },
              raisedIssueId: {
                required: true, 
              },
           
              
                
            },
            // Specify validation error messages
            messages: {
              issueTitle: {
                required: "Please enter title  ",

                pattern:
                  "Title should only start with letters",
              },
              description: {
                required: "Please enter description",

                pattern:
                  "Description should only start with letters",
              },
              raisedIssueId: {
                required: "Please select type of issue", 
              },
             
              
              
            },

            
            // Add Bootstrap 4 styling to the error messages
            errorElement: "span",
            errorClass: "invalid-feedback",
            
            highlight: function (element, errorClass, validClass) {
              $(element).addClass("is-invalid").removeClass("is-valid");
            },
            unhighlight: function (element, errorClass, validClass) {
              $(element).removeClass("is-invalid").addClass("is-valid");
          },

          submitHandler: function (form) {
               // Gather field values into an array of objects
      var appNumber = [];
      var appTypes=[];
      //for application numbers
      $("[name^='applicationNumber']").each(function() {
        
        appNumber.push($(this).val());
      });
      //for application types
      $("[name^='applicationType']").each(function() {
        
        appTypes.push($(this).val());
      });

       // Create a hidden input field to store the app numbers field values array
       var applicationNumber = $("<input>")
          .attr("type", "hidden")
          .attr("name", "applicationNumber")
          .val(appNumber);

        // Create a hidden input field to store the app types field values array
        var applicationType = $("<input>")
          .attr("type", "hidden")
          .attr("name", "applicationType")
          .val(appTypes);

        // Append the hidden input fields to the form
        $(form).append(applicationNumber);

        // Append the hidden input fields to the form
        $(form).append(applicationType);
        
            // Submit the form
            if (confirm("Are you sure you want to raise issue ?")) {
              form.submit();
            }
          },
        });
      });
    
    
  </script>
    <script>
   function checkApplength(inputField){
      // Access the value of the input field
    var value = inputField.value;
   // If the value is negative, make it positive
   if (value < 0) {
        inputField.value = Math.abs(value);
    }
    if (value.length > 3) {
        // alert('You cannot enter more than 3 digits');
        inputField.value = value.slice(0, 3);
    }
    console.log(value);

   }
    var applicationNames = [
  <c:forEach var="name" items="${applicationTypes}" varStatus="status">
      "${name}"<c:if test="${!status.last}">,</c:if>
  </c:forEach>
  ];
     
      function createApplicationTypeAndNumberGroups() {
        
    var numberOfApplications = $('#appNumber').val(); // Get the selected value from the appList dropdown
    if (numberOfApplications > 10) {
        // alert('You cannot enter a number greater than 10');
         document.getElementById("appNumber").value = 0;
         document.getElementById('applicationTypeAndNumberContainer').innerHTML = '';
    }
    else{

   
      var groups = '';
for (var i = 0; i < numberOfApplications; i++) {

  console.log(i);

    groups += '<div class="applicationTypeAndNumberGroup mt-2 d-flex">';
    groups += '<div id="applicationType" class="mx-2 w-100    ">';
    groups += '<label for="applicationType">Application Type  <span class="required">*</span> </label>';
    groups += '<select required id="applicationType' + i + '" name="applicationType' +i+'" class="form-control" />';
    groups += '<option value="-1" disabled selected>------- Select -------  </option>';

    for (var j = 0; j < applicationNames.length; j++) {
        groups += '<option value="' + applicationNames[j] + '">' + applicationNames[j] + '</option>';
    }

    groups += '</select>';
    groups += '</div>';
    groups += '<div id="applicationNumber" class=" w-100  ">';
    groups += '<label for="applicationNumber' + i + '">Application Number  <span class="required">*</span></label>';
    groups += '<input type="number" required id="applicationNumber'+i+'" name="applicationNumber_'+i+ '" onchange="checkApplength(this)"'; 
    groups +='onkeyup="checkApplength(this)"  maxlength="3" placeholder="Enter Application Number" min="0" max="999" class="form-control">';
    
    groups += '</div>';
    groups += '</div>';
}

   
    
    document.getElementById('applicationTypeAndNumberContainer').innerHTML = groups;
   }

   
}
    </script>
      </body>

        </html>
