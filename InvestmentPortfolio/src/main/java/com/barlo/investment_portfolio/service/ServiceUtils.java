package com.barlo.investment_portfolio.service;

import com.barlo.investment_portfolio.model.AbstractBaseEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
public class ServiceUtils {

    private static final String MESSAGE = "Not found.";
    protected static <T extends AbstractBaseEntity> List<T> checkListIsPresent(List<T> entities, StringBuilder logMessage) {
        if (entities.isEmpty()) {
            logMessage.append(MESSAGE);
            log.info(logMessage.toString());
            throw new NoSuchElementException(MESSAGE);
        }
        logMessage.append(entities);
        log.info(logMessage.toString());
        return entities;
    }

    protected static <T extends AbstractBaseEntity> T checkIsPresent(Optional<T> entity, StringBuilder logMessage) {
        if (entity.isEmpty()) {
            logMessage.append(MESSAGE);
            log.info(logMessage.toString());
            throw new NoSuchElementException(MESSAGE);
        }
        logMessage.append(entity);
        log.info(logMessage.toString());
        return entity.orElseThrow();
    }
}
