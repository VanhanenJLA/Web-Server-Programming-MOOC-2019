/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoreservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
POST /games/{name}/scores luo uuden tuloksen pelille name ja asettaa tulokseen pelin tiedot. Tuloksen tiedot lähetetään kyselyn rungossa.
GET /games/{name}/scores listaa pelin name tulokset.
GET /games/{name}/scores/{id} palauttaa tunnuksella id löytyvän tuloksen name-nimiselle pelille.
DELETE /games/{name}/scores/{id} poistaa avaimen id mukaisen tuloksen peliltä name (pelin tietoja ei tule pyynnön rungossa). Palauttaa poistetun tuloksen tiedot.
 */

@RestController
public class ScoreController {

    @Autowired
    private ScoreRepository srepo;
    
    @Autowired
    private GameRepository grepo;

    @PostMapping("/games/{name}/scores")
    public Score postScore(@RequestBody Score score, @PathVariable String name) {
        score.setGame(grepo.findByName(name));
        srepo.save(score);
        return score;
    }

    @GetMapping("/games/{name}/scores")
    public List<Score> getGamesScores(@PathVariable String name) {
        return srepo.findByGame(grepo.findByName(name));
    }

    @GetMapping("/games/{name}/scores/{id}")
    public Score getScoreByGameNameAndScoreId(@PathVariable String name, @PathVariable Long id) {
        return srepo.findByGameAndId(grepo.findByName(name), id);
    }

    @DeleteMapping("/games/{name}/scores/{id}")
    public Score deleteScore(@PathVariable String name, @PathVariable Long id) {
        Score score = srepo.findByGameAndId(grepo.findByName(name), id);
        srepo.delete(score);
        return score;
    }
}
