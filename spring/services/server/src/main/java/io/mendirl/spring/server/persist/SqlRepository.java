package io.mendirl.spring.server.persist;

import io.mendirl.spring.server.domain.Energie;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlRepository extends ReactiveCrudRepository<Energie, Long> {
}
