package com.rizom.repo;

import com.rizom.model.BiosEthosContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiosEthosContactRepository extends JpaRepository<BiosEthosContact, Long> {
    // Özel sorgular veya işlemler burada tanımlanabilir
}
