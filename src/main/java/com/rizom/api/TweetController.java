package com.rizom.api;

import com.rizom.model.Tweet;
import com.rizom.service.TweetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TweetController {

    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

//    @GetMapping("/")
//    public String showTweet(Model model) {
//        List<Tweet> allTweets = tweetService.getAllTweets();
//        if (!allTweets.isEmpty()) {
//            // Sondaki tweet'i alarak son tweet'i sayfada gösterelim
//            Tweet latestTweet = allTweets.get(allTweets.size() - 1);
//            model.addAttribute("latestTweet", latestTweet.getTweetHtml());
//        } else {
//            model.addAttribute("latestTweet", "frefer");
//        }
//        return "index";
//    }

    @GetMapping("/addTweetForm")
    public String showAddTweetForm() {
        return "admin/add_tweet_form";
    }

    @PostMapping("/addTweet")
    public String addTweet(@RequestParam("tweetContent") String tweetContent) {
        Tweet newTweet = new Tweet();
        newTweet.setTweetHtml(tweetContent);
        tweetService.saveTweet(newTweet);
        return "redirect:/";
    }
}
