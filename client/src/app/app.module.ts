import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { LoginPage } from '../pages/login/login';
import { CadastroClientePage } from '../pages/cadastro-cliente/cadastro-cliente';
import { CadastroFornecedorPage } from '../pages/cadastro-fornecedor/cadastro-fornecedor';
import { RedefinicaoSenhaPage } from '../pages/redefinicao-senha/redefinicao-senha';

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    LoginPage,
    CadastroClientePage,
    CadastroFornecedorPage,
    RedefinicaoSenhaPage,
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp, {
      statusbarPadding: true,},
      { links: [
        { component: CadastroClientePage, name:'CadastroClientePage', segment: 'cadastro-cliente'},
        { component: CadastroFornecedorPage, name:'CadastroFornecedorPage', segment: 'cadastro-fornecedor'},
        { component: LoginPage, name: 'LoginPage', segment: 'login'},
        { component: RedefinicaoSenhaPage, name: 'RedeficicaoSenhaPage', segment: 'redeficicao-senha'}
      ]})
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    LoginPage,
    CadastroClientePage,
    CadastroFornecedorPage,
    RedefinicaoSenhaPage,

  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
