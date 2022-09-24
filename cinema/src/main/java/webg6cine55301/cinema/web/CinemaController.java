package webg6cine55301.cinema.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import webg6cine55301.cinema.business.Cinema;
import webg6cine55301.cinema.model.Review;

@Controller
public class CinemaController {
    @Autowired
    private Cinema cinema;
    @GetMapping("private/avis")
    public String getArtsistes(Model model) {
        model.addAttribute("avis", cinema.getAvis());
        return "avis";
    }

    @GetMapping("private/ajouterAvis")
    public String addReviewPage(Model model){
        model.addAttribute("movies", cinema.getMovies());
        model.addAttribute("review", new Review());
        return "ajouterAvis";
    }

       /*  public String currentUserName(Principal principal) {
        return principal.getName();
    } */

    @PostMapping("private/avis")
    public String addAvis( @Valid @ModelAttribute Review review,  Errors errors,Model model) throws Exception{
       
        if (errors.hasErrors()) {
            model.addAttribute("review", new Review());
        }
       // An improvement to this snippet is first checking if there is an authenticated user before trying to access it:
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String currentUserName="";
       if (!(authentication instanceof AnonymousAuthenticationToken)) {
         currentUserName = authentication.getName();
         cinema.addRate(review.getRate(), review.getMovie(), currentUserName);
       }
        return "redirect:/private/avis";
    }
}
