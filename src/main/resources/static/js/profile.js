function startAdminLock(searchValue){
    $.getJSON('HardLockServlet',{searchValue:searchValue,operation:'lock'}, function(data)
	{
    	 go();
	});
}


function startClearAdminLock(searchValue){
    $.getJSON('HardLockServlet',{searchValue:searchValue,operation:'unlock'}, function(data)
	{
    	 go();
	});
}

function startClearSoftLock(searchValue){
    $.getJSON('HardLockServlet',{searchValue:searchValue,operation:'softunlock'}, function(data)
	{
    	 go();
	});
}

function startClearHardLock(searchValue){
    $.getJSON('HardLockServlet',{searchValue:searchValue,operation:'clearHardLock'}, function(data)
	{
    	 go();
	});
}

function clearPin(email){
	$.getJSON('ProfileServlet',{operation:'nullifyPin',email:email}, function(data)
	 {
		 go();
		 
	 });
}

function updateNotifPref(userId){
	var iamNotifPref = $('#iamNotifPref').val();
	if (iamNotifPref ==''){
		alert("Please Provide a Notification Preference");
	}
	else{
		$.getJSON('ProfileServlet',{operation:'updateNotificationPref',userId:userId,notificationPreference:iamNotifPref}, function(data)
		 {
			 go();
			 
		 });
	}
	
}

function updatePin(userId){
	var pin = $('#iamProfilePin').val();
	if (pin ==''){
		alert("Please Provide a PIN");
	}
	else{
		$.getJSON('ProfileServlet',{operation:'updatePin',userId:userId,pin:pin}, function(data)
		 {
			 go();
			 
		 });
	}
	
}

