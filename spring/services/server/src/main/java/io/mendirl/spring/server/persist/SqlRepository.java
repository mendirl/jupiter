package io.mendirl.spring.server.persist;

import io.mendirl.spring.server.domain.Energie;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@Repository
public interface SqlRepository extends ReactiveCrudRepository<Energie, Long> {
}
