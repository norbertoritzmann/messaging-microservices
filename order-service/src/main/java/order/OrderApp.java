package order;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import common.Order;
import common.OrderStatus;
import common.OrderType;
import common.Product;
import common.Shipment;
import common.ShipmentType;


@SpringBootApplication
@EnableBinding(Source.class)
public class OrderApp {

	protected Logger logger = Logger.getLogger(OrderApp.class.getName());
	
	private int index = 0;
	
	public static void main(String[] args) {
		SpringApplication.run(OrderApp.class, args);
	}

	@Bean
	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
	public MessageSource<Order> orderSource() {
		return () -> {
			Order o = new Order(index++, OrderType.PURCHASE, LocalDateTime.now(), OrderStatus.NEW, new Product("Example#2"), new Shipment(ShipmentType.SHIP));
			logger.info("Sending order: " + o);
			return new GenericMessage<>(o); 
		};
	}
	
	@Bean
	public AlwaysSampler defaultSampler() {
	  return new AlwaysSampler();
	}
	
}
