<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

                <% String path=request.getContextPath(); String basePath=request.getScheme() + "://" +
                    request.getServerName() + ":" + request.getServerPort() + path + "/" ; %>

                    <!DOCTYPE html>
                    <html lang="en">

                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>Issue Tracking Approval</title>

                        <!-- External CSS -->
                        <link rel="stylesheet" href="<%= basePath %>resources/css/bootstrap.min.css">
                        <link rel="stylesheet" href="<%= basePath %>resources/css/font-awesome4.2.min.css">
                        <link rel="stylesheet" href="<%= basePath %>resources/custom.css">
                        <link rel="stylesheet" href="<%= basePath %>resources/menu/sm-core-css.css">
                        <link rel="stylesheet" href="<%= basePath %>resources/menu/sm-blue.css">
                        <link rel="stylesheet" href="<%= basePath %>resources/css/google_poppins.css">
                        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">

                        <!-- jQuery and DataTables JS -->
                        <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> -->
                        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
                        <script src="https://cdn.datatables.net/buttons/2.2.3/js/dataTables.buttons.min.js"></script>
                        <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
                        <script src="https://cdn.datatables.net/buttons/2.2.3/js/buttons.html5.min.js"></script>

                        <style>
                            body {
                                font-family: 'Poppins', sans-serif;
                                background-color: #f4f6f9;
                            }

                            .container {
                                margin-top: 30px;
                            }

                            .card {
                                border-radius: 10px;
                                box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
                            }

                            .card-header {
                                background-color: #003366;
                                /* Dark blue */
                                color: #003366;
                                font-weight: bold;
                                border-radius: 10px 10px 0 0;

                            }

                            .table-wrapper {

                                width: 100%;
                            }

                            .table {
                                min-width: 1200px;
                                /* Ensures table does not shrink */
                                white-space: nowrap;
                                /* Prevents text from wrapping */
                            }

                            .table th {
                                background-color: #003366;
                                /* Dark blue */
                                color: white;
                                text-align: center;
                                padding: 10px;
                            }

                            .table td {
                                text-align: center;
                                padding: 10px;
                            }

                            .msg-success {
                                text-align: center;
                                font-size: 14px;
                                color: green;
                                padding-top: 10px;
                            }

                            #issueTrackerReportTable_wrapper .dataTables_filter {
                                position: absolute;
                                bottom: 0.5px;
                            }
                        </style>
                    </head>

                    <body>

                        <div class="container">
                            <div class="card">
                                <div class="card-header">
                                    <h3>Issue Tracking Approval</h3>
                                </div>
                                <!-- Button trigger modal -->
                                <!-- <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#exampleModal">
                                    Launch demo modal
                                </button> -->

                                <!-- Modal -->
                                <div class="modal fade" id="exampleModal" tabindex="-1"
                                    aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                    aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                ...
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary"
                                                    data-dismiss="modal">Close</button>
                                                <button type="button" class="btn btn-primary">Save changes</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <c:if test="${not empty msg}">
                                        <div class="msg-success">${msg}</div>
                                    </c:if>

                                    <form:form action="getIssueDetails" method="post" id="issueTracker"
                                        modelAttribute="issueTrackerDTO">
                                        <div class="table-wrapper">
                                            <table class="table table-bordered table-striped"
                                                id="issueTrackerReportTable">
                                                <thead>
                                                    <tr>
                                                        <th>S.NO.</th>
                                                        <th>Officer Name</th>
                                                        <th>Designation</th>
                                                        <th>Issue Type</th>
                                                        <th>Priority</th>
                                                        <th>Module</th>
                                                        <th>Sub Module</th>
                                                        <th>Service</th>
                                                        <th>Issue No</th>
                                                        <th>Issue Date</th>
                                                        <th>Issue Details</th>
                                                        <th>Status</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="reportData" items="${issueTrackerReport}"
                                                        varStatus="row">
                                                        <tr>
                                                            <td>${row.index + 1}</td>
                                                            <td>${reportData.created_by}</td>
                                                            <td>${reportData.designation}</td>
                                                            <td>${reportData.issue_type}</td>
                                                            <td>${reportData.priority}</td>
                                                            <td>${reportData.module_name}</td>
                                                            <td>${reportData.submodule_name}</td>
                                                            <td>${reportData.service_name}</td>
                                                            <td> <a href="javascript:void(0)" class="text-primary"
                                                                    data-toggle="tooltip" title="Issue Tracker Details"
                                                                    onclick="urlPostSubmit('issueTrackerDetails', ['trackerId', 'view'], ['${reportData.issue_tracker_id}', '0'])">
                                                                    ${reportData.issue_tracker_id}
                                                                </a></td>
                                                            <td>${reportData.issue_created_date}</td>
                                                            <td>${reportData.issue_description}</td>
                                                            <td>${reportData.status}</td>

                                                            <td>
                                                                <c:if test="${reportData.status eq 'resolved'}">
                                                                    -
                                                                </c:if>
                                                                <c:if test="${reportData.status ne 'resolved'}">
                                                                    <a href="javascript:void(0)" class="text-primary"
                                                                        data-toggle="tooltip"
                                                                        title="Issue Tracker Details"
                                                                        onclick="urlPostSubmit('issueTrackerDetails', ['trackerId', 'view'], ['${reportData.issue_tracker_id}', '1'])"><i
                                                                            class="fa fa-edit fa-lg"
                                                                            aria-hidden="false">
                                                                        </i></a>
                                                                </c:if>

                                                            </td>

                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>


                                    </form:form>
                                </div>
                            </div>
                        </div>

                        <script>
                            $('#issueTrackerReportTable').DataTable({
                                "paging": true,
                                "lengthMenu": [[15, 25, 50, -1], [15, 25, 50, "All"]],
                                "searching": true,
                                "ordering": true,
                                "info": true,
                                "scrollX": true,
                                dom: '<"row"<"col-lg-6 text-left"f><"col-lg-6 text-right"Br><"col-lg-12"t><"col-lg-5"i><"col-lg-7"p>>',
                                buttons: [
                                    {
                                        extend: 'excel',
                                        text: '<i class="fa fa-file-excel-o"></i>', // Excel icon
                                        title: 'Issue Tracker Report',
                                        className: 'btn btn-secondary btn-sm', // Grey color
                                        filename: 'Issue_Tracker_Report_' + new Date().toISOString().slice(0, 10),
                                        exportOptions: {
                                            columns: ':visible'
                                        }
                                    }
                                ]
                            });


                            function urlPostSubmit(path, paramNames, paramValues) {

                                var myForm = document.createElement("form");
                                myForm.setAttribute("method", "post");
                                myForm.setAttribute("action", path);

                                var token = $('#_csrf').attr('content');
                                var csrfField = document.createElement("input");
                                csrfField.setAttribute("type", "hidden");
                                csrfField.setAttribute("name", "_csrf");
                                csrfField.setAttribute("value", token);
                                myForm.appendChild(csrfField);


                                for (var i = 0; i < paramNames.length; i++) {

                                    var customizedField = document.createElement("input");
                                    customizedField.setAttribute("type", "hidden");
                                    customizedField.setAttribute("name", paramNames[i]);
                                    customizedField.setAttribute("value", paramValues[i]);
                                    myForm.appendChild(customizedField);
                                }
                                document.body.appendChild(myForm);

                                myForm.submit();
                                return false;

                            }

                        </script>
                    </body>

                    </html>
