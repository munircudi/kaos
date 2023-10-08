package com.rizom.repo;

import com.rizom.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Özel sorguları burada tanımlayabilirsiniz, ancak JpaRepository genellikle yeterli olacaktır.
}
