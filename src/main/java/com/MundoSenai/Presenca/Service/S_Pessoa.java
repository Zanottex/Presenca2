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
        boolean cadastrovalido = true;
        String mensagemRetorno = "";
        if (!senha.equals(confsenha)) {
            mensagemRetorno += "A senha e a confirmação de senha devem ser iguais.<br/>";
            cadastrovalido = false;
        } if (!CpfValidator.validateCPF(cpf)) {
            mensagemRetorno += "CPF inválido.<br/>";
            cadastrovalido = false;
        } if (nome == null || nome.trim() == "") {
            mensagemRetorno += "Deve ser informado um nome.<br/>";
            cadastrovalido = false;
        } if ((email == null || email.trim() == "") && (NumberCleaner.cleanNumber(telefone) == null || NumberCleaner.cleanNumber(telefone).trim() == "")) {
            mensagemRetorno += "E-mail e/ou telefone precisa ser informado.<br/>";
            cadastrovalido = false;
        } if(cadastrovalido) {
            M_Pessoa m_pessoa = new M_Pessoa();
            m_pessoa.setNome(nome);
            m_pessoa.setCpf(Long.valueOf(cpf));
            m_pessoa.setTelefone(Long.valueOf(NumberCleaner.cleanNumber(telefone)));
            m_pessoa.setEmail(email);
            m_pessoa.setDataNasc(LocalDate.parse(dataNasc));
            m_pessoa.setSenha(senha);
            r_pessoa.save(m_pessoa);
            mensagemRetorno += "Cadastro realizado com sucesso!";
        }
        return mensagemRetorno;
    }

}
