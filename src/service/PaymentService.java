package service;

import dao.PaymentDAO;
import model.Payment;
import java.util.List;

public class PaymentService {
    private final PaymentDAO paymentDAO = new PaymentDAO();

    public Payment addPayment(Payment payment) {
        return paymentDAO.addPayment(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentDAO.getAllPayments();
    }
}
