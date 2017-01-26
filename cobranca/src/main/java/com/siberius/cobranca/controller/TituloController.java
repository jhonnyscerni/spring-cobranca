package com.siberius.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.siberius.cobranca.model.StatusTitulo;
import com.siberius.cobranca.model.Titulo;
import com.siberius.cobranca.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired
	private Titulos titulos;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject(new Titulo());
		return mv;
	}

	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Titulo titulo, Errors erros) {

		ModelAndView mv = new ModelAndView("CadastroTitulo");
		if (erros.hasErrors()) {
			return mv;
		}
		
		titulos.save(titulo);
		mv.addObject("mensagem", "Título salvo com sucesso!");
		return mv;
	}

	@ModelAttribute("statusTitulo")
	public List<StatusTitulo> statusTitulos() {
		return Arrays.asList(StatusTitulo.values());
	}

}