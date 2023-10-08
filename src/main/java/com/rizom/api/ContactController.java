package com.rizom.api;

import com.rizom.model.BiosEthosContact;
import com.rizom.model.Contact;
import com.rizom.model.News;
import com.rizom.model.Notification;
import com.rizom.model.Tweet;
import com.rizom.repo.NotificationRepository;
import com.rizom.service.BiosEthosContactService;
import com.rizom.service.ContactService;
import com.rizom.service.NewsService;
import com.rizom.service.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;

import java.util.List;

@AllArgsConstructor
@Controller
public class ContactController {


    private final ContactService contactService;
    private final NotificationRepository notificationRepository;
    private final BiosEthosContactService biosEthosContactService;
    private final NewsService newsService;
    private final TweetService tweetService;

    public void populateNewsAndLatestTweet(Model model) {
        List<News> newsList = newsService.getAllNews();
        model.addAttribute("newsList", newsList);

        List<Tweet> allTweets = tweetService.getAllTweets();
        if (!allTweets.isEmpty()) {
            // Sondaki tweet'i alarak son tweet'i sayfada gösterelim
            Tweet latestTweet = allTweets.get(allTweets.size() - 2);
            model.addAttribute("latestTweet", latestTweet.getTweetHtml());
        } else {
            model.addAttribute("latestTweet", "frefer");
        }
    }

    //-----------------------------------
    @GetMapping("/contact")
    public String showContactForm(Model model) {

        populateNewsAndLatestTweet(model);

        BiosEthosContact bios = biosEthosContactService.getContact(); // Veritabanından ilgili iletişim bilgisini çekmek için servisi çağırabilirsiniz
        model.addAttribute("bios", bios);

        List<Contact> contactList = contactService.getAllContacts();
        model.addAttribute("contactList", contactList);
        model.addAttribute("contactUs", new Contact());
        return "contact";
    }

    @GetMapping("/admin/messages")
    public String viewMessages(Model model) {

        List<Notification> notifications = notificationRepository.findAll();
        model.addAttribute("notifications", notifications);
        List<Contact> messages = contactService.getAllContacts();
        model.addAttribute("messages", messages);

        return "admin/view-messages";
    }

    @PostMapping("/contact")
    public String submitContactForm(Contact contactUs) {
        contactService.saveContactUs(contactUs);
        return "redirect:/contact?success";
    }
    //---------------------------------

    @GetMapping("/contacts/{id}")
    public String getContactById(@PathVariable Long id, Model model) {
        Contact contact = contactService.getContactById(id);
        model.addAttribute("contact", contact);
        return "contact-details";
    }

    @GetMapping("/contacts/create")
    public String createContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "admin/admin-add-contact";
    }

    @PostMapping("/contacts/create")
    public String createContact(@ModelAttribute Contact contact) {
        contactService.saveContact(contact);
        return "redirect:/contact";
    }

    @GetMapping("/contacts/edit/{id}")
    public String editContactForm(@PathVariable Long id, Model model) {
        Contact contact = contactService.getContactById(id);
        model.addAttribute("contact", contact);
        return "contact-edit";
    }

    @PostMapping("/contacts/edit/{id}")
    public String editContact(@PathVariable Long id, @ModelAttribute Contact contact) {
        contact.setId(id);
        contactService.updateContact(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/contacts/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/admin/messages";
    }

}
