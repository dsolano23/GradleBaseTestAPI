package com.gdt.baseClient.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationContext {
    private Integer firstPageIndex = null;
    private Integer currentPageSize = null;
    private Integer lastPageIndex = null;
}
