package fr.afcepf.al32.group2.controller;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacebookFetchFeeds {

    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    public FacebookFetchFeeds(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping("/")
    public String getFacebookFeed(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

//        facebook.userOperations().getUserProfile().getFirstName();
//        facebook.userOperations().getUserProfile().getLastName();
//        facebook.userOperations().getUserProfile().getEmail();
//        facebook.userOperations().getUserProfileImage();
        PagedList<Post> feed = facebook.feedOperations().getFeed();
        model.addAttribute("feed", feed);
        return "hello";
    }

}
