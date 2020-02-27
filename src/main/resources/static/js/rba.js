function rbaServer(searchType){

	//alert("RBA search in JS ");
	$.getJSON('RBAMonitorServlet',{siteName:searchType}, function(data)
	{
		$('#rbaHosts').show();
		$('#progressImgRba').hide();
		var availableHosts = '<table cellpadding="0.5" border=0 bgcolor=#003399 width="100%"  style="font-family:verdana;">';
		availableHosts = availableHosts + "<tr align=center style=color:white><td colspan='3' >RBA Overall</td></tr>";
		$.each(data.rbaStatus, function(index, value) {
			availableHosts = availableHosts + "<tr><td><table>";
			$.each(value.rbaStatus, function(index, value1) {
				if (index==0){
					availableHosts = availableHosts + "<tr bgcolor=#b3cbff><td colspan='8'>RBA Node</td>";
					availableHosts = availableHosts + "</tr>";
					availableHosts = availableHosts + '<tr nowrap height=10px style=color:white>';
					availableHosts = availableHosts + '<td>Node</td>';
			        availableHosts = availableHosts + '<td>User Activity Status</td><td>Risk Assessment Status</td>';
			        availableHosts = availableHosts + '<td>User Activity Application</td><td>User Activity Health</td>';
			        availableHosts = availableHosts + '<td>Risk Assessment Application</td><td>Risk Assessment Health</td>';
			        availableHosts = availableHosts + '<td>Agent Status</td>';
			        availableHosts = availableHosts + '</tr>';
				}
				availableHosts = availableHosts + '<tr nowrap height=10px bgcolor=white>';
				availableHosts = availableHosts + '<td>' + value1.hostName + '</td>';
				if (value1.userActivityStatus=='ERROR' || value1.userActivityStatus=='Halted'){
					availableHosts += '<td style="background-color:red;">' + value1.userActivityStatus + '</td>';
				}
				else{
					availableHosts += '<td>' + value1.userActivityStatus + '</td>';	
				}
				
				if (value1.userActivityStatus=='ERROR' || value1.userActivityStatus=='Halted'){
					availableHosts += '<td style="background-color:red;">' + value1.userActivityStatus + '</td>';
				}
				else{
					availableHosts += '<td>' + value1.riskAssessmentStatus + '</td>';	
				}
				
				if (value1.userActivityHealthStatus=='ERROR' || value1.userActivityHealthStatus=='Halted'){
					availableHosts += '<td style="background-color:red;">' + value1.userActivityStatus + '</td>';
				}
				else{
					availableHosts += '<td>' + value1.userActivityHealthStatus + '</td>';
				}
				
				if (value1.userActivityCassandraHealthStatus=='ERROR' || value1.userActivityCassandraHealthStatus=='Halted'){
					availableHosts += '<td style="background-color:red;">' + value1.userActivityCassandraHealthStatus + '</td>';
				}
				else{
					availableHosts += '<td>' + value1.userActivityCassandraHealthStatus + '</td>';
				}
				
				if (value1.riskAssessmentHealthStatus=='ERROR' || value1.riskAssessmentHealthStatus=='Halted'){
					availableHosts += '<td style="background-color:red;">' + value1.riskAssessmentHealthStatus + '</td>';
				}
				else{
					availableHosts += '<td>' + value1.riskAssessmentHealthStatus + '</td>';
				}
				
				if (value1.riskAssessmentCassandraHealthStatus=='ERROR' || value1.riskAssessmentCassandraHealthStatus=='Halted'){
					availableHosts += '<td style="background-color:red;">' + value1.riskAssessmentCassandraHealthStatus + '</td>';
				}
				else{
					availableHosts += '<td>' + value1.riskAssessmentCassandraHealthStatus + '</td>';
				}
				if (value1.agentReportStatus=='ERROR' || value1.agentReportStatus=='Halted'){
					availableHosts += '<td style="background-color:red;">';
					//availableHosts = availableHosts + '<div class="tooltip"> '+ value1.agentReportStatus + '<span class="tooltiptext"> '+ value1.agentReport + '</span></div>';
					availableHosts = availableHosts + "<a href='#' onclick=errorInfo("+value1.agentReport+")>" + value1.agentReportStatus+"</a>";
					availableHosts += '</td>';
				}
				else{
					availableHosts += '<td>' + value1.agentReportStatus + '</td>';
				}
				
				availableHosts = availableHosts + '</tr>';
				
		   });
			availableHosts = availableHosts + "</table></td></tr>";
			availableHosts = availableHosts + "<tr><td><table width=100%>";
			$.each(value.cassandraStatus, function(index, value1) {
				if (index==0){
					availableHosts = availableHosts + "<tr bgcolor=#b3cbff><td colspan=3>CASSANDRA</td>";
					availableHosts = availableHosts + "</tr>";
					availableHosts = availableHosts + '<tr nowrap height=10px style=color:white>';
					availableHosts = availableHosts + '<td>Node</td>';
			        availableHosts = availableHosts + '<td>Server Status</td><td>Agents Status</td></tr>';  
				}
				availableHosts = availableHosts + '<tr nowrap height=10px bgcolor=white>';
				availableHosts = availableHosts + '<td>' + value1.hostName + '</td>';
				if (value1.cassandraStatus=='ERROR' || value1.cassandraStatus=='Halted'){
					availableHosts += '<td style="background-color:red;">' + value1.cassandraStatus + '</td>';
				}
				else{
					availableHosts = availableHosts + '<td>' + value1.cassandraStatus + '</td>';
				}
				
				/*if (value1.agentReportStatus=='ERROR' || value1.agentReportStatus=='Halted'){
					availableHosts += '<td style="background-color:red;">';
					//availableHosts = availableHosts + '<div class="tooltip"> '+ value1.agentReportStatus + '<span class="tooltiptext"> '+ value1.agentReport + '</span></div>';
					availableHosts = availableHosts + "<a href='#' onclick=errorInfo("+value1.agentReport+")>" + value1.agentReportStatus+"</a>";
					availableHosts += '</td>';
				}
				else{
					availableHosts += '<td>' + value1.agentReportStatus + '</td>';
				}*/
				
				if (value1.agentReportStatus=='ERROR' || value1.agentReportStatus=='Halted'){
					availableHosts += '<td style="background-color:red;">';
					//availableHosts = availableHosts + '<div class="tooltip"> '+ value1.agentReportStatus + '<span class="tooltiptext"> '+ value1.agentReport + '</span></div>';
					availableHosts = availableHosts + "<a href='#' onclick=errorInfo("+value1.agentReport+")>" + value1.agentReportStatus+"</a>";
					availableHosts += '</td>';
				}
				else{
					availableHosts += '<td>' + value1.agentReportStatus + '</td>';
				}
				
				availableHosts = availableHosts + '</tr>';
		   });
			availableHosts = availableHosts + "</table></td></tr>";
			availableHosts = availableHosts + "<tr><td><table width=100%>";
		   $.each(value.kafkaStatus, function(index, value1) {
			  if (index==0){
				  availableHosts = availableHosts + "<tr bgcolor=#b3cbff><td colspan=3>KAFKA</td>";
				  availableHosts = availableHosts + "</tr>";
				  availableHosts = availableHosts + '<tr nowrap height=10px style=color:white>';
				  availableHosts = availableHosts + '<td>Node</td>';
		          availableHosts = availableHosts + '<td>Kafka Status</td><td>Zookeeper Status</td></tr>';
			  }
			  availableHosts = availableHosts + '<tr nowrap height=10px bgcolor=white>';
			  availableHosts = availableHosts + '<td>' + value1.hostName + '</td>';
			  if (value1.kafkaStatus=='ERROR' || value1.kafkaStatus=='Halted'){
					availableHosts += '<td style="background-color:red;">' + value1.kafkaStatus + '</td>';
			  }
			  else{
					availableHosts = availableHosts + '<td>' + value1.kafkaStatus + '</td>';
			  }
			  
			  if (value1.zookeeperStatus=='ERROR' || value1.zookeeperStatus=='Halted'){
					availableHosts += '<td style="background-color:red;">' + value1.zookeeperStatus + '</td>';
			  }
			  else{
				  availableHosts = availableHosts + '<td>' + value1.zookeeperStatus + '</td>';
			  }
			  
			  
			  availableHosts += '</tr>';
			  
		   });
		   availableHosts = availableHosts + "</table></td></tr>";
		});
		availableHosts = availableHosts + "</table>";
		$('#rbaHosts').html(availableHosts);
	});
}


function rbaApiStatus(searchType){
	//alert(searchType);
	$('#progressImgRbaApiPerformance').show();
	$('#rbaApiPerformance').hide();
	  $.getJSON('RBAMonitorServlet',{siteName:searchType, operation:"apiStatus"}, function(data)
	  {
		  $('#progressImgRbaApiPerformance').hide();
		  $('#rbaApiPerformance').show();
		  var apiPerformance = '<table cellpadding="1" border=0 bgcolor=#003399 width="100%"  style="font-family:verdana;">';
		  apiPerformance += '<tr nowrap height=12px style=color:white><td>' + searchType + '</td><td><button onclick="rbaApiStatus(' + searchType + ')">Check Again</button></td></tr>';
		  
			apiPerformance += '<tr nowrap height=12px style=color:white><td>Node Name</td><td>User Activity</td></tr>';  
			$.each(data.rbaApiStatus, function(index, value) {
				apiPerformance += '<tr nowrap height=10px bgcolor=white><td>';
				apiPerformance = apiPerformance + '<div class="tooltip"> '+ value.hostName + '<span class="tooltiptext"> '+ value.hostAddress + '</span></div></td>';

				if (value.userActivityApiStatus=='ERROR' || value.userActivityApiStatus=='Halted'){
					apiPerformance += '<td style="background-color:red;">' + value.userActivityApiStatus + '</td>';
				}
				else{
					apiPerformance += '<td>' + value.userActivityApiStatus + '</td>';
				}

				apiPerformance += "</tr>";
			});

			apiPerformance += '</table>';
			apiPerformance += '<br/><br/>';
			//alert(apiPerformance);
			//if (siteName.indexOf("ece") !== -1){
				$('#rbaApiPerformance').html(apiPerformance);
			//}
			
	  });
}

function errorInfo(info){
	alert("OVO Status : " + info.ovoStatus + " --- Splunk status : " + info.splunkStatus + " --- Appd Status : " + info.appdStatus);
}