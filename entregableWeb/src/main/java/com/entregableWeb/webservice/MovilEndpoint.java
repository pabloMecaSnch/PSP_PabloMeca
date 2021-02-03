package com.entregableWeb.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.entregableWeb.xml.moviles.*;

@Endpoint
public class MovilEndpoint {

	private static final String NAMESPACE_URI = "http://www.entregableWeb.com/xml/school";
	private MovilRepository MovilRepository;

	@Autowired
	public MovilEndpoint(MovilRepository MovilRepository) {
		this.MovilRepository = MovilRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "MovilDetailsRequest")
	@ResponsePayload
	public MovilDetailsResponse getMovil(@RequestPayload MovilDetailsRequest request) {
		MovilDetailsResponse response = new MovilDetailsResponse();
		response.setStudent(MovilRepository.findStudent(request.getName()));

		return response;
	}
}
