var productTable;
$(function () {
	// fw initializations
	//Initialize Select2 Elements
    $("#createProductCompanyListID").select2({
    	  placeholder: "Select a Company"
    	//  allowClear: true
    	});

	productTable = $("#productData").DataTable({
		  	autoWidth: false,
	        "columnDefs": [
	                       {
	                           "targets": [ 0 ],
	                           "visible": false,
	                           "searchable": false
	                       }
	                   ]
	               } );

//load products
	loadProducts();

	// load company list for adding products
	loadCompaniesforCreateProductForm();


	// company datatable row select and de-select
	$('#productData tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('active') ) {
            $(this).removeClass('active');
        }
        else {
        	productTable.$('tr.active').removeClass('active');
            $(this).addClass('active');

        }
    } );


//loading companies for create product form

	//comapny create form to show
	$('#productCreateBtn').on( 'click', function () {
		$('.inputForm').hide();
		$('#productFormModal-title').html("Create Product Details");
		$('.FormvalidationError').empty();
		$('#productFormeModal').modal('toggle');
		$('#createProductForm').show();

	});


	$('#productEditBtn').on( 'click', function () {
		$('.inputForm').hide();
		$('#productFormModal-title').html("Edit Product Details");
		$('.FormvalidationError').empty();
		// populating data in edit forms
		var rowSelected = productTable.$('tr.active');
		var index = productTable.row(rowSelected).index();
		if (index >= 0) {
			var rowData = productTable.row(index).data();
			var productName =  rowData[1];
			var productType =  rowData[2];
			var companyName =  rowData[3];

			$('#editProductNameID').val(productName);
			$('#editProductTypeID').val(productType);
			$('#editProductCompanyListID').empty();
			$('#editProductCompanyListID').append(  "<option>" + companyName + "</option>" );

			$('#productFormeModal').modal('toggle');
			$('#editProductForm').show();

		} else {
			alert("please select the row to update");
		}

	});




	// adding company to db as well as to datatable on clicking of company form submit button
	$('#createProductSubmitBtn').on( 'click', function () {
		//$('#companyCreateModal').modal('toggle');
		$('.FormvalidationError').empty();
		var companyName = $("#select2-createProductCompanyListID-container").text();
		var productName = $("#createProductNameID").val();
		var productType = $("#createProductTypeID").val();

		var product = {
			    'companyName': companyName,
			    'productName': productName,
			    'productType': productType
			}

		var request = $.ajax({
			 url: contextPath+"/products/create",
			  method: "POST",
			  contentType: "application/json",
		       dataType: "json",
			  data: JSON.stringify(product)

			}).done(function(res) {

                if(res.status==="SUCCESS"){
                	$('#productFormeModal').modal('toggle');
                	addProductData(res.object);
                	//clearing form input  fields
                	$('#createProductForm').find("input,textarea,select").val('').end();

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
	$('#editProductSubmitBtn').on( 'click', function () {
		//$('#companyCreateModal').modal('toggle');
		$('.FormvalidationError').empty();
		var productName = $("#editProductNameID").val();
		var productType = $("#editProductTypeID").val();
		var companyName = $("#editProductCompanyListID option").val();
		// get company id
		var selected = productTable.$('tr.active');
		var rowIndex = productTable.row(selected).index();
		var data = productTable.row(rowIndex).data();
		var productID = data[0];

		var product = {
				'companyName': companyName,
				'productName': productName,
				'productType': productType,
				'productID' : productID
			}
		var request = $.ajax({
			 url: contextPath+"/product/update",
			  method: "POST",
			  contentType: "application/json",
		      dataType: "json",
			  data: JSON.stringify(product)

			}).done(function(res) {

                if(res.status==="SUCCESS"){
                	$('#productFormeModal').modal('toggle');
                	updateProductData(res.object, rowIndex);

                }else{
                	 for(var key in res.errorsMap){
                     	$('.FormvalidationError').append("<span style=\"color:red; display:block;\">* "+res.errorsMap[key]+"</span>");
                     }
                }
            }).fail(function(data){
            	 $('.FormvalidationError').append("span").css("color", "red").text("Server failed to process request");
            });
	});



	$('#productDeleteBtn').on( 'click', function () {
		var selected = productTable.$('tr.active');
		var rowIndex = productTable.row(selected).index();

		if (rowIndex >= 0) {

			var data = productTable.row(rowIndex).data();
			var productID = data[0];

			var request = $.ajax({
				 url: contextPath+"/product/"+productID,
				 method: "DELETE",
				 // contentType: "application/json",
			     // dataType: "json",
				 // data: JSON.stringify(company)

				}).done(function(res) {

                   if(res.status==="SUCCESS"){
                	   deleteProductData(rowIndex);

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
function loadProducts(){

	 var request = $.ajax({
		 url: contextPath+"/products/list",
		  method: "GET",
		  dataType: 'json'

		}).done(function(res) {
            if(res.status==="SUCCESS"){
            	addProducts(res.object);
            }else{
            	//alert("company retrieve failed");
            }
        }).fail(function(data){
        	//alert("company retrieve failed");
        });
}

function addProducts(products) {
	 $.each(products, function(idx, product) {
			var rowNode  = productTable.row.add([
			            product.productID,
			            product.productName,
			            product.productType,
			            product.companyName,
			            product.createTS,
			            product.lastUpdateTS
			        ] ).draw( false );
		});

}

//add single company row  to datatable
function addProductData(product) {
	var rowNode  = productTable.row.add( [
	                product.productID,
	              	product.productName,
	              	product.productType,
	              	product.companyName,
	              	product.createTS,
	              	product.lastUpdateTS
	        ] ).draw( false );
}

//edit single company row  in datatable
function updateProductData(product , index) {
	var rowNode  = productTable.row(index).data( [
	               product.productID,
	               product.productName,
	               product.productType,
	               product.companyName,
	               product.createTS,
	               product.lastUpdateTS
	        ] ).draw( false );
}

//delete single company row  in datatable
function deleteProductData(index) {
	var rowNode  = productTable.row(index).remove().draw(false);
}

function loadCompaniesforCreateProductForm() {

	 var request = $.ajax({
		 url: contextPath+"/company/name/list",
		  method: "GET",
		  dataType: 'json'

		}).done(function(res) {
           if(res.status==="SUCCESS"){
        	   $.each(res.object, function(idx, obj) {
        		   $('#createProductCompanyListID').append(
         				  "<option>" +obj+ "</option>"
         				  )
        	   });
           }else{
           	//alert("company retrieve failed");
           }
       }).fail(function(data){
       	//alert("company retrieve failed");
       });

}



$( window ).load(function() {
	$('#slidebarMenuProduct').addClass("active");
});