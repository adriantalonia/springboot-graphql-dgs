package com.course.graphqldemo.component.fake;

import com.course.graphql.generated.DgsConstants;
import com.course.graphql.generated.types.Cat;
import com.course.graphql.generated.types.Dog;
import com.course.graphql.generated.types.Pet;
import com.course.graphql.generated.types.PetFilter;
import com.course.graphqldemo.datasource.fake.FakePetDataSource;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import io.micrometer.common.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DgsComponent
public class FakePetDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.Pets)
    public List<Pet> getPets(@InputArgument(name = "petFilter")
                             Optional<PetFilter> filter) {

        if (filter.isEmpty()) {
            return FakePetDataSource.PET_LIST;
        }

        return FakePetDataSource.PET_LIST.stream().filter(
                pet -> this.matchFilter(filter.get(), pet)
        ).collect(Collectors.toList());
    }

    private boolean matchFilter(PetFilter petFilter, Pet pet) {
        if (StringUtils.isBlank(petFilter.getPetType())) {
            return true;
        }

        if (petFilter.getPetType().equalsIgnoreCase(Dog.class.getSimpleName())) {
            return pet instanceof Dog;
        } else if (petFilter.getPetType().equalsIgnoreCase(Cat.class.getSimpleName())) {
            return pet instanceof Cat;
        } else {
            return false;
        }
    }

}
