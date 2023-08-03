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

    public static String cadastrarPessoa(String nome, String cpf, String email, String telefone, String dataNasc, String senha, String confsenha) {
        if (!senha.equals(confsenha)) {
            return "A senha e a confirmação de senha devem ser iguais.";
        } else if (!CpfValidator.validateCPF(cpf)) {
            return "CPF inválido.";
        } else if (nome == null || nome.trim() == "") {
            return "Deve ser informado um nome.";
        } else if ((email == null || email.trim() == "") && (NumberCleaner.cleanNumber(telefone) == null || NumberCleaner.cleanNumber(telefone).trim() == "")) {
            return "E-mail e/ou telefone precisa ser informado";
        } else {
            M_Pessoa m_pessoa = new M_Pessoa();
            m_pessoa.setNome(nome);
            m_pessoa.setCpf(Long.valueOf(cpf));

            m_pessoa.setTelefone(Long.valueOf(telefone));
            m_pessoa.setEmail(email);
            m_pessoa.setDataNasc(LocalDate.parse(dataNasc));
            m_pessoa.setSenha(senha);
            r_pessoa.save(m_pessoa);
        }
        return "Cadastro efetuado com sucesso!";
    }

}
