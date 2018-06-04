package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.PaymentItem;
import br.com.uol.pagseguro.api.common.domain.builder.PaymentItemBuilder;
import br.com.uol.pagseguro.api.utils.Builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for direct pre approval charge.
 *
 * @author PagSeguro Internet Ltda.
 */
public class DirectPreApprovalRequestChargeBuilder implements Builder<DirectPreApprovalRequestCharge>{

    private String preApprovalCode = null;
    private String reference = null;
    private String senderHash = null;
    private String senderIp = null;
    private List<PaymentItem> items = new ArrayList<PaymentItem>();

    /**
     * Set Pre Approval Code of pre approval edition
     *
     * @param preApprovalCode Pre Approval Code
     * @return Builder for Pre Approval Edition
     * @see DirectPreApprovalRequestCharge#getPreApprovalCode()
     */
    public DirectPreApprovalRequestChargeBuilder withCode(String preApprovalCode) {
        this.preApprovalCode = preApprovalCode;
        return this;
    }

    /**
     * Set Pre Approval Code of pre approval edition
     *
     * @param reference Pre Approval Code
     * @return Builder for Pre Approval Edition
     * @see DirectPreApprovalRequestCharge#getReference()
     */
    public DirectPreApprovalRequestChargeBuilder withReference(String reference) {
        this.reference = reference;
        return this;
    }

    /**
     * Set Pre Approval Code of pre approval edition
     *
     * @param senderHash Pre Approval Code
     * @return Builder for Pre Approval Edition
     * @see DirectPreApprovalRequestCharge#getSenderHash()
     */
    public DirectPreApprovalRequestChargeBuilder withSenderHash(String senderHash) {
        this.senderHash = senderHash;
        return this;
    }

    /**
     * Set Pre Approval Code of pre approval edition
     *
     * @param senderIp Pre Approval Code
     * @return Builder for Pre Approval Edition
     * @see DirectPreApprovalRequestCharge#getSenderIp()
     */
    public DirectPreApprovalRequestChargeBuilder withSenderIp(String senderIp) {
        this.senderIp = senderIp;
        return this;
    }

    /**
     * Add Item to pre approval charging
     *
     * @param item Item of Pre Approval charging
     * @return Builder for Pre Approval charging
     * @see DirectPreApprovalRequestCharge#getItems()
     */
    public DirectPreApprovalRequestChargeBuilder addItem(PaymentItem item) {
        this.items.add(item);
        return this;
    }

    /**
     * Add Item to pre approval charging
     *
     * @param paymentItemBuilder Builder for Item of Pre Approval charging
     * @return Builder for Pre Approval charging
     * @see DirectPreApprovalRequestCharge#getItems()
     */
    public DirectPreApprovalRequestChargeBuilder addItem(Builder<PaymentItem> paymentItemBuilder) {
        return addItem(paymentItemBuilder.build());
    }

    /**
     * Add Items to pre approval charging
     *
     * @param items Items of Pre Approval charging
     * @return Builder for Pre Approval charging
     * @see DirectPreApprovalRequestCharge#getItems()
     */
    public DirectPreApprovalRequestChargeBuilder addItems(Iterable<? extends PaymentItem> items) {
        for (PaymentItem item : items) {
            addItem(item);
        }
        return this;
    }

    /**
     * Build the Direct Pre Approval Charge
     *
     * @return Interface for Direct Pre Approval Charge
     * @see DirectPreApprovalRequestEdition
     */
    @Override
    public DirectPreApprovalRequestCharge build() { return new SimpleDirectPreApprovalRequestCharge(this); }

    /**
     * Implementation of {@amountPerPayment DirectPreApprovalRequestEdition and @updateSubscriptions DirectPreApprovalRequestEdition}
     */
    private static class SimpleDirectPreApprovalRequestCharge implements DirectPreApprovalRequestCharge {

        private final DirectPreApprovalRequestChargeBuilder directPreApprovalRequestChargeBuilder;

        private SimpleDirectPreApprovalRequestCharge(DirectPreApprovalRequestChargeBuilder directPreApprovalRequestChargeBuilder) {
            this.directPreApprovalRequestChargeBuilder = directPreApprovalRequestChargeBuilder;
        }

        @Override
        public String getPreApprovalCode() { return directPreApprovalRequestChargeBuilder.preApprovalCode; }

        @Override
        public String getReference() { return directPreApprovalRequestChargeBuilder.reference; }

        @Override
        public String getSenderHash() { return directPreApprovalRequestChargeBuilder.senderHash; }

        @Override
        public String getSenderIp() { return directPreApprovalRequestChargeBuilder.senderIp; }

        @Override
        public List<PaymentItem> getItems() { return directPreApprovalRequestChargeBuilder.items; }
    }
}
