package com.rizom.service;

import com.rizom.model.BiosEthosContact;
import com.rizom.repo.BiosEthosContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiosEthosContactService {
    private final BiosEthosContactRepository contactRepository;

    @Autowired
    public BiosEthosContactService(BiosEthosContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<BiosEthosContact> getAllContacts() {
        return contactRepository.findAll();
    }

    public BiosEthosContact getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    public void saveContact(BiosEthosContact contact) {
        contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    public BiosEthosContact getContact() {
        return contactRepository.findById(1L).orElse(null);
    }

    // Diğer servis metodları burada tanımlanabilir
}
