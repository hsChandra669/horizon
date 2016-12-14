var companytable;
var rowNode;
$(function () {

	/*$('#slidebarMenuCompany').on( 'click', function () {
		$('#content2').hide();
		$('#content1').show();


	});*/

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

	$('#companyEditBtn').on( 'click', function () {
		$('.inputForm').hide();
		$('#formModal-title').html("Edit Company Details");
		$('.FormvalidationError').empty();
		// populating data in edit forms
		var rowSelected = companytable.$('tr.active');
		var index = companytable.row(rowSelected).index();
		if (index >= 0) {
			var rowData = companytable.row(index).data();
			var companyName =  rowData[2];
			var companyAddress =  rowData[3];
			var companyCity =  rowData[4];

			$('#editFormCompNameId').val(companyName);
			$('#editFormCompAddressId').val(companyAddress);
			$('#editFormCompCityId').val(companyCity);

			$('#formeModal').modal('toggle');
			$('#editCompanyForm').show();

		} else {
			alert("please select the row to update");
		}

	});

	$('#companyUploadFileBtn').on( 'click', function () {
		$('.inputForm').hide();
		$('#formModal-title').html("Uplaod Company Releated Files");
		$('.FormvalidationError').empty();
		var rowSelected = companytable.$('tr.active');
		var index = companytable.row(rowSelected).index();
		if (index >= 0) {
		//	var rowData = companytable.row(index).data();
			//var companyName =  rowData[1];
			//var companyAddress =  rowData[2];
			//var companyCity =  rowData[3];

			//$('#editFormCompNameId').val(companyName);
			//$('#editFormCompAddressId').val(companyAddress);
			//$('#editFormCompCityId').val(companyCity);

			$('#formeModal').modal('toggle');
			$('#uploadFileCompanyForm').show();
		}

	});



	/*$('#companyData tbody').on( 'click', 'td', function () {
	    var columnData = companytable.column( $(this).index()+':visIdx' ).data();
	} );
*/



			// company datatable row select and de-select
    		$('#companyData tbody').on( 'click', 'tr', function (e) {
    			var tdClass = $(e.target).attr("class");
    			if ($.trim(tdClass) != "details-control") {
    				alert(tdClass);

    				if ( $(this).hasClass('active') ) {
    					$(this).removeClass('active');
    					// show create button and hide edit and delete button button
    					$('.myCustomButton').hide();
    					$('#companyCreateBtn').show();
    					// hide product details
    					$('#boxCompanyProductData').hide();
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
    						loadProductData();
    						$('#boxCompanyProductData').show();
    					}

    				}
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


    		$('#uploadFileCompanyForm').fileupload({
    	       // dataType: 'json',
    			 replaceFileInput:false,
    			 autoUpload:false,
    			 url:contextPath+"/file/upload",
    			 type:'POST',

    	        add: function (e, data) {
    	        	//data.files = [];
    	        	 $.each(data.files, function (index, file) {
 	        	        console.log('sending file: ' + file.name + '   ' + jQuery.now());
 	        	    });

    	        	 $('#compProgBar').hide();
    	        	 $('#compProgBar').css('width', '0%' );
    	        	 $('#compProgBar').show();
    	        	 $('#compProgBarPercentage').text('0%');

    	        	// alert(data.files.length);
    	               	$('#companyFileUploadSubmitBtn').on( 'click', function (){
    	               		//alert("submit length - " + data.files.length);
    	                    data.submit();
    	                });
    	        },
    	        progress: function (e, data) {
    	            var progress = parseInt(data.loaded / data.total * 100, 10);
    	            $('#compProgBar').css('width',  progress + '%' );
    	            $('#compProgBarPercentage').text(progress + '%');
    	        },
    	        change: function (e, data) {
    	        	 $.each(data.files, function (index, file) {
    	        	   //     console.log('Selected file: ' + file.name);
    	        	    });
    	        },
    	        done: function (e, data) {
    	           //data.context.text('Upload finished.');
    	        	//resetting form;
    	        	//jQuery.removeData(data.files);
    	        	$( "#companyFileUploadSubmitBtn").unbind( "click" );
    	        	$('#uploadFileCompanyForm')[0].reset();
    	        	$('#companyInputFile1').val('');

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
            				  "<td>" +null+ "</td>" +
            				  "<td>" +obj.companyName+ "</td>" +
            				  "<td>" +obj.address+ "</td>" +
            				  "<td>" +obj.city+ "</td>" +
            				  "<td>" +obj.createTS+ "</td>" +
            				  "<td>" +obj.lastUpdateTS+ "</td>" +
            				  "</tr>"
            				  )
    				});
            	  companytable = $("#companyData").DataTable( {
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
            	alert("company retrieve failed");
            }
        }).fail(function(data){
        	alert("company retrieve failed");
        });


	// Add event listener for opening and closing details
	    $('#companyData tbody').on('click', 'td.details-control', function () {
	        var tr = $(this).closest('tr');
	        var row = companytable.row( tr );

	        if ( row.child.isShown() ) {
	            // This row is already open - close it
	            row.child.hide();
	            tr.removeClass('shown');
	        }
	        else {
	            // Open this row
	            row.child( format("test") ).show();
	            tr.addClass('shown');
	        }
	    } );

}

//add single company row  to datatable
function addCompanyData(company) {
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
function updateCompanyData(company , index) {
	var rowNode  = companytable.row(index).data( [
	            company.companyID,
	            null,
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
	var selectedClass = $("#companyData > tbody > tr").find("td").attr("class");
	if (selectedClass == "dataTables_empty") {
		$('.myCustomButton').hide();
        $('#companyCreateBtn').show();
	}
}

function loadProductData(companyID) {
	// get company id
	var rowSelected = companytable.$('tr.active');
	var index = companytable.row(rowSelected).index();
	var data = companytable.row(index).data();
	var companyID = data[0];

	 var request = $.ajax({
		 url: contextPath+"/products/list/"+companyID,
		  method: "GET",
		  dataType: 'json'

		}).done(function(res) {
            if(res.status==="SUCCESS"){
            	addCompanyProducts(res.object);

            }else{
            	alert("product retrieve failed");
            }
        }).fail(function(data){
        	alert("product retrieve failed");
        });

}



function addCompanyProducts(products) {
	var table = $('#companyProductData').DataTable();
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

/* Formatting function for row details - modify as you need */
function format ( d ) {
    // `d` is the original data object for the row
    return '<table class="table table-bordered display" cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
        '<tr>'+
            '<td>Full name:</td>'+
            '<td>sanjeev</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Extension number:</td>'+
            '<td>1234</td>'+
        '</tr>'+
        '<tr>'+
            '<td>Extra info:</td>'+
            '<td>And any further details here (images etc)...</td>'+
        '</tr>'+
    '</table>';
}

$( window ).load(function() {
	$('#slidebarMenuCompany').addClass("active");
});



