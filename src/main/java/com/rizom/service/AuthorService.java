package com.rizom.service;

import com.rizom.model.Author;
import com.rizom.model.AuthorGroup;
import com.rizom.model.News;
import com.rizom.repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }


    public void saveOrUpdateNews(Author author, MultipartFile imageFile) throws IOException {

        // Fotoğrafı kaydetme işlemi
        if (!imageFile.isEmpty()) {
            byte[] imageBytes = imageFile.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            author.setAuthorImage(base64Image);
        }
        authorRepository.save(author);
    }




    public List<AuthorGroup> getAuthorsGroupedByFirstLetter() {
        List<Author> allAuthors = getAllAuthors(); // Tüm yazarları alın, bu metodu AuthorService'de tanımlamışsınız.
        Map<String, List<Author>> authorMap = new HashMap<>();

        // Yazarları gruplayın
        for (Author author : allAuthors) {
            String firstLetter = author.getName().substring(0, 1).toUpperCase();
            if (!authorMap.containsKey(firstLetter)) {
                authorMap.put(firstLetter, new ArrayList<>());
            }
            authorMap.get(firstLetter).add(author);
        }

        // AuthorGroup nesnelerini oluşturun
        List<AuthorGroup> authorGroups = new ArrayList<>();
        for (Map.Entry<String, List<Author>> entry : authorMap.entrySet()) {
            AuthorGroup authorGroup = new AuthorGroup(entry.getKey(), entry.getValue());
            authorGroups.add(authorGroup);
        }

        return authorGroups;
    }

}
