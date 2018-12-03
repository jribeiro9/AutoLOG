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
import com.example.model.Veiculo;
import com.example.service.VeiculoService;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {

	private static final String MSG_SUCESS_INSERT = "Veiculo cadastrado com sucesso.";
	private static final String MSG_SUCESS_UPDATE = "Veiculo alterado com sucesso.";
	private static final String MSG_SUCESS_DELETE = "Veiculo removido com sucesso.";
	private static final String MSG_ERROR = "Erro.";

	@Autowired
	private VeiculoService veiculoService;
	

	@GetMapping
	public String index(Model model) {
		List<Veiculo> all = veiculoService.findAll();
		model.addAttribute("listVeiculo", all);
		return "veiculo/index";
	}
	
	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		if (id != null) {
			Veiculo veiculo = veiculoService.findOne(id).get();
			model.addAttribute("veiculo", veiculo);
		}
		return "veiculo/show";
	}

	@GetMapping(value = "/new")
	public String create(Model model, @ModelAttribute Veiculo entityVeiculo, @ModelAttribute Chamado entityChamado) {
		model.addAttribute("veiculo", entityVeiculo);
		return "veiculo/form";
	}
	
	@PostMapping
	public String create(@Valid @ModelAttribute Veiculo entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Veiculo veiculo = null;
		try {
			veiculo = veiculoService.save(entity);
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
		return "redirect:/veiculos/" + veiculo.getId();
	}
	
	@GetMapping("/{id}/edit")
	public String update(Model model, @PathVariable("id") Integer id) {
		try {
			if (id != null) {
				Veiculo entity = veiculoService.findOne(id).get();
				model.addAttribute("veiculo", entity);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "veiculo/form";
	}
	
	@PutMapping
	public String update(@Valid @ModelAttribute Veiculo entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Veiculo veiculo = null;
		try {
			veiculo = veiculoService.save(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/veiculos/" + veiculo.getId();
	}
	
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				Veiculo entity = veiculoService.findOne(id).get();
				veiculoService.delete(entity);
				redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/veiculos/";
	}

}
