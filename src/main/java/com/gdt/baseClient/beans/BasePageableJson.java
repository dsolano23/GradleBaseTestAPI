package com.gdt.baseClient.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gdt.baseClient.utils.PaginationContext;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
//@ToString(exclude = {"logger"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasePageableJson extends AbstractBaseJson {
    protected Integer page;
    protected Integer limit;
    protected Integer total;
    @JsonIgnore
    private static Logger logger= LoggerFactory.getLogger(BasePageableJson.class);

    public boolean isPaginated() {
        return this.page != null && this.limit != null && this.total != null;
    }

    public enum EnumPageStatus {
        NOT_PAGINATED, NOT_LAST_PAGE, LAST_PAGE
    }

    private int pageIndexStartingIn1(PaginationContext pagContext) {
        return this.page + (1 - pagContext.getFirstPageIndex());
    }

    private int currentPageUpperLimit(PaginationContext pagContext) {
        return this.pageIndexStartingIn1(pagContext) * this.limit;
    }

    private int remainingResults(PaginationContext pagContext) {
        int total = this.total - (this.pageIndexStartingIn1(pagContext) - 1) * this.limit;
        if (total < 0)
            return 0;
        return this.total - (this.pageIndexStartingIn1(pagContext) - 1) * this.limit;
    }

    public EnumPageStatus isLastPage(PaginationContext pagContext) {
        if (this.isPaginated()) {
            if (this.currentPageUpperLimit(pagContext) >= this.total)
                return EnumPageStatus.LAST_PAGE;
            return EnumPageStatus.NOT_LAST_PAGE;
        }
        return EnumPageStatus.NOT_PAGINATED;
    }

    public Integer getNumberOfResultsInCurrentPage(PaginationContext pagContext) {

        switch (this.isLastPage(pagContext)) {
            case LAST_PAGE:
                return this.remainingResults(pagContext);
            case NOT_LAST_PAGE:
                return this.limit;
            case NOT_PAGINATED:
            default:
                return null;
        }
    }

    public Integer getLastPageIndex(PaginationContext pagContext) {
        Integer times = this.total / this.limit;
        if (this.total % this.limit > 0) {
            times++;
        }
        return pagContext.getFirstPageIndex() + times - 1;

    }

}
