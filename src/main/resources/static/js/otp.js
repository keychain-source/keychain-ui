function otpServer(searchType){
	//alert("OIDC");
	$('#otpHosts').hide();
	//$('#progressImgOidc').show();
	
	$.getJSON('OtpStatusServlet',{siteName:searchType}, function(data)
	{
		$('#otpHosts').show();
		//$('#progressImgOidc').hide();
		  
		  var availableHosts = '<table cellpadding="0.5" border=0 bgcolor=#003399 width="100%"  style="font-family:verdana;">';
		  availableHosts = availableHosts + "<tr nowrap height=10px style=color:white>";
		  availableHosts = availableHosts + "<td colspan=6><table border=1 width=100%><tr><td>OTP</td>";
		  /* availableHosts = availableHosts + "<td> Status Check Time : </td>"; */
		  availableHosts = availableHosts + "<td>" + data.lastRunTime + " <button onClick=oidcServer('"+ searchType +"')> Refresh </button></td></tr></table></td>";
		  availableHosts = availableHosts + "</tr>";
		  availableHosts = availableHosts + '<tr nowrap height=10px style=color:white>';
		  /* availableHosts = availableHosts + '<td>Select</td><td>Node</td>'; */
		  availableHosts = availableHosts + '<td>Node</td>';
          availableHosts = availableHosts + '<td>System Status</td><td>Server Status</td></tr>';
		  
			$.each(data.sites, function(index, value) {
				availableHosts = availableHosts + "<tr nowrap height=10px bgcolor=white>";
				/* availableHosts = availableHosts + "<td> <input type='checkbox' onclick=selectedSite(this,'" + value.host +"')></td>"; */
				availableHosts += "<td>" + value.hostName + "</td>";
				availableHosts += "<td>" + value.systemStatus + "</td>";
				if (value.otpStatus == 'Running'){
					availableHosts += "<td>" + value.otpStatus + "</td>";
				}
				else{
					availableHosts += "<td style='background-color:red;'>" + value.otpStatus + "</td>";
				}

				availableHosts += "</tr>";
			});
			availableHosts = availableHosts + "</table>";
			$('#otpHosts').html(availableHosts);
		});
}