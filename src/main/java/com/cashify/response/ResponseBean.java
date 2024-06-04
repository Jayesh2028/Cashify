package com.cashify.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBean {
	private String status;
	private List<Object> data;
	private String message;
	private String serviceName;
	private String timeStamp;
	private Integer page;
	private Integer totalResult;
	private Integer noOfResult;
	private Integer code;

	public ResponseBean(String status, List<Object> data, String message, String serviceName, String timeStamp,
			Integer page, Integer totalResult, Integer noOfResult, Integer code) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
		this.serviceName = serviceName;
		this.timeStamp = timeStamp;
		this.page = page;
		this.totalResult = totalResult;
		this.noOfResult = noOfResult;
		this.code = code;
	}

	public ResponseBean() {
		// TODO Auto-generated constructor stub
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(Integer totalResult) {
		this.totalResult = totalResult;
	}

	public Integer getNoOfResult() {
		return noOfResult;
	}

	public void setNoOfResult(Integer noOfResult) {
		this.noOfResult = noOfResult;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
