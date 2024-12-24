package com.chamodidh.market_pulse.service;

import com.chamodidh.market_pulse.entity.CustomerDetails;
import com.chamodidh.market_pulse.entity.Order;
import com.chamodidh.market_pulse.entity.Payment;
import com.chamodidh.market_pulse.repository.PaymentRepository;
import com.chamodidh.market_pulse.repository.UserRepository;
import com.chamodidh.market_pulse.utility.enums.ShipmentStatus;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@Transactional
public class PaymentServiceImp implements PaymentService {
    @Autowired
    StripeService stripeService;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShipmentService shipmentService;
    @Override
    public Charge managePayment(String token, double amount, long userId) throws StripeException {
        CustomerDetails customerDetails = userRepository.findById(userId).get().getCustomerDetails();
        Charge charge = stripeService.createCharge(token, amount);
        float orderamount = (float) amount;
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 14);

        Order order = orderService.addOrder(customerDetails,orderamount);
        Payment payment = addPayment(order,customerDetails, orderamount);

        shipmentService.addShipment(ShipmentStatus.PENDING,calendar.getTime(),order,customerDetails);
        return charge;



    }

    @Override
    public Payment addPayment(Order order, CustomerDetails customerDetails, float amount) {
      Payment payment = new Payment();
      payment.setPaymentDate(new Date());
      payment.setAmount(amount);
      payment.setCustomerDetails(customerDetails);
      payment.setOrder(order);
      return paymentRepository.save(payment);

    }


}
