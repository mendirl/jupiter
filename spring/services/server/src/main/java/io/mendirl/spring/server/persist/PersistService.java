package io.mendirl.spring.server.persist;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class PersistService {

    final SqlRepository repository;

    public void save() {
//        repository.saveAll()
    }
}
