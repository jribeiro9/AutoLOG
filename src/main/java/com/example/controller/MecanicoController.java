package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Chamado;
import com.example.model.Mecanico;
import com.example.service.MecanicoService;

@Controller
@RequestMapping("/mecanicos")
public class MecanicoController {

	private static final String MSG_SUCESS_INSERT = "Mecanico cadastrado com sucesso.";
	private static final String MSG_SUCESS_UPDATE = "Mecanico alterado com sucesso.";
	private static final String MSG_SUCESS_DELETE = "Mecanico removido com sucesso.";
	private static final String MSG_ERROR = "Erro.";

	@Autowired
	private MecanicoService mecanicoService;
	

	@GetMapping
	public String index(Model model) {
		List<Mecanico> all = mecanicoService.findAll();
		model.addAttribute("listMecanico", all);
		return "mecanico/index";
	}
	
	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		if (id != null) {
			Mecanico mecanico = mecanicoService.findOne(id).get();
			model.addAttribute("mecanico", mecanico);
		}
		return "mecanico/show";
	}

	@GetMapping(value = "/new")
	public String create(Model model, @ModelAttribute Mecanico entityMecanico, @ModelAttribute Chamado entityChamado) {
		model.addAttribute("mecanico", entityMecanico);
		return "mecanico/form";
	}
	
	@PostMapping
	public String create(@Valid @ModelAttribute Mecanico entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Mecanico mecanico = null;
		try {
			mecanico = mecanicoService.save(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_INSERT);
		} catch (Exception e) {
			System.out.println("Exception:: exception");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
		}catch (Throwable e) {
			System.out.println("Throwable:: exception");
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
		}
		return "redirect:/mecanicos/" + mecanico.getId();
	}
	
	@GetMapping("/{id}/edit")
	public String update(Model model, @PathVariable("id") Integer id) {
		try {
			if (id != null) {
				Mecanico entity = mecanicoService.findOne(id).get();
				model.addAttribute("mecanico", entity);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "mecanico/form";
	}
	
	@PutMapping
	public String update(@Valid @ModelAttribute Mecanico entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Mecanico mecanico = null;
		try {
			mecanico = mecanicoService.save(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/mecanicos/" + mecanico.getId();
	}
	
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				Mecanico entity = mecanicoService.findOne(id).get();
				mecanicoService.delete(entity);
				redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/mecanicos/";
	}

}