package com.nataniel.api.camel;

import com.nataniel.api.MailBoxLayerIntegration;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;

import javax.servlet.ServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class MailServiceRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("cxfrs:bean:server")
		.process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				Message inMessage = exchange.getIn();
				org.apache.cxf.message.Message cxfMessage = inMessage.getHeader(CxfConstants.CAMEL_CXF_MESSAGE, org.apache.cxf.message.Message.class);
		        ServletRequest request = (ServletRequest) cxfMessage.get("HTTP.REQUEST");
		        String path = inMessage.getHeader(Exchange.HTTP_PATH, String.class);
				String nome = request.getParameter("email");
				if (nome == null) {
					Response r = Response.status(404).entity("Email nao pode ser nulo " + path).build();
					throw new WebApplicationException(r);
				} else {
					MailBoxLayerIntegration test = new MailBoxLayerIntegration(nome);
					exchange.getOut().setBody(test.validate());
				}
			}
			
	});
	}
}