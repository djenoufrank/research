package webg6.g55301.examenJanvier.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import webg6.g55301.examenJanvier.business.Musique;
import webg6.g55301.examenJanvier.model.Track;

@Controller
public class musiqueController {
    @Autowired
    private Musique musiq;

    @GetMapping("/artistes")
    public String getArtsistes(Model model) {
        model.addAttribute("artists", musiq.getArtists());
       
        return "artistes";
    }
    @GetMapping("/artistDetail")
    public String showArtistDetail(@RequestParam(name = "artist") String artistLogin,Model model){
            try {
                model.addAttribute("newTrack", new Track());
                model.addAttribute("tracks",musiq.get_artist_detail(artistLogin));
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("artistC", musiq.getArtistById(artistLogin));
        return "artistDetail";
    }
    @PostMapping("/artistDetail")
    public String addStream(@Valid @ModelAttribute(name = "newTrack") Track newTrack, Errors errors,@RequestParam(name = "nameOfArtist") String artisteLogin, Model model) throws Exception {
        if (errors.hasErrors()) {
            System.out.println("+++++++qddddddddddddddddddddddddddd"+errors.toString()+" id "+newTrack.getId());
            model.addAttribute("tracks",musiq.get_artist_detail(artisteLogin));
            model.addAttribute("artistC", musiq.getArtistById(artisteLogin));
            return "artistDetail";
        }
        System.out.println("+++++++sdkjcbsdcnbqlj");
        musiq.addStreamSSS(newTrack, artisteLogin);
        // musiq.addStream(artisteLogin,trackOfArtist,streaming);
        model.addAttribute("artistC", musiq.getArtistById(artisteLogin));
        try {
            model.addAttribute("tracks",musiq.get_artist_detail(artisteLogin));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "artistDetail";
    }
}
