package com.ericsson;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ericsson.dao.AzureDBConn;
import com.ericsson.dto.request.SignUpUserProfileRequestDTO;
import com.ericsson.dto.response.GenerateQrResponseDTO;
import com.ericsson.dto.response.GetQRRegistryResponseDTO;
import com.ericsson.dto.response.LinkProfileResponseDTO;
import com.ericsson.dto.response.LoginResponseDTO;
import com.ericsson.dto.response.QRScannedResponseDTO;
import com.ericsson.dto.response.SignUpResponseDTO;
import com.ericsson.utils.KeychainProperties;
import com.ericsson.utils.KeychainUtils;
import com.ericsson.utils.RestCall;
import com.ericsson.vo.UserProfileVO;

//@SpringBootApplication
@RestController
public class KeychainController {

	// @Value("${spring.application.name}")
	// private static final String template = "Hello, %s!";
	// private final AtomicLong counter = new AtomicLong();
	private static final Logger logger = LoggerFactory.getLogger(KeychainController.class);
	
	@Autowired
	KeychainProperties keychainProperties;

	@RequestMapping("/keychain-ui/generateqr")
	public GenerateQrResponseDTO generateQR(@RequestParam("requester_device_id") String requesterDeviceId) {
		GenerateQrResponseDTO responseDto = new GenerateQrResponseDTO();
		
		RestCall restCall = new RestCall();
		Map<String,String> queryParams = new HashMap<String,String>();
		
		logger.debug("Inside Geenerate QR ");
		queryParams.put("requester_device_id", requesterDeviceId);
		
		try {
			JSONObject generateQRResp = restCall.sendPostRestCall(keychainProperties.getKeychainWsUrl() + "/keychain-ws/generateqr", null, queryParams, null);
			logger.debug(generateQRResp.toString());
			JSONObject result = new JSONObject(generateQRResp.getString("result"));
			logger.debug(result.toString());
			responseDto.setQrCodeUrl(result.getString("qr_code_url"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseDto;

		/*
		 * return new GenerateQrResponseDTO(counter.incrementAndGet(),
		 * String.format(template, name));
		 */
	}

	@RequestMapping("/keychain-ui/checkqrregitry")
	public GetQRRegistryResponseDTO checkQRRegistry(@RequestParam("requester_device_id") String requesterDeviceId) {
		GetQRRegistryResponseDTO responseDto = new GetQRRegistryResponseDTO();
		Map<String,String> queryParams = new HashMap<String,String>();
		queryParams.put("requester_device_id", requesterDeviceId);
		RestCall restCall = new RestCall();
		try {
			JSONObject generateQRResp = restCall.sendPostRestCall(keychainProperties.getKeychainWsUrl() + "/keychain-ws/checkqrregistry", null, queryParams, null);
			JSONObject resultObj = new JSONObject(generateQRResp.getString("result"));
			logger.debug("CheckRegistry Response :: " + resultObj.toString());
			if (resultObj.has("user_id") && null != resultObj.get("user_id")){
				responseDto.setUser_id(String.valueOf(resultObj.get("user_id")));;	
			}
			else{
				responseDto.setUser_id("no_user_found");
			}
			if (resultObj.has("qr_registration_status") && null != resultObj.get("qr_registration_status")){
				responseDto.setQr_registration_status(resultObj.getString("qr_registration_status"));	
			}
			if (resultObj.has("qr_code") && null != resultObj.get("qr_code")){
				responseDto.setQr_code(resultObj.getString("qr_code"));	
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseDto;

		/*
		 * return new GenerateQrResponseDTO(counter.incrementAndGet(),
		 * String.format(template, name));
		 */
	}

	/*@RequestMapping("/keychain-ws/registerqr")
	public GenerateQrResponseDTO registerqr(@RequestBody RegisterQRRequestDTO registerQr,
			@RequestHeader("alapp_id") String animatedLoginAppId) {
		GenerateQrResponseDTO responseDto = new GenerateQrResponseDTO();
		QRGenerator generator = new QRGenerator();
		// responseDto.setQrCode(generator.createQRImage());
		responseDto.setQrCode("abcdef");
		return responseDto;

		
		 * return new GenerateQrResponseDTO(counter.incrementAndGet(),
		 * String.format(template, name));
		 
	}

	@RequestMapping("/keychain-ws/deregisterqr")
	public GenerateQrResponseDTO deRegisterqr(@RequestBody RegisterQRRequestDTO registerQr,
			@RequestHeader("alapp_id") String animatedLoginAppId) {
		GenerateQrResponseDTO responseDto = new GenerateQrResponseDTO();
		QRGenerator generator = new QRGenerator();
		// responseDto.setQrCode(generator.createQRImage());
		responseDto.setQrCode("abcdef");
		return responseDto;

		
		 * return new GenerateQrResponseDTO(counter.incrementAndGet(),
		 * String.format(template, name));
		 
	}*/

	@RequestMapping("/keychain-ui/linkprofile")
	public LinkProfileResponseDTO registerprofile(@RequestParam("requester_device_id") String requeterDeviceId,
			@RequestParam("user_name") String userName,@RequestParam("password") String password) {
		RestCall restCall = new RestCall();
		Map<String,String> queryParams = new HashMap<String,String>();
		
		logger.debug("Inside Geenerate QR ");
		queryParams.put("requester_device_id", requeterDeviceId);
		queryParams.put("user_name", userName);
		LinkProfileResponseDTO response = new LinkProfileResponseDTO();
		try {
			LoginBusiness login = new LoginBusiness();
			LoginResponseDTO loginRespDTO = login.login(userName, password);
			if ("success".equals(loginRespDTO.getStatus())){
				logger.debug("User Authenticated successfully !!! ");
				JSONObject linkProfileResp = restCall.sendPostRestCall(keychainProperties.getKeychainWsUrl() + "/keychain-ws/registerprofile", null, queryParams, null);
				logger.debug(linkProfileResp.toString());
				JSONObject result = new JSONObject(linkProfileResp.getString("result"));
				logger.debug(result.toString());
				response.setStatus(String.valueOf(linkProfileResp.get("status")));
			}
			else{
				logger.debug("User Authentication failed !!! ");
				response.setStatus(loginRespDTO.getStatus());
				response.setErrorCode(loginRespDTO.getErrorCode());
				response.setErrorDescription(loginRespDTO.getErrorDescription());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return response;
	}

	@RequestMapping("/keychain-ui/signupprofile")
	public SignUpResponseDTO signUpProfile(@RequestBody SignUpUserProfileRequestDTO signUpDTO) {
		logger.debug("Controller Entry for SignUp Flow");
		SignUpResponseDTO responseDto = new SignUpResponseDTO();

		signUpDTO.setUserId(KeychainUtils.generateUserId());
		signUpDTO.setCreateTimestamp(KeychainUtils.getCurrentTimestamp());

		logger.debug(signUpDTO.toString());

		try {
			AzureDBConn azureDb = new AzureDBConn();
			azureDb.signUpUser(signUpDTO);
			responseDto.setUserId(signUpDTO.getUserId());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responseDto;

		/*
		 * return new GenerateQrResponseDTO(counter.incrementAndGet(),
		 * String.format(template, name));
		 */
	}
	
	
	@RequestMapping("/keychain-ui/loginprofile")
	public LoginResponseDTO loginProfile(@RequestParam(value = "userName") String userName,
			@RequestParam(value = "password") String password) {
		logger.debug("Controller Entry for Login Flow");
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
		logger.debug(responseDto.toString());
		return responseDto;

		/*
		 * return new GenerateQrResponseDTO(counter.incrementAndGet(),
		 * String.format(template, name));
		 */
	}
	
	@RequestMapping("/keychain-ui/qrscanned")
	public QRScannedResponseDTO qrScanned(@RequestParam(value = "qrcode") String qrCode) {
		logger.debug("Controller Entry for QR Scanned Flow");
		QRScannedResponseDTO responseDto = new QRScannedResponseDTO();
		RestCall restCall = new RestCall();
		JSONObject body = new JSONObject();
		try {
			body.put("qr_code", qrCode);
			body.put("qr_code_status", "scanned");
			restCall.sendPostRestCall(keychainProperties.getKeychainWsUrl() + "/keychain-ws/scanqr", null, null, body.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.debug(responseDto.toString());
		return responseDto;

		/*
		 * return new GenerateQrResponseDTO(counter.incrementAndGet(),
		 * String.format(template, name));
		 */
	}

}