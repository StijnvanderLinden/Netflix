package Resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import Model.Feature;
import Service.FeatureService;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/api/feature")
@Produces(APPLICATION_JSON)
public class FeatureResource {

    @Inject
    FeatureService featureService;

    @Operation(summary = "Returns a feature for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Feature.class)))
    @APIResponse(responseCode = "204", description = "The feature is not found for a given identifier")
    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/get")
    public Response getFeature(Feature feature) {
        System.out.println("hoi");
        System.out.println(feature);
        Feature entity = featureService.findFeatureByProfileIdAndVideoId(feature.profile.id, feature.video.id);
        if (entity != null) {
            return Response.ok(entity).build();
        } else {
            return Response.noContent().build();
        }
    }

    @Operation(summary = "Returns the features for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Feature.class)))
    @APIResponse(responseCode = "204", description = "The features are not found for a given identifier")
    @GET
    @PermitAll
    @Path("/profile/{id}")
    public Response getFeaturesByProfileId(
        @Parameter(description = "Profile identifier", required = true)
        @PathParam("id") Long id) {
        List<Feature> features = featureService.getFeaturesByProfileId(id);
        if (features != null) {
            return Response.ok(features).build();
        } else {
            return Response.noContent().build();
        }
    }

    @Operation(summary = "Returns the features for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Feature.class)))
    @APIResponse(responseCode = "204", description = "The features are not found for a given identifier")
    @GET
    @PermitAll
    @Path("/profile/favorite/{id}")
    public Response getFavoritesByProfileId(
        @Parameter(description = "Profile identifier", required = true)
        @PathParam("id") Long id) {
        List<Feature> features = featureService.getFavoritesByProfileId(id);
        if (features != null) {
            return Response.ok(features).build();
        } else {
            return Response.noContent().build();
        }
    }

    @Operation(summary = "Creates a feature")
    @APIResponse(responseCode = "200", description = "The created feature", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Feature.class)))
    @POST
    @PermitAll
    public Response createFeature(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Feature.class)))
        @Valid Feature feature) {
        feature = featureService.persistFeature(feature);
        return Response.ok(feature).build();
    }

    @Operation(summary = "Updates an exiting feature")
    @APIResponse(responseCode = "200", description = "The updated feature", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Feature.class)))
    @PUT
    @PermitAll
    public Response updateFeature(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Feature.class)))
        @Valid Feature feature) {
        featureService.updateFeature(feature);
        return Response.ok(feature).build();
    }

    @Operation(summary = "Deletes an exiting feature")
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
