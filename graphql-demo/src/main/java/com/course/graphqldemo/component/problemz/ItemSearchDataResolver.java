package com.course.graphqldemo.component.problemz;

import com.course.graphql.generated.DgsConstants;
import com.course.graphql.generated.types.SearchItemFilter;
import com.course.graphql.generated.types.SearchableItem;
import com.course.graphqldemo.util.GraphqlBeanMapper;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class ItemSearchDataResolver {

    /*@Autowired
    private ProblemzQueryService problemzQueryService;

    @Autowired
    private SolutionzQueryService solutionzQueryService;
*/
    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ItemSearch)
    public List<SearchableItem> searchItems(
            @InputArgument(name = "filter") SearchItemFilter filter) {
        var result = new ArrayList<SearchableItem>();
        var keyword = filter.getKeyword();

        var problemzByKeyword = problemzQueryService.problemzByKeyword(keyword)
                .stream().map(GraphqlBeanMapper::mapToGraphql).collect(Collectors.toList());
        result.addAll(problemzByKeyword);

        var solutionzByKeyword = solutionzQueryService.solutionzByKeyword(keyword)
                .stream().map(GraphqlBeanMapper::mapToGraphql).collect(Collectors.toList());
        result.addAll(solutionzByKeyword);

        if (result.isEmpty()) {
            throw new DgsEntityNotFoundException("No item with keyword " + keyword);
        }

        result.sort(Comparator.comparing(SearchableItem::getCreateDateTime).reversed());

        return result;
    }
}
