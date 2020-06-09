package Resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import Model.Account;
import Model.Profile;
import Service.ProfileService;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/api/profile")
@Produces(APPLICATION_JSON)
public class ProfileResource {

    @Inject
    ProfileService profileService;

    @Operation(summary = "Returns a profile for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class)))
    @APIResponse(responseCode = "204", description = "The profile is not found for a given identifier")
    @GET
    @Path("/{id}")
    public Response getProfile(
        @Parameter(description = "Profile identifier", required = true)
        @PathParam("id") Long id) {
        Profile profile = profileService.findProfileById(id);
        if (profile != null) {
            return Response.ok(profile).build();
        } else {
            return Response.noContent().build();
        }
    }

    @Operation(summary = "Returns a profile for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class)))
    @APIResponse(responseCode = "204", description = "The profile is not found for a given identifier")
    @GET
    @Path("/account/{id}")
    public Response getProfilesByAccountId(
        @Parameter(description = "Account identifier", required = true)
        @PathParam("id") Long id) {
        List<Profile> profiles = profileService.findProfilesByAccountId(id);
        if (profiles != null) {
            return Response.ok(profiles).build();
        } else {
            return Response.noContent().build();
        }
    }


    @Operation(summary = "Creates a profile")
    @APIResponse(responseCode = "200", description = "The created profile", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class)))
    @POST
    @PermitAll
    public Response createProfile(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class)))
        @Valid Profile profile) {
        System.out.println(profile);
        profile = profileService.persistProfile(profile);
        return Response.ok(profile).build();
    }

    @Operation(summary = "Updates an exiting  profile")
    @APIResponse(responseCode = "200", description = "The updated profile", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class)))
    @PUT
    public Response updateProfile(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class)))
        @Valid Profile profile) {
        profile = profileService.updateUsername(profile);
        return Response.ok(profile).build();
    }

    @Operation(summary = "Deletes an exiting profile")
    @APIResponse(responseCode = "204")
    @DELETE
    @Path("/{id}")
    public Response deleteProfile(
        @Parameter(description = "Profile identifier", required = true)
        @PathParam("id") Long id) {
        profileService.deleteProfile(id);
        return Response.noContent().build();
    }
}
