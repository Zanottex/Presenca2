package com.MundoSenai.Presenca.Service;

import com.MundoSenai.Presenca.Model.M_Pessoa;
import com.MundoSenai.Presenca.Repository.R_Pessoa;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class S_Pessoa {
    private static R_Pessoa r_pessoa;

    public S_Pessoa(R_Pessoa r_pessoa) {
        this.r_pessoa = r_pessoa;
    }

    public static M_Pessoa getPessoaLogin(String usuario, String senha) {
        return r_pessoa.findbyUsuarioESenha(Long.valueOf(usuario), senha);
    }
    public static void cadastrarPessoa(String nome, String cpf, String email, String telefone, String dataNasc, String senha, String confsenha){
        M_Pessoa m_pessoa = new M_Pessoa();
        m_pessoa.setNome(nome);
        m_pessoa.setCpf(Long.valueOf(cpf));
        m_pessoa.setTelefone(Long.valueOf(telefone));
        m_pessoa.setEmail(email);
        m_pessoa.setDataNasc(LocalDate.parse(dataNasc));
        m_pessoa.setSenha(senha);
        r_pessoa.save(m_pessoa);
    }
}
