package com.fineias.marketplace.user.gateway.port;

import com.fineias.marketplace.user.core.model.Cart;
import com.fineias.marketplace.user.core.model.User;

import java.util.UUID;

public interface UserPort {

    User getAuthenticatedUser();

}
