package com.rizom.service;

import com.rizom.model.Announcement;
import com.rizom.repo.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    public Optional<Announcement> getAnnouncementById(Long id) {
        return announcementRepository.findById(id);
    }



    public void saveAnnouncement(Announcement announcement) {
        announcementRepository.save(announcement);
    }

    public void deleteAnnouncement(Long id) {
        announcementRepository.deleteById(id);
    }
}
