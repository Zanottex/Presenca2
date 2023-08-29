package com.MundoSenai.Presenca.Controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class C_erro implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        int statusCode = (int) request.getAttribute("" + "jakarta.servlet.error.status_code");
        String mensagem;
        if (statusCode == 404) {
            mensagem = "Página não encontrada";
        } else if (statusCode == 403) {
            mensagem = "Você não tem acesso<br/>a este recurso";
        } else if (statusCode == 500) {
            mensagem = "Algo deu errado<br/>no servidor";
        } else {
            mensagem = "Ocorreu um erro<br/> inesperado";
        }
        model.addAttribute("nErro", statusCode);
        model.addAttribute("mensagem", mensagem);
        return "Erros/Error_404";

    }
}
