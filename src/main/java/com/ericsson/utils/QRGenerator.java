package com.ericsson.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.json.JSONObject;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRGenerator {

	/*public static void main(String[] args) throws WriterException, IOException {
		String qrCodeText = "http://10.253.72.237:8080/iammonitor/TokenServlet?token=generate";
		String filePath = "C:\\git\\iam_ms\\keychain\\qrcodes\\JD.png";
		int size = 125;
		String fileType = "png";
		File qrFile = new File(filePath);
		createQRImage(qrFile, qrCodeText, size, fileType);
		System.out.println("DONE");
	}*/

	public String createQRImage() {
		
		//ConfigManager congManager = new ConfigManager();
		String filePath = null;//congManager.getTestProp();//ConfigManager.getProperty("qrcodes.path");//"C:\\git\\iam_ms\\keychain\\qrcodes\\JD.png";
		System.out.println("File Path : " + filePath);
		int size = 250;
		String fileType = "png";
		String qrId = KeychainUtils.getAlphaNumericString();
		File qrFile = new File(filePath + File.separator + qrId + ".png");
		//String qrCodeText = "http://10.255.254.237:8080/keychain-ws/ValidateQRServlet?qrCode=" + qrId;
		String qrCodeText = qrId;
		//String qrCodeUrl = "http://10.255.254.237:8080/qrcodes/" + qrFile.getName();
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix;
		try {
			byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
			// Make the BufferedImage that are to hold the QRCode
			int matrixWidth = byteMatrix.getWidth();
			BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);

			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, matrixWidth, matrixWidth);
			// Paint and save the image using the ByteMatrix
			graphics.setColor(Color.BLACK);

			for (int i = 0; i < matrixWidth; i++) {
				for (int j = 0; j < matrixWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
			try {
				ImageIO.write(image, fileType, qrFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return qrCodeText;
	}

}