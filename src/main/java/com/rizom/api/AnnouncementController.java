package com.rizom.api;

import com.rizom.model.Announcement;
import com.rizom.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping("/duyurular")
    public String showAnnouncements(Model model) {
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        model.addAttribute("announcements", announcements);
        return "announcements";
    }
//
//    @GetMapping("/announcements/{id}")
//    public String showAnnouncementDetails(@PathVariable Long id, Model model) {
//        Optional<Announcement> announcement = announcementService.getAnnouncementById(id);
//        model.addAttribute("announcement_", announcement.orElse(null));
//        List<Announcement> announcements = announcementService.getAllAnnouncements();
//        model.addAttribute("announcements", announcements);
//
//        if (announcement.isPresent()) {
//            String markdownContent = announcement.get().getContent();
//            Parser parser = Parser.builder().build();
//            HtmlRenderer renderer = HtmlRenderer.builder().build();
//            String htmlContent = renderer.render(parser.parse(markdownContent));
//
//            model.addAttribute("htmlContent", htmlContent);
//        }
//        return "announcements";
//    }

    @GetMapping("/admin/announcements/{id}")
    public String adminAnnouncementDetails(@PathVariable Long id, Model model) {
        Optional<Announcement> announcement = announcementService.getAnnouncementById(id);
        model.addAttribute("announcement", announcement.orElse(null));
        return "admin-announcement_details";
    }

    @GetMapping("/duyurular/{id}")
    public String announcementsDetails(@PathVariable Long id, Model model) {
        Optional<Announcement> announcement = announcementService.getAnnouncementById(id);
        model.addAttribute("announcement", announcement.orElse(null));
        return "announcement_details";
    }

    @GetMapping("/addAnnouncementForm")
    public String showAddAnnouncementForm(Model model) {

        model.addAttribute("anno", new Announcement());

        return "add_announcement_form";
    }

    @PostMapping("/addAnnouncement")
    public String addAnnouncement(@ModelAttribute Announcement announcement) {
        announcementService.saveAnnouncement(announcement);
        return "redirect:/duyurular";
    }

    @GetMapping("/editAnnouncementForm/{id}")
    public String showEditAnnouncementForm(@PathVariable Long id, Model model) {
        Optional<Announcement> announcement = announcementService.getAnnouncementById(id);
        model.addAttribute("announcement", announcement.orElse(null));
        return "edit_announcement_form";
    }

    @PostMapping("/editAnnouncement/{id}")
    public String editAnnouncement(@PathVariable Long id, @ModelAttribute Announcement announcement) {
        announcement.setId(id);
        announcementService.saveAnnouncement(announcement);
        return "redirect:/duyurular";
    }

    @GetMapping("/deleteAnnouncement/{id}")
    public String deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
        return "redirect:/duyurular";
    }
}
