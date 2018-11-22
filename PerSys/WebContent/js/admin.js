/**
 * 下拉列表
 */
window.onload = function() {
    var oUl = document.getElementById("list");
    var aH1 = oUl.getElementsByTagName("h1");
    var oU2 = oUl.getElementsByTagName("ul");
    var oLi = null;
    var arrLi = [];

    for (var i = 0; i < aH1.length; i++) {
        aH1[i].index = i;
        aH1[i].onclick = function() {
            if (this.className == "") {
                oU2[this.index].style.display = "block";
                this.className = "active";
            } else {
                oU2[this.index].style.display = "none";
                this.className = "";
            }
        }
    }

    for (i = 0; i < oU2.length; i++) {
        oLi = oU2[i].getElementsByTagName("li");
        for (j = 0; j < oLi.length; j++) {
            arrLi.push(oLi[j]); //数组中添加新元素
        }

    }
    /*三级菜单*/
    for (i = 0; i < arrLi.length; i++) {
        arrLi[i].onclick = function() {
            for (i = 0; i < arrLi.length; i++) {
                arrLi[i].className = "";
            }
            this.className = "hover";
        }
    }
}

function checkperson(theform){
	if(theform.no.value == ""){
		alert("工号不能为空");
		theform.no.focus();
		return false;
	}
	if(theform.name.value == ""){
		alert("姓名不能为空");
		theform.name.focus();
		return false;
	}
	if(theform.phone.value == ""){
		alert("电话不能为空");
		theform.phone.focus();
		return false;
	}
}

function submitForm(form){
    //var form = document.getElementById("myform");
    form.submit();
}


