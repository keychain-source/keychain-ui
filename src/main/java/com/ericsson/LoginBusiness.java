package com.ericsson;

import java.sql.SQLException;

import com.ericsson.dao.AzureDBConn;
import com.ericsson.dto.response.LoginResponseDTO;
import com.ericsson.vo.UserProfileVO;

public class LoginBusiness {
	
	public LoginResponseDTO login(String userName,String password){
		LoginResponseDTO responseDto = new LoginResponseDTO();

		try {
			AzureDBConn azureDb = new AzureDBConn();
			UserProfileVO userprofileVo = azureDb.searchUserProfile(userName);
			if (null != userprofileVo && password.equals(userprofileVo.getUserPassword())){
				responseDto.setStatus("success");
			}
			else{
				responseDto.setStatus("error");
				responseDto.setErrorCode("wrong_credentails");
				responseDto.setErrorDescription("Please Check the provided Credentails !!! ");
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseDto.setStatus("error");
			responseDto.setErrorCode("unexpected_error");
			responseDto.setErrorDescription("Internal Server Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseDto.setStatus("error");
			responseDto.setErrorCode("unexpected_error");
			responseDto.setErrorDescription("Internal Server Error");
		}
		
		return responseDto;
	}

}
