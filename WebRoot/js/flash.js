
function GetFlash(id, playerfile, flashvers ,width, height, NotTransparent)
{
	if(NotTransparent==true)
	{
		NotTransparent="window";
	}
	else
	{
		NotTransparent="transparent";
	}
	var str="<object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000' codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,22,0'";
	str+="width='"+ width+ "' height='"+ height+ "' id='"+ id +"' align='middle' >";
	str+="<param name='allowScriptAccess' value='always' />";
	str+="<param name='movie' value='"+playerfile+"' />";
	str+="<param name='quality' value='high' />";
	str+="<param name='scale' value='noScale' />";
	str+="<param name='align' value='tl' />";
	str+="<param name='wmode' value='"+NotTransparent+"' />"; 
	str+="<param name='flashvars' value='"+flashvers+"' />";
	str+="<embed src='"+playerfile+"' quality='high' scale='noScale' bgcolor='#ffffff' width='"+ width+ "' height='"+ height+ "' flashvars='"+flashvers+"' id='"+ id +"' name='"+ id +"' align='middle' allowScriptAccess='always' type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/go/getflashplayer'/>";
	str+="</object>";
	document.write(str);
}
function GetFlashCode(id, playerfile, flashvers ,width, height, transparent)
{
	if(transparent!=true)
	{
		transparent="window";
	}
	else
	{
		transparent="transparent";
	}
	var str="<object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000' codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,22,0'";
	str+="width='"+ width+ "' height='"+ height+ "' id='"+ id +"' align='middle' >";
	str+="<param name='allowScriptAccess' value='always' />";
	str+="<param name='movie' value='"+playerfile+"' />";
	str+="<param name='quality' value='high' />";
	str+="<param name='scale' value='noScale' />";
	str+="<param name='wmode' value='"+transparent+"' />"; 
	str+="<param name='flashvars' value='"+flashvers+"' />";
	str+="<embed src='"+playerfile+"' quality='high' scale='noScale' bgcolor='#ffffff' width='"+ width+ "' height='"+ height+ "' flashvars='"+flashvers+"' id='"+ id +"' name='"+ id +"' align='middle' allowScriptAccess='always' type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/go/getflashplayer'/>";
	str+="</object>";
	    if (arguments.length == 7)
        $("#" + arguments[6]).html(str);
    else
        return str;
}

function GetWord(aWord)
{
	var ID= 9999999*Math.random();
	GetFlash("hjw_"+ ID, "http://dict.hjenglish.com/speaker_wv.swf", "w="+ aWord.split(" ").join("#"), 16, 16, false ); 
}
//显示读音，暂时只支持中英
function ShowSound(aLinkObj, alan, aword){ 
	if(alan=="en"){
		var sound = "http://dict.hjenglish.com/s/"+ aword;
		aLinkObj.parentNode.innerHTML = GetFlashCode("dict_sound_"+aword ,"http://dict.hjenglish.com/common/speaker_mini.swf","autoplay=true&son="+sound, 16, 16, true);
	}
	return false;
}
var audioPlayer = "/2009/images/FlvPlayer.swf";
function HJ$(aControlID)
{
  return document.getElementById(aControlID);
}
var soundPlayer ="http://class.yeshj.com/media/FlvPlayer.swf"; 

function ShowRecPlayer(aDiv, audioFile)
{
  aDiv.innerHTML = GetFlashCode("flv_" + Math.random(), soundPlayer, "autoplay=true&file=" + audioFile, 149, 27, false);
} 
