package shipment;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.SendTo;

import common.Order;

@SpringBootApplication
@EnableBinding(Processor.class)
public class ShipmentApp {

	@Autowired
	private ShipmentService shipmentService;
	
	protected Logger logger = Logger.getLogger(ShipmentApp.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(ShipmentApp.class, args);
	}

	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Order processOrder(Order order) {
		logger.info("Processing order: " + order);
		order.setShipment(shipmentService.processOrder(order));
		logger.info("Output order: " + order);
		return order;
	}

	@Bean
	public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
	}

}
