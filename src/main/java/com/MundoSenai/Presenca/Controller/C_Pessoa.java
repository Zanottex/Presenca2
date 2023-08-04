package com.MundoSenai.Presenca.Controller;


import com.MundoSenai.Presenca.Service.S_Pessoa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class C_Pessoa {
    @GetMapping("/")
    public String holleWord() {
        return "Login/login";
    }

    @PostMapping
    public String postLogin(@RequestParam("usuario") String usuario, @RequestParam("senha") String senha) {
        if (S_Pessoa.getPessoaLogin(usuario, senha) == null) {
            return "Login/login";
        } else {
            return "Home/home";
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
