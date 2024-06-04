package com.cashify.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

import com.cashify.response.ResponseBean;

public class CommonUtils {

	public static String currentTimeMillisToLocalTime(long currentTimeMillis) {
		try {
			LocalDateTime dateTime = Instant.ofEpochMilli(currentTimeMillis).atZone(ZoneId.systemDefault())
					.toLocalDateTime();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			return dateTime.format(formatter);
		} catch (Exception e) {
			throw new RuntimeException("Error converting currentTimeMillis to local time", e);
		}
	}

	public ResponseBean responseStructure(List<Object> allProd, long currentTimeMillis, String message,
			String serviceName, Integer page, Integer totalResult, Integer noOfResult) {
		ResponseBean responseStructure = null;
		String timeStamp = currentTimeMillisToLocalTime(currentTimeMillis);

		if (!allProd.isEmpty() && allProd.get(0) != null) {
			responseStructure = new ResponseBean();
			responseStructure.setStatus(UtilsConstants.SUCCESSFUL);
			responseStructure.setData(allProd.stream().map(obj -> (Object) obj).collect(Collectors.toList()));
			responseStructure.setMessage(message);
			responseStructure.setServiceName(serviceName);
			responseStructure.setTimeStamp(String.valueOf(timeStamp));
			responseStructure.setPage(page);
			responseStructure.setTotalResult(totalResult);
			responseStructure.setNoOfResult(noOfResult);
			responseStructure.setCode(200);
			return responseStructure;
		} else {
//			responseStructure.setCode(200);
			return responseStructure;

		}
	}

	public ResponseBean responseForFailed(long currentTimeMillis, String serviceName) {
		ResponseBean responseStructure = new ResponseBean();
		String timeStamp = currentTimeMillisToLocalTime(currentTimeMillis);
		ResponseBean productResponse = new ResponseBean();
		HttpStatus status = HttpStatus.NOT_FOUND;
		List<Object> response = new ArrayList<>();

		responseStructure.setStatus(UtilsConstants.SUCCESSFUL);

		responseStructure.setStatus("success");
		responseStructure.setData(response);
		responseStructure.setMessage(UtilsConstants.NO_RECORDS_FOUND);
		responseStructure.setServiceName(serviceName);
		responseStructure.setTimeStamp(String.valueOf(timeStamp));
		responseStructure.setPage(0);
		responseStructure.setTotalResult(0);
		responseStructure.setNoOfResult(0);
		productResponse.setCode(status.value());
		return responseStructure;

	}

}
