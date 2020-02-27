function dasServer(searchType){
	//alert("Vault");
	$('#dasHosts').hide();
	//$('#progressImgOidc').show();
	
	$.getJSON('DasStatusServlet',{siteName:searchType}, function(data)
	{
		$('#dasHosts').show();
		//$('#progressImgOidc').hide();
		  
		  var availableHosts = '<table cellpadding="0.5" border=0 bgcolor=#003399 width="100%"  style="font-family:verdana;">';
		  availableHosts = availableHosts + "<tr nowrap height=10px style=color:white>";
		  availableHosts = availableHosts + "<td colspan=6><table border=1 width=100%><tr><td>DAS</td>";
		  /* availableHosts = availableHosts + "<td> Status Check Time : </td>"; */
		  availableHosts = availableHosts + "<td>" + data.lastRunTime + " <button onClick=dasServer('"+ searchType +"')> Refresh </button></td></tr></table></td>";
		  availableHosts = availableHosts + "</tr>";
		  availableHosts = availableHosts + '<tr nowrap height=10px style=color:white>';
		  /* availableHosts = availableHosts + '<td>Select</td><td>Node</td>'; */
		  availableHosts = availableHosts + '<td>Node</td>';
          availableHosts = availableHosts + '<td>System Status</td><td>Server Status</td><td>Agent Status</td></tr>';
		  
			$.each(data.sites, function(index, value) {
				availableHosts = availableHosts + "<tr nowrap height=10px bgcolor=white>";
				/* availableHosts = availableHosts + "<td> <input type='checkbox' onclick=selectedSite(this,'" + value.host +"')></td>"; */
				availableHosts += "<td>" + value.hostName + "</td>";
				//availableHosts += "<td>" + value.systemStatus + "</td>";
				
				if (value.systemStatus == 'Running' || value.systemStatus == 'running'){
					availableHosts += "<td>" + value.systemStatus + "</td>";
				}
				else{
					availableHosts += "<td style='background-color:red;'>" + value.systemStatus + "</td>";
				}
				
				if (value.iamDasStatus == 'Running'){
					availableHosts += "<td>" + value.iamDasStatus + "</td>";
				}
				else{
					availableHosts += "<td style='background-color:red;'>" + value.iamDasStatus + "</td>";
				}

				if (value.agentReportStatus=='ERROR' || value.agentReportStatus=='Halted'){
					availableHosts += '<td style="background-color:red;">';
					//availableHosts = availableHosts + '<div class="tooltip"> '+ value1.agentReportStatus + '<span class="tooltiptext"> '+ value1.agentReport + '</span></div>';
					availableHosts = availableHosts + "<a href='#' onclick=errorInfo("+value.agentReport+")>" + value.agentReportStatus+"</a>";
					availableHosts += '</td>';
				}
				else{
					availableHosts += '<td>' + value.agentReportStatus + '</td>';
				}
				
				availableHosts += "</tr>";
			});
			availableHosts = availableHosts + "</table>";
			$('#dasHosts').html(availableHosts);
		});
}