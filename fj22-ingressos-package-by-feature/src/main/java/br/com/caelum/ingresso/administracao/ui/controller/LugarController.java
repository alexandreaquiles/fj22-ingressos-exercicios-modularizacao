package br.com.caelum.ingresso.administracao.ui.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.administracao.ui.form.LugarForm;
import br.com.caelum.ingresso.core.domain.Lugar;
import br.com.caelum.ingresso.core.domain.LugarRepository;
import br.com.caelum.ingresso.core.domain.Sala;
import br.com.caelum.ingresso.core.domain.SalaRepository;

/**
 * Created by nando on 03/03/17.
 */
@Controller
public class LugarController {


    @Autowired
    private SalaRepository salas;
    
    @Autowired
    private LugarRepository lugares;

    @GetMapping("/admin/lugar")
    public ModelAndView form(@RequestParam("salaId") Integer salaId, LugarForm lugarDto) {

        lugarDto.setSalaId(salaId);

        ModelAndView view = new ModelAndView("lugar/lugar");

        view.addObject("lugarDto", lugarDto);

        return view;
    }



    @PostMapping("/admin/lugar")
    @Transactional
    public ModelAndView salva(@Valid LugarForm lugarDto, BindingResult result) {

        if (result.hasErrors()) return form(lugarDto.getSalaId(), lugarDto);

        Integer salaId = lugarDto.getSalaId();

        Lugar lugar = lugarDto.toLugar();
        lugares.save(lugar);

        Sala sala = salas.findOne(salaId);
        sala.add(lugar);

        salas.save(sala);

        return new ModelAndView("redirect:/admin/sala/"+salaId+"/lugares/");
    }

}
