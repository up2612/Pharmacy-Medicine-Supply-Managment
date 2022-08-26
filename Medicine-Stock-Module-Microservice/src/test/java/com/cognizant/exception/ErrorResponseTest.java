package com.cognizant.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)

@SpringBootTest
public class ErrorResponseTest {

	

	@SuppressWarnings("unused")
	private HttpStatus status;
	@SuppressWarnings("unused")
	private String message;
	
	@InjectMocks
	ErrorResponse errorresponse;
	
	@Test
	public void setterAndGetterForStatusTest()
	{
		errorresponse.setStatus(HttpStatus.ACCEPTED);
		assertThat(errorresponse.getStatus().equals(HttpStatus.ACCEPTED));
	}
	@Test
	public void setterAndGetterForMessageTest()
	{
		errorresponse.setMessage("Invalid..!!");;
		assertThat(errorresponse.getMessage().equalsIgnoreCase("Invalid..!!"));
	}
	@Test
	public void allArgsConstructor()
	{
		errorresponse=new ErrorResponse(HttpStatus.ACCEPTED,"Invalid..!!");
	}
	@Test
	public void NoArgsConstructor()
	{
		errorresponse=new ErrorResponse();
	}
	
}
