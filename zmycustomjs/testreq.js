var requesttable;
var rowNode;
var obj = {
	    "txt": "fa-file-text-o",
	    "xls": "fa-file-excel-o",
	    "xlam": "fa-file-excel-o",
	    "xlsb": "fa-file-excel-o",
	    "xltm": "fa-file-excel-o",
	    "xlsm": "fa-file-excel-o",
	    "xlsx": "fa-file-excel-o",
	    "jpeg": "fa-file-image-o",
	    "jpg": "fa-file-image-o",
	    "png": "fa-file-image-o",
	    "docx": "fa-file-word-o",
	    "doc": "fa-file-word-o",
	    "pdf": "fa-file-pdf-o"
	};

$(function () {


	/*var kvArray = [["key1", "value1"], ["key2", "value2"]];
	var myMap = new Map(kvArray);
	alert(myMap.get("key1"));*/


	/*$('#slidebarMenuRequest').on( 'click', function () {
		$('#content2').hide();
		$('#content1').show();


	});*/

	//hiding second content

	$('#content2').hide();

	loadCompanies();


	//comapny create form to show
	$('#requestCreateBtn').on( 'click', function () {
		$('.inputForm').hide();
		$('#formModal-title').html("Create Request Details");
		$('.FormvalidationError').empty();
		$('#formeModal').modal('toggle');
		$('#createRequestForm').show();

	});

	$('#requestEditBtn').on( 'click', function () {
		$('.inputForm').hide();
		$('#formModal-title').html("Edit Request Details");
		$('.FormvalidationError').empty();
		// populating data in edit forms
		var rowSelected = requesttable.$('tr.active');
		var index = requesttable.row(rowSelected).index();
		if (index >= 0) {
			var rowData = requesttable.row(index).data();
			var requestName =  rowData[2];
			var requestAddress =  rowData[3];
			var requestCity =  rowData[4];

			$('#editFormCompNameId').val(requestName);
			$('#editFormCompAddressId').val(requestAddress);
			$('#editFormCompCityId').val(requestCity);

			$('#formeModal').modal('toggle');
			$('#editRequestForm').show();

		} else {
			alert("please select the row to update");
		}

	});

	


	/*$('#requestData tbody').on( 'click', 'td', function () {
	    var columnData = requesttable.column( $(this).index()+':visIdx' ).data();
	} );
*/



			// request datatable row select and de-select
    		$('#requestData tbody').on( 'click', 'tr', function (e) {
    			var isContinue = false;
    			var custClass = $(this).closest('tr').attr('class');
    			var tdClass = $(e.target).attr("class");

    			if (custClass !="custom-file-display-row" && custClass !="smalltable") {
    				isContinue = true;
    			}

    			if (isContinue){
    			if ($.trim(tdClass) != "details-control") {

    				if ( $(this).hasClass('active') ) {
    					$(this).removeClass('active');
    					// show create button and hide edit and delete button button
    					$('.myCustomButton').hide();
    					$('#requestCreateBtn').show();
    					// hide details details
    					$('#boxRequestDetailsData').hide();
    				}
    				else {
    					requesttable.$('tr.active').removeClass('active');
    					var selectedClass = $(this).find("td").attr("class");
    					if (selectedClass != "dataTables_empty") {
    						$(this).addClass('active');
    						// show edit and delete buttonds and hide create button
    						$('.myCustomButton').hide();
    						$('#requestEditBtn').show();
    						$('#requestDeleteBtn').show();
    						$('#requestUploadFileBtn').show();


    						//show details details
    						loadDetailsData();
    						$('#boxRequestDetailsData').show();
    					}

    				}
    			}
    		}

    	    } );


    		// adding request to db as well as to datatable on clicking of request form submit button
    		$('#createrequestsubmitBtn').on( 'click', function () {
    			//$('#requestCreateModal').modal('toggle');
    			$('.FormvalidationError').empty();
    			var requestName = $("#requestName").val();
    			var requestAddress = $("#requestAddress").val();
    			var requestLoc = $("#requestLocation").val();

    			var request = {
    				    'requestName': requestName,
    				    'address': requestAddress,
    				    'city': requestLoc
    				}

    			var request = $.ajax({
    				 url: contextPath+"/request/create",
    				  method: "POST",
    				  contentType: "application/json",
    			       dataType: "json",
    				  data: JSON.stringify(request)

    				}).done(function(res) {

                        if(res.status==="SUCCESS"){
                        	$('#formeModal').modal('toggle');
                        	addRequestData(res.object);
                        	//clearing form input  fields
                        	$('#createRequestForm').find("input,textarea,select").val('').end();

                        }else{
                        	 for(var key in res.errorsMap){
                             	$('.FormvalidationError').append("<span style=\"color:red; display:block;\">* "+res.errorsMap[key]+"</span>");
                             }
                        }
                    }).fail(function(data){
                    	 $('.FormvalidationError').append("span").css("color", "red").text("Server failed to process request");
                    });
    		});


    		// editing request to db as well as to datatable on clicking of request form submit button
    		$('#editrequestsubmitBtn').on( 'click', function () {
    			//$('#requestCreateModal').modal('toggle');
    			$('.FormvalidationError').empty();
    			var requestName = $("#editFormCompNameId").val();
    			var requestAddress = $("#editFormCompAddressId").val();
    			var requestLoc = $("#editFormCompCityId").val();
    			// get request id
    			var selected = requesttable.$('tr.active');
    			var rowIndex = requesttable.row(selected).index();
    			var data = requesttable.row(rowIndex).data();
    			var requestID = data[0];

    			var request = {
    					'requestID' : requestID,
    				    'requestName': requestName,
    				    'address': requestAddress,
    				    'city': requestLoc
    				}

    			var request = $.ajax({
    				 url: contextPath+"/request/update",
    				  method: "POST",
    				  contentType: "application/json",
    			      dataType: "json",
    				  data: JSON.stringify(request)

    				}).done(function(res) {

                        if(res.status==="SUCCESS"){
                        	$('#formeModal').modal('toggle');
                        	updateRequestData(res.object, rowIndex);

                        }else{
                        	 for(var key in res.errorsMap){
                             	$('.FormvalidationError').append("<span style=\"color:red; display:block;\">* "+res.errorsMap[key]+"</span>");
                             }
                        }
                    }).fail(function(data){
                    	 $('.FormvalidationError').append("span").css("color", "red").text("Server failed to process request");
                    });
    		});


    		$('#requestDeleteBtn').on( 'click', function () {
    			var selected = requesttable.$('tr.active');
    			var rowIndex = requesttable.row(selected).index();

    			if (rowIndex >= 0) {

    				var data = requesttable.row(rowIndex).data();
        			var requestID = data[0];

        			var request = $.ajax({
       				 url: contextPath+"/request/"+requestID,
       				 method: "DELETE",
       				 // contentType: "application/json",
       			     // dataType: "json",
       				 // data: JSON.stringify(request)

       				}).done(function(res) {

                           if(res.status==="SUCCESS"){
                           	deleteRequestData(rowIndex);
                            $('#boxRequestDetailsData').hide();

                           }else{
                        	   alert("failed");
                           	 for(var key in res.errorsMap){
                                }
                           }
                       }).fail(function(data){
                    	   alert("failed internal error");
                       	// $('.FormvalidationError').append("span").css("color", "red").text("Server failed to process request");
                       });

    			} else {
    				alert("Please select row to delete");
    			}

    		});


    		


    		// Add event listener for opening and closing details
    	    $('#requestData tbody').on('click', 'td.details-control', function () {
    	        var tr = $(this).closest('tr');
    	        var row = requesttable.row( tr );

    	        if ( row.child.isShown() ) {
    	            // This row is already open - close it
    	            row.child.hide();
    	            tr.removeClass('shown');
    	        }
    	        else {
    	        	// custom implementation
    	        	var rowData = row.data();
    	        	var requestID = rowData[0];

    	            // Open this row
    	        	var res = format(requestID, function(result) {
    	        		var result = addFiles(result.object);
    	        		 row.child( result ).show();
    	        		$(row.child()).addClass('smalltable');
    	        		 tr.addClass('shown');
    	        	});
    	        }
    	    } );


    	/*    $('#requestData > #customFileDisplayTable tbody').on('click', 'tr', function () {
    	    		alert("clicked");
    	    });
*/
    	    $('#requestData tbody ').on('click', 'tr.smalltable td > i.fa-trash', function () {
    	    	  var tr = $(this).closest('tr');
    	    	  var fileID = tr.attr("fileID");

      			var request = $.ajax({
     				 url: contextPath+"/file/"+fileID,
     				 method: "DELETE"

     				}).done(function(res) {
                         if(res.status==="SUCCESS"){
                        	 tr.remove();
                         }else{
                      	   alert("failed");
                         	 for(var key in res.errorsMap){
                              }
                         }
                     }).fail(function(data){
                  	   alert("failed internal error");
                     });

    	    });


    		// ending *onload*

  });

//Loading companies to datatable on page load
function loadCompanies(){

	 var request = $.ajax({
		 url: contextPath+"/request/list",
		  method: "GET",
		  dataType: 'json'

		}).done(function(res) {
            if(res.status==="SUCCESS"){
            	  $.each(res.object, function(idx, obj) {
            		  $('#requestData tbody').append(
            				  "<tr>" +
            				  "<td>" +obj.requestId+ "</td>" +
            				  "<td></td>" +
            				  "<td>" +obj.createTS+ "</td>" +
            				  "<td>" +obj.createdBy+ "</td>" +
            				  "<td>" +obj.companyId+ "</td>" +
            				  "<td>" +obj.createTS+ "</td>" +
            				  "<td>" +obj.serviceId+ "</td>" +
							  "<td>" +null+ "</td>" +
							  "<td>" +null+ "</td>" +
            				  "</tr>"
            				  )
    				});
            	  requesttable = $("#requestData").DataTable( {
            		  	autoWidth: false,
            	        "columnDefs": [
            	                       {
            	                           "targets": [ 0 ],
            	                           "visible": false,
            	                           "searchable": false
            	                       },

            	                       {
            	                    	   "targets": [ 1 ],
            	                    	   "className":      'details-control',
            	                           "orderable":      false,
            	                           "data":           null,
            	                           "defaultContent": ''
            	                       }
            	                   ]

            	               } );
            }else{
            	alert("request retrieve failed");
            }
        }).fail(function(data){
        	alert("request retrieve failed");
        });

}







//add single request row  to datatable
function addRequestData(request) {
	var rowNode  = requesttable.row.add( [
	            request.requestID,
	            null,
	            request.requestName,
	            request.address,
	            request.city,
	            request.createTS,
	            request.lastUpdateTS
	        ] ).draw( false );
}

//edit single request row  in datatable
function updateRequestData(request , index) {
	var rowNode  = requesttable.row(index).data( [
	            request.requestID,
	            null,
	            request.requestName,
	            request.address,
	            request.city,
	            request.createTS,
	            request.lastUpdateTS
	        ] ).draw( false );

	$('.myCustomButton').hide();
    $('#requestCreateBtn').show();
    $('#requestData tr').removeClass('active');
}

//delete single request row  in datatable
function deleteRequestData(index) {
	var rowNode  = requesttable.row(index).remove().draw(false);
	var selectedClass = $("#requestData > tbody > tr").find("td").attr("class");
//	if (selectedClass == "dataTables_empty") {
		$('.myCustomButton').hide();
        $('#requestCreateBtn').show();
//	}
}

function loadDetailsData(requestID) {
	// get request id
	var rowSelected = requesttable.$('tr.active');
	var index = requesttable.row(rowSelected).index();
	var data = requesttable.row(index).data();
	var requestID = data[0];

	 var request = $.ajax({
		 url: contextPath+"/details/list/"+requestID,
		  method: "GET",
		  dataType: 'json'

		}).done(function(res) {
            if(res.status==="SUCCESS"){
            	addRequestDetails(res.object);

            }else{
            	alert("details retrieve failed");
            }
        }).fail(function(data){
        	alert("details retrieve failed");
        });

}

/*function loadDetailsData(requestID){

	 var request = $.ajax({
		 url: contextPath+"/details/list/"+requestID,
		  method: "GET",
		  dataType: 'json'

		}).done(function(res) {
           if(res.status==="SUCCESS"){
           	  $.each(res.object, function(idx, obj) {
           		  $('#requestDetailsData tbody').append(
           				  "<tr>" +
           				  "<td>" +obj.detailsName+ "</td>" +
           				  "<td>" +null+ "</td>" +
           				  "<td>" +obj.detailsType+ "</td>" +
           				  "<td>" +obj.lastUpdateTS+ "</td>" +
           				  "<td>" +null+  "</td>" +
           				  "</tr>"
           				  )
   				});
           	  detailstable = $("#requestDetailsData").DataTable( {
           		  	autoWidth: false,
           	        "columnDefs": [
           	                       {
           	                           "targets": [ 0 ],
           	                           "visible": false,
           	                           "searchable": false
           	                       },

           	                       {
           	                    	   "targets": [ 1 ],
           	                    	   "className":      'details-control',
           	                           "orderable":      false,
           	                           "data":           null,
           	                           "defaultContent": ''
           	                       }
           	                   ]

           	               } );
           }else{
           	alert("request retrieve failed");
           }
       }).fail(function(data){
       	alert("request retrieve failed");
       });

}*/

function addRequestDetails(details) {
	var table = $('#requestDetailsData').DataTable();
	table.clear().draw();
	 $.each(details, function(idx, obj) {

			var rowNode  = table.row.add( [
			            obj.submitedDate,
			            obj.contactPersonName,
			            obj.contactPersonNumber,
			            obj.createdOn,
			            obj.createdBy,
			            obj.estimatedFdaApprovalDate,
			            obj.lastUpdatedOn
			        ] ).draw( false );

		});
}





/*function format ( requestID ) {

    // `d` is the original data object for the row
    return '<table class="table table-bordered display" id="customFileDisplayTable" cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
        '<tr class="custom-file-display-row">'+
            '<td>101</td>'+
            '<td><a target="_blank" href="/shop/file/download/inline/136"><i class="fa fa-file-pdf-o"></i> file name </a></td>'+
            '<td>documenttype</td>'+
            '<td>updated</td>'+
            '<td>size</td>'+
            '<td><a href="/shop/file/download/attachment/136"><i class="fa fa-download"></i></a></td>'+
            '<td><i class="fa fa-trash"></i></td>'+
        '</tr>'+

        '<tr class="custom-file-display-row">'+
        '<td>101</td>'+
        '<td><a href="#"><i class="fa fa-file-image-o"></i> file name dsaddsadaadasdasd</a></td>'+
        '<td>documenttype</td>'+
        '<td>updated</td>'+
        '<td>size</td>'+
        '<td><i class="fa fa-download"></i></td>'+
        '<td><i class="fa fa-trash"></i></td>'+
    '</tr>'+
    '</table>';

}
*/

function format ( requestID, callback ) {
	// get the uploaded files related request
	var request = $.ajax({
			 url: contextPath+"/file/list/company/"+requestID,
			 method: "GET"

			}).done(callback)/*(function(res) {
               if(res.status==="SUCCESS"){
            		alert("after ajax success")
            	  // var result = addFiles(res.object);
            	  // return result;
               }else{
            	   alert("failed to list the file contents");
               }

           })*/.fail(function(data){
        	   alert("failed to list the file contents");
           });
}

function addFiles ( files ) {

	var rowElement="";
	 $.each(files, function(idx, document) {
		 var temp  = '<tr class="custom-file-display-row" fileID='+document.documentID+'>'+
         '<td><a target="_blank" href="'+contextPath+'/file/download/inline/'+document.documentID+'"><i class="fa '+ getProperty(document.extensionType) +'"></i> '+document.documentName+'</a></td>'+
         '<td>'+document.documentTypeID+'</td>'+
         '<td>'+document.lastUpdateTS+'</td>'+
         '<td>'+document.fileSize+'</td>'+
         '<td><a href="'+contextPath+'/file/download/attachment/'+document.documentID+'"><i class="fa fa-download"></i></a></td>'+
         '<td><i class="fa fa-trash"></i></td>'+
     '</tr>'

         rowElement = rowElement.concat(temp);
	 });

	 return '<table class="table table-bordered display" id="customFileDisplayTable" cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
	 '<tr id="custom-file-display-heading" class="custom-file-display-row">'+
	 '<td>Name</td>'+
	 '<td>DocType</td>'+
	 '<td>Updated</td>'+
	 '<td>Size</td>'+
	 '<td></td>'+
	 '<td></td>'+
	 '</tr>'+
	 rowElement+'</table>';
}


var getProperty = function (propertyName) {
    var res =  obj[propertyName.toLowerCase()];
    if (res == undefined) {
    	res="fa-file-o";
    }
    return res;

   // console.log(getProperty("key1"));
	//console.log(getProperty("key2"));

};


$( window ).load(function() {
	$('#slidebarMenuRequest').addClass("active");
});



