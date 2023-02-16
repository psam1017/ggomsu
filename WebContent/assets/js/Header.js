const myAlarm = document.querySelector('#myAlarm');

myAlarm.addEventListener('click', function(){
	var alarmOpen = document.querySelector('.alarmOpen');
	if(!alarmOpen.classList.contains('off')){
		alarmOpen.classList.add('off');
	}else{
		alarmOpen.classList.remove('off');
	}
})