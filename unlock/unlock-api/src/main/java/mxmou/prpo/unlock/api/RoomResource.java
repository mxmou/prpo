package mxmou.prpo.unlock.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import mxmou.prpo.unlock.api.model.SetRoomLocked200Response;
import mxmou.prpo.unlock.api.model.SetRoomLockedRequest;

public class RoomResource implements RoomApi {
    public SetRoomLocked200Response setRoomLocked(
            Integer room, @Valid @NotNull SetRoomLockedRequest request) {
        return new SetRoomLocked200Response().locked(request.getLocked());
    }
}
