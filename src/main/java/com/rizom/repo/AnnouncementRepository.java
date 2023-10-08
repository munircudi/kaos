package com.rizom.repo;

import com.rizom.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    // Özel sorguları burada tanımlayabilirsiniz
}
