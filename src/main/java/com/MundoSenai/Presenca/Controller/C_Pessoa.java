package com.MundoSenai.Presenca.Controller;


import com.MundoSenai.Presenca.Model.M_Pessoa;
import com.MundoSenai.Presenca.Model.M_Resposta;
import com.MundoSenai.Presenca.Service.S_Pessoa;
import jakarta.servlet.http.HttpServletRequest;
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
    @ResponseBody
    public M_Resposta postLogin(@RequestParam("usuario") String usuario,
                            @RequestParam("senha") String senha,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        M_Pessoa pessoa = S_Pessoa.getPessoaLogin(usuario, senha);
        session.setAttribute("usuario", pessoa);
        if (session.getAttribute("usuario") == null) {
            String mensagem = "VTNC 3";
            boolean status = false;
            M_Resposta logou = new M_Resposta( status,mensagem);
            return logou;
        } else {
            redirectAttributes.addFlashAttribute("nome", pessoa.getNome());
            String mensagem = "";
            boolean status = true;
            M_Resposta logou = new M_Resposta( status,mensagem);
            return logou;
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
    @ResponseBody
    public M_Resposta postCadastro(@RequestParam("nome") String nome,
                                     @RequestParam("email") String email,
                                     @RequestParam("cpf") String cpf,
                                     @RequestParam("telefone") String telefone,
                                     @RequestParam("data_Nasc") String dataNasc,
                                     @RequestParam("senha") String senha,
                                     @RequestParam("confsenha") String confsenha
    ) {
        return S_Pessoa.cadastrarPessoa(nome, cpf, email, telefone, dataNasc, senha, confsenha);
    }



}
