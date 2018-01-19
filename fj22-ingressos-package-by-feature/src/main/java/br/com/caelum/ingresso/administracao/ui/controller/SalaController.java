package br.com.caelum.ingresso.administracao.ui.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.core.domain.Sala;
import br.com.caelum.ingresso.core.domain.SalaRepository;
import br.com.caelum.ingresso.core.domain.SessaoRepository;

/**
 * Created by nando on 03/03/17.
 */
@Controller
public class SalaController {

    @Autowired
    private SalaRepository salas;
    
    @Autowired
    private SessaoRepository sessoes;


    @GetMapping({"/admin/sala", "/admin/sala/{id}"})
    public ModelAndView form(@PathVariable("id")Optional<Integer> id, Sala sala){
        ModelAndView modelAndView = new ModelAndView("sala/sala");

        if (id.isPresent()){
            sala = salas.findOne(id.get());
        }

        modelAndView.addObject("sala", sala);
        

        return modelAndView;
    }




    @PostMapping("/admin/sala")
    @Transactional
    public ModelAndView salva(@Valid Sala sala, BindingResult result){

        if (result.hasErrors()){
            return form(Optional.ofNullable(sala.getId()) ,sala);
        }

        salas.save(sala);
        return new ModelAndView("redirect:/admin/salas");
    }

    @GetMapping("/admin/salas")
    public ModelAndView lista(){
        ModelAndView modelAndView = new ModelAndView("sala/lista");

        modelAndView.addObject("salas", salas.findAll());

        return modelAndView;
    }


    @GetMapping("/admin/sala/{id}/sessoes")
    public ModelAndView listaSessoes(@PathVariable("id") Integer id) {

        Sala sala = salas.findOne(id);

        ModelAndView view = new ModelAndView("sessao/lista");
        view.addObject("sala", sala);
        view.addObject("sessoes", sessoes.buscaSessoesDaSala(sala));

        return view;
    }

    @GetMapping("/admin/sala/{id}/lugares/")
    public ModelAndView listaLugares(@PathVariable("id") Integer id) {

        ModelAndView modelAndView = new ModelAndView("lugar/lista");

        Sala sala = salas.findOne(id);
        modelAndView.addObject("sala", sala);

        return modelAndView;
    }


    @DeleteMapping("/admin/sala/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
        salas.delete(id);
    }
}
