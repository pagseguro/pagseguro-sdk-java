package br.com.uol.pagseguro.api.common.domain;

/**
 * Interface for Pre Approval Payment Method. This class contains all the payment data.
 *
 * @author PagSeguro Internet Ltda.
 */
public interface PreApprovalPaymentMethod {
    /**
     * Get type of payment
     * @return Type
     */
    String getType();

    /**
     * Get pre approval credit card data
     * @return PreApprovalCreditCard
     */
    PreApprovalCreditCard getPreApprovalCreditCard();
}
