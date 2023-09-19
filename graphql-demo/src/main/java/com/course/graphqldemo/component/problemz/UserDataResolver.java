package com.course.graphqldemo.component.problemz;

import com.course.graphql.generated.DgsConstants;
import com.course.graphql.generated.types.*;
import com.course.graphqldemo.util.GraphqlBeanMapper;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.web.bind.annotation.RequestHeader;

@DgsComponent
public class UserDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.Me)
    public User accountInfo(@RequestHeader(name = "authToken", required = true) String authToken) {
        var userz = userzQueryService.findUserzByAuthToken(authToken)
                .orElseThrow(DgsEntityNotFoundException::new);

        return GraphqlBeanMapper.mapToGraphql(userz);
    }

    //@Secured("ROLE_ADMIN")
    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.UserCreate)
    public UserResponse createUser(@InputArgument(name = "user") UserCreateInput userCreateInput) {
//        var userAuth = userzQueryService.findUserzByAuthToken(authToken)
//                .orElseThrow(ProblemzAuthenticationException::new);
//
//        if (!StringUtils.equals(userAuth.getUserRole(), "ROLE_ADMIN")) {
//            throw new ProblemzPermissionException();
//        }

        var userz = GraphqlBeanMapper.mapToEntity(userCreateInput);
        var saved = userzCommandService.createUserz(userz);
        var userResponse = UserResponse.newBuilder().user(
                GraphqlBeanMapper.mapToGraphql(saved)).build();

        return userResponse;
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.UserLogin)
    public UserResponse userLogin(@InputArgument(name = "user") UserLoginInput userLoginInput) {
        var generatedToken = userzCommandService.login(userLoginInput.getUsername(),
                userLoginInput.getPassword());
        var userAuthToken = GraphqlBeanMapper.mapToGraphql(generatedToken);
        var userInfo = accountInfo(userAuthToken.getAuthToken());
        var userResponse = UserResponse.newBuilder().authToken(userAuthToken)
                .user(userInfo).build();

        return userResponse;
    }

    //@Secured("ROLE_ADMIN")
    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.UserActivation)
    public UserActivationResponse userActivation(
            @InputArgument(name = "user") UserActivationInput userActivationInput) {
//        var userAuth = userzQueryService.findUserzByAuthToken(authToken)
//                .orElseThrow(ProblemzAuthenticationException::new);
//
//        if (!StringUtils.equals(userAuth.getUserRole(), "ROLE_ADMIN")) {
//            throw new ProblemzPermissionException();
//        }

        var updated = userzCommandService.activateUser(
                userActivationInput.getUsername(), userActivationInput.getActive()
        ).orElseThrow(DgsEntityNotFoundException::new);
        var userActivationResponse = UserActivationResponse.newBuilder()
                .isActive(updated.isActive()).build();

        return userActivationResponse;
    }
}
