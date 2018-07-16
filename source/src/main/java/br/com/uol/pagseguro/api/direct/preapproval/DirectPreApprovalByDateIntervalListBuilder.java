package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.DateRange;
import br.com.uol.pagseguro.api.common.domain.enums.DirectPreApprovalStatus;
import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for direct pre approval by date interval list
 * This class is responsible for building the attributes for list direct pre approval by date interval
 *
 * @author PagSeguro Internet Ltda.
 */
public final class DirectPreApprovalByDateIntervalListBuilder implements Builder<DirectPreApprovalByDateIntervalList> {

    private DateRange dateRange = null;
    private Integer page = null;
    private Integer maxPageResults = null;
    private String status = null;
    private String code = null;
    private String senderEmail = null;

    /**
     * Set the direct pre approval by date interval date range to filter
     *
     * @param dateRange DateRangeBuilder
     * @return Builder for direct pre approval by date interval list
     * @see DirectPreApprovalByDateIntervalList#getDateRange()
     */
    public DirectPreApprovalByDateIntervalListBuilder withDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
        return this;
    }

    /**
     * Set Date Range of direct pre approval by date interval
     *
     * @param dateRangeBuilder Builder for Date Range
     * @return Builder for direct pre approval by date interval
     * @see DirectPreApprovalByDateIntervalList#getDateRange()
     */
    public DirectPreApprovalByDateIntervalListBuilder withDateRange(Builder<DateRange> dateRangeBuilder) {
        return withDateRange(dateRangeBuilder.build());
    }

    /**
     * Set the direct pre approval by date interval page to filter
     *
     * @param page Page number
     * @return Builder for direct pre approval by date interval list
     * @see DirectPreApprovalByDateIntervalList#getPage()
     */
    public DirectPreApprovalByDateIntervalListBuilder withPage(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * Set the direct pre approval by date interval max page results to filter
     *
     * @param maxPageResults Max number of results returned in each page
     * @return Builder for direct pre approval by date interval list
     * @see DirectPreApprovalByDateIntervalList#getMaxPageResults()
     */
    public DirectPreApprovalByDateIntervalListBuilder withMaxPageResults(Integer maxPageResults) {
        this.maxPageResults = maxPageResults;
        return this;
    }

    /**
     * Set the direct pre approval by date interval status to filter
     *
     * @param status Direct Pre Approval Status
     * @return Builder for direct pre approval by date interval list
     * @see DirectPreApprovalByDateIntervalList#getStatus()
     */
    public DirectPreApprovalByDateIntervalListBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * Set the direct pre approval by date interval status to filter
     *
     * @param status Direct Pre Approval Status
     * @return Builder for direct pre approval by date interval list
     * @see DirectPreApprovalByDateIntervalList#getStatus()
     */
    public DirectPreApprovalByDateIntervalListBuilder withStatus(DirectPreApprovalStatus status) {
        this.status = status.getValue();
        return this;
    }

    /**
     * Set the direct pre approval by date interval status to filter
     *
     * @param code Pre Approval Request
     * @return Builder for direct pre approval by date interval list
     * @see DirectPreApprovalByDateIntervalList#getCode()
     */
    public DirectPreApprovalByDateIntervalListBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Set the direct pre approval by date interval sender email to filter
     *
     * @param senderEmail Sender Email
     * @return Builder for direct pre approval by date interval list
     * @see DirectPreApprovalByDateIntervalList#getSenderEmail()
     */
    public DirectPreApprovalByDateIntervalListBuilder withSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
        return this;
    }

    /**
     * Build the direct pre approval by date interval list
     *
     * @return DirectPreApprovalByDateIntervalList build
     */
    @Override
    public DirectPreApprovalByDateIntervalList build() { return new SimpleDirectPreApprovalByDateIntervalList(this); }

    private static class SimpleDirectPreApprovalByDateIntervalList implements DirectPreApprovalByDateIntervalList {
        private final DirectPreApprovalByDateIntervalListBuilder directPreApprovalByDateIntervalListBuilder;

        public SimpleDirectPreApprovalByDateIntervalList(DirectPreApprovalByDateIntervalListBuilder directPreApprovalByDateIntervalListBuilder) {
            this.directPreApprovalByDateIntervalListBuilder = directPreApprovalByDateIntervalListBuilder;
        }

        @Override
        public DateRange getDateRange() {
            return directPreApprovalByDateIntervalListBuilder.dateRange;
        }

        @Override
        public Integer getPage() {
            return directPreApprovalByDateIntervalListBuilder.page;
        }

        @Override
        public Integer getMaxPageResults() {
            return directPreApprovalByDateIntervalListBuilder.maxPageResults;
        }

        @Override
        public String getStatus() {
            return directPreApprovalByDateIntervalListBuilder.status;
        }

        @Override
        public String getCode() {
            return directPreApprovalByDateIntervalListBuilder.code;
        }

        @Override
        public String getSenderEmail() {
            return directPreApprovalByDateIntervalListBuilder.senderEmail;
        }
    }
}
