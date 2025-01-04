package com.fineias.marketplace.user.gateway.port;

import java.util.UUID;

public interface UserPort {

    UUID getAuthenticatedUserId();

}
