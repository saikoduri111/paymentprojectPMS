package com.user.validate.user.service;



import com.user.validate.user.exception.InvalidTransactionIdException;
import com.user.validate.user.model.Transaction;
import com.user.validate.user.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.Currency;
import java.util.Optional;



//import java.util.List;



@Service
public class TransactionService {



    @Autowired
    TransactionRepository transactionRepository;



    @Autowired
    CustomerService customerService;



    @Autowired
    CurrencyService currencyService;



    @Autowired
    TransferTypeService transferTypeService;



    Status status = new Status();



//    public Transaction setTransactionDetails(Transaction transaction) throws Exception {
//
//        Customers customer = customerService.fetchCustomerDetails(transaction.getCustomerId().getCustomerId());
//        float updatedAmount = customerService.updateCurrencyCodeValue(customer, transaction.getCurrencyCode().getCurrencyCode(), transaction.getInrAmount());
//        customer.setClearBalance(transaction.getTransferFees() + customer.getClearBalance());
//        float amountAfterTax = customerService.updateBalance(customer.getCustomerId(), updatedAmount);
//
////        if (Objects.equals(status.getMessage(), "Unsuccessfull")) {
////
////            throw new Exception("Error Occurred While Updating");
////        }
//
//        return transactionRepository.save(transaction);
//
//    }



    public Transaction getTransactionDetails(int transactionId) throws InvalidTransactionIdException {



        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if (transaction.isEmpty()) throw new InvalidTransactionIdException("Invalid Transaction Id");
        else return transaction.get();



    }



    public Transaction setTransactionDetails(Transaction transaction) {



//        TransferType transferType = transaction.getTransferTypeCode();
//        System.out.println(transferType.getTransferTypeCode())



        try {



            float conversionRate = currencyService.getConversionRate(transaction.getCurrency().getCurrencyCode());
            float amount = transaction.getInrAmount();
            double tax = (amount * conversionRate) * 0.025;
            transaction.setTransferFees((float)tax);
            double totalAmount = (amount * conversionRate) + tax;
            float clearBalance = customerService.fetchCustomerDetails(transaction.getCustomer().getCustomerId()).getClearBalance();
            double totalClearBalance = clearBalance - totalAmount;
            transaction.getCustomer().setClearBalance((float)totalClearBalance);
            customerService.updateBalance(transaction.getCustomer().getCustomerId(), (float)totalClearBalance);
            transaction.getCustomer().setClearBalance((float) totalClearBalance);
            transactionRepository.save(transaction);
//            status.setMessage("Successfully Inserted");
            return transactionRepository.findById(transaction.getTransactionId()).get();



        } catch (Exception e) {



            status.setMessage("Unsuccessful");
            return null;



        }



    }



}