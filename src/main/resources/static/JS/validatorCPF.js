function campoVazio(campo){
if(campo == ""){
    return true;
}
else{
    return false;
}
}
function validatorCPF(cpf) {
  // Remove caracteres não numéricos
  cpf = cpf.replace(/\D/g, '');

  // Verifica se o CPF tem 11 dígitos
  if (cpf.length !== 11) {
    return false;
  }

  // Verifica se todos os dígitos são iguais
  if (/^(\d)\1+$/.test(cpf)) {
    return false;
  }

  // Calcula o primeiro dígito verificador
  let soma = 0;
  for (let i = 0; i < 9; i++) {
    soma += parseInt(cpf.charAt(i)) * (10 - i);
  }
  let primeiroDigito = 11 - (soma % 11);
  if (primeiroDigito > 9) {
    primeiroDigito = 0;
  }

  // Verifica o primeiro dígito verificador
  if (primeiroDigito !== parseInt(cpf.charAt(9))) {
    return false;
  }

  // Calcula o segundo dígito verificador
  soma = 0;
  for (let i = 0; i < 10; i++) {
    soma += parseInt(cpf.charAt(i)) * (11 - i);
  }
  let segundoDigito = 11 - (soma % 11);
  if (segundoDigito > 9) {
    segundoDigito = 0;
  }

  // Verifica o segundo dígito verificador
  if (segundoDigito !== parseInt(cpf.charAt(10))) {
    return false;
  }

  return true;
}
