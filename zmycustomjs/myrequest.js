var requesttable;
var rowNode;

$(function () {
	$('#content2').hide();
	loadRequests();


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
			var companyName =  rowData[2];
			var companyAddress =  rowData[3];
			var companyCity =  rowData[4];

			$('#editFormCompNameId').val(companyName);
			$('#editFormCompAddressId').val(companyAddress);
			$('#editFormCompCityId').val(companyCity);

			$('#formeModal').modal('toggle');
			$('#editRequestForm').show();

		} else {
			alert("please select the row to update");
		}

	});



	/*$('#companyData tbody').on( 'click', 'td', function () {
	    var columnData = companytable.column( $(this).index()+':visIdx' ).data();
	} );
*/



			// company datatable row select and de-select
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
    					// hide product details
    					//$('#boxCompanyProductData').hide();
    				}
    				else {
    					requesttable.$('tr.active').removeClass('active');
    					var selectedClass = $(this).find("td").attr("class");
    					if (selectedClass != "dataTables_empty") {
    						$(this).addClass('active');
    						// show edit and delete buttonds and hide create button
    						$('.myCustomButton').hide();
    						$('#companyEditBtn').show();
    						$('#companyDeleteBtn').show();


    						//show product details
    						//loadProductData();
    						//$('#boxCompanyProductData').show();
    					}

    				}
    			}
    		}

    	    } );

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
    					// hide product details
    					$('#boxRequestDetailsData').hide();
    				}
    				else {
    					companytable.$('tr.active').removeClass('active');
    					var selectedClass = $(this).find("td").attr("class");
    					if (selectedClass != "dataTables_empty") {
    						$(this).addClass('active');
    						// show edit and delete buttonds and hide create button
    						$('.myCustomButton').hide();
    						$('#companyEditBtn').show();
    						$('#companyDeleteBtn').show();
    						$('#companyUploadFileBtn').show();


    						//show product details
    						loadRequestDetailsData();
    						$('#boxRequestDetailsData').show();
    					}

    				}
    			}
    		}

    	    } );


    		// adding company to db as well as to datatable on clicking of company form submit button
    		$('#createrequestsubmitBtn').on( 'click', function () {
    			//$('#companyCreateModal').modal('toggle');
    			$('.FormvalidationError').empty();
    			var companyName = $("#companyName").val();
    			var companyAddress = $("#companyAddress").val();
    			var companyLoc = $("#companyLocation").val();

    			var company = {
    				    'companyName': companyName,
    				    'address': companyAddress,
    				    'city': companyLoc
    				}

    			var request = $.ajax({
    				 url: contextPath+"/request/create",
    				  method: "POST",
    				  contentType: "application/json",
    			       dataType: "json",
    				  data: JSON.stringify(company)

    				}).done(function(res) {

                        if(res.status==="SUCCESS"){
                        	$('#formeModal').modal('toggle');
                        	addCompanyData(res.object);
                        	//clearing form input  fields
                        	$('#createrequestForm').find("input,textarea,select").val('').end();

                        }else{
                        	 for(var key in res.errorsMap){
                             	$('.FormvalidationError').append("<span style=\"color:red; display:block;\">* "+res.errorsMap[key]+"</span>");
                             }
                        }
                    }).fail(function(data){
                    	 $('.FormvalidationError').append("span").css("color", "red").text("Server failed to process request");
                    });
    		});


    		// editing company to db as well as to datatable on clicking of company form submit button
    		$('#editrequestsubmitBtn').on( 'click', function () {
    			//$('#companyCreateModal').modal('toggle');
    			$('.FormvalidationError').empty();
    			var companyName = $("#editFormCompNameId").val();
    			var companyAddress = $("#editFormCompAddressId").val();
    			var companyLoc = $("#editFormCompCityId").val();
    			// get company id
    			var selected = companytable.$('tr.active');
    			var rowIndex = companytable.row(selected).index();
    			var data = companytable.row(rowIndex).data();
    			var companyID = data[0];

    			var company = {
    					'companyID' : companyID,
    				    'companyName': companyName,
    				    'address': companyAddress,
    				    'city': companyLoc
    				}

    			var request = $.ajax({
    				 url: contextPath+"/company/update",
    				  method: "POST",
    				  contentType: "application/json",
    			      dataType: "json",
    				  data: JSON.stringify(company)

    				}).done(function(res) {

                        if(res.status==="SUCCESS"){
                        	$('#formeModal').modal('toggle');
                        	updateCompanyData(res.object, rowIndex);

                        }else{
                        	 for(var key in res.errorsMap){
                             	$('.FormvalidationError').append("<span style=\"color:red; display:block;\">* "+res.errorsMap[key]+"</span>");
                             }
                        }
                    }).fail(function(data){
                    	 $('.FormvalidationError').append("span").css("color", "red").text("Server failed to process request");
                    });
    		});


    		$('#companyDeleteBtn').on( 'click', function () {
    			var selected = companytable.$('tr.active');
    			var rowIndex = companytable.row(selected).index();

    			if (rowIndex >= 0) {

    				var data = companytable.row(rowIndex).data();
        			var companyID = data[0];

        			var request = $.ajax({
       				 url: contextPath+"/company/"+companyID,
       				 method: "DELETE",
       				 // contentType: "application/json",
       			     // dataType: "json",
       				 // data: JSON.stringify(company)

       				}).done(function(res) {

                           if(res.status==="SUCCESS"){
                           	deleteCompanyData(rowIndex);
                            $('#boxCompanyProductData').hide();

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
    	        var row = companytable.row( tr );

    	        if ( row.child.isShown() ) {
    	            // This row is already open - close it
    	            row.child.hide();
    	            tr.removeClass('shown');
    	        }
    	        else {
    	        	// custom implementation
    	        	var rowData = row.data();
    	        	var companyID = rowData[0];

    	            // Open this row
    	        	var res = format(companyID, function(result) {
    	        		var result = addFiles(result.object);
    	        		 row.child( result ).show();
    	        		$(row.child()).addClass('smalltable');
    	        		 tr.addClass('shown');
    	        	});
    	        }
    	    } );


    	/*    $('#companyData > #customFileDisplayTable tbody').on('click', 'tr', function () {
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
function loadRequests(){

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
            				  "<td>" +obj.createTS+ "</td>" +
            				  "<td>" +"Chandra"+ "</td>" +
            				  "<td>" +"97427"+ "</td>" +
            				  "<td>" +obj.createTS+ "</td>" +
            				  "<td>" +obj.createdBy+ "</td>" +
            				  "<td>" +"Estimated PDA Appear"+ "</td>" +
            				  "<td>" +obj.companyId+ "</td>" +
            				  "<td>" +obj.serviceId+ "</td>" +
            				  "</tr>"
            				  )
    				});
            	  companytable = $("#requestData").DataTable( {
            		  	autoWidth: false,
            		  	"columnDefs": [
            		  		{
	                           "targets": [ 0 ],
	                           "visible": true,
	                           "searchable": false,
	                           "orderable":      false,
	                           "data":           null,
	                           "defaultContent": ''
	                       }
 	                   ]

            	               } );
            }else{
            	alert("Requests retrieve failed");
            }
        }).fail(function(data){
        	alert("Requests retrieve failed");
        });

}


function loadRequestDetailData(requestId) {
	// get company id
	var rowSelected = companytable.$('tr.active');
	var index = companytable.row(rowSelected).index();
	var data = companytable.row(index).data();
	var companyID = data[0];

	 var request = $.ajax({
		 url: contextPath+"/requestDetails/list/"+companyID,
		  method: "GET",
		  dataType: 'json'

		}).done(function(res) {
            if(res.status==="SUCCESS"){
            	addRequestDetails(res.object);

            }else{
            	alert("product retrieve failed");
            }
        }).fail(function(data){
        	alert("product retrieve failed");
        });

}

function addRequestDetails(details) {
	var table = $('#requestDetailData').DataTable();
	table.clear().draw();
	 $.each(products, function(idx, obj) {

			var rowNode  = table.row.add( [
			            obj.productName,
			            obj.productType,
			            obj.createTS,
			            obj.lastUpdateTS
			        ] ).draw( false );

		});
}


//add single company row  to datatable
function addRequestData(company) {
	var rowNode  = companytable.row.add( [
	            company.companyID,
	            null,
	            company.companyName,
	            company.address,
	            company.city,
	            company.createTS,
	            company.lastUpdateTS
	        ] ).draw( false );
}

//edit single company row  in datatable
function updateRequestData(company , index) {
	var rowNode  = requestData.row(index).data( [
	            company.companyID,
	            null,
	            company.companyName,
	            company.address,
	            company.city,
	            company.createTS,
	            company.lastUpdateTS
	        ] ).draw( false );

	$('.myCustomButton').hide();
    $('#requestCreateBtn').show();
    $('#requestData tr').removeClass('active');
}

//delete single company row  in datatable
function deleteRequestData(index) {
	var rowNode  = requestData.row(index).remove().draw(false);
	var selectedClass = $("#requestData > tbody > tr").find("td").attr("class");
//	if (selectedClass == "dataTables_empty") {
		$('.myCustomButton').hide();
        $('#requestCreateBtn').show();
//	}
}



$( window ).load(function() {
	$('#').addClass("active");
});



