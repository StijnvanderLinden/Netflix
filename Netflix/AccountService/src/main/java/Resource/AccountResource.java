package main.java.Resource;

import main.java.Service.AccountService;
import javax.annotation.security.PermitAll;
import javax.json.Json;
import javax.json.JsonObject;
import main.java.Model.Account;
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

@Path("/api/accounts")
@Produces(APPLICATION_JSON)
public class AccountResource {

    private static final Logger LOGGER = Logger.getLogger(AccountResource.class);

    @Inject
    AccountService service;

    @Operation(summary = "Returns a random account")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class, required = true)))
    @Counted(name = "countGetRandomAccount", description = "Counts how many times the getRandomAccount method has been invoked")
    @Timed(name = "timeGetRandomAccount", description = "Times how long it takes to invoke the getRandomAccount method", unit = MetricUnits.MILLISECONDS)
    @GET
    @Path("/random")
    public Response getRandomAccount() {
        Account account = service.findRandomAccount();
        LOGGER.debug("Found random account " + account);
        return Response.ok(account).build();
    }

    @Operation(summary = "Returns all the accounts from the database")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "No accounts")
    @GET
    public Response getAllAccounts() {
        List<Account> accounts = service.findAllAccounts();
        LOGGER.debug("Total number of accounts " + accounts);
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
        Account account = service.findAccountById(id);
        if (account != null) {
            LOGGER.debug("Found account " + account);
            return Response.ok(account).build();
        } else {
            LOGGER.debug("No account found with id " + id);
            return Response.noContent().build();
        }
    }
    @Operation(summary = "Updates an exiting  account")
    @APIResponse(responseCode = "200", description = "The updated account", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class)))
    @PUT
    public Response updateAccount(
        @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Account.class)))
        @Valid Account account) {
        account = service.updateAccount(account);
        LOGGER.debug("Account updated with new valued " + account);
        return Response.ok(account).build();
    }

    @Operation(summary = "Deletes an exiting account")
    @APIResponse(responseCode = "204")
    @DELETE
    @Path("/{id}")
    public Response deleteAccount(
        @Parameter(description = "Account identifier", required = true)
        @PathParam("id") Long id) {
        service.deleteAccount(id);
        LOGGER.debug("Account deleted with " + id);
        return Response.noContent().build();
    }
}
