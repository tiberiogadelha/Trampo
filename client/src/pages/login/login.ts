import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { CadastroClientePage } from '../cadastro-cliente/cadastro-cliente';
import { CadastroFornecedorPage } from '../cadastro-fornecedor/cadastro-fornecedor';
import { RedefinicaoSenhaPage } from '../redefinicao-senha/redefinicao-senha';

@Component({
  selector: 'login',
  templateUrl: 'login.html'
})
export class LoginPage {

  constructor(public navCtrl: NavController) {
  	
  }

  cadastroCliente(){
    this.navCtrl.push(CadastroClientePage);
  }

  cadastroFornecedor(){
    this.navCtrl.push(CadastroFornecedorPage);
  }

  redefinicaoSenha(){
    this.navCtrl.push(RedefinicaoSenhaPage);
  }
}