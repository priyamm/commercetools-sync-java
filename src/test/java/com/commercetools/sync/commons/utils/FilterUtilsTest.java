package com.commercetools.sync.commons.utils;

import com.commercetools.sync.products.SyncFilter;
import org.junit.Test;

import static com.commercetools.sync.commons.utils.FilterUtils.executeSupplierIfPassesFilter;
import static com.commercetools.sync.products.ActionGroup.CATEGORIES;
import static com.commercetools.sync.products.ActionGroup.IMAGES;
import static com.commercetools.sync.products.ActionGroup.PRICES;
import static org.assertj.core.api.Assertions.assertThat;

public class FilterUtilsTest {

    @Test
    public void executeSupplierIfPassesFilter_WithGroupInBlackList_ShouldFilterOutOnlyThisGroup() {
        final SyncFilter syncFilter = SyncFilter.ofBlackList(IMAGES);
        final Boolean areImagesFilteredIn = executeSupplierIfPassesFilter(syncFilter, IMAGES, () -> true, () -> false);
        assertThat(areImagesFilteredIn).isFalse();

        final Boolean arePricesFilteredIn = executeSupplierIfPassesFilter(syncFilter, PRICES, () -> true, () -> false);
        assertThat(arePricesFilteredIn).isTrue();

        final Boolean areCategoriesFilteredIn =
            executeSupplierIfPassesFilter(syncFilter, CATEGORIES, () -> true, () -> false);
        assertThat(areCategoriesFilteredIn).isTrue();
    }

    @Test
    public void executeSupplierIfPassesFilter_WithGroupNotInBlackList_ShouldFilterInThisGroup() {
        final SyncFilter syncFilter = SyncFilter.ofBlackList(PRICES);
        final Boolean areImagesFilteredIn = executeSupplierIfPassesFilter(syncFilter, IMAGES, () -> true, () -> false);
        assertThat(areImagesFilteredIn).isTrue();

        final Boolean arePricesFilteredIn = executeSupplierIfPassesFilter(syncFilter, PRICES, () -> true, () -> false);
        assertThat(arePricesFilteredIn).isFalse();
    }

    @Test
    public void executeSupplierIfPassesFilter_WithGroupInWhiteList_ShouldFilterInOnlyThisGroup() {
        final SyncFilter syncFilter = SyncFilter.ofWhiteList(PRICES);

        final Boolean arePricesFilteredIn = executeSupplierIfPassesFilter(syncFilter, PRICES, () -> true, () -> false);
        assertThat(arePricesFilteredIn).isTrue();

        final Boolean areImagesFilteredIn = executeSupplierIfPassesFilter(syncFilter, IMAGES, () -> true, () -> false);
        assertThat(areImagesFilteredIn).isFalse();

        final Boolean areCategoriesFilteredIn =
            executeSupplierIfPassesFilter(syncFilter, CATEGORIES, () -> true, () -> false);
        assertThat(areCategoriesFilteredIn).isFalse();
    }

    @Test
    public void executeSupplierIfPassesFilter_WithNoFilter_ShouldNotFilterInEveryThing() {
        final Boolean arePricesFilteredIn = executeSupplierIfPassesFilter(SyncFilter.of(), PRICES,
            () -> true, () -> false);
        assertThat(arePricesFilteredIn).isTrue();

        final Boolean areImagesFilteredIn = executeSupplierIfPassesFilter(SyncFilter.of(), IMAGES,
            () -> true, () -> false);
        assertThat(areImagesFilteredIn).isTrue();

        final Boolean areCategoriesFilteredIn =
            executeSupplierIfPassesFilter(SyncFilter.of(), CATEGORIES, () -> true, () -> false);
        assertThat(areCategoriesFilteredIn).isTrue();
    }
}
