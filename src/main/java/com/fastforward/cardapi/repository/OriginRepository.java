package com.fastforward.cardapi.repository;

import com.fastforward.cardapi.model.Origin;

import java.util.Optional;

public interface OriginRepository {

    public Optional<Origin> findOriginById(int id);

    public Origin save(Origin origin);
}
