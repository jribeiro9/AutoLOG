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
import com.example.model.Veiculo;
import com.example.service.ChamadoService;
import com.example.service.MecanicoService;
import com.example.service.VeiculoService;

@Controller
@RequestMapping("/chamados")
public class ChamadoController {
	
	@Autowired
	private ChamadoService chamadoService;
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private MecanicoService mecanicoService;
	
	@GetMapping
	public String index(Model model) {
		List<Chamado> all = chamadoService.findAll();
		model.addAttribute("listChamado", all);
		model.addAttribute("");
		return "chamado/index";
	}
	
	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		if (id != null) {
			Chamado chamado = chamadoService.findOne(id).get();
			model.addAttribute("chamado", chamado);
		}
		return "chamado/show";
	}

	@GetMapping(value = "/new")
	public String create(Model model, @ModelAttribute Chamado entityChamado, 
			             @ModelAttribute Veiculo entityVeiculo, @ModelAttribute Mecanico entityMecanico) {
		model.addAttribute("chamado", entityChamado);
		List<Veiculo> all = veiculoService.findAll();
		List<Mecanico> allm = mecanicoService.findAll();
		model.addAttribute("veiculos", all);
		model.addAttribute("mecanicos", allm);
		
		return "chamado/form";
	}
	 
	@PostMapping
	public String create(@Valid @ModelAttribute Chamado entityChamado, 
			             @Valid @ModelAttribute Veiculo entityVeiculo,
			             BindingResult result, RedirectAttributes redirectAttributes) {
		Chamado chamado = null;
		String pagina_retorno = "redirect:/chamados/" ;
	
		try {
			chamado = chamadoService.save(entityChamado);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_INSERT);
			pagina_retorno = pagina_retorno + chamado.getId();
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
		}catch (Throwable e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
		}
		
		return pagina_retorno;
	}
	
	@GetMapping("/{id}/edit")
	public String update(Model model, @PathVariable("id") Integer id) {
		
		try {
			if (id != null) {
				List<Veiculo> all = veiculoService.findAll();
				model.addAttribute("veiculos", all);
				
				Chamado entity = chamadoService.findOne(id).get();
				model.addAttribute("chamado", entity);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "chamado/form";
	}
	
	@PutMapping
	public String update(@Valid @ModelAttribute Chamado entity, BindingResult result, 
			             RedirectAttributes redirectAttributes) {
		Chamado chamado = null;
		try {
			chamado = chamadoService.save(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/chamados/" + chamado.getId();
	}
	
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				Chamado entity = chamadoService.findOne(id).get();
				chamadoService.delete(entity);
				redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/chamados/";
	}
	
	private static final String MSG_SUCESS_INSERT = "Chamado adicionado com sucesso.";
	private static final String MSG_SUCESS_UPDATE = "Chamado atualizado com sucesso.";
	private static final String MSG_SUCESS_DELETE = "Chamado removido com sucesso.";
	private static final String MSG_ERROR = "Erro na criacao do chamado.";


}
