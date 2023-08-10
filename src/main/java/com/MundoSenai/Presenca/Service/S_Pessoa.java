package com.MundoSenai.Presenca.Service;

import com.MundoSenai.Presenca.Model.M_Pessoa;
import com.MundoSenai.Presenca.Model.M_Resposta;
import com.MundoSenai.Presenca.Repository.R_Pessoa;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class S_Pessoa {
    private static R_Pessoa r_pessoa;

    public S_Pessoa(R_Pessoa r_pessoa) {
        this.r_pessoa = r_pessoa;
    }

    public static M_Pessoa getPessoaLogin(String usuario, String senha) {
        usuario = NumberCleaner.cleanNumber(usuario);
        if(usuario.equals("")) {
            return r_pessoa.findbyUsuarioESenha(null,senha);
        }else{
            return r_pessoa.findbyUsuarioESenha(Long.valueOf(usuario), senha);
        }
    }

    public static M_Resposta cadastrarPessoa(String nome, String cpf, String email, String telefone, String dataNasc, String senha, String confsenha) {
        boolean cadastrovalido = true;
        String mensagemRetorno = "";
        telefone = NumberCleaner.cleanNumber(telefone);
        if (telefone.equals("")){
            telefone = null;
        }
        if (!senha.equals(confsenha)) {
            mensagemRetorno += "A senha e a confirmação de senha devem ser iguais.<br/>";
            cadastrovalido = false;
        } if (!CpfValidator.validateCPF(cpf)) {
            mensagemRetorno += "CPF inválido.<br/>";
            cadastrovalido = false;
        } if (nome == null || nome.trim() == "") {
            mensagemRetorno += "Deve ser informado um nome.<br/>";
            cadastrovalido = false;
        } if ((email == null || email.trim() == "") && telefone == null) {
            mensagemRetorno += "E-mail e/ou telefone precisa ser informado.<br/>";
            cadastrovalido = false;
        } if(cadastrovalido) {
            M_Pessoa m_pessoa = new M_Pessoa();
            m_pessoa.setNome(nome);
            m_pessoa.setCpf(Long.valueOf(NumberCleaner.cleanNumber(cpf)));
            if(telefone != null){
                m_pessoa.setTelefone(Long.valueOf(telefone));
            }else{
                m_pessoa.setTelefone(null);
            }
            m_pessoa.setEmail(email);
            m_pessoa.setDataNasc(LocalDate.parse(dataNasc));
            m_pessoa.setSenha(senha);
            try {
                r_pessoa.save(m_pessoa);
                mensagemRetorno += "Cadastro realizado com sucesso!";
            }
            catch (DataIntegrityViolationException e){
                if(e.getMessage().contains("pessoa_cpf_key")){
                    mensagemRetorno +="O CPF fornecido ja existe.";

                }
                else{
                    mensagemRetorno+="Erro ao cadastrar.";
                }
                cadastrovalido = false;
            }
        }
        M_Resposta m_resposta = new M_Resposta(cadastrovalido, mensagemRetorno);
        return m_resposta;
    }

}
