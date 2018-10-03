package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.utils.Builder;

/**
 * Builder for direct pre approval by day interval list
 * This class is responsible for building the attributes for list direct pre approval by day interval
 *
 * @author PagSeguro Internet Ltda.
 */
public final class DirectPreApprovalByDayIntervalListBuilder implements Builder<DirectPreApprovalByDayIntervalList> {

    private Integer page = null;
    private Integer maxPageResults = null;
    private Integer interval = null;

    /**
     * Set the direct pre approval by day interval page to filter
     *
     * @param page Page number
     * @return Builder for direct pre approval by day interval list
     * @see DirectPreApprovalByDayIntervalList#getPage()
     */
    public DirectPreApprovalByDayIntervalListBuilder withPage(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * Set the direct pre approval by day interval max page results to filter
     *
     * @param maxPageResults Max number of results returned in each page
     * @return Builder for direct pre approval by day interval list
     * @see DirectPreApprovalByDayIntervalList#getMaxPageResults()
     */
    public DirectPreApprovalByDayIntervalListBuilder withMaxPageResults(Integer maxPageResults) {
        this.maxPageResults = maxPageResults;
        return this;
    }

    /**
     * Set the direct pre approval by day interval interval to filter
     *
     * @param interval Interval (in days)
     * @return Builder for direct pre approval by day interval list
     * @see DirectPreApprovalByDayIntervalList#getInterval()
     */
    public DirectPreApprovalByDayIntervalListBuilder withInterval(Integer interval) {
        this.interval = interval;
        return this;
    }

    /**
     * Build the direct pre approval by day interval list
     *
     * @return DirectPreApprovalByDayIntervalList build
     */
    @Override
    public DirectPreApprovalByDayIntervalList build() { return new DirectPreApprovalByDayIntervalListBuilder.SimpleDirectPreApprovalByDayIntervalList(this); }

    private static class SimpleDirectPreApprovalByDayIntervalList implements DirectPreApprovalByDayIntervalList {
        private final DirectPreApprovalByDayIntervalListBuilder directPreApprovalByDayIntervalListBuilder;

        public SimpleDirectPreApprovalByDayIntervalList(DirectPreApprovalByDayIntervalListBuilder directPreApprovalByDayIntervalListBuilder) {
            this.directPreApprovalByDayIntervalListBuilder = directPreApprovalByDayIntervalListBuilder;
        }

        @Override
        public Integer getPage() {
            return directPreApprovalByDayIntervalListBuilder.page;
        }

        @Override
        public Integer getMaxPageResults() {
            return directPreApprovalByDayIntervalListBuilder.maxPageResults;
        }

        @Override
        public Integer getInterval() {
            return directPreApprovalByDayIntervalListBuilder.interval;
        }
    }
}
