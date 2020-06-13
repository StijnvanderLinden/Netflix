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
import main.java.Model.Category;
import main.java.Service.CategoryService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/api/category")
@Produces(APPLICATION_JSON)
public class CategoryResource {

    @Inject
    CategoryService categoryService;

    @Operation(summary = "Returns a category for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Category.class)))
    @APIResponse(responseCode = "204", description = "The category is not found for a given identifier")
    @GET
    @Path("/{id}")
    public Response getCategory(
        @Parameter(description = "Category identifier", required = true)
        @PathParam("id") Long id) {
        Category category = categoryService.findCategoryById(id);
        if (category != null) {
            return Response.ok(category).build();
        } else {
            return Response.noContent().build();
        }
    }

    @Operation(summary = "Returns all the videos from the database")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Category.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "No videos")
    @GET
    @PermitAll
    public Response getCategories() {
        List<Category> category = categoryService.findAllCategories();
        return Response.ok(category).build();
    }

    @Operation(summary = "Creates a category")
    @APIResponse(responseCode = "200", description = "The created category", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Category.class)))
    @PUT
    public Response createCategory(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Category.class)))
        @Valid Category category) {
        category = categoryService.persistCategory(category);
        return Response.ok(category).build();
    }

    @Operation(summary = "Updates an exiting category")
    @APIResponse(responseCode = "200", description = "The updated category", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Category.class)))
    @PUT
    public Response updateCategory(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Category.class)))
        @Valid Category category) {
        category = categoryService.updateCategory(category);
        return Response.ok(category).build();
    }

    @Operation(summary = "Deletes an exiting category")
    @APIResponse(responseCode = "204")
    @DELETE
    @Path("/{id}")
    public Response deleteCategory(
        @Parameter(description = "Category identifier", required = true)
        @PathParam("id") Long id) {
        categoryService.deleteCategory(id);
        return Response.noContent().build();
    }
}
