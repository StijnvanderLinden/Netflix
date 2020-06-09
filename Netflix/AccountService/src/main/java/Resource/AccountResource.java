package Resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.OK;

import Model.Account;
import Service.AccountService;
import Service.ProfileService;
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
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/api/account")
@Produces(APPLICATION_JSON)
public class AccountResource {

    @Inject
    AccountService accountService;

    @Inject
    ProfileService profileService;

    @POST
    @Path("/register")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(Account account) throws Exception {
        System.out.println("username: " + account.username);
        System.out.println("id: " + account.id);
        accountService.persistAccount(account);
        return Response.status(OK).build();
    }

    @Operation(summary = "Returns all the accounts from the database")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "No accounts")
    @GET
    public Response getAllAccounts() {
        List<Account> accounts = accountService.findAllAccounts();
        return Response.ok(accounts).build();
    }

    @Operation(summary = "Returns a account for a given identifier")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class)))
    @APIResponse(responseCode = "204", description = "The account is not found for a given identifier")
    @GET
    @Path("/{id}")
    public Response getAccount(
        @Parameter(description = "Account identifier", required = true)
        @PathParam("id") Long id) {
        Account account = accountService.findAccountById(id);
        if (account != null) {
            return Response.ok(account).build();
        } else {
            return Response.noContent().build();
        }
    }

    @Operation(summary = "Creates a profile")
    @APIResponse(responseCode = "200", description = "The created profile", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class)))
    @PUT
    @PermitAll
    @Path("update")
    public Response updateAccount(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class)))
        @Valid Account account) {
        account = accountService.updateAccount(account);
        return Response.ok(account).build();
    }

    @Operation(summary = "Deletes an exiting account")
    @APIResponse(responseCode = "204")
    @DELETE
    @Path("/{id}")
    public Response deleteAccount(
        @Parameter(description = "Account identifier", required = true)
        @PathParam("id") Long id) {
        accountService.deleteAccount(id);
        return Response.noContent().build();
    }
}
