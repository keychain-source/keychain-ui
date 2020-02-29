function fd()
{
	if($('#fin').val()=='')
	{
		alert("Please enter your first name");
		//document.fn.fin.focus();
		return false;
	}
	else
	if($('#ln').val()=='')
	{
		alert("Please enter your  last name");
		//document.fn.ln.focus();
		return false;
	}
	else
	if($('#email').val()=='')
	{
		alert("Please enter your email");
		//document.fn.email.focus();
		return false;
	}
	else
	if($('#mobile').val()=='')
	{
		alert("Please enter your Mobile Number");
		//document.fn.mobile.focus();
		return false;
	}
	else
	if($('#pass').val()=='')
	{
		alert("Please enter your  password");
		//document.fn.pass.focus();
		return false;
	}
	else
	if($('#rpass').val()=='')
	{
		alert("Please retype your password");
		//document.fn.rpass.focus();
		return false;
	}
	else
	if($('#signUpPassword').val()!=$('#rpass').val())
	{
		alert("Entered Password does not match ");
		//document.fn.rpass.focus();
		return false;
	}
	else
		if($('#signUpUserName').val()!=$('#signUpUserName').val())
		{
			alert("Please provide an User Name ");
			//document.fn.rpass.focus();
			return false;
		}
		else
			if($('#signUpPassword').val()!=$('#signUpPassword').val())
			{
				alert("Please provide User Password ");
				//document.fn.rpass.focus();
				return false;
			}
	else
	return true;
	}

function PushLoginData()
{
	if (fd()){

		var fin = $('#fin').val();
		var ln = $('#ln').val();
		var email = $('#email').val();
		var signUpUserName = $('#signUpUserName').val();
		var signUpPassword= $('#signUpPassword').val();
		
		var inputBody = { userFirstName : fin ,userLastName:ln,userEmail:email,userName:signUpUserName,userPassword:signUpPassword};
		//alert(inputBody);
		var inputBodyJson = JSON.stringify(inputBody);
		//alert(inputBodyJson);
		$.ajax({
		    url: "keychain-ui/signupprofile",
		    type: 'POST',
		    dataType: 'json',
		    contentType: "application/json; charset=utf-8",
		    data: inputBodyJson,
		    success: function(data) {
		        alert("SignUp Successful, Please Login Using Credentials !!!");
		        ResetSignUp();
		    }
		});
	}
}

function ResetSignUp()
{
	$('#fin').val("");
	$('#ln').val("");
	$('#email').val("");
	$('#userName').val("");
	$('#pass').val("");
	$('#rpass').val("");
	$('#signUpUserName').val("");
	$('#signUpPassword').val("");
	
}