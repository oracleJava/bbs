function getDate(){ 
var date=new Date();  
var month=date.getMonth()+1;
var day=date.getDate(); 
if(month.toString().length == 1){  //ªÚ’ﬂ”√if (eval(month) <10) {month="0"+month}      
     
month='0'+month;      
}      
if(day.toString().length == 1){      
day='0'+day;      
}      
return date.getYear()+'/'+month+'/'+day+'  '+date.toLocaleString().substring(date.toLocaleString().length-8);      
}