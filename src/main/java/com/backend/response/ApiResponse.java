package com.backend.response;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<M> {
	
	private String mensaje;
	private int status;
	private M data;

}