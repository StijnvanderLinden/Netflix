package main.java.Resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import main.java.Model.Video;
import main.java.Service.VideoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/api/video")
@Produces(APPLICATION_JSON)
public class VideoResource {
    @Inject
    VideoService videoService;

    @Operation(summary = "Returns a video for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Video.class)))
    @APIResponse(responseCode = "204", description = "The video is not found for a given identifier")
    @GET
    @Path("/{id}")
    public Response getVideo(
        @Parameter(description = "Video identifier", required = true)
        @PathParam("id") Long id) {
        Video video = videoService.findVideoById(id);
        if (video != null) {
            return Response.ok(video).build();
        } else {
            return Response.noContent().build();
        }
    }

    @Operation(summary = "Returns all the videos from the database")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Video.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "No videos")
    @GET
    @PermitAll
    public Response getVideos() {
        List<Video> accounts = videoService.findAllVideos();
        return Response.ok(accounts).build();
    }

    @Operation(summary = "Creates a video")
    @APIResponse(responseCode = "200", description = "The created video", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Video.class)))
    @PUT
    public Response createVideo(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Video.class)))
        @Valid Video video) {
        video = videoService.persistVideo(video);
        return Response.ok(video).build();
    }

    @Operation(summary = "Updates an exiting  profile")
    @APIResponse(responseCode = "200", description = "The updated profile", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Video.class)))
    @PUT
    public Response updateVideo(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Video.class)))
        @Valid Video video) {
        video = videoService.updateVideo(video);
        return Response.ok(video).build();
    }

    @Operation(summary = "Deletes an exiting video")
    @APIResponse(responseCode = "204")
    @DELETE
    @Path("/{id}")
    public Response deleteVideo(
        @Parameter(description = "Video identifier", required = true)
        @PathParam("id") Long id) {
        videoService.deleteVideo(id);
        return Response.noContent().build();
    }
}
