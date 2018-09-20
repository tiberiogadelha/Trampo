import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import { CadastroClientePage } from '../pages/cadastro-cliente/cadastro-cliente';
import { CadastroFornecedorPage } from '../pages/cadastro-fornecedor/cadastro-fornecedor';

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    CadastroClientePage,
    CadastroFornecedorPage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp, {
      statusbarPadding: true,},
      { links: [
        { component: CadastroClientePage, name:'CadastroClientePage', segment: 'cadastro-cliente'},
        { component: CadastroFornecedorPage, name:'CadastroFornecedorPage', segment: 'cadastro-fornecedor'}
      ]})
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    CadastroClientePage,
    CadastroFornecedorPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
