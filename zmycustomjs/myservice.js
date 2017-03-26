var servicetable;
var rowNode;
$(function () {

	loadServices();

	//comapny create form to show
	$('#serviceCreateBtn').on( 'click', function () {
		$('.inputForm').hide();
		$('#formModal-title').html("Create Service Details");
		$('.FormvalidationError').empty();
		$('#formeModal').modal('toggle');
		$('#createServiceForm').show();

	});


	$('#serviceEditBtn').on( 'click', function () {
		$('.inputForm').hide();
		$('#formModal-title').html("Edit Serice Details");
		$('.FormvalidationError').empty();
		// populating data in edit forms
		var rowSelected = servicetable.$('tr.active');
		var index = servicetable.row(rowSelected).index();
		if (index >= 0) {
			var rowData = servicetable.row(index).data();
			var serviceName =  rowData[1];
			var serviceType =  rowData[2];

			$('#editFormServiceNameId').val(serviceName);
			$('#editFormServicetypeId').val(serviceType);

			$('#formeModal').modal('toggle');
			$('#editServiceForm').show();

		} else {
			alert("please select the row to update");
		}

	});



	// service datatable row select and de-select
	$('#serviceData tbody').on( 'click', 'tr', function (e) {
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
				$('#serviceCreateBtn').show();
				// hide product details
				//$('#boxCompanyProductData').hide();
			}
			else {
				servicetable.$('tr.active').removeClass('active');
				var selectedClass = $(this).find("td").attr("class");
				if (selectedClass != "dataTables_empty") {
					$(this).addClass('active');
					// show edit and delete buttonds and hide create button
					$('.myCustomButton').hide();
					$('#serviceEditBtn').show();
					$('#serviceDeleteBtn').show();
					//$('#companyUploadFileBtn').show();


					//show product details
					//loadProductData();
					//$('#boxCompanyProductData').show();
				}

			}
		}
	}

    });


	// adding services to db as well as to datatable on clicking of service form submit button
	$('#createServiceSubmitBtn').on( 'click', function () {
		//$('#companyCreateModal').modal('toggle');
		$('.FormvalidationError').empty();
		var serviceName = $("#serviceName").val();
		var serviceType = $("#servicetype").val();

		var services = {
			    'serviceName': serviceName,
			    'serviceType': serviceType
			}

		var request = $.ajax({
			 url: contextPath+"/services/create",
			  method: "POST",
			  contentType: "application/json",
		       dataType: "json",
			  data: JSON.stringify(services)

			}).done(function(res) {

                if(res.status==="SUCCESS"){
                	$('#formeModal').modal('toggle');
                	addServiceData(res.object);
                	//clearing form input  fields
                	$('#createServiceForm').find("input,textarea,select").val('').end();

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
	$('#editServiceSubmitBtn').on( 'click', function () {
		//$('#companyCreateModal').modal('toggle');
		$('.FormvalidationError').empty();
		var serviceName = $("#editFormServiceNameId").val();
		var serviceType = $("#editFormServicetypeId").val();
		// get company id
		var selected = servicetable.$('tr.active');
		var rowIndex = servicetable.row(selected).index();
		var data = servicetable.row(rowIndex).data();
		var serviceID = data[0];

		var service = {
				'serviceID' : serviceID,
			    'serviceName': serviceName,
			    'serviceType': serviceType
			}

		var request = $.ajax({
			 url: contextPath+"/services/update",
			  method: "POST",
			  contentType: "application/json",
		      dataType: "json",
			  data: JSON.stringify(service)

			}).done(function(res) {

                if(res.status==="SUCCESS"){
                	$('#formeModal').modal('toggle');
                	updateServiceData(res.object, rowIndex);

                }else{
                	 for(var key in res.errorsMap){
                     	$('.FormvalidationError').append("<span style=\"color:red; display:block;\">* "+res.errorsMap[key]+"</span>");
                     }
                }
            }).fail(function(data){
            	 $('.FormvalidationError').append("span").css("color", "red").text("Server failed to process request");
            });
	});


	$('#serviceDeleteBtn').on( 'click', function () {
		var selected = servicetable.$('tr.active');
		var rowIndex = servicetable.row(selected).index();

		if (rowIndex >= 0) {

			var data = servicetable.row(rowIndex).data();
			var serviceID = data[0];

			var request = $.ajax({
				 url: contextPath+"/services/"+serviceID,
				 method: "DELETE",
				 // contentType: "application/json",
			     // dataType: "json",
				 // data: JSON.stringify(company)

				}).done(function(res) {

                   if(res.status==="SUCCESS"){
                	   deleteServiceData(rowIndex);
                   // $('#boxCompanyProductData').hide();

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





});


//Loading loadServices to datatable on page load
function loadServices(){

	 var request = $.ajax({
		 url: contextPath+"/services/list",
		  method: "GET",
		  dataType: 'json'

		}).done(function(res) {
            if(res.status==="SUCCESS"){
            	  $.each(res.object, function(idx, obj) {
            		  $('#serviceData tbody').append(
            				  "<tr>" +
            				  "<td>" +obj.serviceID+ "</td>" +
            				  "<td>" +obj.serviceName+ "</td>" +
            				  "<td>" +obj.serviceType+ "</td>" +
            				  "<td>" +obj.createTS+ "</td>" +
            				  "<td>" +obj.lastUpdateTS+ "</td>" +
            				  "</tr>"
            				  )
    				});
            	  servicetable = $("#serviceData").DataTable( {
            		  	autoWidth: false,
            	        "columnDefs": [
            	                       {
            	                           "targets": [ 0 ],
            	                           "visible": false,
            	                           "searchable": false
            	                       }
            	                   ]

            	               } );
            }else{
            	alert("Service retrieve failed");
            }
        }).fail(function(data){
        	alert("Service retrieve failed");
        });

}

//add single service row  to datatable
function addServiceData(service) {
	var rowNode  = servicetable.row.add( [
	            service.serviceID,
	            service.serviceName,
	            service.serviceType,
	            service.createTS,
	            service.lastUpdateTS
	        ] ).draw( false );
}

//edit single service row  in datatable
function updateServiceData(service , index) {
	var rowNode  = servicetable.row(index).data( [
	            service.serviceID,
	            service.serviceName,
	            service.serviceType,
	            service.createTS,
	            service.lastUpdateTS
	        ] ).draw( false );

	$('.myCustomButton').hide();
    $('#serviceCreateBtn').show();
    $('#serviceData tr').removeClass('active');
}

//delete single company row  in datatable
function deleteServiceData(index) {
	var rowNode  = servicetable.row(index).remove().draw(false);
	var selectedClass = $("#serviceData > tbody > tr").find("td").attr("class");
//	if (selectedClass == "dataTables_empty") {
		$('.myCustomButton').hide();
        $('#serviceCreateBtn').show();
//	}
}


$( window ).load(function() {
	$('#').addClass("active");
});
