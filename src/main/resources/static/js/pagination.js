$(document).ready(function() {
	var table = $('#usersTable').DataTable( {
		lengthMenu : [[2,3,4,-1],['2', '3' , '4' , 'All']],
		"pageLength": 2,
		"ajax": {
			url: "http://localhost:8080/api/users",
			dataSrc:""
		},
        "columns": [
			{ "data": "id" },
            { "data": "firstName" },
            { "data": "lastName" },
            { "data": "email" },
            {data: null, render: function(data, type, row) {
                    return '<button type="button" class="btn fa fa-trash-can icon-dark btn-danger delete-button" data-id="' + row.id + '">Delete</button>';
            }}
		],
        "paging": true,  
        dom: 'Bfrtip',
    	buttons: [
			// 'pdf', 'excel'
			{
      		extend: 'pdf',
      		title: 'Employee Details PDF File',
      		filename: 'Data'
    	},
    	{
      		extend: 'excel',
      		title: 'Employee Details Excel Sheet',
      		filename: 'Data'
    	}]    
	} );
	
	//Get data from user pot it into database and load the datatable
	$('#formUser').submit(function(e){
		e.preventDefault();
		$.ajax({
			type: 'POST',
			url: 'http://localhost:8080/api/save',
			data: JSON.stringify({
				firstName: $('input[name=firstName]').val(),
                lastName: $('input[name=lastName]').val(),
                email: $('input[name=email]').val(),
                password: $('input[name=password]').val()
			}),
			contentType: 'application/json',
            success: function(data) {
            	$('#usersTable').DataTable().ajax.reload();
                $('#formUser')[0].reset();
            }
		});
	});
	
	//Delete Data from database and reload the table
	$('#usersTable tbody').on('click', 'tr', function() {
    	var data = table.row(this).data();
        $.ajax({
        	url: "http://localhost:8080/api/delete/" + data.id,
        	type: 'DELETE',
        	success: function() {
        		$('#usersTable').DataTable().ajax.reload();
        	}
		});   
	});
	
	// Export the data
	$('#export-btn').click(function() {
	    $.ajax({
	        url: 'http://localhost:8080/api/export',
	        method: 'GET',
	        success: function(response) {
	            // Use the FileSaver.js library to download the file
	            /*var blob = new Blob([response], { type: 'text/plain;charset=utf-8' });
	            saveAs(blob, 'data.txt');*/
	            var binaryData = [];
					binaryData.push(response);
	                var downloadLink = document.createElement('a');
	                downloadLink.href = URL.createObjectURL(new Blob(binaryData));
	                downloadLink.download = 'datatable.txt';
	                downloadLink.click();
	        },
	        error: function(error) {
	            console.log(error);
	        }
	    });
	});
	
	
});