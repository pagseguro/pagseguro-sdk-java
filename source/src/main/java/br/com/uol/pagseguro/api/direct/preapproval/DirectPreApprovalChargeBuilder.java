package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.PaymentItem;
import br.com.uol.pagseguro.api.utils.Builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for direct pre approval charge.
 *
 * @author PagSeguro Internet Ltda.
 */
public class DirectPreApprovalChargeBuilder implements Builder<DirectPreApprovalCharge>{

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
     * @see DirectPreApprovalCharge#getPreApprovalCode()
     */
    public DirectPreApprovalChargeBuilder withCode(String preApprovalCode) {
        this.preApprovalCode = preApprovalCode;
        return this;
    }

    /**
     * Set Pre Approval Code of pre approval edition
     *
     * @param reference Pre Approval Code
     * @return Builder for Pre Approval Edition
     * @see DirectPreApprovalCharge#getReference()
     */
    public DirectPreApprovalChargeBuilder withReference(String reference) {
        this.reference = reference;
        return this;
    }

    /**
     * Set Pre Approval Code of pre approval edition
     *
     * @param senderHash Pre Approval Code
     * @return Builder for Pre Approval Edition
     * @see DirectPreApprovalCharge#getSenderHash()
     */
    public DirectPreApprovalChargeBuilder withSenderHash(String senderHash) {
        this.senderHash = senderHash;
        return this;
    }

    /**
     * Set Pre Approval Code of pre approval edition
     *
     * @param senderIp Pre Approval Code
     * @return Builder for Pre Approval Edition
     * @see DirectPreApprovalCharge#getSenderIp()
     */
    public DirectPreApprovalChargeBuilder withSenderIp(String senderIp) {
        this.senderIp = senderIp;
        return this;
    }

    /**
     * Add Item to pre approval charging
     *
     * @param item Item of Pre Approval charging
     * @return Builder for Pre Approval charging
     * @see DirectPreApprovalCharge#getItems()
     */
    public DirectPreApprovalChargeBuilder addItem(PaymentItem item) {
        this.items.add(item);
        return this;
    }

    /**
     * Add Item to pre approval charging
     *
     * @param paymentItemBuilder Builder for Item of Pre Approval charging
     * @return Builder for Pre Approval charging
     * @see DirectPreApprovalCharge#getItems()
     */
    public DirectPreApprovalChargeBuilder addItem(Builder<PaymentItem> paymentItemBuilder) {
        return addItem(paymentItemBuilder.build());
    }

    /**
     * Add Items to pre approval charging
     *
     * @param items Items of Pre Approval charging
     * @return Builder for Pre Approval charging
     * @see DirectPreApprovalCharge#getItems()
     */
    public DirectPreApprovalChargeBuilder addItems(Iterable<? extends PaymentItem> items) {
        for (PaymentItem item : items) {
            addItem(item);
        }
        return this;
    }

    /**
     * Build the Direct Pre Approval Charge
     *
     * @return Interface for Direct Pre Approval Charge
     * @see DirectPreApprovalEdition
     */
    @Override
    public DirectPreApprovalCharge build() { return new SimpleDirectPreApprovalCharge(this); }

    /**
     * Implementation of {@amountPerPayment DirectPreApprovalEdition and @updateSubscriptions DirectPreApprovalEdition}
     */
    private static class SimpleDirectPreApprovalCharge implements DirectPreApprovalCharge {

        private final DirectPreApprovalChargeBuilder directPreApprovalChargeBuilder;

        private SimpleDirectPreApprovalCharge(DirectPreApprovalChargeBuilder directPreApprovalChargeBuilder) {
            this.directPreApprovalChargeBuilder = directPreApprovalChargeBuilder;
        }

        @Override
        public String getPreApprovalCode() { return directPreApprovalChargeBuilder.preApprovalCode; }

        @Override
        public String getReference() { return directPreApprovalChargeBuilder.reference; }

        @Override
        public String getSenderHash() { return directPreApprovalChargeBuilder.senderHash; }

        @Override
        public String getSenderIp() { return directPreApprovalChargeBuilder.senderIp; }

        @Override
        public List<PaymentItem> getItems() { return directPreApprovalChargeBuilder.items; }
    }
}
