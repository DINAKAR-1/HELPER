	var mandatoryMsg = "Please Provide : ";
		var flag = true;
		
		var eId = "electionFor-phase-districtId-revenueDivisionId-mandalId-officerCategory";/* -trainingDate-hour_id-secondId-am_pm-place */

		var eName = "Election Type-Phase-District-Division-Election Mandal-Officer Category";/* -Date-Time Hours-Time Seconds-Time AM/PM-Place */

		var id = [];
		var name = [];
		id = eId.split("-");
		name = eName.split("-");

		for ( var x = 0; x < id.length; x++) {
		
			if(id[x]!="secondId"){
				var value = $("#" + id[x]).val();
				if (!value || value.trim() == "" || value == "0" || Number(value) == 0) {
					mandatoryMsg += "\n - " + name[x];
					flag = false;
				}
			}else{
				var value = $("#" + id[x]).val();
				if (!value || value.trim() == "" || value == "0") {
					mandatoryMsg += "\n - " + name[x];
					flag = false;
				}
			}
		}
		
		if (flag) {
            $("#loading").html("Loading Please wait...........................");
			document.forms[0].action="getindividualAppointmentOrder";
			document.forms[0].submit();
		} else {
			alert(mandatoryMsg);
			return false;
		}
