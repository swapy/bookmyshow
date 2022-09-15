package com.bookmy.theatres.domain.entity;


import javax.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Persistable;

/**
 * We need to check state of entity before save or update to save one sql select call. ref:
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.entity-persistence.saving-entites.strategies
 * ref: https://thorben-janssen.com/spring-data-jpa-state-detection/
 */
@MappedSuperclass
@Slf4j
public abstract class AbstractEntity extends AuditAwareBaseEntity implements Persistable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Transient
    private boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PrePersist
    @PostLoad
    void markNotNew() {
        this.isNew = false;
    }
}
