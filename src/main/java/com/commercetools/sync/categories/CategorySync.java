package com.commercetools.sync.categories;

import com.commercetools.sync.commons.helpers.SyncResult;
import io.sphere.sdk.categories.Category;
import io.sphere.sdk.categories.CategoryDraft;

import javax.annotation.Nonnull;
import java.util.List;

public interface CategorySync {
    void syncCategoryDrafts(@Nonnull final List<CategoryDraft> categories);
    void syncCategories(@Nonnull final List<Category> categories);
    String getSummary(@Nonnull final SyncResult<Category> syncResult);
}
