/**
 * 
 */

function getDate()
{
	var date = new Date();	
	return date.getDate() + "/" + date.getMonth() + "/" + date.getYear();
}

function getTime()
{
	var time = new Date()
	return time.getHours() + ":" + time.getMinutes();
}