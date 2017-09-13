package org.agoncal.talk.msexperience.demo03.bookapi.client.isbn.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.agoncal.talk.msexperience.demo03.bookapi.client.isbn.model.ProfileInfoVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2017-09-13T08:37:49.439+02:00")

@Api(value = "api", description = "the api API")
public interface ApiApi {

    @ApiOperation(value = "generateIsbnNumber", notes = "", response = String.class, tags={ "isbn-resource", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = String.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
        @ApiResponse(code = 403, message = "Forbidden", response = String.class),
        @ApiResponse(code = 404, message = "Not Found", response = String.class) })
    @RequestMapping(value = "/api/isbn",
        produces = "*/*",
        consumes = "application/json",
        method = RequestMethod.GET)
    ResponseEntity<String> generateIsbnNumberUsingGET();


    @ApiOperation(value = "getActiveProfiles", notes = "", response = ProfileInfoVM.class, tags={ "profile-info-resource", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = ProfileInfoVM.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = ProfileInfoVM.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ProfileInfoVM.class),
        @ApiResponse(code = 404, message = "Not Found", response = ProfileInfoVM.class) })
    @RequestMapping(value = "/api/profile-info",
        produces = "*/*",
        consumes = "application/json",
        method = RequestMethod.GET)
    ResponseEntity<ProfileInfoVM> getActiveProfilesUsingGET();

}
