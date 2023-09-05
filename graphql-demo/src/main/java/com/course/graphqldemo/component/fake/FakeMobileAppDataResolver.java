package com.course.graphqldemo.component.fake;

import com.course.graphql.generated.DgsConstants;
import com.course.graphql.generated.types.MobileApp;
import com.course.graphql.generated.types.MobileAppFilter;
import com.course.graphqldemo.datasource.fake.FakeMobileAppDataSource;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DgsComponent
public class FakeMobileAppDataResolver {

    @DgsData(
            parentType = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.MobileApps
    )
    public List<MobileApp> getMobileApps(@InputArgument(name = "mobileAppFilter")
                                         Optional<MobileAppFilter> filter) {
        if (filter.isEmpty()) {
            return FakeMobileAppDataSource.MOBILE_APPS_LIST;
        }

        return FakeMobileAppDataSource.MOBILE_APPS_LIST.stream().filter(
                mobileApp -> this.matchFilter(filter.get(), mobileApp)
        ).collect(Collectors.toList());
    }

    private boolean matchFilter(MobileAppFilter mobileAppFilter, MobileApp mobileApp) {
        var isAppMatch = StringUtils.containsIgnoreCase(mobileApp.getName(),
                StringUtils.defaultIfBlank(mobileAppFilter.getName(), StringUtils.EMPTY))
                && StringUtils.containsIgnoreCase(mobileApp.getVersion(),
                StringUtils.defaultIfBlank(mobileAppFilter.getVersion(), StringUtils.EMPTY))
                && mobileApp.getReleaseDate().isAfter(
                        Optional.ofNullable(mobileAppFilter.getReleasedAfter()).orElse(LocalDate.MIN))
                && mobileApp.getDownloaded() >= Optional.ofNullable(mobileAppFilter.getMinimumDownload()).orElse(0);

        if (!isAppMatch) {
            return false;
        }

        if (StringUtils.isNotBlank(mobileAppFilter.getPlatform())
                && !mobileApp.getPlatform().contains(mobileAppFilter.getPlatform().toLowerCase())) {
            return false;
        }

        if (mobileAppFilter.getAuthor() != null
                && !StringUtils.containsIgnoreCase(mobileApp.getAuthor().getName(),
                StringUtils.defaultIfBlank(mobileAppFilter.getAuthor().getName(), StringUtils.EMPTY))) {
            return false;
        }

        return true;
    }
}
