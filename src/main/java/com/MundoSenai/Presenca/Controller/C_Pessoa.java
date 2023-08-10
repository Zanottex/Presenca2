package com.MundoSenai.Presenca.Controller;


import com.MundoSenai.Presenca.Model.M_Pessoa;
import com.MundoSenai.Presenca.Model.M_Resposta;
import com.MundoSenai.Presenca.Service.S_Pessoa;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes("Usuario")
public class C_Pessoa {
    @GetMapping("/")
    public String holleWord() {
        return "Login/login";
    }

    @PostMapping("/")
    public String postLogin(@RequestParam("usuario") String usuario,
                            @RequestParam("senha") String senha,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        M_Pessoa pessoa = S_Pessoa.getPessoaLogin(usuario, senha);
        session.setAttribute("usuario", pessoa);
        if (session.getAttribute("usuario") == null) {
            return "Login/login";
        } else {
            redirectAttributes.addFlashAttribute("nome",pessoa.getNome());
            return "redirect:/home";
        }
    }

    @ModelAttribute("usuario")
    public M_Pessoa getUsuario(HttpSession session) {
        return (M_Pessoa) session.getAttribute("usuario");
    }

    @GetMapping("/home")
    public String getHome(@ModelAttribute("usuario") String usuario) {
        if (usuario != null) {
            return "Home/home";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/cadastro")
    public String getCadastro() {
        return "Pessoa/cadastro";
    }

    @PostMapping("/cadastro")
    public RedirectView postCadastro(@RequestParam("Nome") String nome,
                                     @RequestParam("email") String email,
                                     @RequestParam("cpf") String cpf,
                                     @RequestParam("telefone") String telefone,
                                     @RequestParam("dataNasc") String dataNasc,
                                     @RequestParam("senha") String senha,
                                     @RequestParam("confsenha") String confsenha,
                                     RedirectAttributes redirectAttributes


    ) {
        M_Resposta resposta = S_Pessoa.cadastrarPessoa(nome, cpf, email, telefone, dataNasc, senha, confsenha);
        redirectAttributes.addFlashAttribute("mensagem", resposta.getMensagem());
        if (resposta.getStatus()) {
            return new RedirectView("/", true);
        } else {
            redirectAttributes.addFlashAttribute("mensagem", resposta.getMensagem());
            redirectAttributes.addFlashAttribute("nome", nome);
            redirectAttributes.addFlashAttribute("email", email);
            redirectAttributes.addFlashAttribute("cpf", cpf);
            redirectAttributes.addFlashAttribute("telefone", telefone);
            redirectAttributes.addFlashAttribute("dataNasc", dataNasc);
            return new RedirectView("/cadastro", true);
        }
    }


}
