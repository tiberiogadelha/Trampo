import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { CadastroClientePage } from '../cadastro-cliente/cadastro-cliente';
import { CadastroFornecedorPage } from '../cadastro-fornecedor/cadastro-fornecedor';

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

}