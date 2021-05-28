package br.edu.usj.ads.usj.jogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;










@Controller
public class JogoController {

    @Autowired
    jogoRepository jogoRepository;
    
   
    @GetMapping(value="/")
    public ModelAndView getInicial() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
    
    @GetMapping(value="/jogos")
    public ModelAndView getListaTodosJog() {
        List<Jogo> lista01 = jogoRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("jogos");
        modelAndView.addObject("lista01", lista01);
        return modelAndView;
    }

    @GetMapping(value="/cadastroJogos")
    public ModelAndView getCadastroJogos() {
        Jogo jogo = new Jogo();
        ModelAndView modelAndView = new ModelAndView("cadastroJogos");
        modelAndView.addObject("jogo", jogo);
        return modelAndView;
    }
    
    @GetMapping(value="/informacoesJogos/{id}")
    public ModelAndView getInformacoesJogos(@PathVariable Long id) {
        Jogo jogo = jogoRepository.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("informacoesJogos");
        modelAndView.addObject("jogo", jogo);
        return modelAndView;
    }

    @GetMapping(value="/deletarJogo/{id}")
    public String getDeletarJogo(@PathVariable Long id) {
        jogoRepository.deleteById(id);
        return "redirect:/jogos";
    }
    
    @GetMapping(value="/editarJogo/{id}")
    public ModelAndView getEditarJogo(@PathVariable Long id) {
        Jogo jogo = jogoRepository.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("cadastroJogos");
        modelAndView.addObject("jogo", jogo);
        return modelAndView;
   }

    @PostMapping(value="/adicionarJogos")
    public String postAdicionarJogo(Jogo jogo) {
        jogoRepository.save(jogo);
        return "redirect:/jogos";
    }

}

