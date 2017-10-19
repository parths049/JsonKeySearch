$(document).ready(function() {

	$("#btn-search").click(function(event) {
		event.preventDefault();
		$("#translation").html("");
		callAjaxSubmit();
	});

	$("#btn-reverse-search").click(function(event) {
		event.preventDefault();
		$("#translation").html("");
		callAjaxReverseSubmit();
	});

});

function callAjaxSubmit() {
	var search = {}
	search["label"] = $("#label").val();

	$("#btn-search").prop("disabled", true);
	$("#btn-reverse-search").prop("disabled", true);

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/api/search",
		data : JSON.stringify(search),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {

			var json = "<h4 align=\"center\"><pre>"
					+ JSON.stringify(data.translation) + "</pre></h4>";
			$('#translation').html(json);

			console.log("SUCCESS : ", data);

			$("#btn-search").prop("disabled", false);
			$("#btn-reverse-search").prop("disabled", false);

		},
		error : function(e) {
			console.log("ERROR : ", e);
			alert('key is invalid');
			$("#btn-search").prop("disabled", false);
			$("#btn-reverse-search").prop("disabled", false);
		}
	});

}

function callAjaxReverseSubmit() {
	var search = {}
	search["label"] = $("#label").val();

	$("#btn-search").prop("disabled", true);
	$("#btn-reverse-search").prop("disabled", true);

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/api/searchReverse",
		data : JSON.stringify(search),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {

			var json = "<h4 align=\"center\"><pre>"
					+ JSON.stringify(data.translation) + "</pre></h4>";
			$('#translation').html(json);

			console.log("SUCCESS : ", data);

			$("#btn-search").prop("disabled", false);
			$("#btn-reverse-search").prop("disabled", false);

		},
		error : function(e) {
			console.log("ERROR : ", e);
			alert('key is invalid');
			$("#btn-search").prop("disabled", false);
			$("#btn-reverse-search").prop("disabled", false);
		}
	});
}