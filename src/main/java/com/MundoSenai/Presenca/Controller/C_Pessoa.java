package com.MundoSenai.Presenca.Controller;


import com.MundoSenai.Presenca.Model.M_Pessoa;
import com.MundoSenai.Presenca.Service.S_Pessoa;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@SessionAttributes("Usuario")
public class C_Pessoa {
    @GetMapping("/")
    public String holleWord() {
        return "Login/login";
    }

    @PostMapping("/")
    public String postLogin(@RequestParam("usuario") String usuario, @RequestParam("senha") String senha, HttpSession session) {
        session.setAttribute("usuario", S_Pessoa.getPessoaLogin(usuario,senha));
        if (session.getAttribute("usuario")==null) {
            return "Login/login";
        } else {
            return "redirect:/home";
        }
    }
    @ModelAttribute("usuario")
    public M_Pessoa getUsuario(HttpSession session){
        return (M_Pessoa) session.getAttribute("usuario");
    }
    @GetMapping("/home")
    public String getHome(@ModelAttribute("usuario") String usuario){
        if(usuario != null) {
            return "Home/home";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/cadastro")
    public String getCadastro() {
        return "Pessoa/cadastro";
    }

    @PostMapping("/cadastro")
    public String postCadastro(@RequestParam("Nome") String nome,
                               @RequestParam("email") String email,
                               @RequestParam("cpf") String cpf,
                               @RequestParam("telefone") String telefone,
                               @RequestParam("dataNasc") String dataNasc,
                               @RequestParam("senha") String senha,
                               @RequestParam("confsenha") String confsenha,
                               Model model

    ) {
        String mensagem = S_Pessoa.cadastrarPessoa(nome, cpf, email, telefone,dataNasc, senha, confsenha);
        model.addAttribute("mensagem", mensagem);
        model.addAttribute("nome", nome);
        model.addAttribute("email", email);
        model.addAttribute("cpf", cpf);
        model.addAttribute("telefone", telefone);
        model.addAttribute("dataNasc", dataNasc);
        return "Pessoa/cadastro";
    }

}
