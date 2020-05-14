package eu.wiegandt.example;


import eu.wiegandt.example.model.Address;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Path("/address")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {
    private final Map<UUID, Address> exampleData;

    public AddressResource() {
        exampleData = new HashMap<>();
        UUID firstId = UUID.randomUUID();
        exampleData.put(firstId, new Address(firstId, "Fake street 2", "Fakecity", "123456"));

        UUID secondId = UUID.randomUUID();
        exampleData.put(secondId, new Address(secondId, "Fake street 3", "Fakecity", "123456"));
    }

    @GET
    @Operation(summary = "Retrieves all addresses")
    public Collection<Address> getAll() {
        return exampleData.values();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Retries a address by the given ID")
    @APIResponse(responseCode = "200", description = "The address with the given ID")
    @APIResponse(responseCode = "404", description = "No address with given ID found")
    public Address getById(@PathParam("id") UUID id) {
        if (exampleData.containsKey(id)) {
            return exampleData.get(id);
        }
        throw new NotFoundException("Can't find a address by ID +" + id);
    }

    @POST
    @Operation(summary = "Create a new address")
    @APIResponse(responseCode = "200", description = "The saved address with it's generated ID")
    public Address save(Address address) {
        if (address.getId() != null) {
            throw new NotAcceptableException("To update use PUT not POST!");
        }
        address.setId(UUID.randomUUID());
        exampleData.put(address.getId(), address);
        return address;
    }

    @PUT
    @Operation(summary = "Updates existing address")
    @APIResponse(responseCode = "200", description = "The updated address")
    public Address change(Address address) {
        exampleData.replace(address.getId(), address);
        return address;
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes the address with the given ID")
    @APIResponse(
            responseCode = "204",
            description = "The address with the given ID has been deleted")
    @APIResponse(responseCode = "404", description = "No address with given ID found")
    public void delete(@PathParam("id") UUID id) {
        exampleData.remove(id);
    }
}