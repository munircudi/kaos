package com.rizom.api;

import com.rizom.model.BiosEthosContact;
import com.rizom.service.BiosEthosContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bios-contacts")
public class BiosEthosContactController {
    private final BiosEthosContactService contactService;

    @Autowired
    public BiosEthosContactController(BiosEthosContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/all")
    public String getAllContacts(Model model) {
        List<BiosEthosContact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "bios-contact-list"; // Thymeleaf template adı
    }


//    @GetMapping
//    public String getContact(Model model) {
//        BiosEthosContact bios = contactService.getContact(); // Veritabanından ilgili iletişim bilgisini çekmek için servisi çağırabilirsiniz
//        model.addAttribute("bios", bios);
//        return "contact"; // Thymeleaf template adı
//    }
    @GetMapping("/{id}")
    public String getContactById(@PathVariable Long id, Model model) {
        BiosEthosContact contact = contactService.getContactById(id);
        model.addAttribute("contact", contact);
        return "bios-contact-detail"; // Thymeleaf template adı
    }

    @GetMapping("/add")
    public String showAddContactForm(Model model) {
        model.addAttribute("contact", new BiosEthosContact());
        return "bios-add-contact"; // Thymeleaf template adı
    }

    @PostMapping("/add")
    public String addContact(@ModelAttribute BiosEthosContact contact) {
        contactService.saveContact(contact);
        return "redirect:/contacts"; // Contact listesine yönlendirme
    }

    @GetMapping("/{id}/edit")
    public String showEditContactForm(@PathVariable Long id, Model model) {
        BiosEthosContact contact = contactService.getContactById(id);
        model.addAttribute("contact", contact);
        return "bios-edit-contact"; // Thymeleaf template adı
    }

    @PostMapping("/{id}/edit")
    public String editContact(@PathVariable Long id, @ModelAttribute BiosEthosContact contact) {
        contactService.saveContact(contact);
        return "redirect:/contacts"; // Contact listesine yönlendirme
    }

    @GetMapping("/{id}/delete")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/contacts"; // Contact listesine yönlendirme
    }

}
