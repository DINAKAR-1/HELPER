<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


    <div style="min-height: 700px;margin: 100px;margin-top: 50px;" class="border shadow-lg ">
        <h2 style="margin-left: 50px;" class="mt-4">Report An Issue</h2>
        <hr>
        <div class="m-5 ">
            <form:form modelAttribute="issue" method="post" enctype="multipart/form-data" id="Issueform"
                action="submitIssue">

                <!-- Row 1 -->
                <div class="row mt-4">
                    <div class="col-sm-1">
                        <label class="col-form-label font-weight-bold" for="moduleId">Module</label><span
                            class="text-danger">*</span>
                    </div>
                    <div class="col-sm-1">
                        <label class="col-form-label font-weight-bold" for="moduleId">:</label>
                    </div>
                    <div class="col-sm-3">
                        <div class="form-group">
                            <form:select class="selectbox2 form-control " required="true" path="module" id="module">
                                <form:option value="">-select-</form:option>
                                <form:option value="X">Option X</form:option>
                                <form:option value="Y">Option Y</form:option>
                                <form:option value="Z">Option Z</form:option>
                            </form:select>
                        </div>
                    </div>
                    <div class="col-sm-1">
                        <label class="col-form-label font-weight-bold" for="moduleId">Sub-Module</label>
                    </div>
                    <div class="col-sm-1">
                        <label class="col-form-label font-weight-bold" for="moduleId">:</label>

                    </div>
                    <div class="col-sm-3">
                        <div class="form-group">
                            <!-- <label for="subModule">Sub Module</label> -->
                            <form:select class="selectbox2 form-control" required="true" path="subModule"
                                id="subModule">
                                <form:option value="">-select-</form:option>
                                <form:option value="1">Option 1</form:option>
                                <form:option value="2">Option 2</form:option>
                                <form:option value="3">Option 3</form:option>
                                <form:option value="4">Option 4</form:option>
                            </form:select>
                        </div>
                    </div>
                </div>

                <!-- Row 2 -->
                <div class="row">
                    <div class="col-sm-1">
                        <label class="col-form-label font-weight-bold" for="moduleId">Service</label><span
                            class="text-danger">*</span>
                    </div>
                    <div class="col-sm-1">
                        <label class="col-form-label font-weight-bold" for="moduleId">:</label>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <!-- <label for="service">Service</label> -->
                            <form:select class="selectbox2 form-control" required="true" path="service" id="service">
                                <form:option value="">-select-</form:option>
                                <form:option value="1">Option 1</form:option>
                                <form:option value="2">Option 2</form:option>
                                <form:option value="3">Option 3</form:option>
                                <form:option value="4">Option 4</form:option>
                            </form:select>
                        </div>
                    </div>
                    <div class="col-sm-1">
                        <label class="col-form-label font-weight-bold" for="moduleId">Type</label><span
                            class="text-danger">*</span>
                    </div>
                    <div class="col-sm-1">
                        <label class="col-form-label font-weight-bold" for="moduleId">:</label>
                    </div>
                    <div class="col-sm-3">
                        <div class="form-group">
                            <!-- <label for="type">Type</label> -->
                            <form:select class="selectbox2 form-control" required="true" path="type" id="type">
                                <form:option value="">-select-</form:option>
                                <form:option value="Technical">Technical</form:option>
                                <form:option value="Non-Technical">Non-Technical</form:option>

                            </form:select>
                        </div>
                    </div>
                </div>

                <!-- Priority -->
                <div class="row">
                    <div class="col-sm-1">
                        <label class="col-form-label font-weight-bold" for="moduleId">Priority</label><span
                            class="text-danger">*</span>
                    </div>
                    <div class="col-sm-1">
                        <label class="col-form-label font-weight-bold" for="moduleId">:</label>
                    </div>
                    <div class="col-sm-3">
                        <div class="form-group">
                            <!-- <label for="priority">Priority</label> -->
                            <form:select class="selectbox2 form-control" required="true" path="priority" id="priority">
                                <form:option value="">-select-</form:option>
                                <form:option value="1">Option 1</form:option>
                                <form:option value="2">Option 2</form:option>
                                <form:option value="3">Option 3</form:option>
                                <form:option value="4">Option 4</form:option>
                            </form:select>
                        </div>
                    </div>
                </div>

                <!-- Description -->
                <div class="row">
                    <div class="col-sm-1">
                        <label class="col-form-label font-weight-bold" for="moduleId">Description</label><span
                            class="text-danger">*</span>
                    </div>
                    <div class="col-sm-1">
                        <label class="col-form-label font-weight-bold" for="moduleId">:</label>
                    </div>
                    <div class="col-sm-8">
                        <div class="form-group">
                            <!-- <label for="description">Description</label> -->
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <form:textarea class="form-control" required="true" path="description"
                                            id="description" rows="10" placeholder="Enter Description" />

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Attachment -->
                <div class="row">
                    <div class="col-sm-1">
                        <label class="col-form-label font-weight-bold" for="moduleId">Attachment&nbsp;<span
                                class="text-danger">*</span></label>
                    </div>
                    <div class="col-sm-1">
                        <label class="col-form-label font-weight-bold" for="moduleId">:</label>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="attachment">Attachment</label>
                            <input type="file" name="file_doc" id="file_doc" class="form-control py-1" />

                        </div>
                    </div>
                </div>

                <!-- Submit Button -->
                <div class="row d-flex justify-content-center text-center">
                    <button type="submit" class="btn btn-primary px-5">Submit</button>
                </div>


            </form:form>
        </div>
    </div>

    <script type="text/javascript">
        function preventBack() {
            window.history.forward();
        }
        setTimeout("preventBack()", 0);
        window.onunload = function () { null };
    </script>

    <script>

        $(document).ready(function () {
            $.validator.addMethod('filesize', function (value, element, param) {
                return this.optional(element) || (element.files[0].size <= param)
            }, 'File size must be less than 1 mb');
            $.validator.addMethod('fileextension', function (value, element, param) {
                var fileExtension = value.split('.').pop().toLowerCase(); // Get the file extension
                return this.optional(element) || param.indexOf(fileExtension) !== -1; // Check if the file extension is in the allowed list
            }, 'Invalid file extension. Only {0} files are allowed.');



        })
        // Initialize form validation on the myForm form element
        $("#Issueform").validate({
            // Specify validation rules
            rules: {
                module: {
                    required: true,

                },
                subModule: {
                    required: true,

                },
                service: {
                    required: true,

                },
                type: {
                    required: true,

                },
                priority: {
                    required: true,

                },
                description: {
                    required: true,

                },
                file_doc: {
                    required: true,
                    fileextension: ["jpeg", "jpg", "png", "pdf"], // Allowed extensionss
                    filesize: 10485,
                }

            },
            // Specify validation error messages
            messages: {
                module: {
                    required: "Please Select Module",

                },
                subModule: {
                    required: "Please Select Sub-Module",

                },
                service: {
                    required: "Please Select Service",

                },
                type: {
                    required: "Please Select Type",

                },
                priority: {
                    required: "Please Select Priority ",

                },
                description: {
                    required: "Please Enter Description",

                },
                file_doc: {
                    required: "Please upload a file",
                    fileextension: "Only JPG, PNG, PDF  files are allowed",
                    filesize: "File size must not exceed 20 MB",
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
                // Submit the form
                if (confirm("Are you sure you want to submit ?")) {
                    form.submit();
                }
            },
        })

    </script>
