/**
 * create for a4 system show.
 * it's working for split title and show data in distinct.
 * @author yurow 
 * @blog birdshover.cnblogs.com
 * @date 2008-10-11
 *
 * @version debug 0.2
 * @date 2008-10-15 update
 * @author birdshover
 */
if(typeof(adv4_isloaded) == "undefined")
	var adv4_isloaded = false;
else
	adv4_isloaded = false;
if(typeof(adsArray) == "undefined")
	var adsArray = new Array();
else
	adsArray = new Array();
function Advance_A4()
{
	var dock = new Array();
	var dock_ptr = 0;
	var num = 0;
	var handler = 0;
	var dochandler = 0;
	var split = "";
	var count = 0;
	var debug = false;
	var debugstring = "";
	var tag = "";
	var closetitle = false;
	var initial = function()
	{
		
	};
	var isGecko=function()
	{
		return navigator.userAgent.indexOf("Gecko") == -1 ? false :true;
	};
	var isOpera=function()
	{
		return navigator.userAgent.indexOf("Opera") == -1 ? false :true;
	};
	var isIE=function()
	{
		return document.all ?  true : false;
	};
	var ready = function()
	{
		if (isGecko() || isOpera()) {
			try {
				document.addEventListener('DOMContentLoaded', initial, false);
				return true;
			}catch(e){ }
		}
		else if (isIE()) {
			try
			{
				document.body.doScroll("left");
				return true;
			}
			catch(e){ return false; }
		}
	};
	var queryInit = function()
	{
		if(ready()){
			clearInterval(dochandler);
			var title = "";
			if(!closetitle)
				title = testTitle();
			var url = "http://a4.yeshj.com/ads_gb/EN017?num=" + num + "&title=" + encodeURIComponent(title) + "&tag=" + encodeURIComponent(tag);
			var script = document.createElement("script");
			script.setAttribute("language","javascript");
			script.setAttribute("type","text/javascript");
			script.setAttribute("charset","gb2312");
			script.setAttribute("src",url);
			document.body.appendChild(script);
			if(debug){
				debugstring += "\nget Title:" + title + "\n\nquery complete.wait to show result.\n";
			}
			handler = setInterval(ShowUIAction,500);
		}
		else
		{
			if(debug){
				debugstring = "";
				alert("document not complete loaded.waiting...");
			}
		}
	};
	var testTitle = function()
	{	
		var title = document.title;
		if(split == "" || count == 0)
			return title;
		try{
			var title_arr = title.split(split);
			if(count > title_arr.length)
				return title;
			else
			{
				var nowtitle = "";
				for(var i =0;i<title_arr.length -count;i++)
				{
					nowtitle += title_arr[i] + split;
				}
				return nowtitle;
			}
		}catch(e){ return title; }
	};
	var getUICode = function(start,num)
	{
		if(start + num > adsArray.length)
			num = adsArray.length - start;
		var str = "";
		for(var i= start;i<start + num;i++)
		{
			str += "<a href=\"" + adsArray[i][0] + "\" target=\"adv4\">" + adsArray[i][1] + "</a><br/>";
		}
		return str;
	};
	var ShowUIAction = function()
	{
		if(adv4_isloaded)
		{
			clearInterval(handler);
			var start = 0;
			for(var i = 0;i < dock.length;i++)
			{
				var obj = document.getElementById(dock[i][0]);
				obj.innerHTML = getUICode(start,dock[i][1]);
				start += dock[i][1];
			}
			if(debug)
			{
				debugstring += "complete!";
				alert(debugstring)
			}
		}
		else{
			if(debug)
				alert("loading data...");
		}
	};
	this.setUIDock = function(name,num)
	{
		dock[dock_ptr] = new Array();
		dock[dock_ptr][0] = name;
		dock[dock_ptr][1] = num;
		dock_ptr++;
	};
	this.setQueryNum = function(number)
	{
		num = number;
	};
	this.setTitleSplit = function(str)
	{
		split=str;
	};
	this.setTitlePart = function(number)
	{
		count = number;
	};
	this.setDebug = function()
	{
		debug = true;	
	};
	this.setTag = function(t)
	{
		tag = t;
	};
	this.CloseTitle = function()
	{
		closetitle = true;
	};
	this.ShowUI = function()
	{
		if(debug)
			debugstring += "500ms wait to query data.\n";
		dochandler = setInterval(queryInit,500);
	}
}

function Shift_BBSA4Panel(alinkObj, aID){ 
	if(document.getElementById(aID).style.width!="50px"){
		alinkObj.innerHTML="&laquo;";
		document.getElementById(aID).style.width='50px';
		document.getElementById(aID+"_txt").style.display="none";
	}
	else{
		alinkObj.innerHTML="&raquo;";
		document.getElementById(aID).style.width='30%';
		document.getElementById(aID+"_txt").style.display="block";
	}
	return false;
}