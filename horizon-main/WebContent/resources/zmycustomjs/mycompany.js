var companytable;
var rowNode;
$(function () {

	$('#slidebarMenuCompany').on( 'click', function () {
		$('#content2').hide();
		$('#content1').show();


	});

	//hiding second content

	$('#content2').hide();

	loadCompanies();


	//comapny create form to show
	$('#companyCreateBtn').on( 'click', function () {
		$('.inputForm').hide();
		$('#formModal-title').html("Create Company Details");
		$('.FormvalidationError').empty();
		$('#formeModal').modal('toggle');
		$('#createCompanyForm').show();

	});

	// comapny edit form to show with pre populated row selected data
	$('#companyEditBtn').on( 'click', function () {
		$('.inputForm').hide();
		$('#formModal-title').html("Edit Company Details");
		$('.FormvalidationError').empty();
		// populating data in edit forms
		var rowSelected = companytable.$('tr.active');
		if (rowSelected.index() >= 0) {
			var rowData = companytable.row(rowSelected.index()).data();
			var companyName =  rowData[1];
			var companyAddress =  rowData[2];
			var companyCity =  rowData[3];

			$('#editFormCompNameId').val(companyName);
			$('#editFormCompAddressId').val(companyAddress);
			$('#editFormCompCityId').val(companyCity);

			$('#formeModal').modal('toggle');
			$('#editCompanyForm').show();

		} else {
			alert("please select the row to update");
		}

	});


	/*$('#companyData tbody').on( 'click', 'td', function () {
	    var columnData = companytable.column( $(this).index()+':visIdx' ).data();
	} );
*/



			// company datatable row select and de-select
    		$('#companyData tbody').on( 'click', 'tr', function () {
    	        if ( $(this).hasClass('active') ) {
    	            $(this).removeClass('active');
    	        }
    	        else {
    	        	companytable.$('tr.active').removeClass('active');
    	            $(this).addClass('active');
    	        }
    	    } );


    		// adding company to db as well as to datatable on clicking of company form submit button
    		$('#createcompanysubmitBtn').on( 'click', function () {
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
    				 url: contextPath+"/company/create",
    				  method: "POST",
    				  contentType: "application/json",
    			       dataType: "json",
    				  data: JSON.stringify(company)

    				}).done(function(res) {

                        if(res.status==="SUCCESS"){
                        	$('#formeModal').modal('toggle');
                        	addCompanyData(res.object);
                        	//clearing form input  fields
                        	$('#createCompanyForm').find("input,textarea,select").val('').end();

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
    		$('#editcompanysubmitBtn').on( 'click', function () {
    			//$('#companyCreateModal').modal('toggle');
    			$('.FormvalidationError').empty();
    			var companyName = $("#editFormCompNameId").val();
    			var companyAddress = $("#editFormCompAddressId").val();
    			var companyLoc = $("#editFormCompCityId").val();
    			// get company id
    			var selected = companytable.$('tr.active');
    			var rowIndex = selected.index();
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
    			var rowIndex = selected.index();

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

//Loading companies to datatable on page load
function loadCompanies(){

	 var request = $.ajax({
		 url: contextPath+"/company/list",
		  method: "GET",
		  dataType: 'json'

		}).done(function(res) {
            if(res.status==="SUCCESS"){
            	  $.each(res.object, function(idx, obj) {
            		  $('#companyData tbody').append(
            				  "<tr>" +
            				  "<td>" +obj.companyID+ "</td>" +
            				  "<td>" +obj.companyName+ "</td>" +
            				  "<td>" +obj.address+ "</td>" +
            				  "<td>" +obj.city+ "</td>" +
            				  "<td>" +obj.createTS+ "</td>" +
            				  "<td>" +obj.lastUpdateTS+ "</td>" +
            				  "</tr>"
            				  )
    				});
            	  companytable = $("#companyData").DataTable( {
            	        "columnDefs": [
            	                       {
            	                           "targets": [ 0 ],
            	                           "visible": false,
            	                           "searchable": false
            	                       }
            	                   ]
            	               } );
            }else{
            	alert("company retrieve failed");
            }
        }).fail(function(data){
        	alert("company retrieve failed");
        });
}

//add single company row  to datatable
function addCompanyData(company) {
	var rowNode  = companytable.row.add( [
	            company.companyID,
	            company.companyName,
	            company.address,
	            company.city,
	            company.createTS,
	            company.lastUpdateTS
	        ] ).draw( false );
}

//edit single company row  in datatable
function updateCompanyData(company , index) {
	var rowNode  = companytable.row(index).data( [
	            company.companyID,
	            company.companyName,
	            company.address,
	            company.city,
	            company.createTS,
	            company.lastUpdateTS
	        ] ).draw( false );
}

//delete single company row  in datatable
function deleteCompanyData(index) {
	var rowNode  = companytable.row(index).remove().draw(false);
}




