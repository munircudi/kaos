package com.rizom.service;

import com.rizom.model.Tweet;
import com.rizom.repo.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;

    @Autowired
    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public void saveTweet(Tweet tweet) {
        tweetRepository.save(tweet);
    }

    public Tweet getLatestTweet() {
        return tweetRepository.findById(tweetRepository.count()).orElse(null);
    }

    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }
}
