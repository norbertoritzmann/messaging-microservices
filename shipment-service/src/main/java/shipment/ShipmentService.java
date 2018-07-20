package shipment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import common.Order;
import common.Shipment;
import common.ShipmentType;

@Service
public class ShipmentService {
	
	private List<Shipment> shipments;
	
	public ShipmentService() {
		shipments = new ArrayList<>();
		shipments.add(new Shipment(1, ShipmentType.CAR, LocalDate.now(), 50));
		shipments.add(new Shipment(2, ShipmentType.PLANE, LocalDate.now(), 200));
		shipments.add(new Shipment(3, ShipmentType.SHIP, LocalDate.now(), 100));
		shipments.add(new Shipment(4, ShipmentType.TRAIN, LocalDate.now(), 20));
	}
	
	public Shipment processOrder(Order order) {
		return shipments.stream().filter(s -> s.getType().equals(order.getShipment().getType())).findAny().get();
	}

}
