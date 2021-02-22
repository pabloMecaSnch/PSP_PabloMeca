package com.example.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.demo.xml.moviles.*;

@Endpoint
public class MovilEndpoint {

	private static final String NAMESPACE_URI = "http://www.demo.com/xml/moviles";
	private MovilRepository MovilRepository;

	@Autowired
	public MovilEndpoint(MovilRepository MovilRepository) {
		this.MovilRepository = MovilRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "MovilDetailsRequest")
	@ResponsePayload
	public MovilDetailsResponse getMovil(@RequestPayload MovilDetailsRequest request) {
		MovilDetailsResponse response = new MovilDetailsResponse();
		response.setMovil(MovilRepository.findMovil(request.getNombre()));

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "MovilBrandRequest")
	@ResponsePayload
	public MovilBrandResponse getMovil(@RequestPayload MovilBrandRequest request) {
		MovilBrandResponse response = new MovilBrandResponse();
		response.setMarca(MovilRepository.findMovil(request.getNombre()).getMarca());
		

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "MovilSizeRequest")
	@ResponsePayload
	public MovilSizeResponse getMovil(@RequestPayload MovilSizeRequest request) {
		MovilSizeResponse response = new MovilSizeResponse();
		response.setTamano(MovilRepository.findMovil(request.getNombre()).getTamano());
		

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "MovilCaracteristicasRequest")
	@ResponsePayload
	public MovilCaracteristicasResponse getMovil(@RequestPayload MovilCaracteristicasRequest request) {
		MovilCaracteristicasResponse response = new MovilCaracteristicasResponse();
		response.setCaracteristicas(MovilRepository.findCaracteristicas(request.getNombre()));

		return response;
	}
}