var productTable;
$(function () {
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


	loadProducts();

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
            	alert("company retrieve failed");
            }
        }).fail(function(data){
        	alert("company retrieve failed");
        });
}

function addProducts(products) {
	 $.each(products, function(idx, product) {

			var rowNode  = productTable.row.add([
			            product.productID,
			            product.productName,
			            product.productType,
			            product.createTS,
			            product.lastUpdateTS
			        ] ).draw( false );
		});

}


$( window ).load(function() {
	$('#slidebarMenuProduct').addClass("active");
});