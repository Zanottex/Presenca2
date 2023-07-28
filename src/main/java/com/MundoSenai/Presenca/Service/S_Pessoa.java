package com.MundoSenai.Presenca.Service;

import com.MundoSenai.Presenca.Model.M_Pessoa;
import com.MundoSenai.Presenca.Repository.R_Pessoa;
import org.springframework.stereotype.Service;

@Service
public class S_Pessoa {
    private static R_Pessoa r_pessoa;

    public S_Pessoa(R_Pessoa r_pessoa) {
        this.r_pessoa = r_pessoa;
    }

    public static M_Pessoa getPessoaLogin(String usuario, String senha) {
        return r_pessoa.findbyUsuarioESenha(Long.valueOf(usuario), senha);
    }
}
