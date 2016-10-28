$(document).ready(function(){
	getEmailCount();
});
function getEmailCount(){
	$.post("workAndMessageControl/get_email_count",{},function(data){
		var num=eval("("+data+")");
		if(num.count!=0){
			$("#message").html(num.count);
		}
	});
}
function setnoread(num){
	$("#message").html(num);
}
