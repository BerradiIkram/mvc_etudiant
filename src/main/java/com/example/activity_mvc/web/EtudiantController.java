package com.example.activity_mvc.web;

import com.example.activity_mvc.entities.Etudiant;
import com.example.activity_mvc.repositories.EtudiantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class EtudiantController {
    private EtudiantRepository EtudiantRepository;
    @GetMapping(path = "/index")
    public String etudiants(Model model, @RequestParam(name="page",defaultValue = "0") int page, @RequestParam(name="size",defaultValue = "5") int size, @RequestParam(name="keyword",defaultValue = "") String keyword){
        Page<Etudiant> pageEtudiants=EtudiantRepository.findByNomContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listEtudiants",pageEtudiants.getContent());
        model.addAttribute("pages",new int[pageEtudiants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "etudiant";
    }
    @GetMapping  ("/delete")
    public String delete(Long id,String keyword,int page) {
        EtudiantRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping ("/")
    public String home() {

        return "redirect:/index";
    }
    @GetMapping ("/etudiants")
    @ResponseBody
    public List<Etudiant> listEtudiant (){
        return EtudiantRepository.findAll();    }
    @GetMapping("/formEtudiants")
    public String formEtudiants(Model model){
        model.addAttribute("etudiant",new Etudiant());
        return "formEtudiants";}

    @GetMapping  ("/editEtudiant")
    public String editEtudiant(Model model,Long id,String keyword,int page) {
        Etudiant etudiant=EtudiantRepository.findById(id).orElse(null);
        if(etudiant==null) throw new RuntimeException("Etudiant introuvable");
        model.addAttribute( "etudiant", etudiant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editEtudiant";
    }
    @PostMapping(path = "/save")
    public String save(Model model, @Validated Etudiant etudiant, BindingResult bindingResult, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formEtudiants";
       EtudiantRepository.save(etudiant);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
}
