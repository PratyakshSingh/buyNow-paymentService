package com.example.paymentservice.paymentGateways;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class RazorPayPG implements PaymentGateway {
    public RazorpayClient razorpayClient;

    public RazorPayPG(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }
    @Override
    public String generatePaymentLink(String email, String phoneNumber, Long amount, String orderId) {
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",true);
        paymentLinkRequest.put("expire_by",1738064430);
        paymentLinkRequest.put("reference_id",orderId);
        paymentLinkRequest.put("description","Payment for order no " + orderId);
        JSONObject customer = new JSONObject();
        customer.put("name","Pratyaksh Singh");
        customer.put("contact","+918171275730");
        customer.put("email","pratyaksh.singh8171@scaler.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",false);
        JSONObject notes = new JSONObject();
        notes.put("Notes","Payment for your Amazon Order");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://www.scaler.com");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink paymentLink = null;
        try {
            paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
        return paymentLink.get("short_url").toString();
    }
}
