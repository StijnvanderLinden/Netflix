package main.java.Resource;

import main.java.Model.Profile;
import main.java.Service.AccountService;
import javax.annotation.security.PermitAll;
import javax.json.Json;
import javax.json.JsonObject;
import main.java.Model.Account;
import main.java.Model.Feature;
import main.java.Service.FeatureService;
import main.java.Service.ProfileService;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.OK;

@Path("/api/feature")
@Produces(APPLICATION_JSON)
public class FeatureResource {

    @Inject
    FeatureService featureService;

    @Operation(summary = "Returns a feature for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class)))
    @APIResponse(responseCode = "204", description = "The feature is not found for a given identifier")
    @GET
    @Path("/{id}")
    public Response getFeature(
        @Parameter(description = "Feature identifier", required = true)
        @PathParam("id") Long id) {
        Feature feature = featureService.findFeatureById(id);
        if (feature != null) {
            return Response.ok(feature).build();
        } else {
            return Response.noContent().build();
        }
    }

    @Operation(summary = "Creates a feature")
    @APIResponse(responseCode = "200", description = "The created feature", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class)))
    @PUT
    public Response createFeature(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class)))
        @Valid Feature feature) {
        feature = featureService.persistFeature(feature);
        return Response.ok(feature).build();
    }

//    @Operation(summary = "Updates an exiting  profile")
//    @APIResponse(responseCode = "200", description = "The updated profile", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class)))
//    @PUT
//    public Response updateFeature(
//        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class)))
//        @Valid Profile profile) {
//        profile = featureService.up(profile);
//        return Response.ok(profile).build();
//    }

    @Operation(summary = "Deletes an exiting profile")
    @APIResponse(responseCode = "204")
    @DELETE
    @Path("/{id}")
    public Response deleteFeature(
        @Parameter(description = "Feature identifier", required = true)
        @PathParam("id") Long id) {
        featureService.deleteFeature(id);
        return Response.noContent().build();
    }
}
