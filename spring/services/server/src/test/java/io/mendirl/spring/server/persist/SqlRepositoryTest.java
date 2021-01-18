package io.mendirl.spring.server.persist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.r2dbc.core.DatabaseClient;

import static org.junit.jupiter.api.Assertions.*;

@DataR2dbcTest
class SqlRepositoryTest {

    @Autowired
    DatabaseClient client;

    @Autowired
    SqlRepository repository;

    @Test
    public void testDatabaseClientExisted() {
        assertNotNull(client);
    }

    @Test
    public void testPostRepositoryExisted() {
        assertNotNull(repository);
    }

    @Test
    public void testInsertAndQuery() {
        this.client.insert()
            .into("posts")
            //.nullValue("id", Integer.class)
            .value("title", "testtitle")
            .value("content", "testcontent")
            .then().block(Duration.ofSeconds(5));


        this.posts.findByTitleContains("testtitle")
            .take(1)
            .as(StepVerifier::create)
            .consumeNextWith(p -> assertEquals("testtitle", p.getTitle()))
            .verifyComplete();
    }
}
