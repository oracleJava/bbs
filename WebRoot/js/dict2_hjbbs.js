﻿
// JScript
var userAgent = navigator.userAgent.toLowerCase();
var is_opera = userAgent.indexOf('opera') != -1 && opera.version();
var is_moz = (navigator.product == 'Gecko') && userAgent.substr(userAgent.indexOf('firefox') + 8, 3);
var is_ie = (userAgent.indexOf('msie') != -1 && !is_opera) && userAgent.substr(userAgent.indexOf('msie') + 5, 3);
var is_kon = userAgent.indexOf('konqueror') != -1;
var is_saf = userAgent.indexOf('applewebkit') != -1 || navigator.vendor == 'Apple Computer, Inc.';
var is_mac = userAgent.indexOf('mac') != -1;
//
if(typeof(hjd_dictInited)=="undefined"){
    var hjd_old_word = "";   
    var _dict_enable = true;  
    var hjd_status; 
    var hjd_iframe=0;
    var hjd_window=0; 
    var hjd_host = 'http://dict.hjenglish.com';
    var hjd_help = "http://dict.hjenglish.com";
    // 
    var hjd_x=0; 
    var hjd_y=0; 
    var _hjd_x=0; 
    var _hjd_y=0; 
    var hjd_width=350;
    var hjd_height=250;
    var hjd_m_offx=0;
    var hjd_m_offy=0; 
    var hjd_clientW=0;
    var hjd_clientH=0;
    var hjd_mouseloc=0; 
    var msgpanelTime=0;
    var hjd_frozen=false;
    var hjd_dictInited=false;
    var hjd_isFullScreen=false; 
    var hjd_lastmsgTitle="";
    var hjd_offsetX=0;
    var hjd_offsetY=0;
    var hjd_framebodyDuce=(is_moz!=false) ? 3:25; 
}

UpdateWinSizeInfo();
function getcookie(name) {
	var cookie_start = document.cookie.indexOf(name);
	var cookie_end = document.cookie.indexOf(";", cookie_start);
	return cookie_start == -1 ? '' : unescape(document.cookie.substring(cookie_start + name.length + 1, (cookie_end > cookie_start ? cookie_end : document.cookie.length)));
}
function setcookie(cookieName, cookieValue, seconds, path, domain, secure) {
	var expires = new Date();
	expires.setTime(expires.getTime() + seconds);
	document.cookie = escape(cookieName) + '=' + escape(cookieValue)
		+ (expires ? '; expires=' + expires.toGMTString() : '')
		+ (path ? '; path=' + path : '/')
		+ (domain ? '; domain=' + domain : '')
		+ (secure ? '; secure' : '');
}
function RecordSetting(){
    if( document.location.href.indexOf("hjbbs.com")!= -1){
        setcookie("hjd_initable", _dict_enable?"on":"off" , 9999999, "/", "hjbbs.com");
    }else if(document.location.href.indexOf("hjenglish.com")!= -1){
        setcookie("hjd_initable", _dict_enable?"on":"off" , 9999999, "/", "hjenglish.com");
    }else{  
        setcookie("hjd_initable", _dict_enable?"on":"off" , 9999999);
    }
}

//Special for HjBBS Start
function getAttribute2(srcElement){
    try{
        return ""+ srcElement.getAttribute("id");
    }catch(e){
        return "--";
    }
}
function HjDictValidP( aevent ){   
    var srcElement=0;
    if(is_ie){
        srcElement = aevent.srcElement;
    }else{
        srcElement = aevent.target;
    }
    try{
	    while(true){   
	        var Ids=getAttribute2(srcElement);  
		    if ( Ids.indexOf("bbs_hjd_")!=-1 ) {   
			    return true;
		    }else{  
			    if( srcElement.parentNode ) {
				    srcElement=srcElement.parentNode;  
			    }else{
				    return false;
			    }
		    }
	    }
        if(aevent.ctrlKey){
            return true;
        }
    }catch(e){
        alert(e.message);
        return false;
    }
}
//Special for HjBBS END

var awsize=getcookie("hjd_wsize");
if( awsize!= "" ){
    awsize=awsize.split("-");
    hjd_width = Number(awsize[0]);
    hjd_height = Number(awsize[1]); 
}
function hj$(aObjID){
    return document.getElementById(aObjID);
}    
function hjd_ShiftSize(aObjid, awidth, aheight, remainfullscreen){  
    if(awidth==0 || aheight==0){ 
        if(hjd_isFullScreen && !remainfullscreen){  
            hjd_isFullScreen=false;
            //
            awidth=hjd_width;
            aheight=hjd_height;
            hj$(aObjid).style.top=hjd_y+"px" ;
            hj$(aObjid).style.left=hjd_x+"px" ;
            hj$("hjd_img_full").src= hjd_host +"/images/btn_wrap_full0.gif";
        }else{
            hj$(aObjid).style.top=hjd_scrn_top()+5 +"px" ;
            hj$(aObjid).style.left=hjd_scrn_left()+5 +"px" ;
            UpdateWinSizeInfo();
            awidth= hjd_clientW -25;
            aheight= 550;//hjd_clientH -45;
            hj$("hjd_img_full").src= hjd_host +"/images/btn_wrap_full1.gif";
            hjd_isFullScreen=true;  
        }
    }else{
        hjd_width=awidth;
        hjd_height=aheight;
        hjd_isFullScreen=false;
        hj$(aObjid).style.top=hjd_y+"px" ;
        hj$(aObjid).style.left=hjd_x+"px" ;
	    setcookie("hjd_wsize", awidth +"-" +aheight , 9999999, "/", "hjenglish.com");
    } 
    hj$("hjd_img_1x").style.display = (awidth==350)? "none":"inline";
    hj$("hjd_img_2x").style.display = (awidth==400)? "none":"inline";
	hj$("hjd_dictFrame").style.height = (aheight -hjd_framebodyDuce)+"px";
	
    hj$(aObjid).style.width= awidth+"px" ;
    hj$(aObjid).style.height= aheight+"px" ; 
    if(!hjd_isFullScreen){
    //
        UpdateDocks();
    }
    _mouseUp();
    return false;
}
function hjd_moveWin(ev, xxx, yyy){   
    var mousePos = 0;
    if(ev==null){
        hjd_x= xxx;
        hjd_y= yyy;
    }else{
        mousePos = mouseCoords(ev);
        hjd_x= mousePos.x - hjd_m_offx;
        hjd_y= mousePos.y - hjd_m_offy;
    }
    //
    if(hjd_mouseloc!=0){
        var willDock=false;
        if(hjd_x<hjd_scrn_left()+10 ){
            willDock=true;
            hjd_x=hjd_scrn_left(); 
        }else if( Math.abs(hjd_x- (hjd_scrn_left()+ hjd_clientW -hjd_width-12))<10 ){
            willDock=true;
            hjd_x=hjd_scrn_left()+ hjd_clientW -hjd_width-12; 
        }else{ 
        }
        if(hjd_y<hjd_scrn_top()+10){
            willDock=true;
            hjd_y=hjd_scrn_top(); 
        }else if(Math.abs( ( hjd_scrn_top() +hjd_clientH -hjd_height-36)-hjd_y)<10  ){
            willDock=true;
            hjd_y=hjd_scrn_top() + hjd_clientH -hjd_height-36; 
        }else{ 
        }
        if( willDock || hjd_frozen){
            hjd_ShiftFrozen(true);
        }
    }   
    hj$('hjd_panel').style.left =Math.max(0, Math.min( hjd_x , hjd_scrn_left()+  hjd_clientW )   )+"px" ;
    hj$('hjd_panel').style.top =Math.max(0, Math.min(hjd_y , hjd_scrn_top()+ hjd_clientH )  ) +"px" ;
}
function hjd_ResetWindowLoc(){
    if(hjd_x > hjd_scrn_left()+ hjd_clientW- hjd_width){
        hjd_x =hjd_x -hjd_width -25;
        hj$('hjd_panel').style.left = hjd_x+"px" ;
    }
    if(hjd_y > hjd_scrn_top()+ hjd_clientH- hjd_height){
        hjd_y =hjd_y -hjd_height -25;
        hj$('hjd_panel').style.top =hjd_y +"px" ;
    } 
}
//
function hjd_hidewindow(){
    hjd_ShiftFrozen(false);
    hj$('hjd_panel').style.display="none"; 
    if(hjd_isFullScreen){
        hjd_ShiftSize('hjd_panel',0,0);
    }
    hjd_old_word="";
}
function hjd_showwindow(){
    hj$('hjd_panel').style.display="block"; 
}
function hjd_scrn_top(){
    if (document.documentElement && document.documentElement.scrollTop){
	    return document.documentElement.scrollTop;
    }else if (document.body){
	    return document.body.scrollTop;
    }
}
function hjd_scrn_left(){ 
    if (document.documentElement && document.documentElement.scrollLeft){
	    return document.documentElement.scrollLeft;
    }else if (document.body){
	    return document.body.scrollLeft;
    }  
}
function _mouseDown(ev){  
    ev         = ev || window.event; 
    var target   = ev.target || ev.srcElement;
    if(hjd_isFullScreen==true){return;} 
    try{  
        // 
        if( target.getAttribute("id")=="hjd_top" ){
            hjd_mouseloc = mouseCoords(ev);
            hjd_m_offx=hjd_mouseloc.x - hjd_x;
            hjd_m_offy=hjd_mouseloc.y - hjd_y;
            UpdateWinSizeInfo();
        }else {
            hjd_m_offx=0;
            hjd_m_offy=0;
            hjd_UpdatePosCache(ev);
            if(hjd_window.style.display=="block"){
                var amouseloc = mouseCoords(ev); 
                if(  amouseloc.x< hjd_x ||  amouseloc.x> (hjd_x + hjd_width)  || amouseloc.y < hjd_y ||   amouseloc.y> ( hjd_y + hjd_height) ){
	                hjd_dictClose();
	            }
            }else{
            }
        }
    }catch(e){  
       alert(e);
    }
}
//
function UpdateWinSizeInfo(){
    if(document.documentElement.clientWidth){
        hjd_clientW= document.documentElement.clientWidth;
        hjd_clientH= document.documentElement.clientHeight;
    }else{
        hjd_clientW= document.body.clientWidth;
        hjd_clientH= document.body.clientHeight;
    }
}
function HjdWindowClicked(srcElement){
    try{
	    var q=0;
	    while(q<5){
		    q++;
		    if(srcElement.tagName.toLowerCase()=="a" && srcElement.tagName.toLowerCase()=="img"){
		        return false;
		    }
		    if (srcElement.tagName.toLowerCase()=="div" && srcElement.getAttribute("id")=="hjd_top") { 
			    return true;
		    }else{
			    srcElement=srcElement.parentNode; 
		    }
	    }
	    return false;
	}catch(e){
	    return false;
	}
}
function _mouseUp(ev){ 
    hjd_mouseloc=0;   
}
function _mouseMove(ev){  
    ev           = ev || window.event;  
    if(hjd_isFullScreen==true){return;}
    //
    if(hjd_mouseloc!=0){  
        hjd_moveWin(ev); 
        return false;
    }else{
        var mousePos = mouseCoords(ev);
        _hjd_x= mousePos.x;
        _hjd_y= mousePos.y;
    } 
}
function _resize(){ 
    if(hjd_isFullScreen){
        hjd_ShiftSize('hjd_panel',0,0,true);
    }else{
        //hjd_moveWin
        _scroll();
    }
}
function _scroll(){ 
    UpdateWinSizeInfo();
    if(hjd_status!=0){
        if(hjd_status.style.display=="block"){
            hjd_status.style.left=hjd_scrn_left()+"px";
            hjd_status.style.top=hjd_scrn_top()+"px";
        }
    }
    if(!hjd_isFullScreen){
        UpdateDocks();
    }else{
        hjd_ShiftSize('hjd_panel',0,0,true);
    }
}
function UpdateDocks(){
    //
    if( hjd_frozen ){    
        hjd_moveWin(null, hjd_scrn_left()+ hjd_offsetX, hjd_scrn_top()+ hjd_offsetY );
    }
}
function hjd_ShiftFrozen(aBool){
    if(aBool!=null){
        hjd_frozen= aBool;
    }else{
        hjd_frozen=!hjd_frozen;
    }  
    hj$("hjd_img_frozen").src= hjd_host + (hjd_frozen==false? "/images/btn_wrap_frozen0.gif": "/images/btn_wrap_frozen1.gif"); 
    if( hjd_frozen ){
        hjd_offsetX= hjd_x - hjd_scrn_left();
        hjd_offsetY= hjd_y - hjd_scrn_top();
    }else{
        hjd_offsetX= 0;
        hjd_offsetY= 0;
    }
    return false;
}
function mouseCoords(ev){
    if(ev.pageX || ev.pageY){
        return {x:ev.pageX, y:ev.pageY};
    }  
    var yy=0;
    if(document.body.scrollTop == hjd_scrn_top()){
        return {  
            x:ev.clientX + document.body.scrollLeft - document.body.clientLeft, 
            y:ev.clientY + document.body.scrollTop  - document.body.clientTop
        };
    }else{
        return {  
            x:ev.clientX + document.body.scrollLeft - document.body.clientLeft, 
            y:ev.clientY + document.body.scrollTop  - document.body.clientTop + hjd_scrn_top()
        };
    }
}
//

function hjd_init(){  
	var Html="";
	//Html+= "<div id='hjd_panel' style='width:280px;height:180px;position:absolute;z-index:1000;left:250px;top:50px;font-size:12px; background:url(http://dict.hjenglish.com/images/titlebg_select.gif) no-repeat;background-color:#EAFABE;border:solid 1px #A4BF3C;padding-bottom:29px !important;padding:5px;'>";
	Html+= "<div style='width:100%;height:100%;padding:0px;margin:0px;'><div id='hjd_top' style='background:url(http://dict.hjenglish.com/images/titlebg_select.gif) top left no-repeat;background-color:#EAFABE;height:24px;line-height:18px;text-align:right;cursor:move;'>";
	Html+= "		<a href='###' onclick='return hjd_ShiftSize(\"hjd_panel\",350,250);'><img id='hjd_img_1x' src='"+hjd_host+"/images/btn_wrap_1x.gif' border=0/></a>";
	Html+= "		<a href='###' onclick='return hjd_ShiftSize(\"hjd_panel\",400,300);'><img id='hjd_img_2x' src='"+hjd_host+"/images/btn_wrap_2x.gif' border=0/></a>";
	Html+= "		<a href='###' onclick='return hjd_ShiftSize(\"hjd_panel\",0,0);'><img id='hjd_img_full' src='"+hjd_host+"/images/btn_wrap_full0.gif' border=0/></a>";
	Html+= "		<a href='###' id='hjd_btn_frozen' onclick='return hjd_ShiftFrozen();'><img id='hjd_img_frozen' src='"+hjd_host+"/images/btn_wrap_frozen0.gif' border=0/></a>";
	Html+= "		<a href='javascript:hjd_hidewindow();'><img id='hjd_img_close' src='"+hjd_host+"/images/btn_wrap_close.gif' border=0/></a>";
	Html+= "</div>";
	Html+= "<div id='hjd_bodyframe' style='background-color:white;'>";
	Html+= "	<iframe style='width:100%;height:"+ (hjd_height -hjd_framebodyDuce) +"px;display:block;background-color:white;' id='hjd_dictFrame' name='hjd_dictFrame' src='about:blank' FRAMEBORDER='0' scrolling='auto'></iframe> ";
	Html+= "</div></div>";
	//Html+= "</div>";

    try{
        var el = document.createElement('div');
        el.id='hjd_panel';
        //width:280px;height:180px;position:absolute;z-index:1000;left:250px;top:50px;font-size:12px;  
        //background:;background-color:#EAFABE;border:;padding-bottom:29px !important;padding:5px;
        el.style.position='absolute';
        el.style.display='none';
        el.style.zIndex=9001;
        el.style.fontSize="12px";
        el.style.border="solid 1px #A4BF3C"; 
        el.style.backgroundColor='#EAFABE'; 
        el.style.width = hjd_width + 'px';
        el.style.height = hjd_height + 'px';
        el.style.left = '0px';
        el.style.top = '0px';
        el.style.padding = '5px';
        if(is_ie){
            //
        }else{
            el.style.paddingBottom  = '29px';
        } 

        document.body.appendChild(el);
        _dictSet(el, Html);

        el = document.createElement('div');
        el.id='hjd_statuspanel';
        el.style.position='absolute';
        el.style.backgroundColor='#F1FCD6';
        el.style.padding='2px';
        el.style.filter='Alpha(Opacity=96)';
        el.style.fontSize='14px';
        el.style.display='none'; 
        el.style.left = hjd_scrn_left()+'px';
        el.style.top = hjd_scrn_top()+'px';
        el.style.zIndex=9002;
        el.style.border = '1px solid #A2D911';

        document.body.appendChild(el);
        _dictSet(el, _dictStatus());
        
        // 
    }catch(x){
        alert("dict can not support this page."+ x); 
        return;
    }
    hjd_status=hj$("hjd_statuspanel");
    hjd_iframe=hj$("hjd_dictFrame");
    hjd_window=hj$("hjd_panel");  
    hjd_dictClose(); 

    if(document.addEventListener) {
        document.addEventListener("mousemove", _mouseMove, false);
        document.addEventListener("mousedown", _mouseDown, false);
        document.addEventListener("mouseup", _mouseUp, false);
        document.addEventListener("mouseup", _dictQuery, false);
        document.addEventListener("dblclick", _dictQuery, true); 
        window.addEventListener("resize", _resize, false);
        window.addEventListener("scroll", _scroll, false);
    } else if(document.attachEvent) {
        document.attachEvent("onmousemove", _mouseMove);
        document.attachEvent("onmousedown", _mouseDown);
        document.attachEvent("onmouseup", _mouseUp);
        document.attachEvent("onmouseup", _dictQuery);
        document.attachEvent("ondblclick", _dictQuery); 
        window.attachEvent("onresize", _resize);
        window.attachEvent("onscroll", _scroll);
    }
    //
    hjd_dictInited=true; 
} 
function _dictSet(el, htmlCode) {
    var ua = navigator.userAgent.toLowerCase();
    if (ua.indexOf('msie') >= 0 && ua.indexOf('opera') < 0) {
        el.innerHTML = '<div style="display:none">for IE</div>' + htmlCode;
        el.removeChild(el.firstChild);
    }
    else {
        var el_next = el.nextSibling;
        var el_parent = el.parentNode;
        el_parent.removeChild(el);
        el.innerHTML = htmlCode;
        if (el_next) {
            el_parent.insertBefore(el, el_next)
        } else {
            el_parent.appendChild(el);
        }
    }
}
function hjd_dictGetSel()
{
	if (window.getSelection) return window.getSelection();
	else if (document.getSelection) return document.getSelection();
	else if (document.selection) return document.selection.createRange().text;
	else return '';
}
//
function hjd_UpdatePosCache(aevent){
    var mouseloc=mouseCoords(aevent);
    _hjd_x = mouseloc.x;
    if (is_ie) {
        _hjd_y = mouseloc.y 
    }else{
        _hjd_y = mouseloc.y;
    }
}

function hjd_getFFinputSelect(e){ 
    try{
	    var myArea = e.target; 
	    if (myArea.selectionStart!= undefined) {
		    return  myArea.value.substr(myArea.selectionStart, myArea.selectionEnd - myArea.selectionStart);
	    }else{
	        return "";
	    }
    }catch(e){
	    return "";
    }
}

function hjd_replaceNumber(aChar,aIndex){
	return String.fromCharCode( aChar.charCodeAt(0) - 65248);
}
function _dictQuery(e)  {
    if (!_dict_enable || getcookie("hjd_initable")=="off") {
        _dict_enable=false;
        return true; 
    }
    if( !HjDictValidP(e) ){
        return false;
    }
        
    if(hjd_mouseloc!=0){
        if (is_ie) {
            window.event.cancelBubble = true;
            window.event.returnValue = false;
        }else{
            e.preventDefault();
        }
        return false; 
    }
    
    var word = hjd_dictGetSel(e); 
    //
    if(e==null){
        e=window.event;
    } 
    var Elem=0;
    if(is_ie){
        Elem= e.srcElement;
    }else{
        Elem= e.target;
    } 
    if(!e.ctrlKey){
        if( (Elem.tagName =="INPUT" && Elem.getAttribute("type").toLowerCase() =="text") || Elem.tagName=="TEXTAREA" ){ 
            return;
        }
    }
    if( Elem.tagName =="INPUT" &&  Elem.getAttribute("type").toLowerCase() !="text" ){
       return;
    }
    if (! is_ie) {
        if(word.toString().length==0){
            word =hjd_getFFinputSelect(e);
        }
    }
    
    word=""+word;
    var ChNumber=/[\uff21-\uff5a]/igm;
    if (ChNumber.test(word))
    {
	    word= word.replace(ChNumber, hjd_replaceNumber);
    } 
    //word= word.replace( /(^[ 　，,：。“”‘、’；;）\)》>]*)|([<《\(（ 　；：;、，,。“”‘’]*$)/g ,"");
    word= word.replace( /(^[ \u3000\uff5e\uff0c,\uff1a\u3002\u201c\u201d\u2018\u3001\u2019\uff1b;\uff09\)\u300b>]*)|([<\u300a\(\uff08 \u3000\uff1b;\u3001\uff0c,\u3002\u201c\u201d\u2018\uff1a\uff5e\u2019]*$)/g ,"");
    //var reg0=/[，。、？；：“‘’”、　！［］｛｝＜＞｀~～]/igm;
    var reg=/([\uff0c\u3002\u3001\uff1f\uff1b\uff1a\u201c\u2018\u2019\u201d\u3001\u3000\uff01\uff3b\uff3d\uff5b\uff5d\uff1c\uff1e\uff40~\uff5e])/g;
    word= word.replace( reg ,"-----------------------------------------"); 
    
    if(word == "" || word== hjd_old_word || word.length > 30 ){
        //cancel for more than 30 chars
        return true;
    }
    
    reg=/(([a-zA-Z0-9][\u0800-\u9fa5])|([\u0800-\u9fa5][a-zA-Z]))/igm
    if(  reg.test(word) && word.length <= 30 ){
        return ; 
    }else{
        reg=/[\u0800-\u9fa5]/igm
        reg2=/[\u3042-\u3106]/igm
        if(  reg.test(word) && !reg2.test(word) && word.length > 4 ){ 
            //cancel for Chs more than 4 chars
            return true;
        }else{
            if(word.split(" ").length>5) { 
                //cancel for the phrase that more than 5 words
                return true;
            }
        }
    }
    if(word.split("\n").length>1){
        return;
    }
    hjd_UpdatePosCache(e);
    hjd_dictShow(word);
} 
//
function _dictDisplay(ev){
    if(ev!=null){
        var amouseloc = mouseCoords(ev);
        hjd_moveWin(null, amouseloc.x, amouseloc.y);
    }else{ 
        if(hjd_frozen==true){
            hjd_moveWin(null, hjd_x, hjd_y);
        }else{
            hjd_moveWin(null, _hjd_x, _hjd_y);
        }
    }
    hjd_window.style.display="block";
    hj$("hjd_img_1x").style.display = (hjd_width==350)? "none":"inline";
    hj$("hjd_img_2x").style.display = (hjd_width==400)? "none":"inline";
}

function hjd_dictShow(word, sentence){
    _dictDisplay();
    try{
        hjd_iframe.src="about:blank";   
    }catch(x){  
	    try{
		    window.frames["hjd_dictFrame"].location="about:blank";   
	    }catch(e){}
    }
    try{  
        var iframeWin = window.frames["hjd_dictFrame"]; 
        iframeWin.document.open();
        iframeWin.document.write('<html><body><span style="color:#666666;font-weight:bold;">Search </span><span style="color:green;font-weight:bold;">'+word+'</span> ... ...</body></html>');
        iframeWin.document.close();
    }catch(x){  
    }
    //catch for regex exception
    try{  
        var type="";
        if(word.indexOf("type=cj")!=-1){
            type="cj";
        }
        if(word.indexOf("type=jc")!=-1){
            type="jc";
        }
        word=word.replace("&type=cj", "");
        word=word.replace("&type=jc", "");
		var reg4=/[\u3042-\u3106]/igm;
		if(reg4.test(word)){
            type="jc";
		}
        var reg=/([\u0800-\u4e00]{1,})/igm
        if( reg.test(word) || type != "" ){
            setTimeout(function(){SkipUrl("hjd_dictFrame", hjd_host+ "/jp/web/"+ hjd_escape(word)+ "&type="+ type+ "&nolan=1");},250);
        }else{
            setTimeout(function(){SkipUrl("hjd_dictFrame", hjd_host+ "/web/"+ hjd_escape(word));},250);
            if(document.location.href.indexOf("dict.hjenglish.com")==-1){ 
                var reg3=/[^a-zA-Z\-\., ]/igm 
            }
        }
    }catch(x){
        setTimeout(function(){SkipUrl("hjd_dictFrame", hjd_host+ "/web/"+ hjd_escape(word)  );},250);
    }
    hjd_old_word = word;
    hjd_ResetWindowLoc();
}
function hjd_escape(astring){
    var ret="";
    try{  
        ret= encodeURI(astring);
        return ret;
    }catch(x){  
        alert("值得反馈的错误信息： hjd_escape."+x.message);
        return astring;
    }
} 
function SkipUrl(ObjID, Url){  
    try{
        document.getElementById(ObjID).src=Url+"&time="+Math.random(); 
    }catch(e){
	    try{
		    document.frames[ObjID].location=Url+"&time="+Math.random();  
	    }catch(e){}
    }
} 

function hjd_dictClose() {
    if(hjd_frozen){return ;}
    try
    { 
    	hjd_window.style.display="none";
        setTimeout(function(){hjd_old_word = "";},500);
    }
    catch (x)
    {
    }
}

function _dictEnable(){
    if (_dict_enable){
        _dict_enable = false;
    }else{
        _dict_enable = true;
    }
    _dictUpdateStatus();   
}

function ShiftDictable(aBool){
    _dict_enable = aBool;
    _dictUpdateStatus();
}

function _dictUpdateStatus(){ 
  _dictSet(hjd_status, _dictStatus());
}

function hjd_remove() {
    hjd_window.style.display="none";
    hjd_status.style.display="none"; 
    _dict_enable = false;
}
function _dictStatus(){
    var h='';
    if (_dict_enable){
     h += '[<a href="'+hjd_help+'" title="&#25105;&#35201;&#26597;&#30475;&#21010;&#35789;&#24110;&#21161;" target="_blank" style="color:#6E9C3C;font-size:14px;">&#21010;&#35789;&#32763;&#35793;</a>&#24050;<a href="javascript:_dictEnable()" title="&#25105;&#35201;&#31105;&#29992;&#21010;&#35789;&#32763;&#35793;" target="_self" style="color:#FF6600;font-size:14px;">&#24320;&#21551;</a>]';
    }else{
      h += '[<a href="'+hjd_help+'" title="&#25105;&#35201;&#26597;&#30475;&#21010;&#35789;&#24110;&#21161;" target="_blank" style="color:#6E9C3C;font-size:14px;">&#21010;&#35789;&#32763;&#35793;</a>&#24050;<a href="javascript:_dictEnable()" title="&#25105;&#35201;&#24320;&#21551;&#21010;&#35789;&#32763;&#35793;" target="_self" style="color:#FF6600;font-size:14px;">&#31105;&#29992;</a>]';
    }
    h +=' <a href="javascript:hjd_remove();" target="_self"><img src='+hjd_host+'/images/close.gif border=0 align=absmiddle></a>';
    return h;
    //&#20320;&#35201;&#26597;&#25214;&#30340;&#26159;&#65306;
}

if(document.location.toString().indexOf("192.168.18")!=-1){
    hjd_host = 'http://192.168.18.69';
}
if(document.location.toString().indexOf("dict2.hjenglish.com")!=-1){
    hjd_host = 'http://dict2.hjenglish.com';
}
if(document.location.toString().indexOf("bulo130.hjenglish.com")!=-1){
    hjd_host = 'http://dict2.hjenglish.com';
}
try{
    if(document.location.href.indexOf("dict.hjenglish.com")!=-1){
        hjd_help = "http://dict.hjenglish.com/snipper.aspx";
    }
}catch(x){;}
_dictShow=hjd_dictShow;
//disable init
try{
    if(_initdisable==true){
        _dict_enable = false;
    }
}catch(x){;}
//
function BeginDict(){
    if(! document.getElementById('hjd_panel')){ 
        //Enable dict by default  
        if(getcookie("hjd_initable")=="off" || (typeof(hjd_dict_enable)!="undefined" && hjd_dict_enable==false)){
            ShiftDictEnable(false);
        }else{
            ShiftDictEnable(true);
        }
        try{
            window.setTimeout(function(){hjd_init();}, 250); 
        }catch(x){
            alert("Dict Error on this page!");
        }  
        //
        window.setTimeout(function(){AttachDictBH();}, 600);  
    }else{
        try{
            _dict_enable = true;
            hjd_status.style.display="block";
            _dictUpdateStatus();
        }catch(x){;}
    }
}
if(document.addEventListener) {
    window.addEventListener("load", BeginDict, false); 
} else if(document.attachEvent) {
    window.attachEvent("onload", BeginDict); 
}
//
//
var hj_dict_timer=0;
var hj_dict_word="";
var hj_dict_wordID=0;

function hj_dict_ShowDict(){ 
	if(hj_dict_word!=""){ 
		hj_dict_ShowDict_do(hj_dict_word);
	} 
}
function hj_dict_ShowDict_do(aword){ 
	//
	if(hjd_dictInited){
		_dictShow(aword); 
	}
}
function GetJp_typeparam(aurl){
	if(aurl.indexOf("/jp/")==-1){
	    return "";
	}else{
        if(aurl.indexOf("type=cj")!=-1){
            return "&type=cj";
        }else {
            return "&type=jc";
        }
	}
}
function AttachDictBH(){
	var elms = document.getElementsByTagName('a');
	for (i = 0; i < elms.length; i++) {
		if (elms[i].className.indexOf("hjdict") == 0) {  
			elms[i].onmouseover = function(aevent){ 
				if(aevent==null){
					aevent=window.event;
				}
				if(hjd_dictInited){
					hjd_UpdatePosCache(aevent); 
				}
				hj_dict_word =this.getAttribute("word")+GetJp_typeparam(this.getAttribute("href")); 
				hj_dict_timer = setTimeout("hj_dict_ShowDict()", 300);
			}
			elms[i].onmouseout = function(){ 
				clearTimeout(hj_dict_timer);
				hj_dict_word  ="";
			}
		}
	}
}
//
//Shift DictEnable, use in hjenglish.com pages
function ShiftDictEnable(aBool, aObjID){
    if(aObjID==null){
        aObjID="hjdict_power";
    }
	try{ 
	    if(aBool==null){
		    _dict_enable=!_dict_enable;
	    }else{
		    _dict_enable=aBool;
	    }
	    RecordSetting();
		if(_dict_enable){
			document.getElementById(aObjID).innerHTML="&#24050;&#21551;&#29992;";
		}else{
			document.getElementById(aObjID).innerHTML="&#24050;&#31105;&#29992;";
		}
	}catch(e){
	    //
	}
	return false;
} 
function sethjmyie2_sidebarenable(aBool){
    try{
	    _dict_enable= !aBool; 
		if(_dict_enable){
			document.getElementById("hjdict_power").innerHTML="&#24050;&#21551;&#29992;";
		}else{
			document.getElementById("hjdict_power").innerHTML="&#24050;&#31105;&#29992;";
		}
	}catch(e){
	    alert(e.message);
	}
} 