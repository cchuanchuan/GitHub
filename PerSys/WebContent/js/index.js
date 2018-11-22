/**
 * 
 */
function submitForm(form){
    //var form = document.getElementById("myform");
    form.submit();
}

function clock(bgclock){
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth();
	var date = now.getDate();
	var day = now.getDay();
	var week;
	month = month+1;
	if(month<10){
		month="0"+month;
	}
	if(date<10){
		date="0"+date;
	}
	var arr_week = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
	week = arr_week[day];
	var time = "";
	time = year+"年"+month+"月"+date+"日"+"&nbsp;&nbsp;";
	
	var hours = now.getHours();
	var minutes = now.getMinutes();
	var seconds = now.getSeconds();
	time += hours;
	time += ((minutes<10)?":0":":")+minutes;
	time += ((seconds<10)?":0":":")+seconds;
	time += "&nbsp;&nbsp;"+week;
	document.getElementById("bgclock").innerHTML=time+"&nbsp;&nbsp;";
	var timer = setTimeout("clock(bgclock)",200);
}

